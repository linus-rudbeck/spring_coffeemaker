package se.distansakademin.spring_coffeemaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.models.Ingredient;
import se.distansakademin.spring_coffeemaker.repositories.CoffeeRecipeRepository;
import se.distansakademin.spring_coffeemaker.repositories.IngredientRepository;

import java.util.Map;

@Service
public class CoffeeMachineService {

    @Autowired
    CoffeeRecipeRepository coffeeRecipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public String makeOrder(String coffeeType) {

        CoffeeRecipe recipe = coffeeRecipeRepository.getRecipeByName(coffeeType);

        hasSufficientIngredients(recipe);

        return "Order placed for " + coffeeType;

    }

    private boolean hasSufficientIngredients(CoffeeRecipe recipe) {
        Map<String, Integer> recipeIngredients = recipe.getIngredients();

        for (Map.Entry<String, Integer> entry : recipeIngredients.entrySet()) {
            String ingredientName = entry.getKey();
            Integer amountRequired = entry.getValue();
            Ingredient dbIngredient = ingredientRepository.findByName(ingredientName);

            // Kolla om dbIngredient

            int amountAvailable = dbIngredient.getQuantity();

            if (amountAvailable < amountRequired) {
                return false;
            }

            dbIngredient.setQuantity(dbIngredient.getQuantity() - amountRequired);
            ingredientRepository.save(dbIngredient);
        }

        return true;
    }
}
