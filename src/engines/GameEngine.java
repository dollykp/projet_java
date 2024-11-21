package engines;

import enums.Direction;
import interfaces.Engine;
import sprites.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;
    private boolean pressShift;
    private boolean pressUp;
    private boolean pressDown;
    private boolean pressLeft;
    private boolean pressRight;
    ArrayList<SolidSprite> environment;

    public GameEngine(DynamicSprite hero, ArrayList<SolidSprite> environment) {
        this.hero = hero;
        this.environment = environment;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void update() {
        if (pressUp) {
            hero.setDirection(Direction.NORTH);
            hero.moveIfPossible(environment);
        } else if (pressDown) {
            hero.setDirection(Direction.SOUTH);
            hero.moveIfPossible(environment);
        } else if (pressLeft) {
            hero.setDirection(Direction.WEST);
            hero.moveIfPossible(environment);
        } else if (pressRight) {
            hero.setDirection(Direction.EAST);
            hero.moveIfPossible(environment);
        }
        else {
            hero.setDirection(Direction.NONE);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pressUp = true;
                hero.setIsMoving(true);
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                pressDown = true;
                hero.setIsMoving(true);
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                pressLeft = true;
                hero.setIsMoving(true);
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                pressRight = true;
                hero.setIsMoving(true);
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_SHIFT:
                pressShift = true;
                hero.setSpeed(hero.getSpeed() * 2);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                pressUp = false;
                break;
            case KeyEvent.VK_DOWN:
                pressDown = false;
                break;
            case KeyEvent.VK_LEFT:
                pressLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                pressRight = false;
                break;
            case KeyEvent.VK_SHIFT:
                pressShift = false;
                hero.setSpeed(hero.getSpeed() / 2);
                break;
        }

        if (!pressUp && !pressDown && !pressLeft && !pressRight) {
            hero.setIsMoving(false);
            hero.setDirection(Direction.NONE);
        }
    }
}
