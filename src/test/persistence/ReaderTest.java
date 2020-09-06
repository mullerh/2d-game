package persistence;

import model.Cell;
import model.MapItems;
import model.types.Type;
import org.junit.jupiter.api.Test;
import model.Character;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp ReaderTest class
public class ReaderTest {

    @Test
    void testConstructor() {
        new Reader();
    }

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp ReaderTest class' testParseAccountsFile1
    //                  method
    @Test
    void testParseCharacterSaveFile1() {
        try {
            Character character = Reader.readCharacter(new File("./data/testSave1.txt"));

            assertEquals("Herman", character.getName());
            assertEquals(95, character.getHealth());
            assertEquals(10, character.getStrength());
            assertEquals(90, character.getStamina());
            assertEquals(Type.Element.NORMAL, character.getCharacterType().getElement());
            assertEquals(new Cell(0,0), character.getLocation());
            //inventory check
            assertEquals(0, character.getInventory().getInventory().size());
            //EquippedWeapon check
            assertEquals("Fist", character.getEquippedWeapon().getItemName());
            assertEquals(1, character.getEquippedWeapon().getAttackStat());
            assertEquals(0, character.getEquippedWeapon().getItemWeight());
            assertEquals(Type.Element.NORMAL, character.getEquippedWeapon().getItemType().getElement());

        //catch and fail exception
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp ReaderTest class' testParseAccountsFile2
    //                  method
    @Test
    void testParseCharacterSaveFile2() {
        try {
            Character character = Reader.readCharacter(new File("./data/testSave2.txt"));

            assertEquals("Gregor", character.getName());
            assertEquals(39, character.getHealth());
            assertEquals(10, character.getStrength());
            assertEquals(79, character.getStamina());
            assertEquals(Type.Element.FIRE, character.getCharacterType().getElement());
            assertEquals(new Cell(0,1), character.getLocation());
            //inventory check
            assertEquals(2, character.getInventory().getInventory().size());
            assertEquals("Fist", character.getInventory().getInventory().get(0).getItemName());
            assertEquals(1, character.getInventory().getInventory().get(0).getAttackStat());
            assertEquals(0, character.getInventory().getInventory().get(0).getItemWeight());
            assertEquals(Type.Element.NORMAL,
                    character.getInventory().getInventory().get(0).getItemType().getElement());
            assertEquals("Stick", character.getInventory().getInventory().get(1).getItemName());
            assertEquals(7, character.getInventory().getInventory().get(1).getAttackStat());
            assertEquals(2, character.getInventory().getInventory().get(1).getItemWeight());
            assertEquals(Type.Element.PLANT,
                    character.getInventory().getInventory().get(1).getItemType().getElement());
            //EquippedWeapon check
            assertEquals("PokieStick", character.getEquippedWeapon().getItemName());
            assertEquals(10, character.getEquippedWeapon().getAttackStat());
            assertEquals(2, character.getEquippedWeapon().getItemWeight());
            assertEquals(Type.Element.PLANT, character.getEquippedWeapon().getItemType().getElement());

        //catch and fail exception
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp ReaderTest class' testParseAccountsFile1
    //                  method
    @Test
    void testParseMapItemsSaveFile1() {
        try {
            MapItems items = Reader.readMapItems(new File("./data/testSave1.txt"));

            assertEquals(0, items.getItemMap().size());

            //catch and fail exception
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp ReaderTest class' testParseAccountsFile2
    //                  method
    @Test
    void testParseMapItemsSaveFile2() {
        try {
            MapItems items = Reader.readMapItems(new File("./data/testSave2.txt"));

            assertEquals(2, items.getItemMap().size());

            //first item
            assertEquals("Stick", items.getItemMap().get(new Cell(0, 1)).getItemName());
            assertEquals(10, items.getItemMap().get(new Cell(0, 1)).getAttackStat());
            assertEquals(5, items.getItemMap().get(new Cell(0, 1)).getItemWeight());
            assertEquals(Type.Element.PLANT,
                    items.getItemMap().get(new Cell(0, 1)).getItemType().getElement());

            //second item
            assertEquals("Flower", items.getItemMap().get(new Cell(8, 1)).getItemName());
            assertEquals(3, items.getItemMap().get(new Cell(8, 1)).getAttackStat());
            assertEquals(2, items.getItemMap().get(new Cell(8, 1)).getItemWeight());
            assertEquals(Type.Element.PLANT,
                    items.getItemMap().get(new Cell(8, 1)).getItemType().getElement());

            //catch and fail exception
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp ReaderTest class' testIOException method
    @Test
    void testIOException() {
        try {
            Reader.readCharacter(new File("./path/does/not/exist/testSave.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
