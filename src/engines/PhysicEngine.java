package engines;

import interfaces.Engine;
import sprites.*;

import java.util.ArrayList;

public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList = new ArrayList<>();
    private ArrayList<SolidSprite> environment = new ArrayList<>();

    public void addToMovingSpriteList(DynamicSprite dynspr){
        if (!movingSpriteList.contains(dynspr)){
            movingSpriteList.add(dynspr);
        }
    }

    public void addToEnvironment(SolidSprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<SolidSprite> environment) {
        this.environment = environment;
    }


    @Override
    public void update() {
        for (DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);
        }
    }
}
