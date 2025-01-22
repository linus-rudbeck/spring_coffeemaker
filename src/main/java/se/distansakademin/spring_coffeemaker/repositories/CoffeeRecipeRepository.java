package se.distansakademin.spring_coffeemaker.repositories;

import org.springframework.stereotype.Repository;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.models.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CoffeeRecipeRepository {

    private List<CoffeeRecipe> recipes;


    public CoffeeRecipeRepository() {
        recipes = new ArrayList<>();

        recipes.add(new CoffeeRecipe(CoffeeRecipe.ESPRESSO, Map.of(
                Ingredient.COFFEE, 1,
                Ingredient.WATER, 1
        )));

        recipes.add(new CoffeeRecipe(CoffeeRecipe.CAPPUCCINO, Map.of(
                Ingredient.COFFEE, 1,
                Ingredient.WATER, 1,
                Ingredient.MILK, 1
        )));

        recipes.add(new CoffeeRecipe(CoffeeRecipe.LATTE, Map.of(
                Ingredient.COFFEE, 1,
                Ingredient.MILK, 2,
                Ingredient.WATER, 1
        )));

    }


    public List<CoffeeRecipe> getAll() {
        return recipes;
    }

    public CoffeeRecipe getRecipeByName(String name) {
        for (CoffeeRecipe recipe : recipes){
            if (recipe.getCoffeeType().equalsIgnoreCase(name)){
                return recipe;
            }
        }

        return null;
    }
}
