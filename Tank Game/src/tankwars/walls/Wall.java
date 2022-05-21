package tankwars.walls;

/**
 * Abstract Wall class, implements the collision class, used for walls
 */

import tankwars.CollisionObject;
import java.awt.*;

public abstract class Wall implements CollisionObject {

    public Wall(){}

    @Override
    public Rectangle getRect() {
        return null;
    }
    public void drawImage(Graphics2D drawImage){ }
    public abstract boolean impact();
}
