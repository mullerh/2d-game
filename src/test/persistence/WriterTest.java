package persistence;

import model.Character;
import model.MapItems;
import model.Cell;
import model.Item;
import model.Inventory;
import model.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//credit for test class: https://github.students.cs.ubc.ca/CPSC210/TellerApp WriterTest class
public class WriterTest {
    private static final String TEST_FILE = "./data/testSaves.txt";
    private Writer testWriter;
    private MapItems items;
    private Character character;

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp WriterTest class' runBefore method
    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));

        //creates a character so the tests can test the Writer class by attempting to write the character to save file
        character = new Character("Herman", 50, 20, 100, new Earth(), new Inventory(),
                new Item("Branch", 15, 16, new Water()), new Cell(0,1));
        character.addInventory(new Item("Flower", 3, 2, new Plant()));

        //creates a list of MapItems so the tests can test the Writer class by attempting to write the MapItems to
        //the save file
        items = new MapItems();
        items.addItemMap(new Cell(0,1), new Item("Shoe", 10, 25, new Earth()));
        items.addItemMap(new Cell(10,15), new Item("Pillow", 1, 555, new Water()));
    }

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp WriterTest class' testWriteAccounts method
    @Test
    void testWriteCharacter() {
        //save character to file
        testWriter.write(character);
        testWriter.close();

        //verify by reading them back against expected results
        try {
            Character character = Reader.readCharacter(new File(TEST_FILE));

            assertEquals("Herman", character.getName());
            assertEquals(50, character.getHealth());
            assertEquals(20, character.getStrength());
            assertEquals(100, character.getStamina());
            assertEquals(Type.Element.EARTH, character.getCharacterType().getElement());
            assertEquals(0, character.getLocation().getRow());
            assertEquals(1, character.getLocation().getColumn());
            //Inventory Test
            assertEquals(1, character.getInventory().getInventory().size());
            assertEquals("Flower", character.getInventory().getInventory().get(0).getItemName());
            assertEquals(3, character.getInventory().getInventory().get(0).getAttackStat());
            assertEquals(2, character.getInventory().getInventory().get(0).getItemWeight());
            assertEquals(Type.Element.PLANT,
                    character.getInventory().getInventory().get(0).getItemType().getElement());
            //EquippedWeapon Test
            assertEquals("Branch", character.getEquippedWeapon().getItemName());
            assertEquals(15, character.getEquippedWeapon().getAttackStat());
            assertEquals(16, character.getEquippedWeapon().getItemWeight());
            assertEquals(Type.Element.WATER, character.getEquippedWeapon().getItemType().getElement());

        //catch and fail exception
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    //credit: based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp WriterTest class' testWriteAccounts method
    @Test
    void testWriteMapItems() {
        //save items to file (have to save character first, since it skips the first line anticipating it to be a
        //character)
        testWriter.write(character);
        testWriter.write(items);
        testWriter.close();

        //verify by reading them back against expected results
        try {
            MapItems items = Reader.readMapItems(new File(TEST_FILE));

            //first item
            assertEquals("Shoe", items.getItemMap().get(new Cell(0,1)).getItemName());
            assertEquals(10, items.getItemMap().get(new Cell(0,1)).getAttackStat());
            assertEquals(25, items.getItemMap().get(new Cell(0,1)).getItemWeight());
            assertEquals(Type.Element.EARTH,
                    items.getItemMap().get(new Cell(0,1)).getItemType().getElement());

            //second item
            assertEquals("Pillow", items.getItemMap().get(new Cell(10,15)).getItemName());
            assertEquals(1, items.getItemMap().get(new Cell(10,15)).getAttackStat());
            assertEquals(555, items.getItemMap().get(new Cell(10,15)).getItemWeight());
            assertEquals(Type.Element.WATER,
                    items.getItemMap().get(new Cell(10,15)).getItemType().getElement());

            //catch and fail exception
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
