package it.paolone.ecommerce.dto;

public class ShippingDTO {
    private long id;
    private String shippingDate;
    private String shippingAddress;
    private String shippingCompany;
    private String trackingCode;

    // getters and setters

    public long getId() {
        return id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
}
