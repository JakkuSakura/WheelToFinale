package client.display.models;

import client.tools.coordinate.GpsCoordinate;
import com.jme3.asset.AssetManager;

public class City extends EarthMatter {
    public City(AssetManager assetManager, GpsCoordinate gpsCoordinate) {
        super(assetManager, gpsCoordinate, "assets/obj/cattle.obj");
    }

}
