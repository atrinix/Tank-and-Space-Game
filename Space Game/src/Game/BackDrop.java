package Game;

/**
 * This class handles how the background image would loop
 * as it's drawn to the screen
 *
 * This code was copied from tank game, with the excepton
 * of removing the for loop that iterated the tank game, and just
 * put in the entire image at once
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackDrop extends JPanel {
    private BufferedImage background = LoadResources.spacebg.getImage();

    //initializes Background image
    private void loadImage(BufferedImage img) {this.background = img;}
    public BackDrop(BufferedImage img) { loadImage(img); }

    //Loop to continuously draw background
    void drawImage(Graphics g) {
        Graphics2D gBG = (Graphics2D) g;

        gBG.drawImage(this.background, 0, 0, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, null);

    }
}
