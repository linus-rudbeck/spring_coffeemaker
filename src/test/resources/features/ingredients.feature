Feature: Managing ingredients

  # Testing constructor and getters
  Scenario: Ingredient instantiation
    Given We create an ingredient "Coffee" with quantity 10
    When We get that ingredient name and quantity
    Then The retrieved values are "Coffee" and 10

  # Testing setters and getters
  Scenario: Setting ingredient properties
    Given We create an ingredient without name and quantity
    When We set that ingredients name to "Coffee" and quantity to 10
    Then That ingredients name is "Coffee" and quantity is 10