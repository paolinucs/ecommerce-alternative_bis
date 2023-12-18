package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.*;
import it.paolone.ecommerce.repositories.CustomerRepository;
import it.paolone.ecommerce.repositories.OrderRepository;
import it.paolone.ecommerce.entities.*;
import it.paolone.ecommerce.repositories.ShippingRepository;
import it.paolone.ecommerce.repositories.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsService {
    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final ShippingService shippingService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    private final ShippingRepository shippingRepository;

    @Autowired
    public OrderDetailsService(ShippingRepository shippingRepository,
    TransactionRepository transactionRepository,
    CustomerRepository customerRepository,
    OrderService orderService, OrderRepository orderRepository, ModelMapper
    modelMapper,
    CustomerService customerService,
    ShippingService shippingService, TransactionService transactionService) {
    this.modelMapper = modelMapper;
    this.customerService = customerService;
    this.shippingService = shippingService;
    this.orderRepository = orderRepository;
    this.orderService = orderService;
    this.customerRepository = customerRepository;
    this.shippingRepository = shippingRepository;
    }

    // public OrderDetailsDTO convertToOrderDetailsDto(Order order) {
    // CustomerDTO customerDtoData =
    // customerService.convertToCustomerDTO(order.getJoinedCustomer());
    // ShippingDTO shippingDtoData =
    // shippingService.convertToShippingDTO(order.getJoinedShipping());

    // OrderDetailsDTO returnData = modelMapper.map(order, OrderDetailsDTO.class);
    // returnData.setCustomerDtoData(customerDtoData);
    // returnData.setShippingDtoData(shippingDtoData);

    // return returnData;

    // }

    // public Order convertToOrder(OrderDetailsDTO orderDetailsDTO) {
    // Order orderData =
    // orderService.convertToOrder(orderDetailsDTO.getOrderDtoData());
    // Customer customerData =
    // customerService.convertToCustomer(orderDetailsDTO.getCustomerDtoData());

    // Shipping shippingData =
    // shippingService.convertToShipping(orderDetailsDTO.getShippingDtoData());

    // orderData.setJoinedCustomer(customerData);
    // orderData.setJoinedShipping(shippingData);

    // return orderData;
    // }

    public List<OrderDetailsDTO> getAllOrdersDetails() {
        List<Order> ordersData = orderRepository.findAll();
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

            Order returningOrderRawData = orderRepository.save(finalFormOrder);
            Shipping returningShippingRawData = shippingRepository.save(finalFormShipping);
            Customer returningCustomerRawData = customerRepository.save(finalCustomerData);

            OrderDetailsDTO returningCheckObj = new OrderDetailsDTO();
            returningCheckObj.setOrderDtoData(modelMapper.map(returningOrderRawData, OrderDTO.class));
            returningCheckObj.setCustomerDtoData(modelMapper.map(returningCustomerRawData, CustomerDTO.class));
            returningCheckObj.setShippingDtoData(modelMapper.map(returningShippingRawData, ShippingDTO.class));

            return returningCheckObj;

        } else
            throw new IllegalArgumentException("!!! saveNewOrder in OrderDetailsService cannot be null !!!");
    }

}
