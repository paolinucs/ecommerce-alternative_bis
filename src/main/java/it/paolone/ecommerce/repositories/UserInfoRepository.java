package it.paolone.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.paolone.ecommerce.entities.User;

public interface UserInfoRepository extends JpaRepository<User,Long>{
    @Query(value = "SELECT * FROM user_info WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);    
}
