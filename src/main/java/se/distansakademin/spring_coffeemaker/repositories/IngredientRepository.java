package se.distansakademin.spring_coffeemaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.distansakademin.spring_coffeemaker.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    Ingredient findByName(String name);
}
