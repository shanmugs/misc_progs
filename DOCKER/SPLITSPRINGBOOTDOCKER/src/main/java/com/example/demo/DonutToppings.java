package com.example.demo;

public enum DonutToppings {

    CHOCOLATE("Chocolate Icing"),
    SPRINKLES("Sprinkles"),
    MAPLE("Maple Icing"),
    GLAZED("Sugar Glaze"),
    BACON("Bacon"),
    POWDERED_SUGAR("Powdered Sugar"),
    NONE("None");

    private final String value;
    DonutToppings(String value) {
        this.value = value;
    }

}