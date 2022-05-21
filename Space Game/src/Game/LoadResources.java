package Game;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Collection of sprites for the game objects to load from
 * Using enum type for easier management with constants
 */
public enum LoadResources{

    spacebg,
    spaceship,
    moonbase,
    asteroid,
    menuImage,
    HelpImage,
    logo,
    lost;

    private BufferedImage image = null;

    public BufferedImage getImage() {
        return this.image;
    }


    public static void init() {
        System.out.println("Resources loaded!");
        try {
            LoadResources.spacebg.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Background.bmp"));
            LoadResources.spaceship.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("spaceship.png"));
            LoadResources.moonbase.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Moon1.png"));
            LoadResources.asteroid.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("asteroidL.png"));
            LoadResources.menuImage.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("Menu_page.png"));
            LoadResources.HelpImage.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("help.png"));
            LoadResources.logo.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("logoinspace.png"));
            LoadResources.lost.image = ImageIO.read(LoadResources.class.getClassLoader().getResource("player-1-wins.png"));

        } catch (IOException ex) {
            System.err.println(ex + ": Cannot be read");
            ex.printStackTrace();
        }
    }
}