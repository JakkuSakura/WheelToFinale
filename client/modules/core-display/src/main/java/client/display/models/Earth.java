package client.display.models;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import static client.display.coordinate.GpsCoordinate.toRad;


public class Earth extends Node {
    private AssetManager assetManager;
    private float radius;
    private float theta = toRad(23.5f);

    public Earth(AssetManager assetManager, float radius) {
        super("EarthNode");
        this.assetManager = assetManager;
        this.radius = radius;
        Spatial model = assetManager.loadModel("assets/obj/earth.obj");
        attachChild(model);
        rotate(-theta, 0, 0);
    }

    public void addModel(EarthMatter earthMatter) {
        Spatial spatial = earthMatter.getSpatial();
        Vector3f translation = earthMatter.getGpsCoordinate().getVector3f().mult(radius);
        System.out.println(translation);
        spatial.setLocalTranslation(translation);
        spatial.rotateUpTo(earthMatter.getGpsCoordinate().getVector3f());
        attachChild(spatial);
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }


}



