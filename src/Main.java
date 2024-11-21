import characters.*;
import engines.*;
import sprites.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * @nom Projet Java
 * @date  20/11/2024
 */

public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    CameraEngine cameraEngine;

    public Main() throws Exception {
        final Image imageHero = ImageIO.read(new File("./img/heroLink/heroTileSheetLowRes.png"));
        final Image imageTree = ImageIO.read(new File("./img/env/tree.png"));
        final Image imageRock =  ImageIO.read(new File("./img/env/rock.png"));
        final Image imageBlue = ImageIO.read(new File("./img/enemies/blueSlime.png"));
        final Image imagePurple = ImageIO.read(new File("./img/enemies/purpleSlime.png"));

        displayZoneFrame = new JFrame("Projet Java");
        displayZoneFrame.setSize(600, 600);
        displayZoneFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(imageHero,200,300,48,51);

        ArrayList<SolidSprite> environment = new ArrayList<>();

        gameEngine = new GameEngine(hero, environment);
        cameraEngine = new CameraEngine(300,300);
        renderEngine = new RenderEngine(displayZoneFrame, cameraEngine);
        physicEngine = new PhysicEngine();

        cameraEngine.update(200, 300, 640, 640);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());
        Timer cameraTimer = new Timer(50, (time) -> cameraEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        cameraTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        SolidSprite tree = new SolidSprite(imageTree,200,300,64,64);
        SolidSprite rock = new SolidSprite(imageRock,250,300,64,64);
        BlueSlime blueSlime = new BlueSlime(imageBlue, 100, 300, 32, 32, "Blue Slime", 100, 2, 5, 2);
        PurpleSlime purpleSlime = new PurpleSlime(imagePurple, 255, 355, 32, 32, "Purple Slime", 150, 3, 10, 5);

        environment.add(rock);
        environment.add(tree);

        Playground level1 = new Playground("./data/level1.txt");
        Playground level2 = new Playground("./data/level2.txt");

        renderEngine.addToRenderList(level1.getSpriteList());
        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(blueSlime);
        renderEngine.addToRenderList(purpleSlime);

        physicEngine.addToMovingSpriteList(hero);
        physicEngine.addToMovingSpriteList(blueSlime);
        physicEngine.addToMovingSpriteList(purpleSlime);

        physicEngine.setEnvironment(level1.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }


}
