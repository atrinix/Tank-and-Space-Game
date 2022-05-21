package tankwars.powerup;

import tankwars.LoadResources;
import tankwars.CollisionObject;
import tankwars.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Power-up which Speeds up Tank
 */

public class SpeedBoost extends PowerUp{

        private int x, y;
    private Rectangle rect;
        public SpeedBoost(int x, int y){
            this.y = y;
            this.x = x;
            this.rect = new Rectangle(x, y, img.getWidth(), img.getHeight());
        }
        
        private static BufferedImage img = LoadResources.SpeedBoost.getImage();
        private boolean collided = false;
        
        @Override
        public void CollideCheck(CollisionObject CO) {
            if(CO instanceof Tank){
                if(this.getRect().intersects(CO.getRect())){
                    collided = true;
                    ((Tank)CO).setSpeed(2);
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
