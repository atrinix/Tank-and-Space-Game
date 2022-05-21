package Game;

import Game.GameObjects.Rocket;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Most was copy pasted from the tank game, but I removed the
 * unecessary keys that wern't in use for galactic mail
 */
public class ControlRocket implements KeyListener {

    private Rocket spaceship;

    private final int right;
    private final int left;
    private final int shoot;
    
    public ControlRocket(Rocket ship, int left, int right, int shoot) {
        this.spaceship = ship;

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

        if (keyPressed == left) {
            this.spaceship.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.spaceship.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.spaceship.toggleShootPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        if (keyReleased  == left) {
            this.spaceship.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.spaceship.unToggleRightPressed();
        }
        if (keyReleased == shoot) {
            this.spaceship.unToggleShootPressed();
        }
    }
}
