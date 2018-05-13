package client.display;

import client.input.Control;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;


public class Display extends SimpleApplication {

    private Control control;

    public Display(Control control) {
        this.control = control;

    }

    @Override
    public void simpleInitApp() {
//        todo do not work
//        settings.setTitle("Wheel to finale");
        inputManager.addRawInputListener(control);
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box");

        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        geom.setMesh(b);
        geom.setMaterial(mat);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));
        rootNode.addLight(sun);
        rootNode.attachChild(geom);

        PointLight lamp_light = new PointLight();
        lamp_light.setColor(ColorRGBA.Yellow);
        lamp_light.setRadius(4f);
        lamp_light.setPosition(new Vector3f(1, 1.5f, 1.5f));
        rootNode.addLight(lamp_light);


    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {

    }


}
