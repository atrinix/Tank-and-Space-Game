
package Game;

import Game.GameObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class GameWorld extends JPanel {

    //Only Screen variables because no moving camera
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 672;

    public BufferedImage spaceArena;
    private Graphics2D drawImage;
    private JFrame frameWindow;
    private Rocket playerRocket;
    private BackDrop BGimage;
    private SoundLoader sp2, sp3, sp4;

    private ArrayList<GameObject> SpaceObjects;

    public enum state_of_game {
        menu, game, help, win, over, exit,
    }
    public static state_of_game state = state_of_game.menu;

    private static BufferedImage menuImage;
    private static BufferedImage HelpImage;
    private static BufferedImage youLost;

    private static int counter = 0;
    private static double speed = 1;

    private static boolean menucheck = false;
    private static boolean menucheck2 = true;

    /**
     * Launcher no longer having it's own class, all moved to GameWorld
     * @param args
     */

    public static void main(String[] args) {
        GameWorld spaceMail = new GameWorld();

        /**
         *         Menu Function that initializes the game in different parts so
         *         player doesn't die while in menu
         */

        while (menucheck != true) {
            if (state == GameWorld.state_of_game.menu && menucheck2 == true) {
                spaceMail.MenuInit();
                menucheck2 = false;
            }
            spaceMail.repaint();
            if (state == GameWorld.state_of_game.game) {
                menucheck = true;
            }
        }
        spaceMail.init(1);

        for (int level = 1; level <= 3 && GameWorld.state != GameWorld.state_of_game.over; level++) {
            boolean levelCheck = true;
            //  System.out.println(level);
            spaceMail.setLevel(level);
            speed = speed + 0.2;
            try {
                while (levelCheck && GameWorld.state == GameWorld.state_of_game.game) {
                    //   System.out.println(state + " " + levelCheck);
                    spaceMail.SpaceObjects.forEach(GameObjectList -> GameObjectList.update());
                    levelCheck = spaceMail.checkCollision();

                    if (GameWorld.state == GameWorld.state_of_game.over) {
                        spaceMail.SpaceObjects.clear();
                    }
                    spaceMail.repaint();
                    Thread.sleep(1000 / 75);
                }
            } catch (InterruptedException ignored) {
                System.out.println(ignored);
            }
        }
        if (GameWorld.state == GameWorld.state_of_game.over) { //If player has lost
            spaceMail.repaint();
        } else { //If player has won
                  System.out.println("out of game loop");
            spaceMail.SpaceObjects.clear();
            GameWorld.state = GameWorld.state_of_game.win;
            spaceMail.repaint();
        }
    }

    /**
     * Called once when in menu, game not started yet, flaw fixed from tank game
     */
    private void MenuInit() {
        this.frameWindow = new JFrame("Galactic Mail Second Game");
        this.spaceArena = new BufferedImage(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        LoadResources.init();
        BGimage = new BackDrop(LoadResources.spacebg.getImage());
        SpaceObjects = new ArrayList<>();

        playerRocket = new Rocket(470, 330, 0, 0, 0);
        //Special loop for menu only
        this.sp2 = new SoundLoader(1,"menuloop.wav");

        //Frame Window stuff,  copy pasted from tank game

        this.addMouseListener(new MouseListening());
        this.frameWindow.setLayout(new BorderLayout());
        this.frameWindow.add(this);
        this.frameWindow.setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 30);
        this.frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameWindow.setResizable(false);
        this.frameWindow.setVisible(true);
        frameWindow.setLocationRelativeTo(null);
        
    }

    /**
     * Starts game AFTER menu is selected
     * Differently implemented from tank game
     * @param level
     */
    private void init(int level) {

        this.sp3 = new SoundLoader(2,"startgame.wav");
        this.sp4 = new SoundLoader(2,"spacesong.wav");

        setLevel(level);
            ControlRocket shipControl = new ControlRocket(playerRocket,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_SPACE);
            this.frameWindow.addKeyListener(shipControl);

        }

    /**
     * I moved the create map function into GameWorld for easier access of
     * the list of game object variables, also it feels faster to run this way
     */

    private int[] m1 = {
            0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,
            0,	0,	2,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	2,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	2,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0
    };

    private int[] m2 = {
            1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,
            0,	0,	0,	0,	2,	0,	0,	0,	0,	0,	0,	2,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	2,	0,	0,	0,	0,	0,	0,	2,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1
    };

    private int[] m3 = {
            1,	0,	0,	0,	0,	0,	0,	0,	2,	0,	0,	0,	0,	0,	0,
            0,	0,	2,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,
            0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	2,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	2,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,
            0,	2,	0,	0,	0,	0,	0,	2,	0,	0,	0,	0,	0,	0,	0,
            0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0
    };
    

    private int[] emptymap = {};

    public void setLevel(int level) {
        if (level == 1) {
             emptymap  = m1;
        } else if (level == 2) {
            emptymap = m2;
        } else if (level == 3) {
            emptymap = m3;
        } 

        int row = 0;
        int map = 0;
        int ang = 0; //For random angles

        for (int i = 0; i < 10; i++) {
         //   System.out.print(i + "i ");
            for (int j = 0; j < 15; j++) {
              //  System.out.print(j + "j ");
                if (row == 10) {
                    row = 0;
                }
                int placeholder = emptymap [map];
                ang = 1 + (int) (Math.random() * 359);
                if (placeholder != 0) {
                    if (placeholder == 1) {
                     //   System.out.print("asteroid placed");
                     //   System.out.print("map: " + map);
                        Asteroid obstacle= new Asteroid(j * 50, i * 50, 10, 10, ang, 1);
                        SpaceObjects.add(obstacle);
                    }
                    if (placeholder == 2) {
                      //  System.out.print("moon placed");
                        moonDelivery moon;
                        if (level > 1) {
                            moon = new moonDelivery(j * 64, i * 64, 10, 10, ang, speed, false);
                          //  System.out.print("2 speed "+ speed);
                        }
                        else {
                         //   System.out.print(" 1 speed "+ speed);
                            moon = new moonDelivery(j * 64, i * 64, 3, 3, ang, 0, false);
                        }
                        SpaceObjects.add(moon);
                    }
                }
                    row++;
                    map++;
                }
            }
            SpaceObjects.add(playerRocket);
        }

        public boolean checkCollision() {

        int moonCount = 0;

        for (int i = 0; i < SpaceObjects.size(); i++) {
            if(SpaceObjects.get(i) instanceof moonDelivery){
                moonCount +=1;
            }
            if (SpaceObjects.get(i).isActive()) {
                //Changed collisions from last game to fit better
                if (i != SpaceObjects.size() - 1 && ( playerRocket.getRectangle().contains(SpaceObjects.get(i).getX()+28,SpaceObjects.get(i).getY()+28) || playerRocket.getRectangle().contains(SpaceObjects.get(i).getX()+9,SpaceObjects.get(i).getY()+9))){

                    if (SpaceObjects.get(i) instanceof moonDelivery) {
                        //If collided with a moon
                        playerRocket.collide(SpaceObjects.get(i));
                    }

                    if (SpaceObjects.get(i) instanceof Asteroid) {
                        //If collided with a meteor
                        playerRocket.collide(SpaceObjects.get(i));
                        SpaceObjects.get(i).collide(playerRocket);
                    }
                }
            }
            else{
                SpaceObjects.remove(i);
            }
        }
        if(moonCount == 0){
           return false;
        }
        return true;
    }



    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        menuImage = LoadResources.logo.getImage();
        HelpImage = LoadResources.HelpImage.getImage();
        youLost = LoadResources.spacebg.getImage();

        if (state == state_of_game.menu) {       //menu state
            (g).drawImage(menuImage, 0, 0, SCREEN_WIDTH + 2, SCREEN_HEIGHT, null);
            //m.drawImage(g);
        } else if (state == state_of_game.help) {  //help state
            (g).drawImage(HelpImage, 0, 0, SCREEN_WIDTH + 2, SCREEN_HEIGHT, null);
        } else if (state == state_of_game.exit) { //exiting
            frameWindow.dispose();
            System.exit(0);
        }

        //Paints a bunch of stuff in game
        else if (state == state_of_game.game) {
            drawImage = spaceArena.createGraphics();
            this.BGimage.drawImage(drawImage);
            //    this.background.drawImage(drawImage);
            this.playerRocket.drawImage(drawImage);

            for (int i = 0; i < this.SpaceObjects.size(); i++) {
                this.SpaceObjects.get(i).drawImage(this.drawImage);
            }
            g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            g2.drawImage(spaceArena, 0, 0, null);
            playerRocket.scoreHandler.drawImage(g2);
        }
        else if (GameWorld.state == GameWorld.state_of_game.over || GameWorld.state == GameWorld.state_of_game.win ) {
            (g).drawImage(youLost, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            playerRocket.scoreHandler.drawImage(g2);
        }
    }
}
