package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PetTest {
    Dog testDog = new Dog("Callie");
    Cat testCat = new Cat("catGracie");
    Food testFood = new Food("testFood", 20, 20);

    @BeforeEach
    public void setup() {
        testFood.addEligiblePet("Dog");
    }

    @Test
    public void testSetName() {
    testDog.setPetName("Callie2");
    assertEquals("Callie2", testDog.checkName());
    }

    @Test
    public void testFeedPet() {
        assertEquals(50, testDog.checkHunger());
        assertEquals(100, testDog.checkEnergy());
        assertEquals(50, testCat.checkHunger());
        assertEquals(100, testCat.checkEnergy());
        testDog.feedPet(testFood);
        assertEquals(70, testDog.checkHunger());
        assertEquals(100, testDog.checkEnergy());
        testCat.feedPet(testFood);
        assertEquals(50, testCat.checkHunger());
        assertEquals(100, testCat.checkEnergy());
    }

    @Test
    public void testWalkPet() {
        testDog.walkPet();
        assertEquals(90, testDog.checkEnergy());
        assertEquals(65, testDog.checkHappiness());

    }

    @Test
    public void testGivePetTreat() {
        testDog.givePetTreat();
        assertEquals(75, testDog.checkHappiness());
        assertEquals(100, testDog.checkEnergy());
    }

    @Test
    public void testWatchMovieWithPet() {
        testDog.watchMovieWithPet();
        assertEquals(65, testDog.checkHappiness());
    }

    @Test
    public void testMaxHappiness() {
        testDog.setEnergy(100);
        testDog.setHappiness(100);
        assertEquals(100, testDog.checkHappiness());
        testDog.laserPointer();
        assertEquals(100, testDog.checkHappiness());
        assertEquals(50, testDog.checkEnergy());
    }

    @Test
    public void testIncreaseEnergyNotMax() {
        testDog.laserPointer();
        assertEquals(50, testDog.checkEnergy());
        testDog.increaseEnergy(5);
        assertEquals(55, testDog.checkEnergy());
    }

    @Test
    public void testDecreaseEnergyMin() {
        testDog.decreaseEnergy(99);
        assertEquals(1, testDog.checkEnergy());
        testDog.decreaseEnergy(50);
        assertEquals(0, testDog.checkEnergy());
    }

    @Test
    public void testFeedPetMax() {
        testDog.feedPet(testFood);
        testDog.feedPet(testFood);
        assertEquals(90,testDog.checkHunger());
        testDog.feedPet(testFood);
        assertEquals(100, testDog.checkHunger());
    }

    @Test
    public void testLaserPointerNotEnoughEnergy() {
        testDog.setEnergy(49);
        testDog.laserPointer();
        assertEquals(49, testDog.checkEnergy());
        assertEquals(50, testDog.checkHappiness());
    }

    @Test
    public void testWalkPetNotEnoughEnergy() {
        testDog.setEnergy(9);
        testDog.walkPet();
        assertEquals(9, testDog.checkEnergy());
        assertEquals(50, testDog.checkHappiness());
    }




}