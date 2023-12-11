package it.paolone.ecommerce.repositories;

import it.paolone.ecommerce.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
