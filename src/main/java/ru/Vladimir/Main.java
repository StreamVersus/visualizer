package ru.Vladimir;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;
import ru.Vladimir.states.GridState;
import ru.Vladimir.states.VectorState;

public class Main extends SimpleApplication {
    public static String[] args;

    public static void main(String[] args) {
        Main.args = args;
        Main main = new Main();
        InputHandler.startService();

        main.setShowSettings(false);

        AppSettings settings = new AppSettings(true);

        settings.put("Width", 1280);

        settings.put("Height", 720);

        settings.put("Title", "Visualizer");

        settings.put("VSync", false);

        settings.put("Samples", 16);

        main.setSettings(settings);

        main.start();
    }

    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);

        var grid = new GridState(0.5F, 200);
        var vectors =  new VectorState();

        stateManager.attach(grid);
        stateManager.attach(vectors);
    }
}