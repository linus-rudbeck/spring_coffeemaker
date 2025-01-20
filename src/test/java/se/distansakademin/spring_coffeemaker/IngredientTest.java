package se.distansakademin.spring_coffeemaker;

import org.junit.jupiter.api.Test;
import se.distansakademin.spring_coffeemaker.models.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    public void testIngredientInstantiation(){
        // Arrange
        Ingredient ingredient = new Ingredient(Ingredient.COFFEE, 10);

        // Act & Assert
        assertEquals("coffee", ingredient.getName());
        assertEquals(10, ingredient.getQuantity());
    }

    @Test
    public void testGettersAndSetters(){
        // Arrange
        Ingredient ingredient = new Ingredient();

        // Act
        ingredient.setId(123L);
        ingredient.setName(Ingredient.COFFEE);
        ingredient.setQuantity(10);

        // Assert
        assertEquals(123L, ingredient.getId());
        assertEquals("coffee", ingredient.getName());
        assertEquals(10, ingredient.getQuantity());
    }
}
