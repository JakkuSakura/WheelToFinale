package client.display;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import static client.coordinate.GpsCoordinate.toRad;

public class Earth extends Node {
    private AssetManager assetManager;
    private Vector3f posiziton = new Vector3f();
    private float radius;
    private float theta = (float) (-Math.PI / 2);

    public Earth(AssetManager assetManager, float radius) {
        super("EarthNode");
        this.assetManager = assetManager;
        this.radius = radius;
        Spatial model = assetManager.loadModel("obj/earth.obj");
        model.scale(this.radius);
        model.center();
        attachChild(model);
    }

    public void addModel(EarthMatter earthMatter) {
        Geometry geometry = earthMatter.getGeometry();
        geometry.setLocalTranslation(0, radius * earthMatter.getGpsCoordinate().getVector3f().getY(), 0);
        double theta = toRad(earthMatter.getGpsCoordinate().getLatitude());
        geometry.rotate(
                (float) (theta * Math.cos(toRad(earthMatter.getGpsCoordinate().getLongitude()))),
                0,
                (float) (theta * Math.sin(toRad(earthMatter.getGpsCoordinate().getLongitude())))
        );
        attachChild(geometry);
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}
