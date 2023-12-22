package it.paolone.ecommerce.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.paolone.ecommerce.entities.Admin;
import it.paolone.ecommerce.exceptions.DataNotFoundException;
import it.paolone.ecommerce.repositories.AdminRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin saveAdmin(Admin data) {
        return adminRepository.save(data);
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> fetchedAdmin = adminRepository.findById(id);
        return fetchedAdmin.orElse(null);
    }

    public Boolean deleteAdmin(Admin data) throws DataNotFoundException {
        try {
            adminRepository.delete(data);
        } catch (IllegalArgumentException exc) {
            throw new DataNotFoundException();
        } catch (Exception exc) {
            throw new RuntimeException("Generic Exception: " + exc);
        }

        return true;
    }

}
