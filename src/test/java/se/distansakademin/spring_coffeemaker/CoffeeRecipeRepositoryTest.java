package se.distansakademin.spring_coffeemaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.repositories.CoffeeRecipeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoffeeRecipeRepositoryTest {

    private CoffeeRecipeRepository repository;

    @BeforeEach
    public void setup(){
        repository = new CoffeeRecipeRepository();
    }

    @Test
    public void testGetAllRecipes(){
        List<CoffeeRecipe> recipes = repository.getAll();

        assertNotNull(recipes);
        assertEquals(3, recipes.size());

        // Check for each recipe
        assertTrue(recipes.stream().anyMatch(r -> r.getCoffeeType().equals(CoffeeRecipe.ESPRESSO)));
        assertTrue(recipes.stream().anyMatch(r -> r.getCoffeeType().equals(CoffeeRecipe.LATTE)));
        assertTrue(recipes.stream().anyMatch(r -> r.getCoffeeType().equals(CoffeeRecipe.CAPPUCCINO)));
    }
}
