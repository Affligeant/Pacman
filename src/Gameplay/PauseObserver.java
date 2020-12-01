package Gameplay;

import Moteur.Entity;
import Moteur.KeyObserver;
import Moteur.Render;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PauseObserver implements KeyObserver {

    Render render;
    Entity pauseSign;
    Boolean paused;
    long pauseStart;
    Pacman pacman;
    ArrayList<Fantome> fantomes;

    public PauseObserver(Render render, double width, double height, Pacman pacman) throws FileNotFoundException {
        this.render = render;
        pauseSign = new Entity(width/2 - 250, height/2 - 106, "src/Images/pauseSign.png", 212, 500, false);
        paused = false;
        this.pacman = pacman;
    }

    @Override
    public void update(String key, boolean pressed) {
        if(pressed && key.equals("P")) {
            if(render.isPaused() && paused) {
                paused = false;
                long dureePause = System.currentTimeMillis() - pauseStart;
                if(pacman.isAccelerated()) {
                    pacman.accelerate(dureePause);
                }
                for(Fantome f : fantomes) {
                    if(f.isScared()) {
                        f.scare(dureePause);
                    }
                }
                render.removeEntity(pauseSign);
                render.togglePause();
            }
            else if(!render.isPaused() && !paused) {
                paused = true;
                pauseStart = System.currentTimeMillis();
                render.addEntity(pauseSign);
                render.togglePause();
            }
        }
    }

    public void setFantomes(ArrayList<Fantome> fantomes) { this.fantomes = fantomes; }
}
