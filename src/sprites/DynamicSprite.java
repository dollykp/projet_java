package sprites;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import enums.Direction;
import sprites.*;


public class DynamicSprite extends SolidSprite {
    private boolean isWalking = false;
    private boolean isMoving = false;
    private double speed = 5;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame = 250;
    private Direction direction = Direction.WEST;

    public DynamicSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    /**
     *
     * @param environment
     * @return définit le sprite déplacé de speed pixels
     */
    private boolean isMovingPossible(ArrayList<SolidSprite> environment) {
            Rectangle2D.Double hitbox = new Rectangle2D.Double();
            switch (direction) {
                case EAST:
                    hitbox.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(), super.getHitBox().getWidth(), super.getHitBox().getHeight());
                    break;
                case WEST:
                    hitbox.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                            super.getHitBox().getWidth(), super.getHitBox().getHeight());
                    break;
                case NORTH:
                    hitbox.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                            super.getHitBox().getWidth(), super.getHitBox().getHeight());
                    break;
                case SOUTH:
                    hitbox.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                            super.getHitBox().getWidth(), super.getHitBox().getHeight());
                    break;
            }

            for (Sprite s : environment){
                if ((s instanceof SolidSprite) && (s!=this)){
                    if (((SolidSprite) s).intersect(hitbox)){
                        return false; // collisions
                    }
                }
            }
            return true; //pas de collisions
        }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    private void move() {
        switch (direction) {
            case NORTH -> {
                this.y -= speed;
            }
            case SOUTH -> {
                this.y += speed;
            }
            case EAST -> {
                this.x += speed;
            }
            case WEST -> {
                this.x -= speed;
            }
        }
    }

    /**
     *
     * @param g
     * @param cameraX
     * @param cameraY
     * @return animation du héros sur place + barre de vie
     */
    @Override
    public void draw(Graphics g, int cameraX, int cameraY) {
        int attitude = isMoving ? direction.getFrameLineNumber() : 0;
        int index = (int) (System.currentTimeMillis()/timeBetweenFrame % spriteSheetNumberOfColumn);

        g.drawImage(image, (int) (x - cameraX), (int) (y - cameraY),
                (int) (x + width - cameraX), (int) (y + height - cameraY),
                (int) (index * width), (int) (attitude * height),
                (int) ((index + 1) * width), (int) ((attitude + 1) * height), null);

        g.setColor(Color.RED);
        g.fillRect((int) x - cameraX, (int) y - cameraY - 10, 50, 3);
    }

    /**
     *
     * @param environment
     * @return le personnage bouge si et seulement le mouvement est possible
     */
    public void moveIfPossible(ArrayList<SolidSprite> environment){
        if (direction != Direction.NONE && isMovingPossible(environment)){
            move();
        }
    }

    public boolean intersect(Rectangle2D.Double other) {
        return this.getHitBox().intersects(other);
    }

}
