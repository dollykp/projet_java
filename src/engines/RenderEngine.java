///décide ce qui va être dessiné

package engines;

import interfaces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {

    protected ArrayList<Displayable> renderList;
    protected CameraEngine cameraEngine;

    public RenderEngine(JFrame jFrame, CameraEngine cameraEngine) {
        renderList = new ArrayList<>();
        this.cameraEngine = cameraEngine;
    }

    public void addToRenderList(Displayable d){
        if (!renderList.contains(d)){
            renderList.add(d);
        }
    }

    public void addToRenderList(ArrayList<Displayable> d){
        if (!renderList.contains(d)){
            renderList.addAll(d);
        }
    }

    public void setRenderEngine(ArrayList<Displayable> renderEngine) {
        this.renderList = renderEngine;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int cameraX = cameraEngine.getX();
        int cameraY = cameraEngine.getY();

        for (Displayable renderObject:renderList){
            renderObject.draw(g, cameraX, cameraY);
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
