package ui;

import model.*;
import model.Character;
import model.types.*;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import java.util.Scanner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

//Represents an RPG Game
public class Game {
    private static final String SAVE_FILE = "./data/save.txt";
    public static final int BOARD_COLS = 60;
    public static final int BOARD_ROWS = 60;

    private Character user;
    private MapItems items;
    private ArrayList<Character> enemies;

    //EFFECTS: set up all game play elements
    public Game() {
        items = new MapItems();
        enemies = new ArrayList<>();
        user = new Character("User", 100, 10, 100, new Normal(), new Inventory(),
                new Item("Fist", 1, 0, new Normal()), new Cell(0, 0));
        enemies.add(new Character("Uncle Benny", 90, 4, 100, new Normal(),
                new Inventory(), new Item("PokieStick", 10, 2, new Plant()),
                new Cell(10, 10)));
        items.addItemMap(new Cell(0, 1), new Item("Stick", 10, 5, new Plant()));
        items.addItemMap(new Cell(8, 1), new Item("Flower", 3, 2, new Plant()));
    }

    public ArrayList<Character> getEnemies() {
        return enemies;
    }

    public MapItems getItems() {
        return items;
    }

    public Character getUser() {
        return user;
    }

    //EFFETCS: return true if user is dead, false otherwise
    public boolean isGameOver() {
        return user.isDead();
    }

    // EFFECTS: returns true if cell is in bounds of game
    //credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeGame class' isInBounds method
    private boolean canMove(Direction direction) {
        switch (direction) {
            case RIGHT:
                return user.getLocation().getColumn() + 1 < BOARD_COLS;
            case LEFT:
                return user.getLocation().getColumn() - 1 >= 0;
            case UP:
                return user.getLocation().getRow() - 1 >= 0;
            default:
                return user.getLocation().getRow() + 1 < BOARD_ROWS;
        }
    }

    //MODIFIES: this
    //EFFECTS: move user based on what key is pressed, and attempts to pick up an item if able
    public void moveCharacter(KeyEvent e) {
        Direction direction;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_UP:
                direction = Direction.UP;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.RIGHT;
                break;
            //LEFT case
            default:
                direction = Direction.LEFT;
        }
        if (canMove(direction)) {
            user.moveCharacter(direction);
        }
        pickUpItem();
    }

    //MODIFIES: this
    //EFFECTS: loads accounts from SAVE_FILE, if that file exists;
    //          otherwise initializes a placeholder character
    //credit:   modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp specifically from TellerApp
    //          class' loadAccount method.
    public void loadGame() {
        try {
            File file = new File(SAVE_FILE);
            user = Reader.readCharacter(file);
            items = Reader.readMapItems(file);
        } catch (IOException e) {
            user = new Character("", 100, 10, 100, new Normal(), new Inventory(),
                    new Item("Fist", 1, 0, new Normal()), new Cell(0, 0));
        }
    }

    //MODIFIES: this
    //EFFECTS: saves the stat of the user's character and progress to SAVE_FILE
    //credit:  modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp TellerApp class' saveAccounts method,
    //         mostly the same though
    public void saveGame() {
        try {
            Writer writer = new Writer(new File(SAVE_FILE));
            writer.write(user);
            writer.write(items);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + SAVE_FILE + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    //MODIFIES: this
    //EFFECTS: if a user location matches with a cell location, add cell to user inventory
    private void pickUpItem() {
        for (Cell cell : items.getItemMap().keySet()) {
            if (user.getLocation().equals(cell)) {
                user.addInventory(items.getItemMap().get(cell));
                items.getItemMap().remove(cell);
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: if button aligns with a inventory spot, add swap equipped item with that item
    public void equipItemNumber(KeyEvent e) {
        int index = Integer.parseInt(String.valueOf(e.getKeyChar()));
        if (user.getInventory().getInventory().size() > index) {
            user.equipWeapon(user.getInventory().getInventory().get(index).getItemName());
        }
    }

    //EFFETCS: if the user location matches an enemy location (for now just 1 enemy), return true, otherwise false
    public boolean canEnemyInteract() {
        return user.getLocation().equals(enemies.get(0).getLocation());
    }

}