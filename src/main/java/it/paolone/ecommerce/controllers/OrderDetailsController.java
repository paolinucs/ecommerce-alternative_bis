package it.paolone.ecommerce.controllers;

import it.paolone.ecommerce.services.OrderDetailsService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderDetailsController {


    public final OrderDetailsService orderDetailsService;

    



    
    

}
