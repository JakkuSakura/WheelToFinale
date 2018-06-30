package client.display.appstate;


import client.display.event.EventMapper;
import client.display.models.Earth;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

/**
 * 坐标系
 *
 * @author yanmaoyuan
 */
public class EarthAppstate extends BaseAppState {


    private AssetManager assetManager;
    private EventMapper eventMapper;
    private SimpleApplication simpleApp;

    public EarthAppstate(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public EventMapper getEventMapper() {
        return eventMapper;
    }

    public Earth getEarth() {
        return earth;
    }

    public Node getRootNode() {
        return rootNode;
    }

    private Node rootNode = new Node("EarthRoot");
    private Earth earth;


    @Override
    protected void initialize(Application app) {
        simpleApp = (SimpleApplication) getApplication();
        assetManager = app.getAssetManager();
        Camera cam = app.getCamera();
        cam.setFrustumFar(30000.0f);
        cam.setLocation(new Vector3f(7000, 0, 0));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);


        FlyByCamera flyByCamera = simpleApp.getFlyByCamera();
        flyByCamera.setEnabled(true);
        flyByCamera.setMoveSpeed(1000);

        // 定向光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));

        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.6f));
        ambient.setColor(lightColor.mult(0.4f));

        rootNode.addLight(sun);
        rootNode.addLight(ambient);

        earth = new Earth(assetManager, 6400.0f);
        rootNode.attachChild(earth);

    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        simpleApp.getRootNode().attachChild(rootNode);
    }

    @Override
    protected void onDisable() {
        rootNode.removeFromParent();
    }

    @Override
    public void update(float tpf) {
        earth.rotate(0, 0.05f * tpf, 0);
    }
}
