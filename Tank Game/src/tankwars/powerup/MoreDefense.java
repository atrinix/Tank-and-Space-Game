package tankwars.powerup;

import tankwars.LoadResources;
import tankwars.CollisionObject;
import tankwars.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *  Power-up which gives tank more defense by decreasing the damege from other tank
 */

public class MoreDefense extends PowerUp{

    private int x, y;
    private Rectangle rect;
    public MoreDefense(int x, int y){
        this.y = y;
        this.x = x;
        this.rect = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }
    
    private static BufferedImage img = LoadResources.MoreDefense.getImage();
    private boolean collided = false;

    @Override
    public void CollideCheck(CollisionObject CO) {
        if(CO instanceof Tank){
            if(this.getRect().intersects(CO.getRect())){
                collided = true;
                ((Tank)CO).setdmgForDefense(-3);
                //Decreases other tank attack to 3, allowing player with power-up to survive more hits
            }
        }
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y, img.getWidth(), img.getHeight());
    }
    public void drawImage(Graphics2D drawImage){
        drawImage.drawImage(img, x, y, null);
    }
    public boolean impact() { return collided; }

}
