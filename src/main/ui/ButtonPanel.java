package ui;

import model.Cat;
import model.Dog;
import model.Food;
import model.Pet;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static ui.GUI.*;

// Class represents JPanel containing all buttons used in application for GUI

public class ButtonPanel extends JPanel implements ActionListener {

    // Used to set button bounds, with y position calculated using it n*BUTTON_Y_SPACING + (n-1)*BUTTON_HEIGHT
    // where n is its order of placement on left (order in which it is called). Also used to change button dimensions.
    // BUTTON_Y_SPACING represents the spacing from the top and between each button, INIT_BUTTON_X_POSITION is the
    // distance from the left side of the screen that buttons are placed.
    static int BUTTON_WIDTH = 200;
    static int BUTTON_HEIGHT = BUTTON_WIDTH / 4;
    static int INIT_BUTTON_X = 10;
    static int BUTTON_Y_SPACING = 10;

    // Used to direct where data is saved to
    private static final String ACCOUNTS_FILE = "./data/pets.txt";

    // Used to set gui as an action listener
    GUI gui;

    // Constructor, initializes all buttons on this panel and adds them to the ButtonPanel
    public ButtonPanel(GUI gui) {
        initButtons();
        this.gui = gui;
        setLayout(null);
    }

    // MODIFIES: this, JPanel
    // EFFECTS: initializes all buttons for GUI
    public void initButtons() {
        addPetButtonInit();
        removePetButtonInit();
        saveButtonInit();
        loadButtonInit();
        feedButtonInit();
        walkButtonInit();
        treatsButtonInit();
        playButtonInit();
        restButtonInit();
        quitButtonInit();
    }

    // MODIFIES: this
    // EFFECTS: initializes addPetButton to display
    public void addPetButtonInit() {
        JButton addPetButton = new JButton("Add Pet");
        addPetButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT);
        addPetButton.setActionCommand("Add Pet");
        addPetButton.addActionListener(this);
        add(addPetButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes removePetButton to display
    public void removePetButtonInit() {
        JButton removePetButton = new JButton("Remove Pet");
        removePetButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 2 + BUTTON_HEIGHT,BUTTON_WIDTH,
                BUTTON_HEIGHT);
        removePetButton.setActionCommand("Remove Pet");
        removePetButton.addActionListener(this);
        add(removePetButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes saveButton to display
    public void saveButtonInit() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(INIT_BUTTON_X,BUTTON_Y_SPACING * 3 + BUTTON_HEIGHT * 2,BUTTON_WIDTH,BUTTON_HEIGHT);
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes loadButton to display
    public void loadButtonInit() {
        JButton loadButton = new JButton("Load");
        loadButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 4 + BUTTON_HEIGHT * 3, BUTTON_WIDTH,BUTTON_HEIGHT);
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS; initializes feedButton to display
    public void feedButtonInit() {
        JButton feedButton = new JButton("Feed");
        feedButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 5 + BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
        feedButton.setActionCommand("Feed");
        feedButton.addActionListener(this);
        add(feedButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes walkButton to display
    public void walkButtonInit() {
        JButton walkButton = new JButton("Walk");
        walkButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 6 + BUTTON_HEIGHT * 5, BUTTON_WIDTH, BUTTON_HEIGHT);
        walkButton.setActionCommand("Walk");
        walkButton.addActionListener(this);
        add(walkButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes treatsButton to display
    public void treatsButtonInit() {
        JButton treatsButton = new JButton("Treats");
        treatsButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 7 + BUTTON_HEIGHT * 6, BUTTON_WIDTH, BUTTON_HEIGHT);
        treatsButton.setActionCommand("Treats");
        treatsButton.addActionListener(this);
        add(treatsButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes playButton to display
    public void playButtonInit() {
        JButton playButton = new JButton("Play");
        playButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 8 + BUTTON_HEIGHT * 7, BUTTON_WIDTH, BUTTON_HEIGHT);
        playButton.setActionCommand("Play");
        playButton.addActionListener(this);
        add(playButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes restButton to display
    public void restButtonInit() {
        JButton restButton = new JButton("Rest all Pets");
        restButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 9 + BUTTON_HEIGHT * 8, BUTTON_WIDTH, BUTTON_HEIGHT);
        restButton.setActionCommand("Rest");
        restButton.addActionListener(this);
        add(restButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes quitButton to display
    public void quitButtonInit() {
        JButton quitButton = new JButton("Save and Quit");
        quitButton.setBounds(INIT_BUTTON_X, BUTTON_Y_SPACING * 10 + BUTTON_HEIGHT * 9, BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setActionCommand("Quit");
        quitButton.addActionListener(this);
        add(quitButton);
    }



    // MODIFIES: this, pet, listOfPets
    // EFFECTS: performs action when button is clicked, based on which button is clicked
    public void actionPerformedTimePasses(ActionEvent e) {
        if (e.getActionCommand().equals("Feed")) {
            handleFeed();
        } else if (e.getActionCommand().equals("Walk")) {
            handleWalk();
        } else if (e.getActionCommand().equals("Treats")) {
            handleTreats();
        } else if (e.getActionCommand().equals("Play")) {
            handlePlay();
        } else if (e.getActionCommand().equals("Rest")) {
            handleRest();
        }
        gui.listOfPets.timePasses();
    }

    // MODIFIES: this, pet, listOfPets
    // EFFECTS: performs action when button is clicked, based on which button is clicked, second method due to too many
    // options :(
    // time only passes when performing actions with pet ie. actionPerformedTimePasses. All will update statusBars and
    // display afterwards
    public void actionPerformed(ActionEvent e) {
        gui.sound.playSound("buttonClick.wav");
        if (e.getActionCommand().equals("Quit")) {
            handleQuit();
        } else if (e.getActionCommand().equals("Add Pet")) {
            handleAddPet();
        } else if (e.getActionCommand().equals("Remove Pet")) {
            handleRemovePet();
        } else if (e.getActionCommand().equals("Save")) {
            handleSave();
        } else if (e.getActionCommand().equals("Load")) {
            handleLoad();
        } else {
            actionPerformedTimePasses(e);
        }
        gui.psp.updateStatusBars();
        gui.updateDisplay();
    }

    // MODIFIES: listOfPets
    // EFFECTS: adds pet to list of pets if list is not full
    public void handleAddPet() {
        String[] petClassOptions = {"Cat", "Dog"};
        if (!gui.listOfPets.isFull()) {
            String petClass = (String) JOptionPane.showInputDialog(
                    null,
                    "What type of pet would you like to add?", "Pet Creator", JOptionPane.QUESTION_MESSAGE,
                    null, petClassOptions, null);
            String petName = (String) JOptionPane.showInputDialog(
                    null, "What would you like to name your pet?", "Pet Selector",
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (petClass == "Cat") {
                Cat cat1 = new Cat(petName);
                gui.listOfPets.addPet(cat1);
                gui.currentPet = cat1;
                gui.sound.playSound("catMeow.wav");
            } else if (petClass == "Dog") {
                Dog dog1 = new Dog(petName);
                gui.listOfPets.addPet(dog1);
                gui.currentPet = dog1;
                gui.sound.playSound("dogBark.wav");
            }
        }
    }

    // MODIFIES: listOfPets
    // EFFECTS: removes pet from list of pets if user has pets. Then, updates currentPet to either the first pet
    // in the list, or defaultPet if the list is empty
    public void handleRemovePet() {
        if (!gui.listOfPets.isEmpty()) {
            Pet tempPet = selectPet();
            gui.listOfPets.getListOfPets().remove(tempPet);
        }
        if (gui.listOfPets.getListOfPets().size() > 0) {
            gui.currentPet = gui.listOfPets.getListOfPets().get(0);
        } else {
            gui.currentPet = gui.defaultPet;
        }
    }

    // EFFECTS: saves the current state to file
    public void handleSave() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            for (Pet p: gui.listOfPets.getListOfPets()) {
                writer.write(p);
            }
            writer.close();
            JOptionPane.showMessageDialog(null,
                    "Data has been saved successfully!",
                    "Saved successfully",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to save pets to" + ACCOUNTS_FILE,
                    "Saving Error",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the current state to file
    public void handleLoad() {
        try {
            gui.listOfPets = Reader.readTamagotchiApp(new File(ACCOUNTS_FILE));
            JOptionPane.showMessageDialog(null,
                    "Data has been loaded successfully!",
                    "Loaded successfully",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
            gui.currentPet = gui.listOfPets.getListOfPets().get(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to load pets from" + ACCOUNTS_FILE,
                    "Loading Error",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No pet data found in " + ACCOUNTS_FILE,
                    "Loading Error",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        }
    }

    // MODIFIES: pet
    // EFFECTS: allows user to feed a pet if user has pets
    public void handleFeed() {
        try {
            if (!gui.listOfPets.isEmpty()) {
                Pet pet = selectPet();
                Food food = selectFood(pet);
                pet.feedPet(food);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Options were not selected properly.",
                    "FEEDback",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        }
    }

    // MODIFIES: pet
    // EFFECTS: allows the user to walk a pet if user has pets
    public void handleWalk() {
        try {
            if (!gui.listOfPets.isEmpty()) {
                Pet pet = selectPet();
                gui.sound.defaultSound(pet);
                pet.walkPet();
            }
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null, "Something seems to have gone wrong, "
                    + "please try again.", "Error", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }

    // MODIFIES: pet
    // EFFECTS: allows the user to feed a treat to a pet if user has pets
    public void handleTreats() {
        try {
            if (!gui.listOfPets.isEmpty()) {
                Pet pet = selectPet();
                pet.givePetTreat();
                gui.sound.defaultSound(pet);
                JOptionPane.showMessageDialog(null,
                        pet.checkName() + " loved the treat!",
                        "Pet Feedback",
                        JOptionPane.INFORMATION_MESSAGE,
                        null);
            }
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null, "Something seems to have gone wrong, "
                    + "please try again.", "Error", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }

    // MODIFIES: pet
    // EFFECTS: allows the user to play with a pet if user has pets
    public void handlePlay() {
        try {
            if (!gui.listOfPets.isEmpty()) {
                Pet pet = selectPet();
                String[] playOptions = {"Pet", "Hug", "Watch Movie", "Play with Laser Pointer"};
                String selectedPlay = (String) JOptionPane.showInputDialog(
                        null, "What would you like to do with your pet?",
                        "Play Selector", JOptionPane.QUESTION_MESSAGE, null, playOptions, null);
                doPlay(selectedPlay, pet);
            }
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null, "Something seems to have gone wrong, "
                    + "please try again.", "Error", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }

    // MODIFIES: pet
    // EFFECTS: using passed selected play option, will play that with pet
    public void doPlay(String sp, Pet pet) {
        if (sp == "Pet") {
            pet.petPet();
            petPetFeedback(pet);
        } else if (sp == "Hug") {
            pet.hugPet();
            hugPetFeedback(pet);
        } else if (sp == "Watch Movie") {
            pet.watchMovieWithPet();
            watchMovieWithPetFeedback(pet);
        } else if (sp == "Play with Laser Pointer") {
            pet.laserPointer();
            laserPointerFeedback(pet);
        }
    }

    // MODIFIES: pet
    // EFFECTS: provides visual and audio feedback on petting pet
    public void petPetFeedback(Pet pet) {
        gui.sound.defaultSound(pet);
        JOptionPane.showMessageDialog(null,
                pet.checkName() + " is really happy!",
                "Pet Feedback",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    // MODIFIES: pet
    // EFFECTS: provides visual and audio feedback on hugging pet
    public void hugPetFeedback(Pet pet) {
        gui.sound.defaultSound(pet);
        JOptionPane.showMessageDialog(null,
                pet.checkName() + " is really happy!",
                "Pet Feedback",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    // MODIFIES: pet
    // EFFECTS: provides visual and audio feedback on watching movie with pet
    public void watchMovieWithPetFeedback(Pet pet) {
        JOptionPane.showMessageDialog(null,
                pet.checkName() + " fell asleep during Titanic again...",
                "Pet Feedback",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    // MODIFIES: pet
    // EFFECTS: provides visual and audio feedback on playing with laser pointer
    public void laserPointerFeedback(Pet pet) {
        String petClass = pet.getClass().getSimpleName();
        if (petClass.equals("Dog")) {
            gui.sound.playSound("crashLarge.wav");
            JOptionPane.showMessageDialog(null,
                    pet.checkName() + " hurt itself in confusion!",
                    "Pet Feedback",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        } else if (petClass.equals("Cat")) {
            gui.sound.defaultSound(pet);
            JOptionPane.showMessageDialog(null,
                    pet.checkName() + " is watching and chasing the laser intently!",
                    "Pet Feedback",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        }
    }


    // MODIFIES: pets
    // EFFECTS: allows the user to rest their pets if user has pets
    public void handleRest() {
        if (!gui.listOfPets.isEmpty()) {
            gui.listOfPets.restPet();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to quit the game
    public void handleQuit() {
        handleSave();
        System.exit(0);
    }

    // EFFECTS: allows user to select pet with which to perform actions
    public Pet selectPet() {
        String[] pets = new String[gui.listOfPets.getListOfPets().size()];
        for (Pet p: gui.listOfPets.getListOfPets()) {
            int i = gui.listOfPets.getListOfPets().indexOf(p);
            pets[i] = p.checkName();
        }
        String selectedPet = (String) JOptionPane.showInputDialog(
                null,
                "Which pet would you like to select?",
                "Pet Selector",
                JOptionPane.QUESTION_MESSAGE,
                null,
                pets,
                gui.listOfPets.getListOfPets().get(0).checkName());
        return gui.listOfPets.findPet(selectedPet);
    }

    // EFFECTS: allows user to select food to feed pet
    public Food selectFood(Pet pet) {
        String[] foodOptions = {"Water","Milk","Bone","Fish"};
        String selectedFood = (String) JOptionPane.showInputDialog(
                null,
                "What food would you like to give your pet?",
                "Pet Selector",
                JOptionPane.QUESTION_MESSAGE,
                null,
                foodOptions,
                gui.listOfPets.getListOfPets().get(0));
        return findFood(selectedFood, pet);
    }

    // EFFECTS: returns food to feed pet based on selected food
    public Food findFood(String selectedFood, Pet pet) {
        foodFeedback(selectedFood, pet);
        Food food;
        if (selectedFood == "Water") {
            food = new Food("Water", 15, 15);
            food.addEligiblePet("Cat");
            food.addEligiblePet("Dog");
        } else if (selectedFood == "Fish") {
            food = new Food("Fish", 30, 10);
            food.addEligiblePet("Cat");
            food.addEligiblePet("Dog");
        } else if (selectedFood == "Milk") {
            food = new Food("Milk", 10, 20);
            food.addEligiblePet("Cat");
            food.addEligiblePet("Dog");
        } else if (selectedFood == "Milk") {
            // Last case for if selectedFood == "Bone"
            food = new Food("Bone", 0, 40);
            food.addEligiblePet("Cat");
            food.addEligiblePet("Dog");
        } else {
            food = new Food("Nothing selected", 0,0);
        }
        return food;
    }

    // EFFECTS: calls appropriate feedback method for selected food
    public void foodFeedback(String selectedFood, Pet pet) {
        if (selectedFood == "Water") {
            waterFeedback(pet);
        } else if (selectedFood == "Fish") {
            fishFeedback(pet);
        } else if (selectedFood == "Milk") {
            milkFeedback(pet);
        } else if (selectedFood == "Bone") {
            boneFeedback(pet);
        } else {
            nothingEatenFeedback(pet);
        }
    }

    // EFFECTS: provides visual and audio feedback on water being selected to feed pet
    public void waterFeedback(Pet pet) {
        gui.sound.playSound("waterSlurp.wav");
        JOptionPane.showMessageDialog(null,
                pet.checkName() + " was really thirsty!",
                "Pet Feedback",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    // EFFECTS: provides visual and audio feedback on fish being selected to feed pet
    public void fishFeedback(Pet pet) {
        gui.sound.defaultSound(pet);
        JOptionPane.showMessageDialog(null,
                pet.checkName() + " ate the fish and seemed to enjoy it!",
                "Pet Feedback",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    // EFFECTS: provides visual and audio feedback on milk being selected to feed pet
    public void milkFeedback(Pet pet) {
        gui.sound.playSound("waterSlurp.wav");
        JOptionPane.showMessageDialog(null,
                pet.checkName() + " really enjoyed the milk!",
                "Pet Feedback",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    // EFFECTS: provides visual and audio feedback on bone being selected to feed pet
    public void boneFeedback(Pet pet) {
        gui.sound.defaultSound(pet);
        JOptionPane.showMessageDialog(null,
                pet.checkName() + " is really munching on the bone you gave it!",
                "Pet Feedback",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

    // EFFECTS: provides visual and audio feedback when no food is selected to feed pet
    public void nothingEatenFeedback(Pet pet) {
        gui.sound.defaultSound(pet);
        JOptionPane.showMessageDialog(null,
                "No food was selected. " + pet.checkName() + " seems sad.",
                "Invalid Selection",
                JOptionPane.INFORMATION_MESSAGE,
                null);
    }

}
