package it.paolone.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paolone.ecommerce.repositories.PaymentDataRepository;
import it.paolone.ecommerce.repositories.PaymentTypeRepository;
import it.paolone.ecommerce.repositories.TransactionRepository;
import it.paolone.ecommerce.dto.PaymentDataDTO;
import it.paolone.ecommerce.dto.TransactionDTO;
import it.paolone.ecommerce.dto.TransactionDetailsDTO;
import it.paolone.ecommerce.entities.Transaction;

@Service
public class TransactionDetailsService {

    private final TransactionRepository transactionRepository;
    private final PaymentDataRepository paymentDataRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public TransactionDetailsService(TransactionRepository transactionRepository,
            PaymentDataRepository paymentDataRepository, PaymentTypeRepository paymentTypeRepository) {
        this.paymentDataRepository = paymentDataRepository;
        this.transactionRepository = transactionRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    // public TransactionDetailsDTO getTransactionDetailsByIdTransaction(Long transactionId) {
    //     Optional<Transaction> fetchedTransaction = transactionRepository.findById(transactionId);

    //     if (fetchedTransaction.isPresent()) {
    //         TransactionDTO fetchedTransactionDto =
    //     }
    // }

}
