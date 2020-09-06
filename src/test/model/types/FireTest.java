package model.types;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FireTest {
    private Type fire = new Fire();

    @Test
    void testAttackMultiplierOnFire() {
        assertEquals(0.5, fire.attackMultiplier(Type.Element.FIRE));
    }

    @Test
    void testAttackMultiplierOnEarth() {
        assertEquals(1, fire.attackMultiplier(Type.Element.EARTH));
    }

    @Test
    void testAttackMultiplierOnWater() {
        assertEquals(0.25, fire.attackMultiplier(Type.Element.WATER));
    }

    @Test
    void testAttackMultiplierOnNormal() {
        assertEquals(1, fire.attackMultiplier(Type.Element.NORMAL));
    }

    @Test
    void testAttackMultiplierOnPlant() {
        assertEquals(2, fire.attackMultiplier(Type.Element.PLANT));
    }
}
