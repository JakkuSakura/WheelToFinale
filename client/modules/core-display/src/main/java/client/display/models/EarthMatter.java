package client.display.models;

import client.display.event.EventMapper;
import client.tools.coordinate.GpsCoordinate;
import client.display.event.Action;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;

public class EarthMatter implements Action {
    private GpsCoordinate gpsCoordinate;
    private String assetPath;
    private Spatial spatial;
    public EarthMatter(AssetManager assetManager, GpsCoordinate gpsCoordinate, String assetPath) {
        this.spatial = assetManager.loadModel(assetPath);
        this.gpsCoordinate = gpsCoordinate;
        this.assetPath = assetPath;
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

    public Spatial getSpatial() {
        return spatial;
    }

    @Override
    public void action(EventMapper.Type type) {

    }
}
