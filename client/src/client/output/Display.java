package client.output;

import client.input.Control;
import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;


public class Display extends SimpleApplication {

    private Control control;

    public Display(Control control) {
        this.control = control;
    }

    @Override
    public void simpleInitApp() {
//        Box b = new Box(1, 1, 1);
//        Geometry geom = new Geometry("Box");
//
//        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
//        geom.setMesh(b);
//        geom.setMaterial(mat);
//
//        DirectionalLight sun = new DirectionalLight();
//        sun.setDirection(new Vector3f(-1, -2, -3));
//        rootNode.addLight(sun);
//        rootNode.attachChild(geom);
//
//        PointLight lamp_light = new PointLight();
//        lamp_light.setColor(ColorRGBA.Yellow);
//        lamp_light.setRadius(4f);
//        lamp_light.setPosition(new Vector3f(1, 1.5f, 1.5f));
//        rootNode.addLight(lamp_light);
//
//        Sphere sphere = new Sphere();

    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        control.receiveKeydown(rm);
    }
}
