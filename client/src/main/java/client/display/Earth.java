package client.display;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import static client.coordinate.GpsCoordinate.toRad;

public class Earth extends Node implements Action {
    private AssetManager assetManager;
    private float radius;
    private float theta = toRad(23.5f);

    public Earth(AssetManager assetManager, float radius) {
        super("EarthNode");
        this.assetManager = assetManager;
        this.radius = radius;
        Spatial model = assetManager.loadModel("obj/earth.obj");
        attachChild(model);
        rotate(-theta, 0, 0);
    }

    public void addModel(EarthMatter earthMatter) {
        Spatial spatial = earthMatter.getSpatial();
        spatial.setLocalTranslation(earthMatter.getGpsCoordinate().getVector3f().mult(radius));
        spatial.rotateUpTo(earthMatter.getGpsCoordinate().getVector3f());
        attachChild(spatial);
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void action(EventManager.Type type) {
        if (type == EventManager.Type.SELECT) {
            System.out.println(this);
            setLocalScale(getLocalScale().add(0.1f, 0.1f, 0.1f));
        }
    }
}



