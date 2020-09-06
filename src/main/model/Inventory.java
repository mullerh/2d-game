package model;

import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Represents the items in a character's inventory
public class Inventory implements Savable {
    private List<Item> inventory;

    //EFFECTS: creates a single blank list of items
    public Inventory() {
        inventory = new ArrayList<>();
    }

    //EFFECTS: returns inventory
    public List<Item> getInventory() {
        return inventory;
    }

    //MODIFIES: this
    //EFFECTS: adds item to the inventory
    public void addItem(Item item) {
        inventory.add(item);
    }

    //MODIFIES: this
    //EFFECTS: removes specified item from inventory
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    //EFFECTS: returns string of all item names with their ordered number (starting at 0) preceding the name
    public String getInventoryItemStrings() {
        String inventoryItemsString = "";
        for (int i = 0; i < Math.min(inventory.size(), 10); i++) {
            Item item = inventory.get(i);
            String itemString = item.getItemName();
            inventoryItemsString = inventoryItemsString + i + itemString + "<br/>";
        }

        return inventoryItemsString;
    }

    //EFFECTS: returns a string representation of the inventory items, including: name,
    //         attack value, and weight.
    public String toString() {
        String inventoryString = "";
        for (Item item : inventory) {
            String itemString = item.toString();
            inventoryString = inventoryString + itemString + "<br/>";
        }

        return inventoryString;
    }

    //REQUIRES: Each element must be a Item in the form of Name:Attack:Weight:Type (Where type is the name with only
    //          the first letter upper case)
    //EFFECTS: return an Inventory constructed from components
    //credit: modified from https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' parseAccount method
    public static Inventory parseInventory(ArrayList<String> los) {
        Inventory inventory = new Inventory();

        for (String s : los) {
            String[] splits = s.split(Reader.SUB_DELIMITER);
            ArrayList<String> itemStrings = new ArrayList<>(Arrays.asList(splits));

            Item item = Item.parseItem(itemStrings);

            inventory.addItem(item);
        }

        return inventory;
    }

    //MODIFIES: printWriter
    //EFFECTS: writes the savable(character's inventory component) to printWriter
    //credit: modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp Account class' save method
    @Override
    public void save(PrintWriter printWriter) {
        for (Item i : inventory) {
            i.save(printWriter);
            printWriter.print(Reader.LIST_DELIMITER);
        }
        printWriter.print(Reader.LIST_DELIMITER);
    }
}
