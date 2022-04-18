package com.techelevator;

public class Drink extends Item {
    //inherits from the Item class which implements Sound Effect interface
    private String sound = "Glug Glug, Yum!";

    public String getSound() {
        return sound;
    }

    public Drink(String[] array) {
        super(array);
    }
}