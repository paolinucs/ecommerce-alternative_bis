package it.paolone.ecommerce.services;

import it.paolone.ecommerce.entities.PaymentData;
import it.paolone.ecommerce.repositories.PaymentDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PaymentDataService {

    private final PaymentDataRepository paymentDataRepository;

    @Autowired
    public PaymentDataService(PaymentDataRepository paymentDataRepository) {
        this.paymentDataRepository = paymentDataRepository;
    }

    public PaymentData getPaymentDataById(Long id) {
        Optional<PaymentData> fetchedPaymentData = paymentDataRepository.findById(id);
        return fetchedPaymentData.orElse(null);
    }

    public List<PaymentData> getAllPaymentData() {
        return paymentDataRepository.findAll();

    }

    public PaymentData savePaymentData(PaymentData data) {
        return paymentDataRepository.save(data);

    }
}
