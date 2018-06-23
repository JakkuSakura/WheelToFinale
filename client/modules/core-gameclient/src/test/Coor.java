import GpsCoordinate;
import org.junit.Test;

public class Coor {
    @Test
    public void c() {
        GpsCoordinate coordinate = new GpsCoordinate(0, 66, 0);
        System.out.println(coordinate.getVector3f());
        GpsCoordinate coordinate2 = new GpsCoordinate().fromVector3f(coordinate.getVector3f());
        System.out.println(coordinate2);
    }

    @Test
    public void rotate() {
    }
}
