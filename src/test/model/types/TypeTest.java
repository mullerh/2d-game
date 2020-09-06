package model.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeTest {

    @Test
    void testGetTypeNormal() {
        Type type = new Normal();
        assertEquals(Type.Element.NORMAL, type.getElement());
    }

    @Test
    void testGetTypePlant() {
        Type type = new Plant();
        assertEquals(Type.Element.PLANT, type.getElement());
    }

    @Test
    void testGetTypeEarth() {
        Type type = new Earth();
        assertEquals(Type.Element.EARTH, type.getElement());
    }

    @Test
    void testGetTypeWater() {
        Type type = new Water();
        assertEquals(Type.Element.WATER, type.getElement());
    }

    @Test
    void testGetTypeFire() {
        Type type = new Fire();
        assertEquals(Type.Element.FIRE, type.getElement());
    }

    @Test
    void testGetTypeStringFire() {
        Type type = new Fire();
        assertEquals("Fire", type.getTypeString());
    }

    @Test
    void testGetTypeStringEarth() {
        Type type = new Earth();
        assertEquals("Earth", type.getTypeString());
    }

    @Test
    void testGetTypeStringWater() {
        Type type = new Water();
        assertEquals("Water", type.getTypeString());
    }

    @Test
    void testGetTypeStringPlant() {
        Type type = new Plant();
        assertEquals("Plant", type.getTypeString());
    }

    @Test
    void testGetTypeStringNormal() {
        Type type = new Normal();
        assertEquals("Normal", type.getTypeString());
    }

    @Test
    void testParseItemTypeFire() {
        assertEquals(Type.Element.FIRE, Type.parseItemType("Fire").getElement());
    }

    @Test
    void testParseItemTypeEarth() {
        assertEquals(Type.Element.EARTH, Type.parseItemType("Earth").getElement());
    }

    @Test
    void testParseItemTypeWater() {
        assertEquals(Type.Element.WATER, Type.parseItemType("Water").getElement());
    }

    @Test
    void testParseItemTypePlant() {
        assertEquals(Type.Element.PLANT, Type.parseItemType("Plant").getElement());
    }

    @Test
    void testParseItemTypeNormal() {
        assertEquals(Type.Element.NORMAL, Type.parseItemType("Normal").getElement());
    }
}
