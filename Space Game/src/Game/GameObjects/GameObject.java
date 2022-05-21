package Game.GameObjects;

import Game.GameWorld;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class GameObject  {
    protected int x, y, a, vx, vy, angle,height,width;
    protected boolean b, active = true;
    protected double R = 0.5;
    Rectangle rectangle;
    BufferedImage image;

    public GameObject(){}

    public GameObject(int x,int y){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        height = image.getHeight();
        width = image.getWidth();
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.image.getWidth()/2.0 , this.image.getHeight()/2.0);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.image, rotation, null);

    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * I moved these from tank class to game object so the asteroid and moon can do this
     */

        public void checkBorder() {
        if (x <= 0) {
            x = GameWorld.SCREEN_WIDTH-30;
      //  System.out.println("LEFT TO RIGHT");
        }
        if (x >= GameWorld.SCREEN_WIDTH+30) {
            x = 0;
         //   System.out.println("RIGHT TO LEFT");
        }
        if (y <= 0){
            y = GameWorld.SCREEN_HEIGHT-30;
         //   System.out.println("DOWN TO UP");
        }
        if (y >= GameWorld.SCREEN_HEIGHT+30) {
            y = 0;
         //   System.out.println("UP TO DOWN");
        }
    }

    //Copied from tank game
    public void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }


    //Setters and getters
    void setX(int x_to_set) {
        this.x = x_to_set;
    }

    public int getX() {
        return this.x;
    }

    void setY(int y_to_set) {
        this.y = y_to_set;
    }

    public int getY() {
        return this.y;
    }


    void setVx(int vx) {
        this.vx = vx;
    }


    void setVy(int vy) {
        this.vy = vy;
    }


    void setAngle(int angle) {
        this.angle = angle;
    }

    void setR(double r) {
        this.R = r;
    }


    public void update(){
        moveForwards();
    }

    public Rectangle getRectangle(){
        return new Rectangle( x, y,this.image.getWidth()-6,this.image.getHeight()-6);

    }
    public Rectangle setRectangle(int x,int y,int width,int height){
        return this.rectangle = new Rectangle( x, y,width,height);
    }

    public abstract void collide(GameObject obj);

}
