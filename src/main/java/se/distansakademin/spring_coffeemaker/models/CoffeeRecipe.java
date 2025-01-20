package se.distansakademin.spring_coffeemaker.models;

import java.util.Map;

public class CoffeeRecipe {

    public static final String ESPRESSO = "espresso";
    public static final String LATTE = "latte";
    public static final String CAPPUCCINO = "cappuccino";


    private String coffeeType;
    private Map<String, Integer> ingredients;

    public CoffeeRecipe(String coffeeType, Map<String, Integer> ingredients) {
        this.coffeeType = coffeeType;
        this.ingredients = ingredients;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }
}
