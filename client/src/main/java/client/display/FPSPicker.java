package client.display;

import com.jme3.app.Application;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;

public class FPSPicker extends Picker {
    protected Spatial cross;


    @Override
    protected void initialize(Application app) {
        super.initialize(app);
        cross = makeCross(app.getAssetManager().loadFont("Interface/Fonts/Default.fnt"), camera);
    }
    /**
     * 在摄像机镜头正中央贴一张纸，充当准星。
     */
    private Spatial makeCross(BitmapFont guiFont, Camera cam) {
        // 采用Gui的默认字体，做个加号当准星。
        BitmapText text = guiFont.createLabel("+");
        text.setColor(ColorRGBA.Red);// 红色

        // 居中
        float x = (cam.getWidth() - text.getLineWidth()) * 0.5f;
        float y = (cam.getHeight() + text.getLineHeight()) * 0.5f;
        text.setLocalTranslation(x, y, 0);

        return text;
    }


    @Override
    protected void onEnable() {
        sapp.getGuiNode().attachChild(cross);
    }

    @Override
    protected void onDisable() {
        sapp.getGuiNode().removeFromParent();
    }

    @Override
    public Ray calcRay() {
        Ray ray = new Ray();
        // 使用摄像机的位置作为射线的原点
        ray.setOrigin(sapp.getCamera().getLocation());
        ray.setDirection(sapp.getCamera().getDirection());
        return ray;
    }
}
