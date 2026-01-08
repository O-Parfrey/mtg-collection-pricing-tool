package com.orion.mtg.model;

import java.util.List;

public class Card {

    private String name;
    private List<CardVersion> versions;
    private int totalQuantity;
    private double mostExpensivePrice;
    private double totalValue;

    public Card(String name) {
        this.name = name;
    }

    // TODO: getters/setters later
}