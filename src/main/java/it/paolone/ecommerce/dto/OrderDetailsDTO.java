package it.paolone.ecommerce.dto;

public class OrderDetailsDTO {

    private OrderDTO orderDtoData;
    private CustomerDTO customerDtoData;
    private ShippingDTO shippingDtoData;
    private ProductDTO productDtoData;
    private TransactionDetailsDTO transactionDetailsDtoData;

    public TransactionDetailsDTO getTransactionDetailsDtoData() {
        return this.transactionDetailsDtoData;
    }

    public void setTransactionDetailsDtoData(TransactionDetailsDTO data) {
        this.transactionDetailsDtoData = data;
    }

    public ProductDTO getProductDtoData() {
        return productDtoData;
    }

    public void setProductDtoData(ProductDTO productData) {
        this.productDtoData = productData;
    }

    public OrderDTO getOrderDtoData() {
        return orderDtoData;
    }

    public CustomerDTO getCustomerDtoData() {
        return customerDtoData;
    }

    public void setOrderDtoData(OrderDTO orderDtoData) {
        this.orderDtoData = orderDtoData;
    }

    public void setCustomerDtoData(CustomerDTO customerDtoData) {
        this.customerDtoData = customerDtoData;
    }

    public ShippingDTO getShippingDtoData() {
        return shippingDtoData;
    }

    public void setShippingDtoData(ShippingDTO shippingDtoData) {
        this.shippingDtoData = shippingDtoData;
    }
}
