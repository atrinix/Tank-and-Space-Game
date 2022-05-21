package tankwars;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse listener class so the user can interact with the menu
 */

public class MouseListening implements MouseListener {

    private int MouseX;
    private int MouseY;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        MouseX = e.getX();
        MouseY = e.getY();

        /**
         * Print Statement for testing purposes
         * System.out.println(GameWindow.state + " x: " + MouseX + "  y: " + MouseY);
         *
         *  This is copied from the Menu class, so
         *  I can reference the mouse reader dimensions
         *
         *  menuGraph.drawRoundRect(70, 575, 180, 70, 10, 10);
         *  menuGraph.drawRoundRect(335, 545, 260, 100, 10, 10);
         *  menuGraph.drawRoundRect(680, 575, 180, 70, 10, 10);
        */

        if (MouseY >= 555 && MouseY <= 80 + 555) {
            if (MouseX >= 335 && MouseX <= 335 + 260 && GameWindow.state != GameWindow.state_of_game.help) {
                GameWindow.state = GameWindow.state_of_game.game;
            }  //Starts game from menu as long as not in HELP page
            else if (MouseX >= 680 && MouseX <= 680 + 180 && GameWindow.state == GameWindow.state_of_game.menu) {
                GameWindow.state = GameWindow.state_of_game.exit;
            } //Exits game if in Menu
            else if (MouseX >= 70 && MouseX <= 70 + 180 && GameWindow.state == GameWindow.state_of_game.menu) {
                GameWindow.state = GameWindow.state_of_game.help;
            } //Goes to HELP page from Menu
        }


        /**
         * This is so the user can return to the main menu after accessing the help page
         */
        if (GameWindow.state == GameWindow.state_of_game.help) {
            if (MouseX >= 20 && MouseX <= 200) {
                if (MouseY >= 10 && MouseY <= 70) {
                    GameWindow.state = GameWindow.state_of_game.menu;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
