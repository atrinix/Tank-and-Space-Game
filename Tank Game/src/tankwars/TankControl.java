package tankwars;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This code is used to help determine the inputs from the player
 * Code used from TankRotationExample from Professor's iLearn site
 */

public class TankControl implements KeyListener {

    private Tank Tank1;
    private int left;
    private int right;
    private int down;
    private int up;
    private int shoot;

    //Public so GameWindow can Access
    protected TankControl(Tank Tank1, int up, int down, int left, int right, int shoot) {
        this.Tank1 = Tank1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();

        if (keyPressed == up) {
            this.Tank1.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.Tank1.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.Tank1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.Tank1.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.Tank1.toggleShootPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        if (keyReleased  == up) {
            this.Tank1.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.Tank1.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.Tank1.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.Tank1.unToggleRightPressed();
        }
        if (keyReleased  == shoot) {
            this.Tank1.unToggleShootPressed();
        }

    }
}
