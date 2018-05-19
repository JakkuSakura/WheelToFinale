package client.display;

import client.coordinate.GpsCoordinate;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;

public class EarthMatter{
    private AssetManager assetManager;
    private GpsCoordinate gpsCoordinate;
    private String assetPath;
    private Geometry geometry;
    public EarthMatter(AssetManager assetManager, GpsCoordinate gpsCoordinate, String assetPath, float r) {
        this.assetManager = assetManager;
        this.gpsCoordinate = gpsCoordinate;
        this.assetPath = assetPath;
        geometry = (Geometry) assetManager.loadModel(assetPath);
    }

    public GpsCoordinate getGpsCoordinate() {
        return gpsCoordinate;
    }

    public void setGpsCoordinate(GpsCoordinate gpsCoordinate) {
        this.gpsCoordinate = gpsCoordinate;
    }

    public String getAssetPath() {
        return assetPath;
    }

    public void setAssetPath(String assetPath) {
        this.assetPath = assetPath;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
