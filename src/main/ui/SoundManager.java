package ui;

import model.Pet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    // EFFECTS: finds location of soundName passed in, then play sound
    public void playSound(String soundName) {
        try {
            File soundFile = new File("./data/" + soundName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            // this is due to a missing sound file
            ex.printStackTrace();
        }
    }

    // EFFECTS: calls bark/meow based on what type the pet is (cat vs dog)
    public void defaultSound(Pet pet) {
        String petClass = pet.getClass().getSimpleName();
        if (petClass.equals("Cat")) {
            playSound("catMeow.wav");
        } else if (petClass.equals("Dog")) {
            playSound("dogBark.wav");
        }
    }

}
