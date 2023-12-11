package it.paolone.ecommerce.repositories;

import it.paolone.ecommerce.entities.SoldProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldProductsRepository extends JpaRepository<SoldProducts, Long> {
}
