package it.paolone.ecommerce.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderDetailsDTO {

    private OrderDTO orderData;
    private CustomerDTO customerData;
    private ShippingDTO shippingData;
    private List<ProductDTO> productData;
    private TransactionDetailsDTO transactionDetailsData;
}
