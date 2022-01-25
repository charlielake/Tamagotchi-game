package persistence;

import model.Cat;
import model.Dog;
import model.ListOfPets;
import model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testTamagotchi.txt";
    private Writer testWriter;
    private model.Pet pet1;
    private Pet pet2;
    private ListOfPets listOfPets;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        pet1 = new Cat("Cat");
        pet1.setEnergy(70);
        pet1.setHappiness(20);
        pet1.setHunger(0);
        pet2 = new Dog("Dog");
        listOfPets = new ListOfPets();
        listOfPets.addPet(pet1);
        listOfPets.addPet(pet2);
    }

    @Test
    void testWriteTamagotchi() {
        // save pets in list to file
        testWriter.write(pet1);
        testWriter.write(pet2);
        testWriter.close();

        // read files to ensure pets have expected values
        try {
            ListOfPets listOfPets1 = Reader.readTamagotchiApp(new File(TEST_FILE));

            Pet pet1 = listOfPets1.getListOfPets().get(0);
            assertEquals("Cat", pet1.checkName());
            assertEquals(0, pet1.checkHunger());
            assertEquals(20, pet1.checkHappiness());
            assertEquals(70, pet1.checkEnergy());
            assertEquals("healthy", pet1.checkHealth());
            assertEquals("Cat", pet1.petType);

            Pet pet2 = listOfPets1.getListOfPets().get(1);
            assertEquals("Dog", pet2.checkName());
            assertEquals(50, pet2.checkHunger());
            assertEquals(50, pet2.checkHappiness());
            assertEquals(100, pet2.checkEnergy());
            assertEquals("healthy", pet2.checkHealth());
            assertEquals("Dog", pet2.petType);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
