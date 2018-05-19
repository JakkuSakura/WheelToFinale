package client.display;

import client.GameClient;
import client.input.Control;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;
import com.jme3.system.AppSettings;


public class Display extends SimpleApplication {

    private Control control;
    private Earth earth;
    public Display(Control control) {
        this.control = control;
        setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1024, 768);// 分辨率
        settings.setTitle("Wheel to Finale");
        setSettings(settings);

    }

    @Override
    public void simpleInitApp() {
        inputManager.addListener(control);

        final float radius = 6400.0f;
        cam.setFrustumFar(30000.0f);
        cam.setLocation(new Vector3f(0, 0, radius));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(100);
        viewPort.setBackgroundColor(ColorRGBA.LightGray);

        earth = new Earth(assetManager, radius);
        rootNode.attachChild(earth);

        // 创建X、Y、Z方向的箭头，作为参考坐标系。
        createArrow(new Vector3f(radius, 0, 0), ColorRGBA.Red);
        createArrow(new Vector3f(0, radius, 0), ColorRGBA.Green);
        createArrow(new Vector3f(0, 0, radius), ColorRGBA.Blue);

        PointLight lamp_light = new PointLight();
        lamp_light.setColor(ColorRGBA.Yellow);
        lamp_light.setRadius(4f);
        lamp_light.setPosition(new Vector3f(1, 1.5f, 1.5f));
        // 定向光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));

        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.6f));
        ambient.setColor(lightColor.mult(0.4f));

        rootNode.addLight(sun);
        rootNode.addLight(ambient);
        rootNode.depthFirstTraversal(System.out::println, Spatial.DFSMode.PRE_ORDER);


    }


    /**
     * 创建一个箭头
     *
     * @param vec3  箭头向量
     * @param color 箭头颜色
     */
    private void createArrow(Vector3f vec3, ColorRGBA color) {
        // 创建材质，设定箭头的颜色
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);

        // 创建几何物体，应用箭头网格。
        Geometry geom = new Geometry("arrow", new Arrow(vec3));
        geom.setMaterial(mat);

        // 添加到场景中
        rootNode.attachChild(geom);
    }


    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {

    }

    public static void main(String[] args) throws Exception {
        GameClient.main(args);
    }

}
