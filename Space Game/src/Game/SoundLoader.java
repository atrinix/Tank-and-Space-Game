package Game;

/**
 *THIS CODE WAS REFERENCED FROM THE AIRSTRIKE GAME
 */
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader{
    private static boolean wl = false;
    private AudioInputStream soundStream;
    private String soundFile;
    private Clip clip;
    private Clip activeClip;
    private int type;//1 for sounds that needs to be played all the time
    // 2 for sounds that only need to be played once


    public SoundLoader(int type, String soundFile) {
        this.soundFile = soundFile;
        this.type = type;
        try {
            soundStream = AudioSystem.getAudioInputStream(SoundLoader.class.getClassLoader().getResource(soundFile));
            clip = AudioSystem.getClip();
            clip.open(soundStream);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "No sound documents are fouond");
        }
        if (this.type == 1) {
            Runnable myRunnable = new Runnable() {
                public void run() {
                  //  if(GameWorld.state == GameWorld.state_of_game.game ) {
                  //      clip.stop(); }

                        clip.start();
                        clip.loop(clip.LOOP_CONTINUOUSLY);
                     //   System.out.println("Loop test");
                    while (wl == false) {
                        System.out.print("");
                        if(GameWorld.state == GameWorld.state_of_game.game) {
                    //        System.out.println("LOOP TRUE");
                            clip.stop();
                            wl = true;
                            System.out.println("LOOP false");}

                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SoundLoader.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
            Thread thread = new Thread(myRunnable);
            thread.start();
        } else if (this.type == 2) {
            clip.start();
        }
        else if (this.type == 3)
        {
            clip.start();
        }
            }

    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
}
