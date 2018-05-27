package client.display;

import client.GameClient;
import client.display.appstate.AxisAppState;
import client.display.appstate.EarthAppstate;
import client.display.appstate.FPSPicker;
import client.display.appstate.Picker;
import client.display.event.EventMapper;
import client.display.event.ExitEvent;
import client.input.Control;
import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.ActionListener;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;


public class GameApp extends SimpleApplication {

    private Control control;
    private EarthAppstate earthAppstate = new EarthAppstate();
    private AxisAppState axisAppState = new AxisAppState(8000.0f);
    private Picker picker = new FPSPicker();
    private EventMapper eventMapper = new EventMapper();
    public GameApp(Control control) {
        this.control = control;
        setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1024, 768);// 分辨率
        settings.setTitle("Wheel to Finale");
        setSettings(settings);
        earthAppstate.setEventMapper(eventMapper);

    }

    @Override
    public void simpleInitApp() {
        inputManager.addListener(new ActionListener(){
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (!isPressed) {
                    return;
                }

                if (name.equals(INPUT_MAPPING_EXIT)) {
                    cleanup();
                }
            }
        }, INPUT_MAPPING_EXIT);
        control.initKeys(inputManager);
        cam.setFrustumFar(30000.0f);
        cam.setLocation(new Vector3f(7000, 0, 0));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(1000);
//        flyCam.setEnabled(false);

        viewPort.setBackgroundColor(ColorRGBA.LightGray);



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

        stateManager.attach(axisAppState);

        stateManager.attach(earthAppstate);

        stateManager.attach(picker);
        picker.setReactor(control.getReactor(), eventMapper);

    }

    private void cleanup() {
        control.getReactor().submitEvent(new ExitEvent());
    }

    public static void main(String[] args) throws Exception {
        GameClient.main(args);
    }



}
