package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodTest {
    Food apple = new Food("Apple", 20, 10);
    Food fish = new Food("Fish", 20, 15);

    @Test
    public void testConstructor() {
        Food fish = new Food("Fish", 20, 15);
        fish.addEligiblePet("Cat");
        assertEquals("Fish", fish.getName());
        assertEquals(20, fish.getHungerValue());
        assertEquals(15, fish.getEnergyValue());
        assertEquals(1, fish.getEligiblePets().size());
        assertTrue(fish.getEligiblePets().contains("Cat"));
    }

    @Test
    public void testAddEligiblePet() {
        Food fish = new Food("Fish", 20, 15);
        apple.addEligiblePet("Cat");
        apple.addEligiblePet("Dog");
        assertEquals(2,apple.getEligiblePets().size());
        apple.addEligiblePet("Cat");
        assertEquals(2, apple.getEligiblePets().size());
    }
}
