package se.distansakademin.spring_coffeemaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.repositories.CoffeeRecipeRepository;

import java.util.List;
import java.util.stream.Stream;

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
        boolean espessoExists_1 =  false;
        for (CoffeeRecipe recipe : recipes){
            if(recipe.getCoffeeType().equalsIgnoreCase(CoffeeRecipe.ESPRESSO)){
                espessoExists_1 = true;
                break;
            }
        }
        assertTrue(espessoExists_1);


        boolean espessoExists_2 = recipes.stream().anyMatch(recipe -> recipe.getCoffeeType().equals(CoffeeRecipe.ESPRESSO));
        assertTrue(espessoExists_2);
        assertTrue(recipes.stream().anyMatch(r -> r.getCoffeeType().equals(CoffeeRecipe.LATTE)));
        assertTrue(recipes.stream().anyMatch(r -> r.getCoffeeType().equals(CoffeeRecipe.CAPPUCCINO)));
    }


    @Test
    public void getGetRecipeByName_Exists(){
        CoffeeRecipe espresso = repository.getRecipeByName("espresso");
        CoffeeRecipe latte = repository.getRecipeByName("latte");
        CoffeeRecipe cappuccino = repository.getRecipeByName("cappuccino");

        assertNotNull(espresso);
        assertNotNull(latte);
        assertNotNull(cappuccino);

    }

    @Test
    public void getRecipedByName_NotExists(){
        CoffeeRecipe recipe = repository.getRecipeByName("NO_RECIPE");
        assertNull(recipe);
    }
}
