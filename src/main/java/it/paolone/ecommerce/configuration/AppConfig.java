package it.paolone.ecommerce.configuration;

import it.paolone.ecommerce.dto.*;
import it.paolone.ecommerce.entities.*;
import it.paolone.ecommerce.implementations.CustomerRepositoryImpl;
import it.paolone.ecommerce.implementations.OrderRepositoryImpl;
import it.paolone.ecommerce.implementations.PaymentDataRepositoryImpl;
import it.paolone.ecommerce.implementations.ProductRepositoryImpl;
import it.paolone.ecommerce.implementations.ShippingRepositoryImpl;
import it.paolone.ecommerce.implementations.SoldProductsRepositoryImpl;
import it.paolone.ecommerce.implementations.TransactionRepositoryImpl;
import it.paolone.ecommerce.repositories.CustomerRepository;
import it.paolone.ecommerce.repositories.OrderRepository;
import it.paolone.ecommerce.repositories.PaymentDataRepository;
import it.paolone.ecommerce.repositories.ProductRepository;
import it.paolone.ecommerce.repositories.ShippingRepository;
import it.paolone.ecommerce.repositories.SoldProductsRepository;
import it.paolone.ecommerce.repositories.TransactionRepository;
import it.paolone.ecommerce.services.CustomerService;
import it.paolone.ecommerce.services.FileUploadService;
import it.paolone.ecommerce.services.PaymentDataService;
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
    public ShippingService shippingService(ShippingRepositoryImpl shippingRepositoryImpl, ModelMapper modelMapper) {
        return new ShippingService(shippingRepositoryImpl, modelMapper);
    }

    @Bean
    public ShippingRepository shippingRepository() {
        return new ShippingRepositoryImpl();
    }

    @Bean
    public ShippingRepositoryImpl shippingRepositoryImpl() {
        return new ShippingRepositoryImpl();
    }

    @Bean
    public PaymentDataService paymentDataService(ModelMapper modelMapper,
            PaymentDataRepositoryImpl paymentDartaDataRepositoryImpl) {

        return new PaymentDataService(paymentDartaDataRepositoryImpl, modelMapper);
    }

    @Bean
    public PaymentData paymentData() {
        return new PaymentData();
    }

    @Bean
    public TransactionService transactionService(TransactionRepositoryImpl transactionRepositoryImpl,
            ModelMapper modelMapper) {
        return new TransactionService(transactionRepositoryImpl, modelMapper);
    }

    // @Bean
    // public TransactionDetailsService transactionDetailsService(TransactionService transactionService, ModelMapper modelMapper, PaymentDataService paymentDataService) {
    //     return new TransactionDetailsService(transactionService, modelMapper, paymentDataService);

    // }

    @Bean
    public FileUploadService fileUploadService() {
        return new FileUploadService();
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepositoryImpl();
    }

    @Bean
    public CustomerRepositoryImpl customerRepositoryImpl() {
        return new CustomerRepositoryImpl();
    }

    @Bean
    public CustomerService customerService(CustomerRepositoryImpl customerRepositoryImpl, ModelMapper modelMapper) {
        return new CustomerService(customerRepositoryImpl, modelMapper);
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepositoryImpl();
    }

    @Bean
    public OrderRepositoryImpl orderRepositoryImpl() {
        return new OrderRepositoryImpl();
    }

    @Bean
    public PaymentDataRepository paymentDataRepository() {
        return new PaymentDataRepositoryImpl();
    }

    @Bean
    public PaymentDataRepositoryImpl paymentDataRepositoryImpl() {
        return new PaymentDataRepositoryImpl();
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductRepositoryImpl productRepositoryImpl() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public TransactionRepository transactionRepository() {
        return new TransactionRepositoryImpl();
    }

    @Bean
    public TransactionRepositoryImpl transactionRepositoryImpl() {
        return new TransactionRepositoryImpl();
    }

    @Bean
    public SoldProductsRepository soldProductRepository(){
        return new SoldProductsRepositoryImpl();
    }

    @Bean
    public SoldProductsRepositoryImpl soldProductsRepositoryImpl(){
        return new SoldProductsRepositoryImpl();
    }
}
