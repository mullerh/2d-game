package model.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlantTest {
    private Type plant = new Plant();

    @Test
    void testAttackMultiplierOnFire() {
        assertEquals(0.25, plant.attackMultiplier(Type.Element.FIRE));
    }

    @Test
    void testAttackMultiplierOnEarth() {
        assertEquals(2, plant.attackMultiplier(Type.Element.EARTH));
    }

    @Test
    void testAttackMultiplierOnWater() {
        assertEquals(2, plant.attackMultiplier(Type.Element.WATER));
    }

    @Test
    void testAttackMultiplierOnNormal() {
        assertEquals(1, plant.attackMultiplier(Type.Element.NORMAL));
    }

    @Test
    void testAttackMultiplierOnPlant() {
        assertEquals(0.5, plant.attackMultiplier(Type.Element.PLANT));
    }
}
