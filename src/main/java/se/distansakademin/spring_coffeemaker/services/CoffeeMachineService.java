package se.distansakademin.spring_coffeemaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.models.Ingredient;
import se.distansakademin.spring_coffeemaker.repositories.CoffeeRecipeRepository;
import se.distansakademin.spring_coffeemaker.repositories.IngredientRepository;
import se.distansakademin.spring_coffeemaker.util.InvalidRequestException;

import java.util.Map;

@Service
public class CoffeeMachineService {

    @Autowired
    CoffeeRecipeRepository coffeeRecipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public String makeOrder(String coffeeType) throws InvalidRequestException {

        CoffeeRecipe recipe = coffeeRecipeRepository.getRecipeByName(coffeeType);

        boolean sufficientIngredients = hasSufficientIngredients(recipe);

        if (!sufficientIngredients) {
            throw new InvalidRequestException("Insufficient ingredients");
        }

        updateIngredients(recipe);

        return "Order placed for " + coffeeType;

    }

    private void updateIngredients(CoffeeRecipe recipe) {
        Map<String, Integer> recipeIngredients = recipe.getIngredients();

        for (Map.Entry<String, Integer> recipeIngredient : recipeIngredients.entrySet()) {
            String ingredientName = recipeIngredient.getKey();
            Integer amountRequired = recipeIngredient.getValue();
            Ingredient dbIngredient = ingredientRepository.findByName(ingredientName);

            int newQuantity = dbIngredient.getQuantity() - amountRequired;
            dbIngredient.setQuantity(newQuantity);
        }
    }

    private boolean hasSufficientIngredients(CoffeeRecipe recipe) {
        Map<String, Integer> recipeIngredients = recipe.getIngredients();

        for (Map.Entry<String, Integer> recipeIngredient : recipeIngredients.entrySet()) {
            String ingredientName = recipeIngredient.getKey();
            Integer amountRequired = recipeIngredient.getValue();
            Ingredient dbIngredient = ingredientRepository.findByName(ingredientName);

            if (dbIngredient == null) {
                return false;
            }

            int amountAvailable = dbIngredient.getQuantity();

            if (amountAvailable < amountRequired) {
                return false;
            }
        }

        return true;
    }
}
