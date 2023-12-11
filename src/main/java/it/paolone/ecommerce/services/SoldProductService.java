package it.paolone.ecommerce.services;

import java.util.List;
import java.util.Optional;
import it.paolone.ecommerce.entities.SoldProducts;
import it.paolone.ecommerce.repositories.SoldProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldProductService {

        private final SoldProductsRepository soldProductsRepository;

        @Autowired
        public SoldProductService(SoldProductsRepository soldProductsRepository) {
                this.soldProductsRepository = soldProductsRepository;
        }

        public SoldProducts getSoldProductById(Long id) {
                Optional<SoldProducts> fetchedSoldProduct = soldProductsRepository.findById(id);
                return fetchedSoldProduct.orElse(null);
        }

        public List<SoldProducts> getAllSoldProducts() {
                return soldProductsRepository.findAll();
        }

        public SoldProducts saveSoldProduct(SoldProducts data) {
                return soldProductsRepository.save(data);
        }

}
