package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.CustomerDTO;
import it.paolone.ecommerce.entities.Customer;
import it.paolone.ecommerce.implementations.CustomerRepositoryImpl;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepositoryImpl customerRepositoryImpl;
    private final ModelMapper modelMapper;

    public Customer getCustomerById(Long query) {
        Optional<Customer> fetchedCustomer = customerRepositoryImpl.findById(query);
        return fetchedCustomer.orElse(null);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> fetchedCustomers = customerRepositoryImpl.findAll();
        return fetchedCustomers;
    }

    public Customer saveCustomer(Customer data) {
        return customerRepositoryImpl.save(data);
    }

    public CustomerDTO convertToCustomerDTO(Customer data) {
        return modelMapper.map(data, CustomerDTO.class);
    }

    public Customer convertToCustomer(CustomerDTO data) {
        return modelMapper.map(data, Customer.class);
    }

}
