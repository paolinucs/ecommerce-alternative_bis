package it.paolone.ecommerce.controllers;

import it.paolone.ecommerce.dto.OrderDetailsDTO;
import it.paolone.ecommerce.services.OrderDetailsService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderDetailsController {


    public final OrderDetailsService orderDetailsService;

    @GetMapping("/get_all")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails(){
        List<OrderDetailsDTO> fetchedData = orderDetailsService.getAllOrdersDetails();
        if(!fetchedData.isEmpty()){
            return ResponseEntity.ok(fetchedData);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/save_new")
    public ResponseEntity<OrderDetailsDTO> saveOrderData(@RequestBody OrderDetailsDTO in) {
        if (in != null) {
            OrderDetailsDTO data = orderDetailsService.saveNewOrder(in);
            if (data != null) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    
    

}
