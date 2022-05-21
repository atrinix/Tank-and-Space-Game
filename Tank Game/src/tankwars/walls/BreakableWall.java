package tankwars.walls;

/**
 * Breakable Wall class, inherited from abstract Wall Class
 */

import tankwars.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {

    private static BufferedImage wallImg = LoadResources.BreakableWall.getImage();
    private boolean collided = false;

    private int x, y;
    private Rectangle rect;
    public BreakableWall(int x, int y){
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public void drawImage(Graphics2D drawImage){
        drawImage.drawImage(wallImg, x, y, null);
    }

    @Override
    public boolean impact() {
        return collided;
    }
    @Override
    public void CollideCheck(CollisionObject CO) {
        if(CO instanceof Bullet){
            if(this.getRect().intersects(CO.getRect())){
                collided = true;
            }
        }
    }
    @Override
    public Rectangle getRect() {
        return rect;
    }
    
}
