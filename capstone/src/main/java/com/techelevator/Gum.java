package com.techelevator;

public class Gum extends Item {
    //inherits from the Item class which implements Sound Effect interface
    private String sound = "Chew Chew, Yum!";

    public String getSound() {
        return sound;
    }

    public Gum(String[] array) {
        super(array);
    }
}