package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Pet.MAX_ENERGY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatTest {
    Cat testCat1;

    @BeforeEach
    public void runBefore() {
        testCat1 = new Cat("Cat!");
    }


    @Test
    public void testConstructor() {
        Cat testCat = new Cat("Molly");
        assertEquals("Molly", testCat.checkName());
        assertEquals(50, testCat.checkHappiness());
        assertEquals(50, testCat.checkHunger());
        assertEquals(MAX_ENERGY, testCat.checkEnergy());
        assertEquals("healthy", testCat.checkHealth());
        assertEquals("Cat", testCat.getClass().getSimpleName());
    }

    @Test
    public void testLaserPointer() {
        testCat1.laserPointer();
        assertEquals(80, testCat1.checkEnergy());
        assertEquals(60, testCat1.checkHappiness());
    }

    @Test
    public void testLaserPointerNotEnoughEnergy() {
        testCat1.laserPointer();
        assertEquals(80, testCat1.checkEnergy());
        assertEquals(60, testCat1.checkHappiness());
        testCat1.setEnergy(10);
        testCat1.laserPointer();
        assertEquals(10, testCat1.checkEnergy());
        assertEquals(60, testCat1.checkHappiness());
    }

    @Test
    public void testPetCat() {
        testCat1.petPet();
        assertEquals(60, testCat1.checkHappiness());
    }

    @Test
    public void testHugCat() {
        testCat1.hugPet();
        assertEquals(55, testCat1.checkHappiness());
    }
}
