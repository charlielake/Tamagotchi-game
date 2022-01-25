package model;

// The cat class allows us to instantiate an object of a Cat, using the abstract class Pet.

import persistence.Saveable;

public class Cat extends Pet {

    public Cat(String name) {
        super(name, "Cat");
    }

    // MODIFIES: this
    // EFFECTS: pets pet, increasing happiness by 10
    @Override
    public void petPet() {
        increaseHappiness(10);
    }

    // MODIFIES: this
    // EFFECTS: hugs pet, increasing happiness by 5
    @Override
    public void hugPet() {
        increaseHappiness(5);
    }

    // MODIFIES: this
    // EFFECTS: increases happiness by 10, decreases energy by 20 if pet has enough energy (20)
    @Override
    public void laserPointer() {
        if (petEnergy >= 20) {
            increaseHappiness(10);
            decreaseEnergy(20);
        }
    }

//    // EFFECTS: cat meows
//    public void meow() {
//       // System.out.println("meow");
//    }

}


