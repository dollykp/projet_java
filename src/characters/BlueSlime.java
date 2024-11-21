package characters;

import enums.Direction;

import java.awt.*;

public class BlueSlime extends Enemy {

    public BlueSlime(Image image, double x, double y, double width, double height, String name, int lifePoints, double speed, double attackPower, double defensePoint) {
        super(image, x, y, width, height, name, lifePoints, speed, attackPower, defensePoint);
    }

    @Override
    public void move() {
    }
}
