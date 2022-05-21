package tankwars.powerup;

import tankwars.LoadResources;
import tankwars.CollisionObject;
import tankwars.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Power-up which gives the tank an extra Life and restore Health
 */

public class ExtraLife extends PowerUp {

    private int x, y;
    private Rectangle rect;
    public ExtraLife(int x, int y){
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }
    
    private static BufferedImage img = LoadResources.ExtraLife.getImage();
    private boolean collided = false;

    //Used to print hearts above health bars
    public static BufferedImage getImg() { return img; }

    @Override
    public void CollideCheck(CollisionObject CO) {
        if(CO instanceof Tank){
            if(this.getRect().intersects(CO.getRect())){
                collided = true;
                ((Tank)CO).addLife();
                ((Tank)CO).powerHealth(100);
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
