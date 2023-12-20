package it.paolone.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.paolone.ecommerce.entities.User;
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
    private PasswordEncoder encoder;

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

    public User addUser(User User) {
        User.setPassword(encoder.encode(User.getPassword()));
        User newUserDetails = repository.save(User);
        newUserDetails.setPassword(null);
        return newUserDetails;
    }

    // @Transactional
    // public Boolean editUserEmail(String actualEmail, String newEmail) {
    //     int isEdited = repository.updateUserEmail(actualEmail, newEmail);
    //     return isEdited == 0;
    // }

    // @Transactional
    // public Boolean editUserPassword(String actualPassword, String newPassword) {
    //     int isEdited = repository.updateUserPassword(actualPassword, newPassword);
    //     return isEdited == 0;
    // }

}
