package characters;

import sprites.*;

import java.awt.*;

public class Enemy extends DynamicSprite {
    protected final String name;
    protected int lifePoints;
    protected double speed;
    protected double attackPower;
    protected double defensePoint;

    public Enemy(Image image, double x, double y, double width, double height, String name, int lifePoints, double speed, double attackPower, double defensePoint) {
        super(image, x, y, width, height);
        this.name = name;
        this.lifePoints = lifePoints;
        this.speed = speed;
        this.attackPower = attackPower;
        this.defensePoint = defensePoint;
    }

    public void takeDamage(double damage){
        this.lifePoints= (int) (this.lifePoints-damage);
        if (this.lifePoints<0) this.lifePoints=0;
    }

    public void attackChara (Enemy e){
        if (this.lifePoints>0){
            if (this.attackPower>e.defensePoint) {
                e.takeDamage(attackPower);
            }
        }
    }

    public void move(){

    }

    /**
     *
     * @param g
     * @param X
     * @param Y
     * @return dessine l'ennemi avec une barre de vie au-dessus
     */
    @Override
    public void draw(Graphics g, int X, int Y) {

        super.draw(g, X, Y);
        g.setColor(Color.RED);
        g.fillRect(X, Y - 10, (int) (width * (lifePoints / 100.0)), 5);
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}