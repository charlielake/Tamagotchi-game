package model;

// The ListOfPets class allows us to represent all pets owned by the player. Each object will have:
// A listOfPets: An arrayList containing all pets owned by the player.
// A static MAX_SIZE: representing the max size of the list (max number of pets a player can own)

import java.util.ArrayList;

import static model.Pet.MAX_ENERGY;

public class ListOfPets {
    private ArrayList<Pet> listOfPets;
    public static int MAX_SIZE = 5;

    public ListOfPets() {
        listOfPets = new ArrayList<Pet>();
    }

    // Getter for ListOfPets
    public ArrayList<Pet> getListOfPets() {
        return listOfPets;
    }

    // MODIFIES: this
    // EFFECTS: if size of list < MAX_SIZE, then adds new pet to list and returns true, otherwise returns false
    public boolean addPet(Pet p) {
        if (listOfPets.size() < MAX_SIZE) {
            listOfPets.add(p);
            return true;
        }
        return false;
    }

    // MODIFIES: Pet
    // EFFECTS: decrease values of pet fields of all pets in list to a minimum of 0, is called upon whenever an action
    // is taken
    public void timePasses() {
        for (Pet p : listOfPets) {
            p.setEnergy(p.checkEnergy() - 2);
            p.setHappiness(p.checkHappiness() - 2);
            p.setHunger(p.checkHunger() - 4);
            if (p.checkEnergy() < 0) {
                p.setEnergy(0);
            }
            if (p.checkHappiness() < 0) {
                p.setHappiness(0);
            }
            if (p.checkHunger() < 0) {
                p.setHunger(0);
            }
        }
    }

    // MODIFIES: Pet
    // EFFECTS: rests all pets, increasing energy to max
    public void restPet() {
        for (Pet p: listOfPets) {
            p.setEnergy(MAX_ENERGY);
        }
    }

    // EFFECTS: returns pet with given name, otherwise returns null
    public Pet findPet(String petName) {
        for (Pet p: listOfPets) {
            if (p.checkName() == petName) {
                return p;
            }
        }
        return null;
    }

    // EFFECTS: returns true if the list is empty, false otherwise
    public Boolean isEmpty() {
        if (listOfPets.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if the list is full, false otherwise
    public Boolean isFull() {
        if (listOfPets.size() == MAX_SIZE) {
            return true;
        } else {
            return false;
        }
    }
}
