package model;

import model.types.*;
import model.types.Earth;
import model.types.Normal;
import model.types.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void runBefore() {
        inventory = new Inventory();
    }

    @Test
    void testConstructor() {
        assertEquals(0, inventory.getInventory().size());
    }

    @Test
    void testGetInventory() {
        Item item1 = new Item("Stick", 8, 1, new Normal());
        inventory.addItem(item1);
        assertEquals(1, inventory.getInventory().size());
        assertEquals("Stick", inventory.getInventory().get(0).getItemName());
        assertEquals(8, inventory.getInventory().get(0).getAttackStat());
        assertEquals(1, inventory.getInventory().get(0).getItemWeight());
        assertEquals("Normal", inventory.getInventory().get(0).getItemType().getTypeString());
    }

    @Test
    void testAddOneItem() {
        Item item = new Item("Dandelion", 0, 1,new Plant());
        inventory.addItem(item);
        assertEquals(1, inventory.getInventory().size());
        assertEquals("Dandelion", inventory.getInventory().get(0).getItemName());
        assertEquals(0, inventory.getInventory().get(0).getAttackStat());
        assertEquals(1, inventory.getInventory().get(0).getItemWeight());
        assertEquals("Plant", inventory.getInventory().get(0).getItemType().getTypeString());
    }

    @Test
    void testAddTwoItems() {
        Item item1 = new Item("Dandelion", 0, 1, new Plant());
        Item item2 = new Item("Rock", 8, 7, new Earth());
        inventory.addItem(item1);
        inventory.addItem(item2);
        assertEquals(2, inventory.getInventory().size());
        assertEquals("Dandelion", inventory.getInventory().get(0).getItemName());
        assertEquals(0, inventory.getInventory().get(0).getAttackStat());
        assertEquals(1, inventory.getInventory().get(0).getItemWeight());
        assertEquals("Plant", inventory.getInventory().get(0).getItemType().getTypeString());
        assertEquals("Rock", inventory.getInventory().get(1).getItemName());
        assertEquals(8, inventory.getInventory().get(1).getAttackStat());
        assertEquals(7, inventory.getInventory().get(1).getItemWeight());
        assertEquals("Earth", inventory.getInventory().get(1).getItemType().getTypeString());
    }

    @Test
    void testToStringNoItem() {
        assertEquals("", inventory.toString());
    }

    @Test
    void testToStringOneItem() {
        Item item = new Item("Dandelion", 0, 1, new Plant());
        inventory.addItem(item);
        assertEquals("Dandelion (Attack: 0, Weight: 1, Type: Plant)<br/>", inventory.toString());
    }

    @Test
    void testToStringTwoItem() {
        Item item1 = new Item("Dandelion", 0, 1, new Plant());
        Item item2 = new Item("Rock", 8, 8, new Earth());
        inventory.addItem(item1);
        inventory.addItem(item2);
        assertEquals("Dandelion (Attack: 0, Weight: 1, Type: Plant)<br/>" +
                "Rock (Attack: 8, Weight: 8, Type: Earth)<br/>", inventory.toString());
    }

    @Test
    void testParseInventoryEmpty() {
        ArrayList<String> testStringList = new ArrayList<>();

        Inventory testInventory = Inventory.parseInventory(testStringList);
        assertEquals(0, testInventory.getInventory().size());
    }

    @Test
    void testParseInventory() {
        ArrayList<String> testStringList = new ArrayList<>();
        testStringList.add("Fist:1:0:Normal");
        testStringList.add("Stick:7:2:Plant");

        Inventory testInventory = Inventory.parseInventory(testStringList);
        assertEquals(2, testInventory.getInventory().size());

        assertEquals("Fist", testInventory.getInventory().get(0).getItemName());
        assertEquals(1, testInventory.getInventory().get(0).getAttackStat());
        assertEquals(0, testInventory.getInventory().get(0).getItemWeight());
        assertEquals("Normal", testInventory.getInventory().get(0).getItemType().getTypeString());

        assertEquals("Stick", testInventory.getInventory().get(1).getItemName());
        assertEquals(7, testInventory.getInventory().get(1).getAttackStat());
        assertEquals(2, testInventory.getInventory().get(1).getItemWeight());
        assertEquals("Plant", testInventory.getInventory().get(1).getItemType().getTypeString());
    }

    @Test
    void testGetInventoryItemStringsEmpty() {
        assertEquals("", inventory.getInventoryItemStrings());
    }

    @Test
    void testGetInventoryItemStringsTwoItems() {
        inventory.addItem(new Item("Wand", 10, 10, new Fire()));
        inventory.addItem(new Item("Cup", 0, 1, new Earth()));
        assertEquals("0Wand<br/>1Cup<br/>", inventory.getInventoryItemStrings());
    }
}