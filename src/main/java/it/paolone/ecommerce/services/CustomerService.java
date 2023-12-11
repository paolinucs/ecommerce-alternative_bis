package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.CustomerDTO;
import it.paolone.ecommerce.entities.Customer;
import it.paolone.ecommerce.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Customer getCustomerById(Long query) {
        Optional<Customer> fetchedCustomer = customerRepository.findById(query);
        return fetchedCustomer.orElse(null);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> fetchedCustomers = customerRepository.findAll();
        return fetchedCustomers;
    }

    public Customer saveCustomer(Customer data) {
        return customerRepository.save(data);
    }

    public CustomerDTO convertToCustomerDTO(Customer data) {
        return modelMapper.map(data, CustomerDTO.class);
    }

    public Customer convertToCustomer(CustomerDTO data) {
        return modelMapper.map(data, Customer.class);
    }

}
