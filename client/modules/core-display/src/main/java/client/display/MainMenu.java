package client.display;

import com.jme3.app.SimpleApplication;
import com.simsilica.lemur.*;
import com.simsilica.lemur.style.BaseStyles;

public class MainMenu extends SimpleApplication {

    public static void main( String... args ) {
        MainMenu main = new MainMenu();
        main.start();
    }

    @Override
    public void simpleInitApp() {

        // Initialize the globals access so that the defualt
        // components can find what they need.
        GuiGlobals.initialize(this);

        // Load the 'glass' style
        BaseStyles.loadGlassStyle();

        // Set 'glass' as the default style when not specified
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

        // Create a simple container for our elements
        Container myWindow = new Container();
        guiNode.attachChild(myWindow);

        // Put it somewhere that we will see it
        // Note: Lemur GUI elements grow down from the upper left corner.
        myWindow.setLocalTranslation(300, 300, 0);

        // Add some elements
        myWindow.addChild(new Label("Hello, World."));
        Button clickMe = myWindow.addChild(new Button("Join Server"));
        Command<? super Button> joinServer = (Command<Button>) source -> System.out.println("The world is yours.");

        clickMe.addClickCommands(joinServer);


    }
}