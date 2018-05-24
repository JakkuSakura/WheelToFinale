package client.display;


import client.coordinate.GpsCoordinate;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

/**
 * 坐标系
 *
 * @author yanmaoyuan
 */
public class EarthAppstate extends BaseAppState{


    private AssetManager assetManager;
    private EventManager eventManager;

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public EventManager getEventManager() {
        return eventManager;
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
        assetManager = app.getAssetManager();
        final float radius = 6400.0f;

        earth = new Earth(assetManager, radius);
        EarthMatter earthMatter = new City(assetManager, new GpsCoordinate());
        if (getEventManager() != null) {
            getEventManager().register(EventManager.Type.SELECT, earth, earth);
        }
        earth.addModel(earthMatter);
        rootNode.attachChild(earth);

    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        SimpleApplication simpleApp = (SimpleApplication) getApplication();
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