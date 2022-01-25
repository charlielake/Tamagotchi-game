package model;

// The Pet class allows us to represent different instances of pets. Each Pet subclass object will have:
// petName: the name of the pet
// petHunger: an int from 0 to 100 representing how hungry the pet is, with 0 representing hungry and 100 full
// petHappiness: an int from 0 to 100 representing how happy the pet is, with 0 representing unhappy and 100 happy
// petEnergy: an int from 0 to 100 representing how much energy the pet has, with 0 representing exhausted and 100
// energetic
// petHealth: a string representing the health of a pet, e.g. if the pet has the flu it will be "has the flu" and if
// the pet is healthy, it will be "healthy".

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleToIntFunction;

public abstract class Pet implements Saveable {
    private String petName;
    private int petHunger;
    private int petHappiness;
    public int petEnergy;
    private String petHealth;
    public String petType;

    public static int MAX_HAPPINESS = 100;
    public static int MAX_HUNGER = 100;
    public static int MAX_ENERGY = 100;


    // Constructor for Pet subclasses
    public Pet(String name, String type) {
        petName = name;
        petHunger = MAX_HUNGER / 2;
        petHappiness = MAX_HAPPINESS / 2;
        petHealth = "healthy";
        petEnergy = MAX_ENERGY;
        petType = type;
    }


    // The following are getters for all the different fields of the Pet class

    public String checkName() {
        return petName;
    }

    public int checkHunger() {
        return petHunger;
    }

    public int checkHappiness() {
        return petHappiness;
    }

    public String checkHealth() {
        return petHealth;
    }

    public int checkEnergy() {
        return petEnergy;
    }


    // The following are setters for different fields of the Pet class


    public void setHunger(int hunger) {
        petHunger = hunger;
    }

    public void setHappiness(int happiness) {
        petHappiness = happiness;
    }

    public void setEnergy(int energy) {
        petEnergy = energy;
    }

    public void setPetName(String name) {
        petName = name;
    }

    public void setPetHealth(String health) {
        petHealth = health;
    }

    // MODIFIES: this
    // EFFECTS: increases petHappiness by given i
    //          if this calculated value is over MAX_HAPPINESS, then sets petHappiness to MAX_HAPPINESS
    public void increaseHappiness(int i) {
        if ((petHappiness + i) > MAX_HAPPINESS) {
            setHappiness(MAX_HAPPINESS);
        } else {
            setHappiness(petHappiness + i);
        }
    }

    // MODIFIES: this
    // EFFECTS: increases petEnergy by given i, if this value is over MAX_ENERGY, then sets petEnergy to MAX_ENERGY
    public void increaseEnergy(int i) {
        if (petEnergy + i > MAX_ENERGY) {
            setEnergy(MAX_ENERGY);
        } else {
            setEnergy(petEnergy + i);
        }
    }

    // MODIFIES: this
    // EFFECTS: decreases petEnergy by given i, if this value is under 0, then sets petEnergy to 0
    public void decreaseEnergy(int i) {
        if ((petEnergy - i) < 0) {
            setEnergy(0);
        } else {
            setEnergy(petEnergy - i);
        }
    }


    // MODIFIES: this
    // EFFECTS: increases hunger based off of what is fed
    //          there are restrictions on what food different pets can eat
    public void feedPet(Food f) {
        if (f.getEligiblePets().contains(this.getClass().getSimpleName())) {
            int temp = petHunger + f.getHungerValue();
            if (temp > MAX_HUNGER) {
                setHunger(MAX_HUNGER);
            } else {
                setHunger(temp);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: takes pet on a walk, will increase happiness by 15, and decrease energy by 10 if pet has enough
    //          energy(10)
    public void walkPet() {
        if (petEnergy >= 10) {
            increaseHappiness(15);
            decreaseEnergy(10);
        }
    }

    // MODIFIES: this
    // EFFECTS: gives pet a treat, will increase happiness by 25 and energy by 10
    public void givePetTreat() {
        increaseHappiness(25);
        increaseEnergy(10);
    }

    // MODIFIES: this
    // EFFECTS: pets this pet, will increase happiness by a value, varying by class
    public abstract void petPet();

    // MODIFIES: this
    // EFFECTS: watches a cute pet-related movie with pet, will increase happiness by 15
    public void watchMovieWithPet() {
        increaseHappiness(15);
    }

    // MODIFIES: this
    // EFFECTS: hugs pet, increasing happiness by a value, varying by class
    public abstract void hugPet();

    // MODIFIES: this
    // EFFECTS: increases happiness by VAL1, decreases energy by VAL2 if pet has enough energy (>=VAL2),
    // varying by class
    public abstract void laserPointer();

    // EFFECTS: saves data of pet to printWriter
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(petName);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(petHunger);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(petHappiness);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(petEnergy);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(petHealth);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(petType);
    }

}

