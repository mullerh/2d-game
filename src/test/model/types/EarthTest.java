package model.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarthTest {
    private Type earth = new Earth();

    @Test
    void testAttackMultiplierOnFire() {
        assertEquals(2, earth.attackMultiplier(Type.Element.FIRE));
    }

    @Test
    void testAttackMultiplierOnEarth() {
        assertEquals(0.5, earth.attackMultiplier(Type.Element.EARTH));
    }

    @Test
    void testAttackMultiplierOnWater() {
        assertEquals(1, earth.attackMultiplier(Type.Element.WATER));
    }

    @Test
    void testAttackMultiplierOnNormal() {
        assertEquals(1, earth.attackMultiplier(Type.Element.NORMAL));
    }

    @Test
    void testAttackMultiplierOnPlant() {
        assertEquals(0.25, earth.attackMultiplier(Type.Element.PLANT));
    }
}
