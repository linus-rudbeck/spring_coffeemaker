package se.distansakademin.spring_coffeemaker;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.models.Ingredient;
import se.distansakademin.spring_coffeemaker.repositories.CoffeeRecipeRepository;
import se.distansakademin.spring_coffeemaker.repositories.IngredientRepository;
import se.distansakademin.spring_coffeemaker.services.CoffeeMachineService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoffeeMachineServiceTest {

    @InjectMocks
    private CoffeeMachineService coffeeMachineService;

    @Mock
    private CoffeeRecipeRepository coffeeRecipeRepository;

    @Mock
    private IngredientRepository ingredientRepository;


    @Test
    public void testPlaceOrder_Success(){
        CoffeeRecipe recipe = new CoffeeRecipe(CoffeeRecipe.ESPRESSO, Map.of(
                Ingredient.COFFEE, 1,
                Ingredient.WATER, 1
        ));

        // Return our recipe when getRecipeByName is called with "espresso"
        when(coffeeRecipeRepository.getRecipeByName(CoffeeRecipe.ESPRESSO))
                .thenReturn(recipe);

        // Return our ingredient when findByName is called with "coffee"
        when(ingredientRepository.findByName(Ingredient.COFFEE))
                .thenReturn(new Ingredient(Ingredient.COFFEE, 10));

        // Return our ingredient when findByName is called with "water"
        when(ingredientRepository.findByName(Ingredient.WATER))
                .thenReturn(new Ingredient(Ingredient.WATER, 10));


        String result = coffeeMachineService.makeOrder("espresso");

        assertEquals("Order placed for espresso", result);
        verify(coffeeRecipeRepository, times(1)).getRecipeByName("espresso");
        verify(ingredientRepository, times(2)).findByName(anyString());
        verify(ingredientRepository, times(2)).save(any(Ingredient.class));

        // 1. Skriv klart testet (testa att coffeeRecipeRepository anropas)
        // 2. Skriv klart coffeeMachineService.makeOrder
    }

    @Test
    public void testPlaceOrder_IngredientsMissing(){
        CoffeeRecipe recipe = new CoffeeRecipe(CoffeeRecipe.ESPRESSO, Map.of(
                Ingredient.COFFEE, 1,
                Ingredient.WATER, 1
        ));

        when(coffeeRecipeRepository.getRecipeByName(CoffeeRecipe.ESPRESSO))
                .thenReturn(recipe);

        when(ingredientRepository.findByName(anyString()))
                .thenReturn(null);


        assertThrows(Exception.class, () -> coffeeMachineService.makeOrder("espresso"));
    }

}
