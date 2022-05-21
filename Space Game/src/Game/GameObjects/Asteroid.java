package Game.GameObjects;

import java.awt.*;

import Game.LoadResources;

public class Asteroid extends GameObject {
    private Rectangle rectangle;
    private final double R = 1;

    public Asteroid(int x, int y,int vx, int vy, int angle, int r) {
        this.setX(x);
        this.setY(y);
        this.setVx(vx);
        this.setVy(vy);
        this.setR(r);
        this.setAngle(angle);
        this.active = true;
        this.image = LoadResources.asteroid.getImage();
        this.rectangle = setRectangle(x,y,this.image.getWidth(),this.image.getHeight());
    }

    @Override
    public void collide(GameObject CollisionObject) {
    }
}
