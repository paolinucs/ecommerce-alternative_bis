package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.ProductDTO;
import it.paolone.ecommerce.entities.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import it.paolone.ecommerce.implementations.ProductRepositoryImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepositoryImpl productRepositoryImpl;

    public Product getProductById(Long id) {
        Optional<Product> fetchedProduct = productRepositoryImpl.findById(id);
        return fetchedProduct.orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepositoryImpl.findAll();
    }

    public Product saveProduct(Product data) {
        Optional<Product> eventuallyExistingProduct = Optional
                .ofNullable(productRepositoryImpl.searchProductByBarcode(data.getBarcode()));

        if (eventuallyExistingProduct.isPresent()) {
            Product existingData = eventuallyExistingProduct.get();
            int newQuantity = data.getQuantity() + existingData.getQuantity();
            productRepositoryImpl.updateProductQuantityByBarcode(data.getBarcode(), newQuantity);
            return productRepositoryImpl.searchProductByBarcode(data.getBarcode());
        } else {
            return productRepositoryImpl.save(data);
        }
    }

    public Product rePriceProduct(String productBarcode, float newPrice) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepositoryImpl.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            productRepositoryImpl.updateProductPriceByBarcode(productBarcode, newPrice);
            return productRepositoryImpl.searchProductByBarcode(productBarcode);
        }

        return null;

    }

    public Product removeProductByBarcode(String productBarcode) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepositoryImpl.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            productRepositoryImpl.deleteProductByBarcode(productBarcode);
            return fetchedProduct.get();
        }

        return null;
    }

    public ProductDTO convertToProductDto(Product data) {
        return modelMapper.map(data, ProductDTO.class);
    }

    public Product convertToProduct(ProductDTO data) {
        return modelMapper.map(data, Product.class);
    }

}
