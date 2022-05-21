package tankwars.powerup;

import tankwars.CollisionObject;

import java.awt.*;

/**
 * Abstract class which acts as the template for the 3 powerups
 * Makes it easier to organize
 */

public abstract class PowerUp implements CollisionObject{
    
    @Override
    public void CollideCheck(CollisionObject CO) { }

    @Override
    public Rectangle getRect() { return null; }
    public void drawImage(Graphics2D drawImage){ }
    public abstract boolean impact();
}
