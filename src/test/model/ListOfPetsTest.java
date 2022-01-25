package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.ListOfPets.MAX_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfPetsTest {

    ListOfPets listOfPetsTest = new ListOfPets();
    Cat testCat = new Cat("Molly");
    Dog testDog = new Dog("Cody");

    @Test
    public void testAddPet() {
        listOfPetsTest.addPet(testCat);
        assertEquals(1, listOfPetsTest.getListOfPets().size());
        assertTrue(listOfPetsTest.addPet(testCat));
        listOfPetsTest.addPet(testCat);
        listOfPetsTest.addPet(testCat);
        listOfPetsTest.addPet(testCat);
        listOfPetsTest.addPet(testCat);
        assertEquals(MAX_SIZE, listOfPetsTest.getListOfPets().size());
        assertFalse(listOfPetsTest.addPet(testCat));
    }

    @Test
    public void testTimePasses() {
        listOfPetsTest.addPet(testDog);
        listOfPetsTest.addPet(testCat);
        listOfPetsTest.timePasses();
        assertEquals(98, testDog.checkEnergy());
        assertEquals(46, testDog.checkHunger());
        assertEquals(48, testDog.checkHappiness());
        testDog.setHappiness(1);
        testDog.setEnergy(1);
        testDog.setHunger(1);
        listOfPetsTest.timePasses();
        assertEquals(96, testCat.checkEnergy());
        assertEquals(42, testCat.checkHunger());
        assertEquals(46, testCat.checkHappiness());
        assertEquals(0, testDog.checkEnergy());
        assertEquals(0, testDog.checkHunger());
        assertEquals(0, testDog.checkHappiness());
    }

    @Test
    public void testRestPet() {
        listOfPetsTest.addPet(testDog);
        testDog.setEnergy(50);
        assertEquals(50, testDog.checkEnergy());
        listOfPetsTest.restPet();
        assertEquals(100, testDog.checkEnergy());
    }

    @Test
    public void testFindPet() {
        listOfPetsTest.addPet(testDog);
        assertEquals(testDog, listOfPetsTest.findPet(testDog.checkName()));
    }

    @Test
    public void testFindPetNull() {
        listOfPetsTest.addPet(testDog);
        assertNull(listOfPetsTest.findPet(testCat.checkName()));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(listOfPetsTest.isEmpty());
        listOfPetsTest.addPet(testDog);
        assertFalse(listOfPetsTest.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(listOfPetsTest.isFull());
        listOfPetsTest.addPet(testDog);
        listOfPetsTest.addPet(testDog);
        listOfPetsTest.addPet(testDog);
        listOfPetsTest.addPet(testDog);
        listOfPetsTest.addPet(testDog);
        assertTrue(listOfPetsTest.isFull());
    }

}
