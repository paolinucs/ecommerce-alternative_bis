package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.ProductDTO;
import it.paolone.ecommerce.entities.Product;
import it.paolone.ecommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Product getProductById(Long id) {
        Optional<Product> fetchedProduct = productRepository.findById(id);
        return fetchedProduct.orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product data) {
        Optional<Product> eventuallyExistingProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(data.getBarcode()));

        if (eventuallyExistingProduct.isPresent()) {
            Product existingData = eventuallyExistingProduct.get();
            int newQuantity = data.getQuantity() + existingData.getQuantity();
            productRepository.updateProductQuantityByBarcode(data.getBarcode(), newQuantity);
            return productRepository.searchProductByBarcode(data.getBarcode());
        } else {
            return productRepository.save(data);
        }
    }

    public Product rePriceProduct(String productBarcode, float newPrice) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            productRepository.updateProductPriceByBarcode(productBarcode, newPrice);
            return productRepository.searchProductByBarcode(productBarcode);
        }

        return null;

    }

    public Product removeProductByBarcode(String productBarcode) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            productRepository.deleteProductByBarcode(productBarcode);
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
