package it.paolone.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.paolone.ecommerce.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
