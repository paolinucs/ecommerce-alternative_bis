package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.*;
import it.paolone.ecommerce.entities.*;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailsService {

    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final ShippingService shippingService;
    private final OrderService orderService;

    public OrderDetailsDTO convertToOrderDetailsDto(Order order) {
        CustomerDTO customerDtoData = customerService.convertToCustomerDTO(order.getJoinedCustomer());
        ShippingDTO shippingDtoData = shippingService.convertToShippingDTO(order.getJoinedShipping());

        OrderDetailsDTO returnData = modelMapper.map(order, OrderDetailsDTO.class);
        returnData.setCustomerDtoData(customerDtoData);
        returnData.setShippingDtoData(shippingDtoData);

        return returnData;

    }

    public Order convertToOrder(OrderDetailsDTO orderDetailsDTO) {
        Order orderData = orderService.convertToOrder(orderDetailsDTO.getOrderDtoData());
        Customer customerData = customerService.convertToCustomer(orderDetailsDTO.getCustomerDtoData());

        Shipping shippingData = shippingService.convertToShipping(orderDetailsDTO.getShippingDtoData());

        orderData.setJoinedCustomer(customerData);
        orderData.setJoinedShipping(shippingData);

        return orderData;
    }

    public List<OrderDetailsDTO> getAllOrdersDetails() {
        List<Order> ordersData = orderService.getAllOrders();
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();

        for (Order order : ordersData) {
            CustomerDTO customerDtoData = customerService.convertToCustomerDTO(order.getJoinedCustomer());
            ShippingDTO shippingDtoData = shippingService.convertToShippingDTO(order.getJoinedShipping());

            OrderDetailsDTO returnData = modelMapper.map(order, OrderDetailsDTO.class);
            returnData.setCustomerDtoData(customerDtoData);
            returnData.setShippingDtoData(shippingDtoData);
        }

        return orderDetailsDTOList;
    }

    public OrderDetailsDTO saveNewOrder(OrderDetailsDTO dataInput) {
        if (dataInput != null) {

            Order finalFormOrder = modelMapper.map(dataInput.getOrderDtoData(), Order.class);
            Shipping finalFormShipping = modelMapper.map(dataInput.getShippingDtoData(), Shipping.class);
            Customer finalCustomerData = modelMapper.map(dataInput.getCustomerDtoData(), Customer.class);

            Order returningOrderRawData = orderService.saveOrder(finalFormOrder);
            Shipping returningShippingRawData = shippingService.saveShippping(finalFormShipping);
            Customer returningCustomerRawData = customerService.saveCustomer(finalCustomerData);

            OrderDetailsDTO returningCheckObj = new OrderDetailsDTO();
            returningCheckObj.setOrderDtoData(modelMapper.map(returningOrderRawData, OrderDTO.class));
            returningCheckObj.setCustomerDtoData(modelMapper.map(returningCustomerRawData, CustomerDTO.class));
            returningCheckObj.setShippingDtoData(modelMapper.map(returningShippingRawData, ShippingDTO.class));

            return returningCheckObj;

        } else
            throw new IllegalArgumentException("!!! saveNewOrder in OrderDetailsService cannot be null !!!");
    }

}
