package it.paolone.ecommerce.dto;

public class OrderDTO {

    private Long orderId;
    private Long customerId;
    private String orderDate;
    private Long shippingId;
    private Long transactionId;

    public String getOrderDate() {
        return orderDate;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
