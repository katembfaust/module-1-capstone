package com.techelevator;

public class Chip extends Item {
    //inherits from the Item class which implements Sound Effect interface
    private String sound = "Crunch Crunch, Yum!";

    public String getSound() {
        return sound;
    }

    public Chip(String[] array) {
        super(array);
    }
}