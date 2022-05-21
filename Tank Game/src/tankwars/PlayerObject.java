package tankwars;

import java.awt.*;

/**
 * Some code used from Professors TRE code
 * Abstract Class for organization purposes and the controls
 */

public abstract class PlayerObject {

    int x;
    int y;

    protected boolean UpPressed;
    protected boolean DownPressed;
    protected boolean RightPressed;
    protected boolean LeftPressed;
    protected boolean ShootPressed;

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void toggleShootPressed() {
        this.ShootPressed = true;
    }

    void unToggleShootPressed() {
        this.ShootPressed = false;
    }


    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    public abstract void update();

    public abstract void drawImage(Graphics g);

}

