package model;

import model.types.Type;
import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.ArrayList;

//represents an Item, while not all are weapons, all can be used as such, each has their own name
//attack stat, which will effect the damage the user is capable of when equipped, and weight, which
//will effect the amount of stamina using the item wastes.
public class Item implements Savable {
    private String itemName;
    private int attackStat;
    private int itemWeight;
    private Type itemType;

    //REQUIRE: attackStat >= 0, and name have no spaces
    //EFFECTS: creates an item of given name, attack stat, and weight
    public Item(String name, int attack, int weight, Type type) {
        itemName = name;
        attackStat = attack;
        itemWeight = weight;
        itemType = type;
    }


    //EFFECTS: returns the item attack stat
    public int getAttackStat() {
        return attackStat;
    }

    //EFFECTS: returns the item name
    public String getItemName() {
        return itemName;
    }

    //EFFECTS: returns the item weight
    public int getItemWeight() {
        return itemWeight;
    }

    //EFFECTS: returns the item Type
    public Type getItemType() {
        return itemType;
    }

    //EFFECTS: returns the item's name, attack stat, weight and Type (all caps) as a string representation
    public String toString() {
        String attackStatString = Integer.toString(attackStat);
        String itemWeightString = Integer.toString(itemWeight);
        return itemName + " (Attack: " + attackStatString + ", Weight: " + itemWeightString + ", Type: "
                + itemType.getTypeString() + ")";
    }

    //REQUIRES: los.size() == 4, the first element must be the item's name, the second the attack, the third the
    //         weight, and the last the ItemType (with only the first letter capitalized).
    //EFFECTS: return an Item constructed from components
    //credit: modified from https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' parseAccount method
    public static Item parseItem(ArrayList<String> los) {
        String name = los.get(0);
        int attack = Integer.parseInt(los.get(1));
        int weight = Integer.parseInt(los.get(2));
        Type itemType = Type.parseItemType(los.get(3));

        return new Item(name, attack, weight, itemType);
    }

    //MODIFIES: printWriter
    //EFFECTS: writes the savable(character's Item component) to printWriter
    //credit: modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp Account class' save method
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(itemName);
        printWriter.print(Reader.SUB_DELIMITER);
        printWriter.print(attackStat);
        printWriter.print(Reader.SUB_DELIMITER);
        printWriter.print(itemWeight);
        printWriter.print(Reader.SUB_DELIMITER);
        printWriter.print(itemType.getTypeString());
    }
}