package client.coordinate;

import com.jme3.math.Vector3f;

public class GpsCoordinate {
    private float longitude, latitude, altitude;
    private Vector3f vector3f;

    public GpsCoordinate() {
        this(0, 0, 0);
    }

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
        return (float) Math.PI * degree / 180.0f;
    }

    public static float toDegree(float rad) {
        return rad * 180.0f / (float) Math.PI;
    }

    public static float toRad(double degree) {
        return toRad((float) degree);
    }

    public static float toDegree(double rad) {
        return toDegree((float) rad);
    }


    /**
     * using left-handed client.coordinate
     *
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
        float x = (float) Math.cos(toRad(longitude)) * (float) Math.cos(latitude);
        float z = (float) Math.sin(toRad(longitude)) * (float) Math.cos(latitude);

        return new Vector3f(x, y, z);
    }

    public GpsCoordinate fromVector3f(Vector3f normalized) {
        latitude = toDegree(Math.asin(normalized.y));
        longitude = toDegree(Math.atan(normalized.z / normalized.x));
        if (normalized.z < 0)
            longitude -= 180.0f;
        return this;
    }

    @Override
    public String toString() {
        return "GpsCoordinate[" +
                longitude +
                ", " +
                latitude +
                "]";
    }

}
