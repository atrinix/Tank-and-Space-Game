package Game.GameObjects;
import Game.LoadResources;

import java.awt.*;

public class moonDelivery extends GameObject {

    private Rectangle rectangle;
    private int r =0;
    private int angle =0;

    public moonDelivery(int x, int y,int vx, int vy, int angle, double r, boolean b) {
        this.setVx(vx);
        this.setVy(vy);
        this.setX(x);
        this.setY(y);
        this.setR(r);
        this.setAngle(angle);
        this.b = true;
        this.active = true;
        this.image = LoadResources.moonbase.getImage();
        this.rectangle = setRectangle(this.x,this.y,this.image.getWidth(),this.image.getHeight());
        this.active = true;
    }


    @Override
    public void collide(GameObject CollisionObject) {
        this.active = false;
        this.b = false;
    }
}
