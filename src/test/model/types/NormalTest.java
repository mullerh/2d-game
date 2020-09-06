package model.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalTest {
    private Type normal = new Normal();

    @Test
    void testAttackMultiplierOnFire() {
        assertEquals(0.75, normal.attackMultiplier(Type.Element.FIRE));
    }

    @Test
    void testAttackMultiplierOnEarth() {
        assertEquals(0.75, normal.attackMultiplier(Type.Element.EARTH));
    }

    @Test
    void testAttackMultiplierOnWater() {
        assertEquals(0.75, normal.attackMultiplier(Type.Element.WATER));
    }

    @Test
    void testAttackMultiplierOnNormal() {
        assertEquals(1.5, normal.attackMultiplier(Type.Element.NORMAL));
    }

    @Test
    void testAttackMultiplierOnPlant() {
        assertEquals(0.75, normal.attackMultiplier(Type.Element.PLANT));
    }
}
