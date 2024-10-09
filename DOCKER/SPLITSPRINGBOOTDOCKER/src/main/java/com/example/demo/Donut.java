package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class Donut {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public String name;

    public Double costDollars;

    public Integer numberAvailable;

    @Enumerated(EnumType.STRING)
    private DonutToppings toppings;

    Donut(String name, DonutToppings toppings, Double costDollars, Integer numberAvailable) {
        this.name = name;
        this.toppings = toppings;
        this.costDollars = costDollars;
        this.numberAvailable = numberAvailable;
    }
}
