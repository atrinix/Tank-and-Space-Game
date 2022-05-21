package tankwars;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * A lot of this code was helped created from the bullet
 * class shown in the professor's iLearn video
 */

public class Bullet implements CollisionObject {

    //Loads in images
    private static final BufferedImage bulletImg = LoadResources.Bullet.getImage();
    private static final BufferedImage explosion_img = LoadResources.Exploding.getImage();
    
    //Loads in ints and booleans
    private Rectangle rect;
    static private int bulletspeed = 5;
    private int x, y, angle, vx, vy;
    private boolean BulletSpawned = false;
    private boolean explodeCheck= true;
    private boolean collided = false;
    
    public boolean impact() {
        return collided;
    }
    
    /**
     * Below 3 classes used for testing purposes in Tank Class, ignore
     */
    private String bullet_owner;
    void setOwner(String owner) {
        this.bullet_owner = owner;
    }
    public static void setBSpeed(int value) { bulletspeed += value; }

    //Sets the values of the bullet, public so other classes can use
    public Bullet(int x, int y, int angle){
        this.vx = (int) Math.round(bulletspeed * Math.cos(Math.toRadians(angle)));
        this.vy = (int) Math.round(bulletspeed * Math.sin(Math.toRadians(angle)));
        this.x = x;
        this.y = y;
        this.angle =  angle;
        this.rect = new Rectangle(x, y, bulletImg.getWidth(), bulletImg.getHeight());
    }

    //If bullet is out of bounds, it makes bullets disappear
    private void checkBorder() { 
        if (x < 35) { //left border
            this.BulletSpawned = true;
        }
        if (x >= GameWindow.WORLD_WIDTH - 60) { //right border
            this.BulletSpawned = true;
        }
        if (y < 35) { //down border
            this.BulletSpawned = true;
        }
        if (y >= GameWindow.WORLD_HEIGHT - 60) { //up border
            this.BulletSpawned = true;
        }
    }

    //Checks status of bullet as it's shot
    public void update() {
        if (!collided) {  //keeps going if not collided
            this.x = x + vx;
            this.y = y + vy;
            this.checkBorder(); //checks for border
        }
        this.rect.setLocation(x,y);
    }

    //Draw class to draw bullet to screen, along with exolosion on impact
    public void drawImage(Graphics2D drawImage){
        AffineTransform turn = AffineTransform.getTranslateInstance(x, y);
        turn.rotate(Math.toRadians(angle), bulletImg.getWidth() / 2.0, bulletImg.getHeight() / 2.0);

        if (collided && explodeCheck) {
            drawImage.drawImage(explosion_img, turn, null);
            System.out.println("Bullet Impact!");
        } else {
            drawImage.drawImage(bulletImg, turn, null);
        }
    }
    
    //Inherited from template, checks collision
    @Override
    public void CollideCheck(CollisionObject CO) {
        if(this.getRect().intersects(CO.getRect())){
            collided = true;
        }
    }

    //Rectangle class inherited from Template
    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y, bulletImg.getWidth(), bulletImg.getHeight());
    }
}
