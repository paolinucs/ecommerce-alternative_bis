package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.ShippingDTO;
import it.paolone.ecommerce.entities.Shipping;
import it.paolone.ecommerce.implementations.ShippingRepositoryImpl;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ShippingService {

    private final ShippingRepositoryImpl shippingRepositoryImpl;
    private final ModelMapper modelMapper;

    public Shipping getShippingById(Long id) {
        Optional<Shipping> fetchedShipping = shippingRepositoryImpl.findById(id);
        return fetchedShipping.orElse(null);
    }

    public List<Shipping> getAllPaymentData() {
        return shippingRepositoryImpl.findAll();
    }

    public Shipping saveShippping(Shipping data) {
        return shippingRepositoryImpl.save(data);
    }

    public ShippingDTO convertToShippingDTO(Shipping data) {
        return modelMapper.map(data, ShippingDTO.class);
    }

    public Shipping convertToShipping(ShippingDTO data) {
        return modelMapper.map(data, Shipping.class);
    }
}
