package ui;

import model.*;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

// This class represents the GUI for this application. It contains all the visuals related to it, all buttons that
// implements the abstraction, and all the sound and visual feedback given when actions are taken. The GUI has
// multiple constant fields, with their uses listed above them all. It also contains a list of pets which
// contains the pets that are interacted with in the application. It also contains a field currentPet representing
// which pet's stats are currently being viewed.

public class GUI extends JFrame {

    // Contains all the subPanels
    JPanel mainPanel;
    // Used to contain buttons.
    ButtonPanel bp;
    // Used to contain pet stats and list
    PetStatusPanel psp;

    // Class used to manage all the sound-related effects for GUI
    public SoundManager sound;

    // Represents currently viewed pet, initialized as a default pet as to prevent null pointers
    public Pet defaultPet = new Cat("Default");
    public Pet currentPet = defaultPet;

    // This never fully worked, but doesn't matter here
//    // Used to change images for pop-ups
//    Image image;
//    ImageIcon imageIcon;
//    public static String iconDir = "C:\\Users\\charl\\OneDrive"
//            + "\\Desktop\\School\\2nd Year Sem 2\\CPSC 210\\DogFace.jpg";

    // List of pets has pets
    ListOfPets listOfPets = new ListOfPets();

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        super("Tamagotchi App");
        initFrame();
        initSoundManager();
//        initIcon();
        initButtonPanel();
        initPetStatusPanel();
        initPanel();
        psp.updatePetMenu();
        updateDisplay();
    }

    // MODIFIES: this
    // EFFECTS: forces the GUI to update and display any changes that have been made
    public void updateDisplay() {
        invalidate();
        validate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: initializes parameters for JFrame
    public void initFrame() {
        setVisible(true);
        setSize(730,670);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // This never fully worked
//    // MODIFIES: this
//    // EFFECTS: initializes icon for JFrame
//    public void initIcon() {
//        image = Toolkit.getDefaultToolkit().getImage("/project_e9c2b/DogFace.jpg");
//        this.setIconImage(image);
//        imageIcon = new ImageIcon("/project_e9c2b/DogFace.jpg");
//    }

    // MODIFIES: this, JPanel
    // EFFECTS: initializes parameters for panel and adds it to the JFrame
    public void initPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2));
        add(mainPanel);

        mainPanel.add(bp);
        mainPanel.add(psp);
    }

    public void initSoundManager() {
        sound = new SoundManager();
    }

    // MODIFIES: this, JPanel
    // EFFECTS: initializes all buttons for GUI
    public void initButtonPanel() {
        bp = new ButtonPanel(this);
    }

    public void initPetStatusPanel() {
        psp = new PetStatusPanel(this);
    }

}
