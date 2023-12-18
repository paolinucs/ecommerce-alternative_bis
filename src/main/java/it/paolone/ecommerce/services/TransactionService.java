package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.TransactionDTO;
import it.paolone.ecommerce.entities.Transaction;
import it.paolone.ecommerce.implementations.TransactionRepositoryImpl;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TransactionService {

    private final TransactionRepositoryImpl transactionRepositoryImpl;
    private final ModelMapper modelMapper;

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> fetchedTransaction = transactionRepositoryImpl.findById(id);
        return fetchedTransaction.orElse(null);
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepositoryImpl.findAll();
    }

    public Transaction saveTransaction(Transaction data) {
        return transactionRepositoryImpl.save(data);
    }

    public TransactionDTO convertToTransactionDTO(Transaction data) {
        return modelMapper.map(data, TransactionDTO.class);
    }

    public Transaction convertToTransaction(TransactionDTO data) {
        return modelMapper.map(data, Transaction.class);
    }
}
