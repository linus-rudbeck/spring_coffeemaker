package se.distansakademin.spring_coffeemaker;

import org.junit.jupiter.api.Test;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.models.Ingredient;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoffeeRecipeTest {

    @Test
    public void testCoffeeRecipeInstantiation(){
        // Arrange
        Map<String, Integer> ingredients = Map.of(
                Ingredient.COFFEE, 1,
                Ingredient.WATER, 1
        );
        CoffeeRecipe recipe = new CoffeeRecipe("espresso", ingredients);

        // Act & Assert
        assertEquals("espresso", recipe.getCoffeeType());
        assertNotNull(recipe.getIngredients());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(1, recipe.getIngredients().get("coffee"));
        assertEquals(1, recipe.getIngredients().get("water"));

    }

}
