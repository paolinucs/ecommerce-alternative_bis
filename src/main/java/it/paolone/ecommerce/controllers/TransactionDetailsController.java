package it.paolone.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paolone.ecommerce.services.TransactionDetailsService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.paolone.ecommerce.dto.TransactionDetailsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/transaction_details")
@AllArgsConstructor
public class TransactionDetailsController {

    public final TransactionDetailsService transactionDetailsService;

    @GetMapping("/{id}")
    public TransactionDetailsDTO getTransactionData(@PathVariable Long id) {
        return transactionDetailsService.retrieveTransactionDetailsDTOById(id);

    }

    @PostMapping("/save_new_transaction")
    public TransactionDetailsDTO crateTransaction(@RequestBody TransactionDetailsDTO transactionDetailsDTO) {

        return this.transactionDetailsService.saveNewTransaction(transactionDetailsDTO);
    }

}
