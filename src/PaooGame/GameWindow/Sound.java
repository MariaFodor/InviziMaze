package PaooGame.GameWindow;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Sound {
    void playMsuic(String musicLocation){
        try{
            File musicPath = new File(musicLocation);
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                //JOptionPane.showMessageDialog(null,"Press ok to stop playing");
            }
            else
            {
                System.out.println("can't find file");
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void Stop(String musicLocation)
    {
        try{
            File musicPath = new File(musicLocation);
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.stop();

            }
            else
            {
                System.out.println("can't find file");
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    }

