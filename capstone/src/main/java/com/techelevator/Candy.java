package com.techelevator;

public class Candy extends Item {
//inherits from the Item class which implements Sound Effect interface
    private String sound = "Munch Munch, Yum!";

    public String getSound() {
        return sound;
    }

    public Candy(String[] array) {
        super(array);
    }
}