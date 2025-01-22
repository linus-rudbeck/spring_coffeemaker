package se.distansakademin.spring_coffeemaker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.distansakademin.spring_coffeemaker.services.CoffeeMachineService;
import se.distansakademin.spring_coffeemaker.util.InvalidRequestException;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeMachineController {

    @Autowired
    CoffeeMachineService service;

    @PostMapping("/order")
    public ResponseEntity<String> makeOrder(@RequestParam String coffeeType){
        try{
            String result = service.makeOrder(coffeeType);

            // Hantera om InvalidRequestException INTE kastas
            return ResponseEntity.ok(result);
        } catch (InvalidRequestException e) {

            // Hantera om InvalidRequestException kastas
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
