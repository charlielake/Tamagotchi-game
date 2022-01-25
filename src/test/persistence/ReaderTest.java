package persistence;

import model.ListOfPets;
import model.Pet;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {


    @Test
    void testParseTamagotchiFile1() {
        try {
            Reader reader = new Reader();
            ListOfPets listOfPets = Reader.readTamagotchiApp(new File("./data/testTamagotchiFile1.txt"));
            Pet pet1 = listOfPets.getListOfPets().get(0);
            assertEquals("Cat", pet1.checkName());
            assertEquals(20, pet1.checkHunger());
            assertEquals(40, pet1.checkHappiness());
            assertEquals(50, pet1.checkEnergy());
            assertEquals("healthy", pet1.checkHealth());
            assertEquals("Cat", pet1.petType);

            Pet pet2 = listOfPets.getListOfPets().get(1);
            assertEquals("Dog", pet2.checkName());
            assertEquals(50, pet2.checkHunger());
            assertEquals(50, pet2.checkHappiness());
            assertEquals(60, pet2.checkEnergy());
            assertEquals("healthy", pet2.checkHealth());
            assertEquals("Dog", pet2.petType);

        } catch (IOException e) {
        fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseTamagotchiFile2() {
        try {
            ListOfPets listOfPets = Reader.readTamagotchiApp(new File("./data/testTamagotchiFile2.txt"));
            Pet pet1 = listOfPets.getListOfPets().get(0);
            assertEquals("Dog", pet1.checkName());
            assertEquals(20, pet1.checkHunger());
            assertEquals(20, pet1.checkHappiness());
            assertEquals(20, pet1.checkEnergy());
            assertEquals("sick", pet1.checkHealth());
            assertEquals("Dog", pet1.petType);

            Pet pet2 = listOfPets.getListOfPets().get(1);
            assertEquals("Dog", pet2.checkName());
            assertEquals(50, pet2.checkHunger());
            assertEquals(50, pet2.checkHappiness());
            assertEquals(50, pet2.checkEnergy());
            assertEquals("healthy", pet2.checkHealth());
            assertEquals("Dog", pet2.petType);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readTamagotchiApp(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
