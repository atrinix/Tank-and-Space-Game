package tankwars;

/**
 * This class handles how the background image would loop
 * as it's drawn to the screen
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackDrop extends JPanel {
    private BufferedImage background = LoadResources.BGimage.getImage();

    //initializes Background image
    private void loadImage(BufferedImage img) {this.background = img;}
    public BackDrop(BufferedImage img) { loadImage(img); }

    //Loop to continuously draw background
    void drawImage(Graphics g) {
        Graphics2D gBG = (Graphics2D) g;

        for(int i = 0; i < GameWindow.WORLD_WIDTH; i += background.getWidth()){
            for(int j = 0; j < GameWindow.WORLD_HEIGHT; j += background.getHeight()){
                gBG.drawImage(this.background, i, j, null);
            }
        }
    }
}
