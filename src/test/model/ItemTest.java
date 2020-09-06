package model;

import model.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;

    @BeforeEach
    void runBefore() {
        item = new Item("Dandelion", 0, 1, new Plant());
    }

    @Test
    void testConstructor() {
        assertEquals(0, item.getAttackStat());
        assertEquals(1, item.getItemWeight());
        assertEquals("Dandelion", item.getItemName());
        assertEquals("Plant", item.getItemType().getTypeString());
    }

    @Test
    void testGetAttackStat() {
        assertEquals(0, item.getAttackStat());
    }

    @Test
    void testGetItemWeight() {
        assertEquals(1, item.getItemWeight());
    }

    @Test
    void testGetItemName() {
        assertEquals("Dandelion", item.getItemName());
    }

    @Test
    void testGetItemType() {
        assertEquals("Plant", item.getItemType().getTypeString());
    }

    @Test
    void testToString() {
        assertEquals("Dandelion (Attack: 0, Weight: 1, Type: Plant)", item.toString());
    }

    @Test
    void testParseItem() {
        ArrayList<String> testStringList = new ArrayList<>();
        testStringList.add("Fist");
        testStringList.add("1");
        testStringList.add("0");
        testStringList.add("Normal");

        Item testItem = Item.parseItem(testStringList);

        assertEquals("Fist", testItem.getItemName());
        assertEquals(1, testItem.getAttackStat());
        assertEquals(0, testItem.getItemWeight());
        assertEquals("Normal", testItem.getItemType().getTypeString());
    }
}
