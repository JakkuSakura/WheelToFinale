package client.display;

import client.coordinate.GpsCoordinate;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

public class City {
    private GpsCoordinate gpsCoordinate;

    public City(GpsCoordinate gpsCoordinate) {
        this.gpsCoordinate = gpsCoordinate;
    }

    public GpsCoordinate getGpsCoordinate() {
        return gpsCoordinate;
    }

    public void setGpsCoordinate(GpsCoordinate gpsCoordinate) {
        this.gpsCoordinate = gpsCoordinate;
    }

    public void addModel(Node node, AssetManager assetManager) {

    }
}
