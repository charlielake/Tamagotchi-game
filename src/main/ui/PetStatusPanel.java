package ui;

import model.Pet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.ButtonPanel.BUTTON_HEIGHT;
import static ui.ButtonPanel.BUTTON_WIDTH;

public class PetStatusPanel extends JPanel implements ActionListener {

    GUI gui;
    // Bars to represent statuses of currently selected pet
    private JProgressBar hunger;
    private JProgressBar energy;
    private JProgressBar happiness;
    // Menu to select currentPet from
    JComboBox petList;

    // Constructor, initializes all JProgressBars and dropDowns on this panel and adds them to this panel
    public PetStatusPanel(GUI gui) {
        this.gui = gui;

        initProgressBars();
        initPetMenu();
        setLayout(null);
    }

    public void initProgressBars() {
        initHungerBar();
        initEnergyBar();
        initHappinessBar();
    }

    // MODIFIES: this
    // EFFECTS: initializes parameters for energy bar
    public void initEnergyBar() {
        energy = new JProgressBar();
        energy.setString("Energy");
        energy.setBorderPainted(true);
        energy.setStringPainted(true);
        energy.setVisible(true);
        energy.setBounds(90, 90, BUTTON_WIDTH + 60, BUTTON_HEIGHT + 15);
        add(energy);
    }

    // MODIFIES: this
    // EFFECTS: initializes parameters for hunger bar
    public void initHungerBar() {
        hunger = new JProgressBar();
        hunger.setString("Hunger");
        hunger.setBorderPainted(true);
        hunger.setStringPainted(true);
        hunger.setVisible(true);
        hunger.setBounds(90, 10, BUTTON_WIDTH + 60, BUTTON_HEIGHT + 15);
        add(hunger);
    }

    // MODIFIES: this
    // EFFECTS: initializes parameters for happiness bar
    public void initHappinessBar() {
        happiness = new JProgressBar();
        happiness.setString("Happiness");
        happiness.setBorderPainted(true);
        happiness.setStringPainted(true);
        happiness.setVisible(true);
        happiness.setBounds(90, 170, BUTTON_WIDTH + 60, BUTTON_HEIGHT + 15);
        add(happiness);
    }

    public void initPetMenu() {
        petList = new JComboBox();
        petList.addActionListener(this);
        petList.setBounds(150, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
        petList.setVisible(true);
        add(petList);
    }

    // MODIFIES: this
    // EFFECTS: updates pet status and status bar displays
    public void updateStatusBars() {
        if (!gui.listOfPets.isEmpty()) {
            updatePetMenu();
            int tempEnergy = gui.currentPet.checkEnergy();
            int tempHunger = gui.currentPet.checkHunger();
            int tempHappiness = gui.currentPet.checkHappiness();

            energy.setValue(tempEnergy);
            hunger.setValue(tempHunger);
            happiness.setValue(tempHappiness);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes pet menu that allows user to select what pet they'd like to monitor
    // action command set afterwards to ensure no event is triggered by index of pet changing
    public void updatePetMenu() {
        String[] petStrings = updatePetMenuOptions();

        petList.setModel(new DefaultComboBoxModel(petStrings));
        petList.setSelectedIndex(gui.listOfPets.getListOfPets().indexOf(gui.currentPet));
        petList.setActionCommand("Pet Change");
    }

    // EFFECTS: returns a list of all pet names currently in list of pets
    public String[] updatePetMenuOptions() {
        String[] petNames = new String[gui.listOfPets.getListOfPets().size()];
        for (Pet p : gui.listOfPets.getListOfPets()) {
            int index = gui.listOfPets.getListOfPets().indexOf(p);
            petNames[index] = p.checkName();
        }
        return petNames;
    }

    // MODIFIES: this
    // EFFECTS: response to new pet being selected from dropDown menu, updates value of currentPet field and
    // causes status bars to update
    public void updateCurrentPet(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String petName = (String) cb.getSelectedItem();
        Pet tempPet = gui.listOfPets.findPet(petName);
        gui.currentPet = tempPet;
        updateStatusBars();
    }

    // MODIFIES: GUI, this
    // EFFECTS: passes ActionEvent to actionPerformed method in GUI to handle effects based on which button is
    // pressed - here should only ever pass dropDown menu ActionEvent
    public void actionPerformed(ActionEvent e) {
        gui.sound.playSound("buttonClick.wav");
        if (e.getActionCommand().equals("Pet Change")) {
            updateCurrentPet(e);
        }
        updateStatusBars();
        gui.updateDisplay();
    }
}
