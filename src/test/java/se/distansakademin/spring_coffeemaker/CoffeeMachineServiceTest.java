package se.distansakademin.spring_coffeemaker;


import org.junit.jupiter.api.BeforeEach;
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
import se.distansakademin.spring_coffeemaker.util.InvalidRequestException;

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


    private CoffeeRecipe espresso;

    @BeforeEach
    public void setup(){
        espresso = new CoffeeRecipe(CoffeeRecipe.ESPRESSO, Map.of(
                Ingredient.COFFEE, 1,
                Ingredient.WATER, 1
        ));
    }


    @Test
    public void testPlaceOrder_Success() throws Exception {

        // Return our recipe when getRecipeByName is called with "espresso"
        when(coffeeRecipeRepository.getRecipeByName(CoffeeRecipe.ESPRESSO))
                .thenReturn(espresso);

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

        // 1. Skriv klart testet (testa att coffeeRecipeRepository anropas) ✔
        // 2. Skriv klart coffeeMachineService.makeOrder
    }

    @Test
    public void testPlaceOrder_NotEnoughIngredients() {
        // Return our recipe when "espresso" is ordered
        when(coffeeRecipeRepository.getRecipeByName(CoffeeRecipe.ESPRESSO))
                .thenReturn(espresso);

        // Return out of stock ingredient any time findByName is called
        when(ingredientRepository.findByName(anyString()))
                .thenReturn(new Ingredient("OUT_OF_STOCK", 0)); // <-- all ingredients are out of stock

        // Check that "our" exception is thrown
        assertThrows(InvalidRequestException.class,
                () -> coffeeMachineService.makeOrder("espresso"));

        // Check that our recipe is requested ONLY ONCE
        verify(coffeeRecipeRepository, times(1)).getRecipeByName("espresso");

        // Check that ONLY ONE ingredient is requested
        verify(ingredientRepository, times(1)).findByName(anyString());

        // Check that we DONT (never) update the ingredient
        verify(ingredientRepository, never()).save(any());
    }

    @Test
    public void testPlaceOrder_LastIngredientOutOfStock(){
        // Check that we don't save ingredients if
        // only last ingredient is out of stock


        when(coffeeRecipeRepository.getRecipeByName(CoffeeRecipe.ESPRESSO))
                .thenReturn(espresso);


        when(ingredientRepository.findByName(Ingredient.COFFEE))
                .thenReturn(new Ingredient(Ingredient.COFFEE, 10));


        when(ingredientRepository.findByName(Ingredient.WATER))
                .thenReturn(new Ingredient(Ingredient.WATER, 0));


        assertThrows(InvalidRequestException.class, () -> coffeeMachineService.makeOrder("espresso"));

        verify(coffeeRecipeRepository, times(1)).getRecipeByName("espresso");

        verify(ingredientRepository, times(1)).findByName("coffee");
        verify(ingredientRepository, times(1)).findByName("water");

        verify(ingredientRepository, never()).save(any());
    }

    @Test
    public void testPlaceOrder_IngredientsMissing(){

        when(coffeeRecipeRepository.getRecipeByName(CoffeeRecipe.ESPRESSO))
                .thenReturn(espresso);

        when(ingredientRepository.findByName(anyString()))
                .thenReturn(null);

        assertThrows(InvalidRequestException.class, () -> coffeeMachineService.makeOrder("espresso"));

        verify(coffeeRecipeRepository, times(1)).getRecipeByName("espresso");

        verify(ingredientRepository, times(1)).findByName(anyString());

        verify(ingredientRepository, never()).save(any());
    }

}
