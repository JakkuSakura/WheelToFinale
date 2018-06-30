package client.display;

import client.display.appstate.AxisAppState;
import client.display.appstate.EarthAppstate;
import client.display.appstate.MainMenuState;
import client.display.event.EventMapper;
import client.display.event.ExitEvent;
import client.input.Control;
import com.jme3.app.LegacyApplication;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;

import java.util.logging.Logger;


public class GameApp extends SimpleApplication {
    private static final Logger logger = Logger.getLogger(LegacyApplication.class.getName());

    private Control control;
    private EventMapper eventMapper = new EventMapper();
    private AxisAppState axisAppState = new AxisAppState(8000.0f);
    private EarthAppstate earthAppstate = new EarthAppstate(eventMapper);

    private MainMenuState mainMenuState = new MainMenuState();

    public GameApp(Control control) {
        this.control = control;
        setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1024, 768);// 分辨率
        settings.setTitle("Wheel to Finale");
        setSettings(settings);
    }


    @Override
    public void simpleInitApp() {
        control.initKeys(inputManager);

        flyCam.setEnabled(false);

        viewPort.setBackgroundColor(ColorRGBA.LightGray);



        mainMenuState.addItem(earthAppstate);
        mainMenuState.addItem(axisAppState);

        stateManager.attach(mainMenuState);
    }


    public void stop() {
        super.stop();
        control.getReactor().submitEvent(new ExitEvent());
    }


}
