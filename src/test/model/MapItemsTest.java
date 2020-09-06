package model;

import model.types.Normal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapItemsTest {
    MapItems mapItems;

    @BeforeEach
    void runBefore() {
        mapItems = new MapItems();
    }

    @Test
    void testConstructor() {
        assertEquals(0, mapItems.getItemMap().size());
    }

    @Test
    void testAddItemMap() {
        mapItems.addItemMap(new Cell(0,0), new Item("Glove", 0, 1, new Normal()));
        assertEquals("Glove", mapItems.getItemMap().get(new Cell(0,0)).getItemName());
        assertEquals(0, mapItems.getItemMap().get(new Cell(0,0)).getAttackStat());
        assertEquals(1, mapItems.getItemMap().get(new Cell(0,0)).getItemWeight());
        assertEquals("Normal",
                mapItems.getItemMap().get(new Cell(0,0)).getItemType().getTypeString());
    }
}
