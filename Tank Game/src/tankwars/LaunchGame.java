package tankwars;

/**
 * Clean Launcher class to start game
 * Some code borrowed from Professors TRE file
 */

public class LaunchGame {

    public static void main(String[] args) {

        GameWindow start = new GameWindow();
        try {
            start.init();
            while (true) {
                start.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
