package it.paolone.ecommerce.configuration;

import it.paolone.ecommerce.dto.*;
import it.paolone.ecommerce.entities.*;
import it.paolone.ecommerce.repositories.ShippingRepository;
import it.paolone.ecommerce.repositories.TransactionRepository;
import it.paolone.ecommerce.services.FileUploadService;
import it.paolone.ecommerce.services.ShippingService;
import it.paolone.ecommerce.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(ProductDTO.class, Product.class).addMappings(mapper -> {
            mapper.map(ProductDTO::getProductName, Product::setProductName);
            mapper.map(ProductDTO::getImageFilename, Product::setImageFilename);
            mapper.map(ProductDTO::getQuantity, Product::setQuantity);
            mapper.map(ProductDTO::getBarcode, Product::setBarcode);
            mapper.skip(Product::setProductId);
        });

        modelMapper.typeMap(Product.class, ProductDTO.class).addMappings(mapper -> {
            mapper.map(Product::getBarcode, ProductDTO::setBarcode);
            mapper.map(Product::getImageFileName, ProductDTO::setImageFilename);
            mapper.map(Product::getProductName, ProductDTO::setProductName);
            mapper.map(Product::getQuantity, ProductDTO::setQuantity);
        });

        return modelMapper;
    }

    @Bean
    public ShippingService shippingService(ShippingRepository shippingRepository, ModelMapper modelMapper) {
        return new ShippingService(shippingRepository, modelMapper);
    }

    @Bean
    public TransactionService transactionService(TransactionRepository transactionRepository,
            ModelMapper modelMapper) {
        return new TransactionService(transactionRepository, modelMapper);
    }



    @Bean
    public FileUploadService fileUploadService() {
        return new FileUploadService();
    }
}
