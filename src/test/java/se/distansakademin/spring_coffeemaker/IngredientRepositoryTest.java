package se.distansakademin.spring_coffeemaker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.distansakademin.spring_coffeemaker.models.Ingredient;
import se.distansakademin.spring_coffeemaker.repositories.IngredientRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository repository;

    @Test
    public void testSaveAndFindByName(){
        // Arrange
        Ingredient coffee = new Ingredient(Ingredient.COFFEE, 20);

        // Act
        repository.save(coffee);
        Ingredient found = repository.findByName("coffee");

        // Assert
        assertNotNull(found);
        assertEquals(20, found.getQuantity());
    }
}
