package model.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaterTest {
    private Type water = new Water();

    @Test
    void testAttackMultiplierOnFire() {
        assertEquals(2, water.attackMultiplier(Type.Element.FIRE));
    }

    @Test
    void testAttackMultiplierOnEarth() {
        assertEquals(1, water.attackMultiplier(Type.Element.EARTH));
    }

    @Test
    void testAttackMultiplierOnWater() {
        assertEquals(0.5, water.attackMultiplier(Type.Element.WATER));
    }

    @Test
    void testAttackMultiplierOnNormal() {
        assertEquals(1, water.attackMultiplier(Type.Element.NORMAL));
    }

    @Test
    void testAttackMultiplierOnPlant() {
        assertEquals(0.25, water.attackMultiplier(Type.Element.PLANT));
    }
}
