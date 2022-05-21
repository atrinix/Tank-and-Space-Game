package tankwars;

/**
 * This is one of the bigger classes, where the game is generated,
 * from its objects being initialized, to the components being painted.
 * Also handles the game States
 */

import tankwars.powerup.ExtraLife;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel  {

    //THESE ARE PUBLIC BECAUSE OTHER CLASSES NEED TO ACCESS THEM
    public static final int SCREEN_HEIGHT = 672;
    public static final int SCREEN_WIDTH = 960;
    public static final int WORLD_HEIGHT = 1536; // 1920/32 = 60 walls high
    public static final int WORLD_WIDTH = 1536;  // 1536/32 = 48 walls wide
    private static int location;
    private static int placement;

    private static boolean gameOver = false;

    //Initialize the Images
    private static BufferedImage menuImage;
    private static BufferedImage HelpImage;
    private static BufferedImage tankArena;
    private static BufferedImage p1WinImage;
    private static BufferedImage p2WinImage;
    private static BufferedImage tank1image;
    private static BufferedImage tank2image;
    private static BufferedImage leftSplit;
    private static BufferedImage rightSplit;

    //Set other class Objects
    private Tank Tank1;
    private Tank Tank2;
    private BackDrop BGimage;
    private static ExtraLife life;
    private Graphics2D drawImage;
    private JFrame frameWindow;
    static private Menu m;
    private TankGameMap map;

    //Set game States
    enum state_of_game {
        menu, game, help, exit,
    }
    static state_of_game state = state_of_game.menu;


    //Initialize game and its objects, main function for it to run
    protected void init() {
        this.frameWindow = new JFrame("Tank Wars");
        tankArena = new BufferedImage(GameWindow.WORLD_WIDTH, GameWindow.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);

        //Loads images in another class
        LoadResources.init();

        //Sets images needed for this class
        tank1image = LoadResources.tank1.getImage();
        tank2image = LoadResources.tank2.getImage();
        p2WinImage = LoadResources.p2WinImage.getImage();
        p1WinImage = LoadResources.p1WinImage.getImage();
        BGimage = new BackDrop(LoadResources.BGimage.getImage());

        /**
         *         m = new Menu();
         *         This class no longer needed as it was used
         *         for referencing purpsoe when making my game images
         */

        //Initialize Game Objects
        Tank1 = new Tank(224, 768, 0, 0, 0, tank1image);
        Tank1.setTankTag("Tank1");
        Tank2 = new Tank(1344, 768, 0, 0, 180, tank2image);
        Tank2.setTankTag("Tank2");
        map = new TankGameMap();

       //KeyListener Stuff
        TankControl tc1 = new TankControl(Tank1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        TankControl tc2 = new TankControl(Tank2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        this.frameWindow.addKeyListener(tc2);
        this.frameWindow.addKeyListener(tc1);
        this.addMouseListener(new MouseListening());

        //Frame Window stuff
        this.frameWindow.setLayout(new BorderLayout());
        this.frameWindow.add(this);
        this.frameWindow.setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 30);
        this.frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameWindow.setResizable(false);
        this.frameWindow.setVisible(true);
        frameWindow.setLocationRelativeTo(null);

    }

    /**
     These functions are used for split screen
     */
    private int getXcoord(Tank tx){
        int x = tx.getX();
        if(x < SCREEN_WIDTH / 4) { x = SCREEN_WIDTH / 4;}
        if(x > WORLD_WIDTH - SCREEN_WIDTH / 4) { x = WORLD_WIDTH - SCREEN_WIDTH / 4; }
        return x;
    }
    public int getYcoord(Tank ty){
        int y = ty.getY();
        if(y < SCREEN_HEIGHT / 2) { y = SCREEN_HEIGHT / 2; }
        if(y > WORLD_HEIGHT - SCREEN_HEIGHT / 2) { y = WORLD_HEIGHT - SCREEN_HEIGHT / 2;}
        return y;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D paint = (Graphics2D) g;
        update();
        drawImage = tankArena.createGraphics();
        super.paintComponent(paint);

        //Checks for which state of game we're in before drawing images
        menuImage = LoadResources.menuImage.getImage();
        HelpImage = LoadResources.HelpImage.getImage();
        if (state == state_of_game.menu) {       //menu state
            (g).drawImage(menuImage, 0, 0, SCREEN_WIDTH + 2, SCREEN_HEIGHT, null);
            //m.drawImage(g);
        } else if (state == state_of_game.help) {  //help state
            (g).drawImage(HelpImage, 0, 0, SCREEN_WIDTH + 2, SCREEN_HEIGHT, null);
        } else if (state == state_of_game.exit) { //exiting
                frameWindow.dispose();
                System.exit(0);
        }

        //Paints a bunch of stuff while in game
        else if (state == state_of_game.game) {

            //Set Color for the GUI Background
            Color bg = new Color(61, 57, 45);
            paint.setColor(bg);
            this.setBackground(bg);

            //Draws background image and map
            this.BGimage.drawImage(drawImage);
            map.drawImage(drawImage);

            //Draws Tanks
            this.Tank1.drawImage(drawImage);
            this.Tank2.drawImage(drawImage);

            //Draws Split Screen
            leftSplit = tankArena.getSubimage(getXcoord(Tank1) - SCREEN_WIDTH / 4, getYcoord(Tank1) - SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT - 60);
            rightSplit = tankArena.getSubimage(getXcoord(Tank2) - SCREEN_WIDTH / 4, getYcoord(Tank2) - SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT - 60);

            paint.drawImage(leftSplit, 0, 0, null);
            paint.drawImage(rightSplit, SCREEN_WIDTH / 2 + 10, 0, null);

            paint.fillRect(0, 0, 25, SCREEN_HEIGHT);
            paint.fillRect(SCREEN_WIDTH-25, 0, 25, SCREEN_HEIGHT);
            paint.fillRect(0, 0, SCREEN_WIDTH, 100);
            paint.fillRect(SCREEN_WIDTH / 2 - WORLD_WIDTH / 16-5, SCREEN_HEIGHT - WORLD_HEIGHT / 7-5, WORLD_WIDTH / 8 +10, WORLD_HEIGHT / 8 +10);

            /**
             * Draws out Player Interface so they can see their Health and Lives
             * Lives and Health are grabbed from tank class using get functions
             * used to draw hearts and healthbars
             */

            //Text
            Font f = new Font("Cambria", Font.BOLD, 25);
            paint.setColor(Color.WHITE);
            paint.setFont(f);
            paint.drawString("Player 1 Lives", 30, 45);
            paint.drawString("Player 1 Health", 30, 90);
            paint.drawString("Player 2 Lives", 525, 45);
            paint.drawString("Player 2 Health", 525, 90);

            //Hearts
            for (int i = 1; i <= Tank1.getLives(); i++) {
                location = (ExtraLife.getImg().getWidth() + 40) * i;
                placement = location /2 + 175;
                paint.drawImage(ExtraLife.getImg(), placement, 10, null);
            }

            for (int i = 1; i <= Tank2.getLives(); i++) {
                location = (ExtraLife.getImg().getWidth() + 40) * i;
                placement = location / 2 + SCREEN_WIDTH - SCREEN_WIDTH /2 + 175;
                paint.drawImage(ExtraLife.getImg(), placement, 10, null);
            }

            //Healthbar
            paint.setColor(Color.RED);
            paint.fillRect(SCREEN_WIDTH / 4, 71, 2 * 100, 20);
            paint.fillRect(SCREEN_WIDTH - SCREEN_WIDTH / 4, 71, 2 * 100, 20);

            paint.setColor(Color.GREEN);
            paint.fillRect(SCREEN_WIDTH / 4, 70, 2 * Tank1.getCurrentHealth(), 20);
            paint.fillRect(SCREEN_WIDTH - SCREEN_WIDTH / 4, 70, 2 * Tank2.getCurrentHealth(), 20);

            /**
             * Below is the minimap, set at bottom of screen
             */
            paint.drawImage(tankArena, SCREEN_WIDTH / 2 - WORLD_WIDTH / 16, SCREEN_HEIGHT - WORLD_HEIGHT / 7, WORLD_WIDTH / 8, WORLD_HEIGHT / 8, null);

            /**
             * Checks when the player reaches 0 lives, sets game into
             * gameOver state with the appropriate win image depending on whom wins
             */
            if (Tank1.getLives() == 0) {
                paint.drawImage(p2WinImage, 0, 0, SCREEN_WIDTH + 10, SCREEN_HEIGHT, null);
                gameOver = true;
            }

            if (Tank2.getLives() == 0) {
                paint.drawImage(p1WinImage, 0, 0, SCREEN_WIDTH + 10, SCREEN_HEIGHT, null);
                gameOver = true;
            }
        }
    }

    //Update Function to keep game running, runs every tick
    private void update(){
        Tank1.update();
        Tank2.update();

        Tank1.CollideCheck(Tank2);
        Tank2.CollideCheck(Tank1);
        map.handleCollision(Tank1);
        map.handleCollision(Tank2);

    }
}

