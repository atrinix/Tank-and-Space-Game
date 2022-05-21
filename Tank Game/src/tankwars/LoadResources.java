package tankwars;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Collection of sprites for the game objects to load from
 * Using enum type for easier management with constants
 */
public enum LoadResources{

    BGimage,
    tank1,
    tank2,
    UnbreakableWall,
    BreakableWall,
    Bullet,
    MoreDefense,
    ExtraLife,
    Exploding,
    menuImage,
    HelpImage,
    SpeedBoost,
    p1WinImage,
    p2WinImage;

    private BufferedImage image = null;

    public BufferedImage getImage() {
        return this.image;
    }

    public static void init() {
        System.out.println("Resources loaded!");
        try {
            LoadResources.tank1.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("tank1.png"));
            LoadResources.tank2.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("tank2.png"));
            LoadResources.BGimage.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Background.bmp"));
            LoadResources.UnbreakableWall.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Wall1.png"));
            LoadResources.BreakableWall.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Wall2.png"));
            LoadResources.Bullet.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Weapon.png"));
            LoadResources.MoreDefense.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("dmgUp.png"));
            LoadResources.ExtraLife.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("extraLife.png"));
            LoadResources.Exploding.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("big_explosion.png"));
            LoadResources.menuImage.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Menu_page.png"));
            LoadResources.HelpImage.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("help.png"));
            LoadResources.SpeedBoost.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("speed-boost.png"));
            LoadResources.p1WinImage.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("player-1-wins.png"));
            LoadResources.p2WinImage.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("player-2-wins.png"));

        } catch (IOException ex) {
            System.err.println(ex + ": Cannot be read");
            ex.printStackTrace();
        }
    }
}