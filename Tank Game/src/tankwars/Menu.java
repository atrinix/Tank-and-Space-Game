package tankwars;

import java.awt.*;

/**
 * This class was used to help me reference where to
 * put the menu buttons when making the title screen and help screen
 * Not in use now
 */

public class Menu {

    public void drawImage(Graphics menuGraph) {

        Font name = new Font("Cambria", Font.BOLD, 55);
        menuGraph.setFont(name);
        menuGraph.setColor(Color.BLACK);

        menuGraph.drawString("Help", 70, 575);
        menuGraph.drawString("Start", 335, 545);
        menuGraph.drawString("Exit", 680, 575);

        menuGraph.setColor(Color.white);
        menuGraph.drawRoundRect(70, 575, 180, 70, 10, 10);
        menuGraph.drawRoundRect(335, 545, 260, 100, 10, 10);
        menuGraph.drawRoundRect(680, 575, 180, 70, 10, 10);

        //Help Back button
        menuGraph.drawRoundRect(20, 10, 180, 70, 10, 10);
    }
}
