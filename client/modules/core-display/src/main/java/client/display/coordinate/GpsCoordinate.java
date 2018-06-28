package client.display.coordinate;

import com.jme3.math.Vector3f;
public class GpsCoordinate {
    private float longitude, latitude, altitude;
    private Vector3f vector3f;

    public GpsCoordinate(float longitude, float latitude, float altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public static float toRad(float degree) {
        return (float) (Math.PI * degree / 180.0);
    }

    public static float toDeg(float rad) {
        return (float) (rad * 180.0 / Math.PI);
    }


    /**
     * using left-handed coordinate
     * @return a Vector3f with x,y,z sized between +1.0 and -1.0
     */
    public Vector3f getVector3f() {
        if (vector3f == null)
            return vector3f = calcVector3f();
        else
            return vector3f;
    }

    public Vector3f calcVector3f() {
        float y = (float) Math.sin(toRad(latitude));
        float x = (float) Math.cos(toRad(longitude)) * Math.abs(y);
        float z = (float) Math.sin(toRad(longitude)) * Math.abs(y);

        return new Vector3f(x, y, z);
    }

}
