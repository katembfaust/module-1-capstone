package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSplitter {

    private List<Item> itemList;

    public FileSplitter() {
        itemList = new ArrayList<>();
    }


    public void splitFile() {
//      grabs data directly from the vendingmachine.csv file and splits it into lines/lists
        String path = "/Users/katelynmbfoust/Desktop/PROJECTS/module-1-capstone/capstone/vendingmachine.csv";
        File file = new File(path);


        try (Scanner fileScan = new Scanner(file)) {
        // Reads the .csv file and splits it into lines

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                String[] lineSplit = splitLine(line);
                //Creates an object of Item from the line contents
                Item item = new Item(lineSplit);
                String type = item.getType();


                // adding items to the itemList based on type
                if (type.equals("Chip")) {
                    Chip chip = new Chip(lineSplit);
                    itemList.add(chip);
                } else if (type.equals("Candy")) {
                    Candy candy = new Candy(lineSplit);
                    itemList.add(candy);
                } else if (type.equals("Drink")) {
                    Drink drink = new Drink(lineSplit);
                    itemList.add(drink);
                } else if (type.equals("Gum")) {
                    Gum gum = new Gum(lineSplit);
                    itemList.add(gum);
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        }

    }

    public List<Item> getItemList() {
        return itemList;
    }

    public String[] splitLine(String line) {
        return line.split("\\|");
    }

}