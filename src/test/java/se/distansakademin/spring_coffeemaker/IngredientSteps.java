package se.distansakademin.spring_coffeemaker;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import se.distansakademin.spring_coffeemaker.models.Ingredient;

public class IngredientSteps {

    Ingredient ingredient;
    String name;
    int quantity;

    /*
    Scenario: Ingredient instantiation
     */
    @Given("We create an ingredient {string} with quantity {int}")
    public void we_create_an_ingredient_with_quantity(String name, Integer quantity) {
        ingredient = new Ingredient(name, quantity);
    }
    @When("We get that ingredient name and quantity")
    public void we_get_that_ingredient_name_and_quantity() {
        name = ingredient.getName();
        quantity = ingredient.getQuantity();
    }
    @Then("The retrieved values are {string} and {int}")
    public void the_retrieved_values_are_and(String name, Integer quantity) {
        Assertions.assertEquals(name, this.name);
        Assertions.assertEquals(quantity, this.quantity);
    }



    /*
    Scenario: Setting ingredient properties
     */
    @Given("We create an ingredient without name and quantity")
    public void we_create_an_ingredient_without_name_and_quantity() {
        ingredient = new Ingredient();
    }
    @When("We set that ingredients name to {string} and quantity to {int}")
    public void we_set_that_ingredients_name_to_and_quantity_to(String name, Integer quantity) {
        ingredient.setName(name);
        ingredient.setQuantity(quantity);
    }
    @Then("That ingredients name is {string} and quantity is {int}")
    public void that_ingredients_name_is_and_quantity_is(String name, Integer quantity) {
        Assertions.assertEquals(name, ingredient.getName());
        Assertions.assertEquals(quantity, ingredient.getQuantity());
    }

}
