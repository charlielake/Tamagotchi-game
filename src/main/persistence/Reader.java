package persistence;

import model.Cat;
import model.Dog;
import model.ListOfPets;
import model.Pet;
import ui.TamagotchiApp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static ListOfPets readTamagotchiApp(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string containing
    // the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a TamagotchiApp parsed from a string which contains data for
    // a TamagotchiApp
    private static ListOfPets parseContent(List<String> fileContent) {
        ListOfPets petData = new ListOfPets();

        for (String line: fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            petData.addPet(parsePets(lineComponents));
        }

        return petData;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 6 where element 0 represents the
    // name of the next pet to be constructed, element 1 represents
    // the hunger, elements 2 represents the happiness, element 3 represents
    // the energy of the account to be constructed, element 4 represents the health
    // and element 5 represents the type of pet (cat/dog/other)
    // EFFECTS: returns an account constructed from components
    private static Pet parsePets(List<String> components) {
        Pet loadPet;
        String petName = components.get(0);
        int petHunger = Integer.parseInt(components.get(1));
        int petHappiness = Integer.parseInt(components.get(2));
        int petEnergy = Integer.parseInt(components.get(3));
        String petHealth = components.get(4);
        String petType = components.get(5);
        if (petType.equals("Dog")) {
            loadPet = new Dog(petName);
        } else {
            loadPet = new Cat(petName);
        }
        loadPet.setHunger(petHunger);
        loadPet.setHappiness(petHappiness);
        loadPet.setEnergy(petEnergy);
        loadPet.setPetHealth(petHealth);
        return loadPet;
    }
}
