package it.paolone.ecommerce.services;

import java.util.List;
import java.util.Optional;
import it.paolone.ecommerce.entities.SoldProducts;
import it.paolone.ecommerce.implementations.SoldProductsRepositoryImpl;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SoldProductService {

        private final SoldProductsRepositoryImpl soldProductsRepositoryImpl;

        public SoldProducts getSoldProductById(Long id) {
                Optional<SoldProducts> fetchedSoldProduct = soldProductsRepositoryImpl.findById(id);
                return fetchedSoldProduct.orElse(null);
        }

        public List<SoldProducts> getAllSoldProducts() {
                return soldProductsRepositoryImpl.findAll();
        }

        public SoldProducts saveSoldProduct(SoldProducts data) {
                return soldProductsRepositoryImpl.save(data);
        }

}
