package se.distansakademin.spring_coffeemaker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ingredient {

    public static final String COFFEE = "coffee";
    public static final String MILK = "milk";
    public static final String WATER = "water";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int quantity;

    public Ingredient(){ }

    public Ingredient(String name, int quantity){
        this.name = name;
        this.quantity =  quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
