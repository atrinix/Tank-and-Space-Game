package Game.GameObjects;

import Game.GameWorld;
import Game.scoreHandler;
import java.awt.image.BufferedImage;
import java.awt.*;
import Game.*;



public class Rocket extends GameObject {

    SoundLoader sp;

    private boolean testplay = false;

    private BufferedImage shipImage;

    private long soundTime=0;
    private final double R = 1;
    private double turnSpeed = 1;

    public void setTurn(double turn_to_set) {
        this.turnSpeed  = turn_to_set;
    }

    public double getTurn() {
        return this.turnSpeed ;
    }

    protected boolean asteroidcheck;

    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean UpPressed;
    private boolean DownPressed;

    private boolean LaunchPressed;
    private boolean onMoon = false;

    private Rectangle lastPosition;

    public scoreHandler scoreHandler;



    public Rocket(int x, int y, int vx, int vy, int angle)  {
        this.setX(x);
        this.setY(y);
        this.setVx(vx);
        this.setVy(vy);
        this.setAngle(angle);
        this.isActive();

        shipImage = LoadResources.spaceship.getImage();
        this.image = shipImage;

      /*  if (this.onMoon){
            this.rectangle = setRectangle(this.x,this.y,this.image.getWidth(),this.image.getHeight());
        }*/

      //  System.out.println("TEST IF IN THIS LOOP");
        this.rectangle = setRectangle(this.x,this.y,this.image.getWidth()-40,this.image.getHeight()-40);

        this.scoreHandler = new scoreHandler();
    }


    // Not in use
    public void toggleUpPressed() {
        this.UpPressed = true;
    }
    public void toggleDownPressed() {
        this.DownPressed = true;
    }
    public void unToggleUpPressed() {
        this.UpPressed = false;
    }
    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    //In use
    public void toggleRightPressed() {
        this.RightPressed = true;
    }
    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }
    public void toggleShootPressed() {
        this.LaunchPressed = true;
       // System.out.println("takeoff");
    }
    public void unToggleRightPressed() {
        this.RightPressed = false;
    }
    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }
    public void unToggleShootPressed() {
        this.LaunchPressed = false;
    }

    /**
     * These classes below are taken directly from the tank game for controls
     */
    
    private void rotateLeft() {
        this.angle -= this.turnSpeed;
    }
    private void rotateRight() {
        this.angle += this.turnSpeed;
    }
    private void moveBackwards() {

        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    @Override
    public void moveForwards() {
            vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            x += vx;
            y += vy;
            checkBorder();
    }


    /**Moved colision for rocket into its own class
     * Handles what happens if objects collide
     * 
     * @param CollisionObject
     */

    public void collide(GameObject CollisionObject) {
        
        if (CollisionObject instanceof Asteroid) {
            if (onMoon == false) {
                asteroidcheck = true;
                if (System.currentTimeMillis() - soundTime > 1000) { 
                    //Added delay in case of multiple asteroid hitting at once
                    this.sp = new SoundLoader(2,"explodesound.wav");
                    soundTime = System.currentTimeMillis(); }
                GameWorld.state = GameWorld.state_of_game.over;
            }
        }
        
        if (CollisionObject instanceof moonDelivery) {
                if (GameWorld.state != GameWorld.state_of_game.over) {
                    if(testplay == false) {
                        this.sp = new SoundLoader(2,"Bonus.wav");
                        testplay = true;
                    }

                this.onMoon = true;
                this.setX(CollisionObject.x);
                this.setY(CollisionObject.y);
                this.setVx(CollisionObject.vx);
                this.setVy(CollisionObject.vy);
                this.turnSpeed = 2; //Easier to aim while on moon
                
           //     System.out.println( "shared x: " + CollisionObject.x + "     shared y: "+ CollisionObject.y);
                if ((System.currentTimeMillis() - scoreHandler.lostPoints) > 20) {
                    scoreHandler.points = scoreHandler.points - 1;
                    scoreHandler.lostPoints = System.currentTimeMillis();
                }

                if (this.LaunchPressed) {
              //      this.rectangle = setRectangle(this.x,this.y,this.image.getWidth()+500,this.image.getHeight()+500);
                    //this.rectangle = setRectangle(this.x,this.y,this.image.getWidth(),this.image.getHeight());
                    this.sp = new SoundLoader(2,"Launch.wav");
                    CollisionObject.collide(CollisionObject);
                    scoreHandler.setPoints();
                    this.turnSpeed = 1;
                    this.onMoon = false;

                   if ((System.currentTimeMillis() - soundTime) > 500) {
                    testplay = false;
                    soundTime = System.currentTimeMillis();}
                }
            }
        }
    }

    public void update() {
        lastPosition = getRectangle();

        /**
         * Removed if statement for moveForwards since this will always be toggled
         */
        this.moveForwards();

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
/**
 * REMOVED BULLET IF STATEMENT
 */
    }

}

