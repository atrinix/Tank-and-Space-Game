package Game;

import java.awt.*;

public class scoreHandler {
    public int points = 0;
    public long lostPoints = System.currentTimeMillis();

    public scoreHandler() {
        this.points = 150;

    }

    public void setPoints() {
        this.points = this.points + 150;
    }

    public int getPoints() {
        return this.points;
    }

    public void drawImage(Graphics g) {
        Graphics2D drawText = (Graphics2D) g;

        if (GameWorld.state == GameWorld.state_of_game.game) {
            Font myFont = new Font("Arial", 1, 35);

            drawText.setColor(Color.LIGHT_GRAY);
            drawText.setFont(myFont);
            drawText.drawString("Money earned: $ " + this.points, GameWorld.SCREEN_WIDTH / 2, 40); }

            if (GameWorld.state == GameWorld.state_of_game.win) {
                Font myFont3 = new Font("Courier New", 1, 30);
                drawText.setColor(Color.WHITE);
                drawText.setFont(myFont3);
                drawText.drawString("Congratulations you delivered all the mail!", GameWorld.SCREEN_WIDTH / 2 - 340, 300);
                drawText.drawString("You earned " + this.points + " dollars.", GameWorld.SCREEN_WIDTH / 2 - 220, 400);

            }

            if (GameWorld.state == GameWorld.state_of_game.over) {
                Font myFont2 = new Font("Arial", 1, 30);
                drawText.setColor(Color.WHITE);
                drawText.setFont(myFont2);
                drawText.drawString("You failed your mission.", GameWorld.SCREEN_WIDTH / 2 - 180, 300);
                drawText.drawString("You also earned " + this.points + " dollars.", GameWorld.SCREEN_WIDTH / 2 - 230, 400);
                drawText.drawString("But you're dead anyways so you can't even use it.", GameWorld.SCREEN_WIDTH / 2 - 370, 500);
            }
        }
    }



