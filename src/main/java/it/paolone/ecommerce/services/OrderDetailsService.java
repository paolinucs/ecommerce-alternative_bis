package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.*;
import it.paolone.ecommerce.entities.*;
import it.paolone.ecommerce.exceptions.ProductQuantityException;
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
    private final ProductService productService;

    public OrderDetailsDTO convertToOrderDetailsDto(Order order) {
        CustomerDTO customerDtoData = customerService.convertToCustomerDTO(order.getJoinedCustomer());
        ShippingDTO shippingDtoData = shippingService.convertToShippingDTO(order.getJoinedShipping());

        OrderDetailsDTO returnData = modelMapper.map(order, OrderDetailsDTO.class);
        returnData.setCustomerData(customerDtoData);
        returnData.setShippingData(shippingDtoData);

        return returnData;

    }

    public Order convertToOrder(OrderDetailsDTO orderDetailsDTO) {
        Order orderData = orderService.convertToOrder(orderDetailsDTO.getOrderData());
        Customer customerData = customerService.convertToCustomer(orderDetailsDTO.getCustomerData());

        Shipping shippingData = shippingService.convertToShipping(orderDetailsDTO.getShippingData());

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
            returnData.setCustomerData(customerDtoData);
            returnData.setShippingData(shippingDtoData);
        }

        return orderDetailsDTOList;
    }

    public OrderDetailsDTO saveNewOrder(OrderDetailsDTO dataInput) throws ProductQuantityException {
        if (dataInput != null) {

            Order finalFormOrder = modelMapper.map(dataInput.getOrderData(), Order.class);
            Shipping finalFormShipping = modelMapper.map(dataInput.getShippingData(), Shipping.class);
            Customer finalCustomerData = modelMapper.map(dataInput.getCustomerData(), Customer.class);

            Order returningOrderRawData = orderService.saveOrder(finalFormOrder);
            Shipping returningShippingRawData = shippingService.saveShippping(finalFormShipping);

            OrderDetailsDTO returningCheckObj = new OrderDetailsDTO();
            returningCheckObj.setOrderData(modelMapper.map(returningOrderRawData, OrderDTO.class));
            returningCheckObj.setShippingData(modelMapper.map(returningShippingRawData, ShippingDTO.class));

            if (!customerService.isCustomerAlreadyRegistered(finalCustomerData)) {
                Customer returningCustomerRawData = customerService.saveCustomer(finalCustomerData);
                returningCheckObj.setCustomerData(modelMapper.map(returningCustomerRawData, CustomerDTO.class));
            }

            for (ProductDTO product : dataInput.getProductData()) {

                Product finalProductData = modelMapper.map(product, Product.class);

                int actualQuantity = productService.getProductByBarcode(finalProductData.getProductBarcode())
                        .getProductQuantity();
                int orderedQuantity = finalProductData.getProductQuantity();

                int updatedQuantity = actualQuantity - orderedQuantity;

                if (updatedQuantity < 0) {
                    throw new ProductQuantityException(
                            "Product quantity for" + finalProductData.getProductBarcode() + "is not enough!");
                } else if (updatedQuantity == 0) {
                    productService.removeProductByBarcode(finalProductData.getProductBarcode());
                } else {
                    productService.updateProductQuantity(finalProductData.getProductBarcode(), updatedQuantity);
                }

            }

            return returningCheckObj;

        } else
            throw new IllegalArgumentException("!!! saveNewOrder in OrderDetailsService cannot be null !!!");
    }

}
