package Gameplay;

import Moteur.Entity;
import Moteur.KeyObserver;
import Moteur.Render;

import java.io.FileNotFoundException;

public class PauseObserver implements KeyObserver {

    Render render;
    Entity pauseSign;
    Boolean paused;

    public PauseObserver(Render render, double width, double height) throws FileNotFoundException {
        this.render = render;
        pauseSign = new Entity(width/2 - 250, height/2 - 106, "src/Images/pauseSign.png", 212, 500, false);
        paused = false;
    }

    @Override
    public void update(String key, boolean pressed) {
        if(pressed && key.equals("P")) {

            if(render.isPaused() && paused) { paused = false; render.removeEntity(pauseSign); render.togglePause(); }
            else if(!render.isPaused() && !paused) { paused = true; render.addEntity(pauseSign); render.togglePause(); }

        }
    }
}
