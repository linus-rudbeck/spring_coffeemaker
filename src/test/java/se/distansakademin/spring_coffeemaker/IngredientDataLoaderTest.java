package se.distansakademin.spring_coffeemaker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.distansakademin.spring_coffeemaker.models.Ingredient;
import se.distansakademin.spring_coffeemaker.repositories.IngredientRepository;
import se.distansakademin.spring_coffeemaker.util.IngredientDataLoader;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class IngredientDataLoaderTest {


    // Class to test: IngredientDataLoader
    @InjectMocks
    IngredientDataLoader ingredientDataLoader;


    // Class to mock: IngredientRepository
    @Mock
    private IngredientRepository repository;


    @Test
    public void testResetAllIngredients() throws Exception {
        // When we call ingredientDataLoader.run()
        ingredientDataLoader.run();


        // Then we want it to call repository.deleteAll() once...
        verify(repository, times(1)).deleteAll();

        // ...and repository.save(.) 3 times
        verify(repository, times(3)).save(any(Ingredient.class));
    }


}
