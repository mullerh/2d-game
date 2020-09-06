package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.*;
import model.Character;
import model.types.Type;

//A reader that can read character data from a file
//credit for class: Modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class
public class Reader {
    public static final String SUPER_DELIMITER = ",";
    public static final String LIST_DELIMITER = "/";
    public static final String SUB_DELIMITER = ":";

    //EFFECTS: returns a Character parsed from file; throws
    //         IOException if an exception is raised when opening / reading from file
    //credit: modified from https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' readAccounts method
    public static Character readCharacter(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseCharacter(fileContent.get(0));
    }

    //EFFECTS: returns a MapItems parsed from file; throws
    //         IOException if an exception is raised when opening / reading from file
    //credit: modified from https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' readAccounts method
    public static MapItems readMapItems(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseMapItems(fileContent.get(1));
    }

    //EFFECTS: returns content of file as a list of strings, each string
    //         containing the content of one row of the file
    //credit: https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' readFile method
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //EFFECTS: returns a list of strings obtained by splitting line on the inputted delimiter
    //credit: modified from https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' splitSting method
    private static ArrayList<String> splitString(String line, String delimiter) {
        String[] splits = line.split(delimiter);
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRE: component must be made of each of the 8 character components (in order Name,Health,Strength,Stamina,
    //         Type,Inventory,EquippedWeapon,Location) must be separated by a SUPER_DELIMITER, the inventory items
    //         must separated by an LIST_DELIMITER, and items must be separated by SUB_DELIMITER, (in order
    //         Name:Attack:Weight:ItemType)
    //EFFECTS: returns a character constructed from components
    //credit: modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' parseAccounts method
    private static Character parseCharacter(String component) {
        ArrayList<String> characterComponents = splitString(component, SUPER_DELIMITER);
        ArrayList<String> inventoryComponents = splitString(characterComponents.get(5), LIST_DELIMITER);
        ArrayList<String> itemComponents = splitString(characterComponents.get(6), SUB_DELIMITER);
        ArrayList<String> locationComponents = splitString(characterComponents.get(7), SUB_DELIMITER);

        String name = characterComponents.get(0);
        int health = Integer.parseInt(characterComponents.get(1));
        int strength = Integer.parseInt(characterComponents.get(2));
        int stamina = Integer.parseInt(characterComponents.get(3));
        Type characterType = Type.parseItemType(characterComponents.get(4));
        Inventory inventory = Inventory.parseInventory(inventoryComponents);
        Item item = Item.parseItem(itemComponents);
        Cell location = Cell.parseCell(locationComponents);

        return new Character(name, health, strength, stamina, characterType, inventory, item, location);
    }

    //REQUIRES: List of 2 components (separated by SUPER_DELIMITER):
    //a location (represented in order: row:column separated by SUB_DELIMITER), and an Item (in order
    //Name:Attack:Weight:ItemType separated by SUB_DELIMITER). Each element of list is separated by LIST_DELIMITER
    //EFFECTS: returns a MapItems constructed from components
    //credit: modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp Reader class' parseAccounts method
    private static MapItems parseMapItems(String component) {
        MapItems mapItems = new MapItems();
        ArrayList<String> mapItemComponents = splitString(component, LIST_DELIMITER);

        for (String singleItemComponent : mapItemComponents) {
            ArrayList<String> singleItemComponents = splitString(singleItemComponent, SUPER_DELIMITER);
            ArrayList<String> cellComponents = splitString(singleItemComponents.get(0), SUB_DELIMITER);
            ArrayList<String> itemComponents = splitString(singleItemComponents.get(1), SUB_DELIMITER);

            Cell cell = Cell.parseCell(cellComponents);
            Item item = Item.parseItem(itemComponents);

            mapItems.addItemMap(cell, item);
        }

        return mapItems;
    }
}
