package se.distansakademin.spring_coffeemaker.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.distansakademin.spring_coffeemaker.models.Ingredient;
import se.distansakademin.spring_coffeemaker.repositories.IngredientRepository;

@Component
public class IngredientDataLoader implements CommandLineRunner {

    @Autowired
    private IngredientRepository repository;

    @Override
    public void run(String... args) throws Exception {
        resetAllIngredients();
    }

    private void resetAllIngredients() {
        repository.deleteAll();

        repository.save(new Ingredient(Ingredient.COFFEE, 10));
        repository.save(new Ingredient(Ingredient.MILK, 10));
        repository.save(new Ingredient(Ingredient.WATER, 10));
        System.out.println("Reset all ingredients to 10 of each");
    }
}
