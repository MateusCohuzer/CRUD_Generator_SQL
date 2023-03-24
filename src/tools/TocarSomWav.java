package tools;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class TocarSomWav extends JFrame {

    // Constructor
    public TocarSomWav(String arquivo) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(true);

        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource(arquivo);//sons dentro de src
            System.out.println("url " + url);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("não suportado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("nao achou");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("outro erro");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TocarSomWav("wav/siren1.wav");
    }
}
