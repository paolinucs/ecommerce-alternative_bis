package it.paolone.ecommerce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paolone.ecommerce.services.TransactionDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import it.paolone.ecommerce.dto.TransactionDetailsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/transaction_details")
public class TransactionDetailsController {

    public final TransactionDetailsService transactionDetailsService;

    @Autowired
    public TransactionDetailsController(TransactionDetailsService transactionDetailsService) {
        this.transactionDetailsService = transactionDetailsService;

    }

    @GetMapping("/{id}")
    public TransactionDetailsDTO getTransactionData(@PathVariable Long id) {
        TransactionDetailsDTO fetchedData = new TransactionDetailsDTO();
        return transactionDetailsService.retrievTransactionDetailsDTOById(id);

    }

    @PostMapping("/save_new_transaction")
    public TransactionDetailsDTO crateTransaction (@RequestBody TransactionDetailsDTO transactionDetailsDTO) {
        
        return this.transactionDetailsService.saveNewTransaction(transactionDetailsDTO);
    }
    

}
