package client.display;

import client.coordinate.GpsCoordinate;
import com.jme3.asset.AssetManager;

public class City extends EarthMatter{
    public City(AssetManager assetManager, GpsCoordinate gpsCoordinate) {
        super(assetManager, gpsCoordinate, "obj/cattle.obj");
    }

}