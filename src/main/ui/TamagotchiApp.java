package ui;

import model.*;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class TamagotchiApp {
    private static final String ACCOUNTS_FILE = "./data/pets.txt";
    private Scanner input;
    ListOfPets listOfPets = new ListOfPets();

    Food fish;
    Food bone;
    Food milk;
    Food water;

    Pet exceptPet = new Cat("Used as a return when no pet exists");

    public void setPets(ListOfPets listOfPets) {
        this.listOfPets = listOfPets;
    }


    // Code taken from TellerApp, modified (quite a bit) to fit my app

    public TamagotchiApp() {
        runTamagotchi();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTamagotchi() {
        System.out.println("Welcome to Tamagotchi!");
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        loadTamagotchi();
        initFood();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            listOfPets.timePasses();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: Initializes food objects for use
    private void initFood() {
        fish = new Food("Fish", 30, 10);
        fish.addEligiblePet("Cat");

        bone = new Food("Bone", 0, 40);
        bone.addEligiblePet("Dog");

        milk = new Food("Milk", 10, 20);
        milk.addEligiblePet("Dog");
        milk.addEligiblePet("Cat");

        water = new Food("Water", 15, 15);
        water.addEligiblePet("Dog");
        water.addEligiblePet("Cat");
    }

    // MODIFIES: this
    // EFFECTS: initializes pets
    public void initTamagotchi() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayInitMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("c")) {
                createPet("c");
            } else if (command.equals("d")) {
                createPet("d");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadTamagotchi() {
        try {
            listOfPets = Reader.readTamagotchiApp(new File(ACCOUNTS_FILE));
        } catch (IOException e) {
            initTamagotchi();
        }
    }

    // EFFECTS: saves state of pets to ACCOUNTS_FILE
    private void saveAccounts() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            for (Pet p: listOfPets.getListOfPets()) {
                writer.write(p);
            }
            writer.close();
            System.out.println("Pets saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save pets to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: creates pet with given name, and adds it to listOfPets
    public void createPet(String petType) {
        String name = null;
        input = new Scanner(System.in);

        System.out.println("What would you like to name your pet?");
        name = input.nextLine();

        if (petType.equals("c")) {
            Cat cat1 = new Cat(name);
            listOfPets.addPet(cat1);
        } else if (petType.equals("d")) {
            Dog dog1 = new Dog(name);
            listOfPets.addPet(dog1);
        }
        if (listOfPets.getListOfPets().size() == 2) {
            System.out.println("You have reached the max number of pets! Please continue.");
        }
    }

    // EFFECTS: displays a menu of options for initializing pets
    public void displayInitMenu() {
        System.out.println("\nWhat pet would you like?");
        System.out.println("\tc -> Cat");
        System.out.println("\td -> Dog");
        System.out.println(("\tq -> Quit tamagotchi creation"));
    }


    // EFFECTS: displays menu of options to player
    public void displayMenu() {
        System.out.println("\nWhat would you like to do?:");
        System.out.println("\tf -> Feed");
        System.out.println("\tw -> Walk");
        System.out.println("\tg -> Give treats");
        System.out.println("\tp -> Play");
        System.out.println("\tr -> Rest");
        System.out.println("\th -> Check health");
        System.out.println("\ts -> Save pets to file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: Pet
    // EFFECTS: processes player's command input
    public void processCommand(String command) {
        if (command.equals("f")) {
            doFeed();
        } else if (command.equals("w")) {
            doWalk();
        } else if (command.equals("g")) {
            doGiveTreats();
        } else if (command.equals("p")) {
            doPlay();
        } else if (command.equals("r")) {
            doRest();
        } else if (command.equals("h")) {
            doCheckHealth();
        } else if (command.equals("s")) {
            saveAccounts();
        } else  {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: selects which pet to perform an action with and returns it
    public Pet selectPet() {
        input = new Scanner(System.in);
        String name = null;
        System.out.println("Which pet do you want to select?");
        name = input.nextLine();
        return searchForPet(name);

    }

    // REQUIRES: Pet with given name is present in listOfPets
    // EFFECTS: searches through array for pet with given name
    public Pet searchForPet(String name) {
        Pet temp = exceptPet;
        for (Pet p:listOfPets.getListOfPets()) {
            if (p.checkName().equals(name)) {
                temp = p;
            }
        }
        if (temp.equals(exceptPet)) {
            System.out.println("A pet with this name does not exist. "
                    + "This is an exception case and so exceptCat will be used for now");
        }
        return temp;
    }

    // MODIFIES: Pet
    // EFFECTS: feeds selected pet
    public void doFeed() {
        Pet temp = selectPet();
        Food testFood = new Food("hi", 1,2);
        selectFood(temp);
    }


    // MODIFIES: Pet
    // EFFECTS: selects food to then be fed to pet
    public void selectFood(Pet temp) {
        input = new Scanner(System.in);
        String command = null;
        displayFoodMenu();

        command = input.next();
        command = command.toLowerCase();
        processFood(command, temp);
    }

    // MODIFIES: Pet
    // EFFECTS: select which food to feed based on input given
    public void processFood(String command, Pet temp) {
        if (command.equals("f")) {
            feedFish(temp);
        } else if (command.equals("b")) {
            feedBone(temp);
        } else if (command.equals("m")) {
            feedMilk(temp);
        } else if (command.equals("w")) {
            feedWater(temp);
        } else {
            System.out.println("That isn't a food!");
        }
    }

    // MODIFIES: Pet
    // EFFECTS: feed pet fish if eligible
    public void feedFish(Pet temp) {
        if (fish.getEligiblePets().contains(temp.getClass().getSimpleName())) {
            temp.feedPet(fish);
            System.out.println(temp.checkName() + " enjoyed the fish and is purring!");
        } else {
            System.out.println(temp.checkName() + " cannot eat that food!");
        }
    }

    // MODIFIES: Pet
    // EFFECTS: feed pet bone if eligible
    public void feedBone(Pet temp) {
        if (bone.getEligiblePets().contains(temp.getClass().getSimpleName())) {
            temp.feedPet(bone);
            System.out.println(temp.checkName() + "is chomping on the bone a little too excitedly...");
        } else {
            System.out.println(temp.checkName() + " cannot eat that food!");
        }
    }

    // MODIFIES: Pet
    // EFFECTS: feed pet milk if eligible
    public void feedMilk(Pet temp) {
        if (milk.getEligiblePets().contains(temp.getClass().getSimpleName())) {
            temp.feedPet(milk);
            System.out.println(temp.checkName() + " really enjoyed the milk!");
        } else {
            System.out.println(temp.checkName() + " cannot eat that food!");
        }
    }

    // MODIFIES: Pet
    // EFFECTS: feed pet water if eligible
    public void feedWater(Pet temp) {
        if (water.getEligiblePets().contains(temp.getClass().getSimpleName())) {
            temp.feedPet(water);
            System.out.println(temp.checkName() + " was thirsty!");
        } else {
            System.out.println(temp.checkName() + " cannot eat that food!");
        }
    }

    // EFFECTS: displays food options
    public void displayFoodMenu() {
        System.out.println("\nWhat would you like to feed your pet?:");
        System.out.println("\tf -> Fish");
        System.out.println("\tb -> Bone");
        System.out.println("\tm -> Milk");
        System.out.println("\tw -> Water");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: Pet
    // EFFECTS: takes pet for a walk
    public void doWalk() {
        Pet temp = selectPet();
        if (temp.checkEnergy() > 10) {
            temp.walkPet();
            if (temp.getClass().getSimpleName() == "Dog") {
                System.out.println(temp.checkName() + " is running around excitedly!");
            } else {
                System.out.println(temp.checkName() + " really enjoyed the walk!");
            }
        } else {
            System.out.println(temp.checkName() + " was too tired to go for a walk.");
        }
    }

    // MODIFIES: Pet
    // EFFECTS: gives pet a treat
    public void doGiveTreats() {
        Pet temp = selectPet();
        temp.givePetTreat();
        System.out.println(temp.checkName() + " is enjoying their treat!");
    }

    // MODIFIES: Pet
    // EFFECTS: plays with pet
    public void doPlay() {
        Pet temp = selectPet();
        input = new Scanner(System.in);
        String command = null;
        displayPlayMenu();

        command = input.next();
        command = command.toLowerCase();

        processPlay(command, temp);
    }

    // MODIFIES: Pet
    // EFFECTS: processes user's command for play options
    public void processPlay(String command,Pet temp) {
        if (command.equals("p")) {
            temp.petPet();
            System.out.println(temp.checkName() + " looks happy!");
        } else if (command.equals("h")) {
            temp.hugPet();
            System.out.println(temp.checkName() + " liked the hug!");
        } else if (command.equals("w")) {
            temp.watchMovieWithPet();
            System.out.println(temp.checkName() + " seems very interested in Marley and Me!");
        } else if (command.equals("l")) {
            if (temp.checkEnergy() >= 50) {
                temp.laserPointer();
                System.out.println(temp.checkName() + " hurts itself in confusion!");
            } else {
                System.out.println(temp.checkName() + " is too tired to play.");
            }
        } else {
            System.out.println("Play time is over :(");
        }
    }

    // EFFECTS: displays menu of play options
    public void displayPlayMenu() {
        System.out.println("\nWhat would you like to do?:");
        System.out.println("\tp -> Pet");
        System.out.println("\th -> Hug");
        System.out.println("\tw -> Watch movie");
        System.out.println("\tl -> Play with a laser pointer");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: listOfPets
    // EFFECTS: allows pets to rest
    public void doRest() {
        listOfPets.restPet();
        System.out.println("Everyone had a nice nap and is all rested up!");
    }

    // EFFECTS: checks pet's current energy, hunger, happiness, and health
    public void doCheckHealth() {
        for (Pet p: listOfPets.getListOfPets()) {
            System.out.println(p.checkName() + "'s hunger is at " + Integer.toString(p.checkHunger()));
            System.out.println(p.checkName() + "'s energy is at " + Integer.toString(p.checkEnergy()));
            System.out.println(p.checkName() + "'s happiness is at " + Integer.toString(p.checkHappiness()));
            System.out.println(p.checkName() + " is " + p.checkHealth());
        }
    }
}
