package it.paolone.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.paolone.ecommerce.entities.User;

@Repository
public interface UserInfoRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM user_info WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM user_info WHERE email = ?1", nativeQuery = true)
    int deleteByEmail(String email);

}
