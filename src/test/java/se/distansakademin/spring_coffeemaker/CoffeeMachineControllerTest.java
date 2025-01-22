package se.distansakademin.spring_coffeemaker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.distansakademin.spring_coffeemaker.controllers.CoffeeMachineController;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.services.CoffeeMachineService;
import se.distansakademin.spring_coffeemaker.util.InvalidRequestException;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoffeeMachineController.class)
public class CoffeeMachineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    CoffeeMachineService service;

    @Test
    public void testPlaceOrder_Success() throws Exception {
        when(service.makeOrder(CoffeeRecipe.ESPRESSO))
                .thenReturn("Order placed for espresso");

        mockMvc.perform(post("/api/coffee/order")
                        .param("coffeeType", "espresso"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order placed for espresso"));
    }
}
