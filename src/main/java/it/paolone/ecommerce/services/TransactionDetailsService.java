package it.paolone.ecommerce.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paolone.ecommerce.repositories.PaymentDataRepository;
// import it.paolone.ecommerce.repositories.PaymentTypeRepository;
import it.paolone.ecommerce.repositories.TransactionRepository;
import it.paolone.ecommerce.dto.PaymentDataDTO;
import it.paolone.ecommerce.dto.PaymentTypeDTO;
import it.paolone.ecommerce.dto.TransactionDTO;
import it.paolone.ecommerce.services.TransactionService;
import it.paolone.ecommerce.dto.TransactionDetailsDTO;
import it.paolone.ecommerce.entities.PaymentData;
import it.paolone.ecommerce.entities.Transaction;
import it.paolone.ecommerce.services.PaymentDataService;
//import it.paolone.ecommerce.services.PaymentTypeService;

@Service
public class TransactionDetailsService {

    private final TransactionRepository transactionRepository;
    private final PaymentDataRepository paymentDataRepository;
    // private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentDataService paymentDataService;
    // private final PaymentTypeService paymentTypeService;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;

    @Autowired
    public TransactionDetailsService(TransactionRepository transactionRepository,
            TransactionService transactionService, PaymentDataRepository paymentDataRepository, // PaymentTypeRepository
                                                                                                // paymentTypeRepository,
            ModelMapper modelMapper, PaymentDataService paymentDataService
    /* PaymentTypeService paymentTypeService */) {
        this.paymentDataRepository = paymentDataRepository;
        this.transactionRepository = transactionRepository;
        // this.paymentTypeRepository = paymentTypeRepository;
        this.modelMapper = modelMapper;
        // this.paymentTypeService= paymentTypeService;
        this.paymentDataService = paymentDataService;
        this.transactionService = transactionService;

    }
        public List<TransactionDetailsDTO>getAll() {
            List<Transaction> transactionData= transactionRepository.findAll();
            List<TransactionDetailsDTO> transactionDetailsDTOList = new ArrayList<>();

            for(Transaction transaction : transactionData){
                PaymentDataDTO paymentDataDataDTO= paymentDataService.convertToPaymentDataDTO(paymentDataService.getPaymentDataById(transaction.getId()));
                TransactionDetailsDTO returnData = modelMapper.map(transaction, TransactionDetailsDTO.class);
                returnData.setPaymentDataData(paymentDataDataDTO);
            }
            return transactionDetailsDTOList;
        }

        public TransactionDetailsDTO saveNewTransaction (TransactionDetailsDTO dataInput){
            if (dataInput != null) {
                Transaction finalFormTransaction = modelMapper.map(dataInput.getTransactionData(), Transaction.class);
                PaymentData finalFormPayment = modelMapper.map(dataInput.getPaymentDataData(),PaymentData.class);

                Transaction returningTransactionRawData = transactionRepository.save(finalFormTransaction);
                PaymentData returningPaymentDataRawData = paymentDataRepository.save(finalFormPayment);

                TransactionDetailsDTO returningCheckingTransaction = new TransactionDetailsDTO();
                returningCheckingTransaction.setPaymentDataData(modelMapper.map(returningPaymentDataRawData, PaymentDataDTO.class));
                returningCheckingTransaction.setTransactionData(modelMapper.map(returningCheckingTransaction, TransactionDTO.class));

                return returningCheckingTransaction;
            
            } else {
                throw new IllegalArgumentException("!!! saveNewOrder in TransactionDetailsService cannot be null !!!");
            }
        }

        public TransactionDetailsDTO retrievTransactionDetailsDTOById(Long id){

            Optional<Transaction> transaction = transactionRepository.findById(id);
            TransactionDetailsDTO finalTransaction = modelMapper.map(transaction, TransactionDetailsDTO.class);
            return finalTransaction;
        }



}
        
        
        
  

