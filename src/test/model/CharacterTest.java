package model;

import model.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Character character;

    @BeforeEach
    void runBefore() {
        character = new Character("Gregor", 100, 10, 100, new Normal(), new Inventory(),
                new Item("Fist", 1, 0, new Normal()), new Cell(0, 0));
    }

    @Test
    void testConstructor() {
        assertEquals("Gregor", character.getName());
        assertEquals(100, character.getHealth());
        assertEquals(10, character.getStrength());
        assertEquals(100, character.getStamina());
        assertEquals(Type.Element.NORMAL, character.getCharacterType().getElement());
        assertEquals(0, character.getInventory().getInventory().size());
        assertEquals("Fist", character.getEquippedWeapon().getItemName());
        assertEquals(1, character.getEquippedWeapon().getAttackStat());
        assertEquals(0, character.getEquippedWeapon().getItemWeight());
        assertEquals(Type.Element.NORMAL, character.getEquippedWeapon().getItemType().getElement());
    }

    @Test
    void testGetUsername() {
        assertEquals("Gregor", character.getName());
    }

    @Test
    void testGetHealth() {
        assertEquals(100, character.getHealth());
    }

    @Test
    void testGetStrength() {
        assertEquals(10, character.getStrength());
    }

    @Test
    void testGetStamina() {
        assertEquals(100, character.getStamina());
    }

    @Test
    void testGetUserType() {
        assertEquals(Type.Element.NORMAL, character.getCharacterType().getElement());
    }

    @Test
    void testGetEquippedWeapon() {
        assertEquals("Fist", character.getEquippedWeapon().getItemName());
        assertEquals(1, character.getEquippedWeapon().getAttackStat());
        assertEquals(0, character.getEquippedWeapon().getItemWeight());
        assertEquals(Type.Element.NORMAL, character.getEquippedWeapon().getItemType().getElement());
    }

    @Test
    void testGetInventory() {
        Item item = new Item("Flower", 1, 2, new Plant());
        assertEquals("Flower", item.getItemName());
        assertEquals(1, item.getAttackStat());
        assertEquals(2, item.getItemWeight());
        assertEquals(Type.Element.PLANT, item.getItemType().getElement());
    }

    @Test
    void testGetLocation() {
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
    }

    @Test
    void testSetName() {
        character.setName("Patrice");
        assertEquals("Patrice", character.getName());
    }

    @Test
    void testSetHealthNone() {
        character.setHealth(0);
        assertTrue(character.isDead());
        assertEquals(0, character.getHealth());
    }

    @Test
    void testSetHealthOther() {
        character.setHealth(110);
        assertFalse(character.isDead());
        assertEquals(110, character.getHealth());
    }

    @Test
    void testSetStrengthNone() {
        character.setStrength(0);
        assertEquals(0, character.getStrength());
    }

    @Test
    void testSetStrengthOther() {
        character.setStrength(50);
        assertEquals(50, character.getStrength());
    }

    @Test
    void testSetUserType() {
        character.setCharacterType(new Fire());
        assertEquals(Type.Element.FIRE, character.getCharacterType().getElement());
    }

    @Test
    void testSetLocation() {
        character.setLocation(new Cell(2,3));
        assertEquals(2, character.getLocation().getRow());
        assertEquals(3, character.getLocation().getColumn());
    }

    @Test
    void testDecreaseHealthNone() {
        character.decreaseHealth(0);
        assertEquals(100, character.getHealth());
    }

    @Test
    void testDecreaseHealthOnce() {
        character.decreaseHealth(10);
        assertEquals(90, character.getHealth());
    }

    @Test
    void testDecreaseHealthMultiple() {
        character.decreaseHealth(10);
        character.decreaseHealth(100);
        assertEquals(-10, character.getHealth());
    }

    @Test
    void testIsDeadFullHealth() {
        assertFalse(character.isDead());
    }

    @Test
    void testIsDeadAlmostDead() {
        character.decreaseHealth(99);
        assertFalse(character.isDead());
    }

    @Test
    void testIsDeadExactlyNoHealth() {
        character.decreaseHealth(100);
        assertTrue(character.isDead());
    }

    @Test
    void testIsDeadOverkill() {
        character.decreaseHealth(101);
        assertTrue(character.isDead());
    }

    @Test
    void testDecreaseStaminaNone() {
        character.decreaseStamina(0);
        assertEquals(100, character.getStamina());
    }

    @Test
    void testDecreaseStaminaOnce() {
        character.decreaseStamina(10);
        assertEquals(90, character.getStamina());
    }

    @Test
    void testDecreaseStaminaMultiple() {
        character.decreaseStamina(10);
        character.decreaseStamina(100);
        assertEquals(-10, character.getStamina());
    }

    @Test
    void testIsExhaustedFullHealth() {
        assertFalse(character.isExhausted());
    }

    @Test
    void testIsExhaustedAlmostExhausted() {
        character.decreaseStamina(99);
        assertFalse(character.isExhausted());
    }

    @Test
    void testIsExhaustedExactlyNoStamina() {
        character.decreaseStamina(100);
        assertTrue(character.isExhausted());
    }

    @Test
    void testIsDeadOverExhausted() {
        character.decreaseStamina(101);
        assertTrue(character.isExhausted());
    }

    @Test
    void testWeakAttackDamageWithZeroAttackWeapon() {
        Item item = new Item("Rose", 0, 1, new Plant());
        character.setEquippedWeapon(item);
        assertEquals(0, character.attackDamage("1", new Normal()));
    }

    @Test
    void testStrongAttackDamageWithZeroAttackWeapon() {
        Item item = new Item("Rose", 0, 1, new Plant());
        character.setEquippedWeapon(item);
        assertEquals(0, character.attackDamage("2", new Plant()));
    }


    @Test
    void testWeakAttackDamageWithZeroStrength() {
        character.setStrength(0);
        assertEquals(0, character.attackDamage("1", new Water()));
        assertEquals(100, character.getStamina());
    }

    @Test
    void testStrongAttackDamageWithZeroStrength() {
        character.setStrength(0);
        assertEquals(0, character.attackDamage("2", new Earth()));
        assertEquals(100, character.getStamina());
    }

    @Test
    void testWeakAttackDamageWithFist() {
        assertEquals(2, character.attackDamage("1", new Fire()));
        assertEquals(100, character.getStamina());
    }

    @Test
    void testStrongAttackDamageWithFist() {
        assertEquals(3, character.attackDamage("2", new Fire()));
        assertEquals(100, character.getStamina());
    }

    @Test
    void testWeakAttackDamageWithNewWeapon() {
        Item weapon = new Item("Big Stick", 10, 5, new Normal());
        character.setEquippedWeapon(weapon);
        assertEquals(30, character.attackDamage("1", new Normal()));
        assertEquals(95, character.getStamina());
    }

    @Test
    void testStrongAttackDamageWithNewWeapon() {
        Item weapon = new Item("Big Stick", 10, 5, new Normal());
        character.setEquippedWeapon(weapon);
        assertEquals(30, character.attackDamage("2", new Water()));
        assertEquals(90, character.getStamina());
    }

    @Test
    void testWeakAttackDamageAlmostExhausted() {
        Item weapon = new Item("Big Stick", 10, 5, new Normal());
        character.setEquippedWeapon(weapon);
        character.decreaseStamina(99);
        assertEquals(15, character.attackDamage("1", new Fire()));
        assertEquals(-4, character.getStamina());
    }

    @Test
    void testStrongAttackDamageAlmostExhausted() {
        Item weapon = new Item("Big Stick", 10, 5, new Normal());
        character.setEquippedWeapon(weapon);
        character.decreaseStamina(99);
        assertEquals(30, character.attackDamage("2", new Fire()));
        assertEquals(-9, character.getStamina());
    }

    @Test
    void testWeakAttackDamageExhausted() {
        Item weapon = new Item("Big Stick", 10, 5, new Normal());
        character.setEquippedWeapon(weapon);
        character.decreaseStamina(100);
        assertEquals(0, character.attackDamage("1", new Plant()));
        assertEquals(0, character.getStamina());
    }

    @Test
    void testStrongAttackDamageExhausted() {
        Item weapon = new Item("Big Stick", 10, 5, new Normal());
        character.setEquippedWeapon(weapon);
        character.decreaseStamina(100);
        assertEquals(0, character.attackDamage("2", new Fire()));
        assertEquals(0, character.getStamina());
    }

    @Test
    void testSetNewEquippedWeapon() {
        Item weapon = new Item("Bow", 25, 7, new Normal());
        character.setEquippedWeapon(weapon);
        assertEquals("Bow", character.getEquippedWeapon().getItemName());
        assertEquals(25, character.getEquippedWeapon().getAttackStat());
        assertEquals(7, character.getEquippedWeapon().getItemWeight());
    }

    @Test
    void testAddInventoryOnce() {
        Item item = new Item("Fish", 2, 1, new Water());
        character.addInventory(item);
        assertEquals(1, character.getInventory().getInventory().size());
        assertEquals("Fish", character.getInventory().getInventory().get(0).getItemName());
        assertEquals(2, character.getInventory().getInventory().get(0).getAttackStat());
        assertEquals(1, character.getInventory().getInventory().get(0).getItemWeight());
    }

    @Test
    void testAddInventoryTwice() {
        Item item1 = new Item("Fish", 2, 1, new Water());
        Item item2 = new Item("Rose", 0, 1, new Plant());
        character.addInventory(item1);
        character.addInventory(item2);
        assertEquals(2, character.getInventory().getInventory().size());
        assertEquals("Fish", character.getInventory().getInventory().get(0).getItemName());
        assertEquals(2, character.getInventory().getInventory().get(0).getAttackStat());
        assertEquals(1, character.getInventory().getInventory().get(0).getItemWeight());
        assertEquals(Type.Element.WATER, character.getInventory().getInventory().get(0).getItemType().getElement());
        assertEquals("Rose", character.getInventory().getInventory().get(1).getItemName());
        assertEquals(0, character.getInventory().getInventory().get(1).getAttackStat());
        assertEquals(1, character.getInventory().getInventory().get(1).getItemWeight());
        assertEquals(Type.Element.PLANT, character.getInventory().getInventory().get(1).getItemType().getElement());
    }

    @Test
    void testEquipWeaponThatUserHasInFirstPosition() {
        Item item1 = new Item("Rose", 0, 1, new Plant());
        Item item2 = new Item("Fish", 2, 1, new Water());
        character.addInventory(item1);
        character.addInventory(item2);
        character.equipWeapon("Rose");
        assertEquals("Rose", character.getEquippedWeapon().getItemName());
        assertEquals(0, character.getEquippedWeapon().getAttackStat());
        assertEquals(1, character.getEquippedWeapon().getItemWeight());
        assertEquals(2, character.getInventory().getInventory().size());
        assertEquals("Fist", character.getInventory().getInventory().get(1).getItemName());
    }

    @Test
    void testEquipWeaponThatUserHas() {
        Item item1 = new Item("Rose", 0, 1, new Plant());
        Item item2 = new Item("Fish", 2, 1, new Water());
        character.addInventory(item1);
        character.addInventory(item2);
        character.equipWeapon("Fish");
        assertEquals("Fish", character.getEquippedWeapon().getItemName());
        assertEquals(2, character.getEquippedWeapon().getAttackStat());
        assertEquals(1, character.getEquippedWeapon().getItemWeight());
        assertEquals(2, character.getInventory().getInventory().size());
        assertEquals("Fist", character.getInventory().getInventory().get(1).getItemName());
    }

    @Test
    void testEquipWeaponThatUserDoesNotHave() {
        Item item1 = new Item("Rose", 0, 1, new Plant());
        Item item2 = new Item("Fish", 2, 1, new Water());
        character.addInventory(item1);
        character.addInventory(item2);
        character.equipWeapon("fish");
        assertEquals("Fist", character.getEquippedWeapon().getItemName());
        assertEquals(1, character.getEquippedWeapon().getAttackStat());
        assertEquals(0, character.getEquippedWeapon().getItemWeight());
        assertEquals(2, character.getInventory().getInventory().size());
        assertEquals(Type.Element.NORMAL, character.getEquippedWeapon().getItemType().getElement());
        assertEquals("Fish", character.getInventory().getInventory().get(1).getItemName());
    }

    @Test
    void testToStringEmptyInventory() {
        String string = "<html>Name: Gregor<br/>" +
                "Health: 100<br/>" +
                "Strength: 10<br/>" +
                "Stamina: 100<br/>" +
                "User Type: Normal<br/>" +
                "Equipped Weapon:<br/>Fist (Attack: 1, Weight: 0, Type: Normal)<br/><br/>" +
                "Inventory:<br/>";
        assertEquals(string, character.toString());
    }

    @Test
    void testToStringFullerInventory() {
        Item item1 = new Item("Fish", 2, 1, new Water());
        Item item2 = new Item("Rose", 0, 1, new Plant());
        character.addInventory(item1);
        character.addInventory(item2);
        String userString = "<html>Name: Gregor<br/>" +
                "Health: 100<br/>" +
                "Strength: 10<br/>" +
                "Stamina: 100<br/>" +
                "User Type: Normal<br/>" +
                "Equipped Weapon:<br/>Fist (Attack: 1, Weight: 0, Type: Normal)<br/><br/>" +
                "Inventory:<br/>" +
                "Fish (Attack: 2, Weight: 1, Type: Water)<br/>" +
                "Rose (Attack: 0, Weight: 1, Type: Plant)<br/>";
        assertEquals(userString, character.toString());
    }

    @Test
    void testMoveCharacterRight() {
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
        character.moveCharacter(Direction.RIGHT);
        assertEquals(0, character.getLocation().getRow());
        assertEquals(1, character.getLocation().getColumn());
    }

    @Test
    void testMoveCharacterLeft() {
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
        character.moveCharacter(Direction.RIGHT);
        assertEquals(0, character.getLocation().getRow());
        assertEquals(1, character.getLocation().getColumn());
        character.moveCharacter(Direction.LEFT);
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
    }

    @Test
    void testMoveCharacterUp() {
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
        character.moveCharacter(Direction.DOWN);
        assertEquals(1, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
        character.moveCharacter(Direction.UP);
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
    }

    @Test
    void testMoveCharacterDown() {
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
        character.moveCharacter(Direction.DOWN);
        assertEquals(1, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
    }

    @Test
    void testMoveCharacterEachDirection() {
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
        character.moveCharacter(Direction.DOWN);
        assertEquals(1, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
        character.moveCharacter(Direction.RIGHT);
        assertEquals(1, character.getLocation().getRow());
        assertEquals(1, character.getLocation().getColumn());
        character.moveCharacter(Direction.UP);
        assertEquals(0, character.getLocation().getRow());
        assertEquals(1, character.getLocation().getColumn());
        character.moveCharacter(Direction.LEFT);
        assertEquals(0, character.getLocation().getRow());
        assertEquals(0, character.getLocation().getColumn());
    }
}