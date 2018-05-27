package client.display.appstate;

import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class MousePicker extends Picker {
    @Override
    public Ray calcRay() {
        Ray ray = new Ray();
        ray.setOrigin(sapp.getCamera().getLocation());

        Vector2f screenCoord = sapp.getInputManager().getCursorPosition();
        Vector3f worldCoord = sapp.getCamera().getWorldCoordinates(screenCoord, 1f);
        // 计算方向
        Vector3f dir = worldCoord.subtract(sapp.getCamera().getLocation());
        dir.normalizeLocal();
        ray.setDirection(dir);
        return ray;
    }
}
