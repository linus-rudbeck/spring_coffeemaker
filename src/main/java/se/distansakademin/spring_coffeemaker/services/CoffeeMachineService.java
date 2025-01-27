package se.distansakademin.spring_coffeemaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.distansakademin.spring_coffeemaker.models.CoffeeRecipe;
import se.distansakademin.spring_coffeemaker.models.Ingredient;
import se.distansakademin.spring_coffeemaker.repositories.CoffeeRecipeRepository;
import se.distansakademin.spring_coffeemaker.repositories.IngredientRepository;
import se.distansakademin.spring_coffeemaker.util.InvalidRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CoffeeMachineService {

    @Autowired
    CoffeeRecipeRepository coffeeRecipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;


    private List<Ingredient> dbIngredients;


    public String makeOrder(String coffeeType) throws InvalidRequestException {

        CoffeeRecipe recipe = coffeeRecipeRepository.getRecipeByName(coffeeType);

        if(recipe == null){
            throw new InvalidRequestException("Recipe does not exist: " + coffeeType);
        }

        dbIngredients = getAllIngredients(recipe);

        boolean sufficientIngredients = hasSufficientIngredients(recipe);

        if (!sufficientIngredients) {
            throw new InvalidRequestException("Insufficient ingredients");
        }

        updateIngredients(recipe);

        return "Order placed for " + coffeeType;

    }

    private List<Ingredient> getAllIngredients(CoffeeRecipe recipe) {
        List<Ingredient> ingredients = new ArrayList<>();

        recipe.getIngredients().forEach((name, quantityRequired) -> {
            Ingredient ingredient = ingredientRepository.findByName(name);
            ingredients.add(ingredient);
        });

        return ingredients;
    }

    private void updateIngredients(CoffeeRecipe recipe) {
        Map<String, Integer> recipeIngredients = recipe.getIngredients();

        for (Map.Entry<String, Integer> recipeIngredient : recipeIngredients.entrySet()) {
            String ingredientName = recipeIngredient.getKey();
            Integer amountRequired = recipeIngredient.getValue();
            Ingredient dbIngredient = getIngredientByName(ingredientName);

            int newQuantity = dbIngredient.getQuantity() - amountRequired;
            dbIngredient.setQuantity(newQuantity);

            ingredientRepository.save(dbIngredient);
        }
    }

    private boolean hasSufficientIngredients(CoffeeRecipe recipe){
        Map<String, Integer> recipeIngredients = recipe.getIngredients();

        for (Map.Entry<String, Integer> recipeIngredient : recipeIngredients.entrySet()) {
            String ingredientName = recipeIngredient.getKey();
            Integer amountRequired = recipeIngredient.getValue();
            Ingredient dbIngredient = getIngredientByName(ingredientName);

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

    private Ingredient getIngredientByName(String ingredientName) {
        for (Ingredient ingredient : dbIngredients){
            if(ingredient == null){
                continue;
            }

            String currentIngredientName = ingredient.getName();

            if (currentIngredientName.equalsIgnoreCase(ingredientName)){
                return ingredient;
            }
        }

        return null;
    }
}
