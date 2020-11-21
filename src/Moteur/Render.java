package Moteur;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collection;

public class Render extends AnimationTimer {

    ArrayList<Character> characters;
    ArrayList<Entity> entities;
    KeyEventManager keyEventManager;
    GraphicsContext gc;
    long lastTimeICheckedMyWatch;

    public Render(GraphicsContext gc, Scene scene) {
        this.gc = gc;
        this.keyEventManager = new KeyEventManager(scene);
        characters = new ArrayList<>();
        entities = new ArrayList<>();
        lastTimeICheckedMyWatch = System.nanoTime();
    }

    @Override
    public void handle(long now) {
        gc.clearRect(0, 0, 10000, 10000); //À modifier plus tard
        for(Entity e : entities) {
            gc.drawImage(e.skin, e.x, e.y);
        }
        for(Character c : characters) {
            c.move(now-lastTimeICheckedMyWatch);
        }
        lastTimeICheckedMyWatch = now;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
        if(entity instanceof Character) {
            characters.add((Character) entity);
        }
    }

    public void addEntity(Collection<Entity> entities) {
        for(Entity e : entities) {
            this.entities.add(e);
            if(e instanceof Character) {
                characters.add((Character) e);
            }
        }
    }

    public KeyEventManager getKeyEventManager() {
        return keyEventManager;
    }
}
