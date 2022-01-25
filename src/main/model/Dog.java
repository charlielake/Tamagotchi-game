package model;

// The dog class allows us to instantiate an object of a Dog, using the abstract class Pet.

public class Dog extends Pet {

    public Dog(String name) {
        super(name, "Dog");
    }

    // MODIFIES: this
    // EFFECTS: chase cute pet around, will increase happiness by 25, and decrease energy by 50
    public void chasePet() {
        increaseHappiness(25);
        decreaseEnergy(50);
    }

    // MODIFIES: this
    // EFFECTS: pets pet, increasing happiness by 10 and energy by 10
    @Override
    public void petPet() {
        increaseEnergy(10);
        increaseHappiness(10);
    }

    // MODIFIES: this
    // EFFECTS: hugs pet, increasing happiness by 15, and energy by 5
    @Override
    public void hugPet() {
        increaseHappiness(15);
        increaseEnergy(5);
    }

    // MODIFIES: this
    // EFFECTS: increases happiness by 25, decreases energy by 50 if pet has enough energy (50)
    @Override
    public void laserPointer() {
        if (petEnergy >= 50) {
            increaseHappiness(25);
            decreaseEnergy(50);
        }
    }

//    // EFFECTS: dog barks
//    public void bark() {
//       // System.out.println("bark");
//    }

}
