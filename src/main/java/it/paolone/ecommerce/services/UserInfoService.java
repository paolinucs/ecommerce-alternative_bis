package it.paolone.ecommerce.services;

import java.util.InputMismatchException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.paolone.ecommerce.dto.UserRegistrationDTO;
import it.paolone.ecommerce.entities.Admin;
import it.paolone.ecommerce.entities.Customer;
import it.paolone.ecommerce.entities.User;
import it.paolone.ecommerce.exceptions.DataNotFoundException;
import it.paolone.ecommerce.exceptions.UserAndAdminEmailMismatchException;
import it.paolone.ecommerce.exceptions.UserAndCustomerEmailMismatchException;
import it.paolone.ecommerce.repositories.UserInfoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByEmail(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found (" + username + ")"));

    }

    @Transactional
    public int deleteUser(String email) {

        Optional<User> fetchedUser = repository.findByEmail(email);

        if (fetchedUser.isPresent() && !fetchedUser.get().getRoles().contains("ROLE_GOD")) {

            int deletedUser = repository.deleteByEmail(email);
            if (deletedUser > 0) {
                return 0;
            }
        }
        return 1;
    }

    public UserRegistrationDTO addUser(UserRegistrationDTO userRegistrationDTO)
            throws UserAndCustomerEmailMismatchException, UserAndAdminEmailMismatchException, DataNotFoundException {

        if (userRegistrationDTO != null) {

            boolean isAdmin = userRegistrationDTO.getRoles().equals("ROLE_ADMIN")
                    || userRegistrationDTO.getRoles().equals("ROLE_GOD");

            User newUser = new User();

            newUser.setPassword(encoder.encode(userRegistrationDTO.getPassword()));
            newUser.setEmail(userRegistrationDTO.getEmail());
            newUser.setRoles(userRegistrationDTO.getRoles());

            User newUserDetails = repository.save(newUser);

            UserRegistrationDTO registrationDetails = new UserRegistrationDTO();
            registrationDetails.setEmail(newUserDetails.getEmail());
            registrationDetails.setRoles(newUserDetails.getRoles());

            if (isAdmin) {
                Admin newAdmin = new Admin();
                newAdmin.setNominative(userRegistrationDTO.getNominative());
                newAdmin.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
                newAdmin.setUser(newUser);
                newAdmin.setRoles(userRegistrationDTO.getRoles());

                Admin newAdminDetails = adminService.saveAdmin(newAdmin);

                if (!newAdminDetails.getUser().getEmail().equals(newUser.getEmail())) {
                    adminService.deleteAdmin(newAdminDetails);
                    try {
                        repository.delete(newUserDetails);
                    } catch (InputMismatchException exc) {
                        throw new DataNotFoundException();
                    } catch (Exception exc) {
                        throw new RuntimeException("Generic Exception: " + exc);
                    }
                    throw new UserAndAdminEmailMismatchException();
                }

            } else {

                Customer newCustomer = new Customer();
                newCustomer.setNominative(userRegistrationDTO.getNominative());
                newCustomer.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
                newCustomer.setUser(newUser);

                Customer newCustomerDetails = customerService.saveCustomer(newCustomer);

                if (!newUserDetails.getEmail().equals(newCustomerDetails.getUser().getEmail())) {
                    repository.delete(newUserDetails);
                    customerService.deleteCustomer(newCustomerDetails);
                    throw new UserAndCustomerEmailMismatchException();
                }

                registrationDetails.setNominative(newCustomerDetails.getNominative());
                registrationDetails.setPhoneNumber(newCustomer.getPhoneNumber());

            }
            return registrationDetails;
        } else
            throw new IllegalArgumentException();
    }
}
