package model;

import model.types.*;
import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;

//represents a user with a given name, health, strength, stamina, inventory, and equipped item.
public class Character implements Savable {

    private String name;
    private int health;
    private int strength;
    private int stamina;
    private Type characterType;
    private Inventory inventory;
    private Item equippedWeapon;
    private Cell location;

    //REQUIRES: health > 0
    //EFFECTS: creates a user with the given name, 100 health, 10 strength, 100 stamina, a blank
    //         inventory, and an item "Fist" equipped.
    public Character(String name, int hp, int strength, int stamina, Type type, Inventory inventory,
                     Item weapon, Cell location) {
        this.name = name;
        health = hp;
        this.strength = strength;
        this.stamina = stamina;
        characterType = type;
        this.inventory = inventory;
        equippedWeapon = weapon;
        this.location = location;
    }

    //EFFECTS: returns Character name
    public String getName() {
        return name;
    }

    //EFFECTS: returns Character health
    public int getHealth() {
        return health;
    }

    //EFFECTS: returns Character stamina
    public int getStamina() {
        return stamina;
    }

    //EFFECTS: returns Character inventory
    public Inventory getInventory() {
        return inventory;
    }

    //EFFECTS: returns Character strength
    public int getStrength() {
        return strength;
    }

    //EFFECTS: returns Character Type
    public Type getCharacterType() {
        return characterType;
    }

    //EFFECTS: returns the Item the Character has currently equipped
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    //EFFECTS: returns the progress of the character
    public Cell getLocation() {
        return location;
    }

    //MODIFIES: this
    //EFFECTS: changes the character name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: change the currently equipped weapon (only used when the game sets weapon, not the user)
    public void setEquippedWeapon(Item weapon) {
        equippedWeapon = weapon;
    }

    //REQUIRES: strength >= 0
    //MODIFIES: this
    //EFFECTS: changes Character strength
    public void setStrength(int strength) {
        this.strength = strength;
    }

    //REQUIRES: health >= 0
    //MODIFIES: this
    //EFFECTS: changes Character health
    public void setHealth(int health) {
        this.health = health;
    }

    //MODIFIES: this
    //EFFECTS: changes Character Type
    public void setCharacterType(Type type) {
        characterType = type;
    }

    //MODIFIES: this
    //EFFECTS: sets a new progress through the game
    public void setLocation(Cell location) {
        this.location = location;
    }

    //MODIFIES: this
    //EFFECTS: adds an item to the Character inventory
    public void addInventory(Item item) {
        inventory.addItem(item);
    }

    //MODIFIES: this
    //EFFECTS: decreases Character health by specified amount (can go into negatives)
    public void decreaseHealth(int damage) {
        health = health - damage;
    }

    //EFFECTS: returns true if the Character health is <= 0 otherwise false
    public boolean isDead() {
        return health <= 0;
    }

    //MODIFIES: this
    //EFFECTS: decreases Character stamina by specified amount
    public void decreaseStamina(int reduction) {
        stamina = stamina - reduction;
    }

    //EFFECTS: returns true if the Character stamina is <= 0 otherwise false
    public boolean isExhausted() {
        return stamina <= 0;
    }

    //REQUIRES attack == "1" || attack == "2"
    //MODIFIES: this
    //EFFECTS: if the Character has any stamina, return a calculated attack value and subtract the
    //         weapon weight from the Character's stamina, if the Character had no stamina,
    //         return 0. "1" gives regular damage as defined above, and "2" gives Strong Damage which
    //         is twice the amount as defined above, and reduces doubled the amount of stamina.
    //         Then multiplies the Type effectiveness of the Character's equipped weapon on the enemy's type
    //         (will be more attack types later, which is why attack is a String not a bool at the moment)
    //CREDIT:  (for how to round a double into the nearest int):
    //         https://javarevisited.blogspot.com/2017/01/how-to-convert-double-to-int-in-java.html
    public int attackDamage(String attack, Type defendingType) {
        int damage = 0;
        double typeMultiplier = equippedWeapon.getItemType().attackMultiplier(defendingType.getElement());

        if (!isExhausted()) {
            if (attack.equals("1")) {
                decreaseStamina(equippedWeapon.getItemWeight());
                damage = (strength * equippedWeapon.getAttackStat()) / 5;
            } else {
                decreaseStamina(equippedWeapon.getItemWeight() * 2);
                damage = ((strength * equippedWeapon.getAttackStat()) / 5) * 2;
            }
        }
        return (int) Math.round(damage * typeMultiplier);
    }

    //MODIFIES: this
    //EFFECTS: if the weapon of the specified name is found, equip it,
    //         and return the equipped item back to inventory, if not,
    //         don't switch weapons
    public void equipWeapon(String weaponName) {
        for (Item item : getInventory().getInventory()) {
            if (item.getItemName().equals(weaponName)) {
                inventory.addItem(equippedWeapon);
                equippedWeapon = item;
                inventory.removeItem(item);
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: moves character over 1 unit in direction
    public void moveCharacter(Direction direction) {
        switch (direction) {
            case RIGHT:
                location = new Cell(location.getRow(), location.getColumn() + 1);
                break;
            case LEFT:
                location = new Cell(location.getRow(), location.getColumn() - 1);
                break;
            case UP:
                location = new Cell(location.getRow() - 1, location.getColumn());
                break;
                //DOWN case
            default:
                location = new Cell(location.getRow() + 1, location.getColumn());
                break;
        }
    }

    //EFFECTS: returns a string representation of the Character health, strength, stamina, type,
    //         equipped weapon stats, and inventory items.
    public String toString() {
        String healthString = Integer.toString(health);
        String strengthString = Integer.toString(strength);
        String staminaString = Integer.toString(stamina);
        String weaponString = equippedWeapon.toString();
        return "<html>Name: " + name + "<br/>"
                + "Health: " + healthString + "<br/>"
                + "Strength: " + strengthString + "<br/>"
                + "Stamina: " + staminaString + "<br/>"
                + "User Type: " + characterType.getTypeString() + "<br/>"
                + "Equipped Weapon:<br/>" + weaponString + "<br/><br/>"
                + "Inventory:<br/>" + inventory.toString();
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the savable(character) to printWriter
    //credit: modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp Account class' save method
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.SUPER_DELIMITER);
        printWriter.print(health);
        printWriter.print(Reader.SUPER_DELIMITER);
        printWriter.print(strength);
        printWriter.print(Reader.SUPER_DELIMITER);
        printWriter.print(stamina);
        printWriter.print(Reader.SUPER_DELIMITER);
        printWriter.print(characterType.getTypeString());
        printWriter.print(Reader.SUPER_DELIMITER);
        inventory.save(printWriter);
        printWriter.print(Reader.SUPER_DELIMITER);
        equippedWeapon.save(printWriter);
        printWriter.print(Reader.SUPER_DELIMITER);
        location.save(printWriter);
    }
}
