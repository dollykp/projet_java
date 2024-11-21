package engines;

import interfaces.Engine;

public class CameraEngine implements Engine{
    private int x;
    private int y;
    private int camWidth;
    private int camHeight;
    private int mapWidth;
    private int mapHeight;

    public CameraEngine(int camWidth, int camHeight) {
        this.x = 0;
        this.y = 0;
        this.camWidth = camWidth;
        this.camHeight = camHeight;
    }

    /**
     *
     * @param heroX
     * @param heroY
     * @param camWidth
     * @param camHeight
     * @return redéfinit x et y comme les dimensions d'un carré centré autour du héros
     */
    public void update(int heroX, int heroY, int camWidth, int camHeight) {
        x = heroX - camWidth / 2;
        y = heroY - camHeight / 2;
    }

    @Override
    public void update() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
