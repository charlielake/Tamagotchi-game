package model;

// The Food class allows us to represent different food items consumed by the pets. Each object will have:
// A name: this gives the name of the item
// A hungerValue: this represents how much the food satisfies the hunger of the pet that eats it
// An energyValue: this represents how much energy the food restores of whatever pet ate it.
// A list of eligiblePets: this shows which classes of pets are allowed to eat the item

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;
    private int hungerValue;
    private int energyValue;
    private ArrayList<String> eligiblePets;


    // EFFECTS: Constructor for food class
    public Food(String name, int hungerValue, int energyValue) {
        this.name = name;
        this.hungerValue = hungerValue;
        this.energyValue = energyValue;
        eligiblePets = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public int getHungerValue() {
        return hungerValue;
    }

    public int getEnergyValue() {
        return energyValue;
    }

    public List<String> getEligiblePets() {
        return eligiblePets;
    }

    public Boolean addEligiblePet(String string) {
        if (eligiblePets.contains(string)) {
            return true;
        }
        eligiblePets.add(string);
        return false;
    }
}
