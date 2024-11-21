import interfaces.Displayable;
import sprites.SolidSprite;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();

    public Playground (String pathName){
        try{
            final Image imageTree = ImageIO.read(new File("./img/env/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/env/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/env/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/env/trap.png"));
            final Image imageDirt = ImageIO.read(new File("./img/env/dirt.png"));
            final Image imageDoor = ImageIO.read(new File("./img/env/door.png"));
            final Image imageWater = ImageIO.read(new File("./img/env/water.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);

            final int imageDirtWidth = imageDirt.getWidth(null);
            final int imageDirtHeight = imageDirt.getHeight(null);

            final int imageDoorWidth = imageDoor.getWidth(null);
            final int imageDoorHeight = imageDoor.getHeight(null);

            final int imageWaterWidth = imageWater.getWidth(null);
            final int imageWaterHeight = imageWater.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(imageTree, columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ' : environment.add(new Sprite(imageGrass, columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite(imageRock, columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight, imageRockWidth, imageRockHeight));
                            break;
                        case 't' : environment.add(new SolidSprite(imageTrap, columnNumber*imageTrapWidth,
                                lineNumber*imageTrapHeight, imageTrapWidth, imageTrapHeight));
                            break;
                        case 'D' : environment.add(new SolidSprite(imageDirt, columnNumber*imageDirtWidth,
                                lineNumber* imageDirtHeight, imageDirtWidth, imageDirtHeight));
                            break;
                        case 'd' : environment.add(new SolidSprite(imageDoor, columnNumber*imageDoorWidth,
                                lineNumber* imageDoorHeight, imageDoorWidth, imageDoorHeight));
                            break;
                        case 'W' : environment.add(new SolidSprite(imageWater, columnNumber*imageWaterWidth,
                                lineNumber* imageWaterHeight, imageWaterWidth, imageWaterHeight));
                            break;
                    }
                    columnNumber++;
                }
                columnNumber =0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<SolidSprite> getSolidSpriteList(){
        ArrayList <SolidSprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add((SolidSprite) sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
}
