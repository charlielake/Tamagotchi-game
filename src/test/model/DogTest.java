package model;

import org.junit.jupiter.api.Test;

import static model.Pet.MAX_ENERGY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DogTest {
    Dog testDog1 = new Dog("Lucky");

    @Test
    public void testConstructor() {
        Dog testDog = new Dog("Fido");
        assertEquals("Fido", testDog.checkName());
        assertEquals(50, testDog.checkHappiness());
        assertEquals(50, testDog.checkHunger());
        assertEquals(MAX_ENERGY, testDog.checkEnergy());
        assertEquals("healthy", testDog.checkHealth());
        assertEquals("Dog", testDog.getClass().getSimpleName());
    }

    @Test
    public void testChasePet() {
        testDog1.chasePet();
        assertEquals(50, testDog1.checkEnergy());
        assertEquals(75, testDog1.checkHappiness());
    }

    @Test
    public void testLaserPointer() {
        testDog1.laserPointer();
        assertEquals(50, testDog1.checkEnergy());
        assertEquals(75, testDog1.checkHappiness());
    }

    @Test
    public void testLaserPointerNotEnoughEnergy() {
        testDog1.setEnergy(20);
        assertEquals(20, testDog1.checkEnergy());
        assertEquals(50, testDog1.checkHappiness());
        testDog1.laserPointer();
        assertEquals(20, testDog1.checkEnergy());
        assertEquals(50, testDog1.checkHappiness());
    }

    @Test
    public void testPetDog() {
        testDog1.petPet();
        assertEquals(60,testDog1.checkHappiness());
        assertEquals(100, testDog1.checkEnergy());
        testDog1.setEnergy(20);
        testDog1.petPet();
        assertEquals(70,testDog1.checkHappiness());
        assertEquals(30, testDog1.checkEnergy());
    }

    @Test
    public void testHugDog() {
        testDog1.hugPet();
        assertEquals(65,testDog1.checkHappiness());
        assertEquals(100, testDog1.checkEnergy());
        testDog1.setEnergy(20);
        testDog1.hugPet();
        assertEquals(80,testDog1.checkHappiness());
        assertEquals(25, testDog1.checkEnergy());
    }
}
