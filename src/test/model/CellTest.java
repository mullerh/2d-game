package model;

import model.types.Fire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    Cell cell;

    @BeforeEach
    void runBefore() {
        cell = new Cell(1,2);
    }

    @Test
    void testConstructor() {
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getColumn());
    }

    @Test
    void testGetScreenHorizontalCoord() {
        assertEquals(cell.getColumn() * Cell.CELL_PIXELS, cell.getScreenHorizontalCoord());
    }

    @Test
    void testGetScreenVerticalCoord() {
        assertEquals(cell.getRow() * Cell.CELL_PIXELS, cell.getScreenVerticalCoord());
    }

    @Test
    void testEqualsDifferentColumn() {
        Cell testCell = new Cell(1,1);
        assertNotEquals(cell, testCell);
    }

    @Test
    void testEqualsDifferentRow() {
        Cell testCell = new Cell(1,2);
        assertEquals(cell, testCell);
    }

    @Test
    void testEqualsDifferentRowAndColumn() {
        Cell testCell = new Cell(0,0);
        assertNotEquals(cell, testCell);
    }

    @Test
    void testEqualsSameDifferentRowAndColumn() {
        Cell testCell = new Cell(0,0);
        assertNotEquals(cell, testCell);
    }

    @Test
    void testEqualsSameRowAndColumn() {
        Cell testCell = new Cell(1,2);
        assertEquals(testCell, cell);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(cell, cell);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(cell, null);
    }

    @Test
    void testEqualsDifferentTypes() {
        assertNotEquals(cell, new Fire());
    }

    @Test
    void testHashCode() {
        assertEquals(63, cell.hashCode());
    }
}
