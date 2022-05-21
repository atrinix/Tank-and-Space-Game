package tankwars;
import java.awt.*;

/**
 * Interface used in most classes for collision check and Rectangle Class
 */

public interface CollisionObject {

    Rectangle getRect();
    void CollideCheck(CollisionObject CO);

}
