package tankwars.walls;

/**
 * UnBreakable Wall class, inherited from abstract Wall Class
 */

import tankwars.CollisionObject;
import tankwars.LoadResources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall {

    private static final BufferedImage wallImg = LoadResources.UnbreakableWall.getImage();
    public void drawImage(Graphics2D drawImage){
        drawImage.drawImage(wallImg, x, y, null);
    }

    private int x, y;
    private Rectangle rect;
    public UnbreakableWall(int x, int y){
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    @Override
    public boolean impact() {
        return false;
    }
    
    @Override
    public void CollideCheck(CollisionObject CO) { }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }
    

}
