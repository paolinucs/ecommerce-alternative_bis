package it.paolone.ecommerce.dto;

public class PaymentDataDTO {

    private Long id;
    private String cardNumber;
    private String cvc;
    private String expiresMonth;
    private String expiresYear;

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public String getExpiresMonth() {
        return expiresMonth;
    }

    public String getExpiresYear() {
        return expiresYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public void setExpiresMonth(String expiresMonth) {
        this.expiresMonth = expiresMonth;
    }

    public void setExpiresYear(String expiresYear) {
        this.expiresYear = expiresYear;
    }

}
