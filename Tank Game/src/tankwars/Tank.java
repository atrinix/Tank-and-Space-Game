package tankwars;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This code manages the tanks and their functions
 * One of the longer classes
 */

public class Tank extends PlayerObject implements CollisionObject  {

    //Setting variables
    private int x, y, vx, vy, angle;
    private Rectangle r;
    private int tankSpeed = 2;
    private final int rotateSpeed = 4;
    private int tankHealth = 100;
    private long LastFired = 0;
    private int tankLives = 3;
    private int damage = 10;
    Tank(int x, int y, int vx, int vy, int angle, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.image = image;
        this.angle = angle;
        this.r = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    public int getX() { return x; }
    public int getY() { return y; }

    private ArrayList<Bullet> bullets = new ArrayList<>();
    private String tanktag;
    private BufferedImage image;

    private boolean checkFire = false;

    /**
     * Below are Deprecated class for testing purposes
     */
    public int getdmg() { return damage; }
    public boolean getFire() { return checkFire; }
    String getTag() { return tanktag; }
    public void fireSwitch(){
        checkFire = true;
    }
    void setTankTag(String tag_to_set) {
        this.tanktag = tag_to_set;
    }

    /**
     * These classes handle the lives and health of tanks
     * along with the health powerup
     */

    public int getCurrentHealth() { return tankHealth; }
    public int getLives() { return this.tankLives; }
    public void addLife() { this.tankLives += 1; }

    public void powerHealth(int num){
        if(tankHealth + num >= 100)
            tankHealth = 100;
        else
            tankHealth += num;
    }

    public void removeLife() {
        if(tankLives == 0){
            tankHealth = 0;
        } else {
            tankLives -= 1;
            powerHealth(100);
        }
    }

    /**
     * Powerup Classes for speed and Defense
     */
    public void setSpeed(int num) { this.tankSpeed+=num; }
    public void setdmgForDefense(int num) { this.damage+=num; }

    /**
     * Every tick this is called so player can control tank in real time
     */

    public void update() {

        this.r.setLocation(x, y);

        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.ShootPressed && (System.currentTimeMillis() - LastFired > 200)) {
            this.shootBullet();//Bullet is spawned, added to array
            LastFired = System.currentTimeMillis();   //Sets delay between bullets to prevent spam
        }
        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).impact()) {
                bullets.remove(i);
            }else{
                bullets.get(i).update();
            }
        }
    }

    /**
     * More controls for tanks
     */
    private void rotateRight() {
        this.angle += this.rotateSpeed;
    }
    private void rotateLeft() {
        this.angle -= this.rotateSpeed;
    }
    private void moveForwards() {
        vx = (int) Math.round(tankSpeed * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(tankSpeed * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }
    private void moveBackwards() {
        vx = (int) Math.round(tankSpeed * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(tankSpeed * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    //Function for player to shoot bullet
    private void shootBullet() {
            Bullet b = new Bullet(this.x, this.y, this.angle);
            b.setOwner(tanktag);
            bullets.add(b);
        //    System.out.println(tanktag + " " + damage);
    }

    //Class to check border for moving class
    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameWindow.WORLD_WIDTH - 88) {
            x = GameWindow.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameWindow.WORLD_HEIGHT - 80) {
            y = GameWindow.WORLD_HEIGHT - 80;
        }
    }

    public void collided(int num){
        if(tankHealth - num <= 0){
            tankHealth = 0;
            //  System.out.println("Death!");
            removeLife();
        }
        else
            //    System.out.println("Health:" + this.tankHealth);
            tankHealth -= num;
    }
    //Collision handler
    @Override
    public void CollideCheck(CollisionObject CO) {
        if(this.getRect().intersects(CO.getRect())){
            if(CO instanceof Bullet){
                collided(damage);
             //   System.out.println(tanktag + " " + damage);
            } else {
                Rectangle intersection = this.getRect().intersection(CO.getRect()); //left
                if(intersection.height > intersection.width  && this.x < intersection.x){
                    x-= intersection.width/2;
                }
                else if(intersection.height > intersection.width  && this.x >CO.getRect().x){ //right
                    x+= intersection.width/2;
                }
                else if(intersection.height < intersection.width  && this.y < intersection.y){ //up
                    y-= intersection.height/2;
                }
                else if(intersection.height < intersection.width  && this.y >CO.getRect().y){ //down
                    y+= intersection.height/2;
                }
            }
        }
        for(Bullet b : bullets){
            b.CollideCheck(CO);
           CO.CollideCheck(b);
        }
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, image.getWidth(), image.getHeight());
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    /**
     * Draw code referenced from TankRotationExample in iLearn
     */
    @Override
    public void drawImage(Graphics g) {
        Graphics2D tankDraw = (Graphics2D) g;
        for(Bullet b : bullets){
            b.drawImage(tankDraw);
        }
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        tankDraw.drawImage(this.image, rotation, null);
    }

}
