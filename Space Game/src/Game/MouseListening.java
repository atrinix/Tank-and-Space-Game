package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse listener class so the user can interact with the menu
 *
 *
 */

public class MouseListening implements MouseListener {

    private int MouseX;
    private int MouseY;
    protected static boolean ingame = false;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        MouseX = e.getX();
        MouseY = e.getY();
        System.out.println("click");

        if (MouseY <= GameWorld.SCREEN_HEIGHT && MouseX <= GameWorld.SCREEN_WIDTH ) {
            if (MouseX >= 0 && MouseX >= 0 && GameWorld.state == GameWorld.state_of_game.menu) {
                System.out.println("game click");
                ingame = true;
                GameWorld.state = GameWorld.state_of_game.game;
            }
        }

        }

   /*     if (MouseY >= 555 && MouseY <= 80 + 555) {
            if (MouseX >= 335 && MouseX <= 335 + 260 && GameWorld.state != GameWorld.state_of_game.help) {
                System.out.println("game click");
                ingame = true;
                GameWorld.state = GameWorld.state_of_game.game;
            }  //Starts game from menu as long as not in HELP page
            else if (MouseX >= 680 && MouseX <= 680 + 180 && GameWorld.state == GameWorld.state_of_game.menu) {
                System.out.println("help");
                GameWorld.state = GameWorld.state_of_game.exit;
            } //Exits game if in Menu
            else if (MouseX >= 70 && MouseX <= 70 + 180 && GameWorld.state == GameWorld.state_of_game.menu) {
                System.out.println("help");
                GameWorld.state = GameWorld.state_of_game.help;
            } //Goes to HELP page from Menu
        }


        /**
         * This is so the user can return to the main menu after accessing the help page

        if (GameWorld.state == GameWorld.state_of_game.help) {
            if (MouseX >= 20 && MouseX <= 200) {
                if (MouseY >= 10 && MouseY <= 70) {
                    GameWorld.state = GameWorld.state_of_game.menu;
                }
            }
        }
    }*/

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
