package Moteur;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Render extends AnimationTimer {

    ArrayList<Character> characters;
    ArrayList<Entity> entities;
    KeyEventManager keyEventManager;
    GraphicsContext gc;
    long lastTimeICheckedMyWatch;
    CollisionObserver collisionObserver;

    public Render(GraphicsContext gc, Scene scene) {
        this.gc = gc;
        this.keyEventManager = new KeyEventManager(scene);
        characters = new ArrayList<>();
        entities = new ArrayList<>();
        lastTimeICheckedMyWatch = System.nanoTime();
        collisionObserver = new CollisionObserver();
    }

    @Override
    public void handle(long now) {
        gc.clearRect(0, 0, 10000, 10000);
        for(Entity e : entities) {
            if(!characters.contains(e)) {
                gc.drawImage(e.getSkin(), e.getX(), e.getY());
            }
        }
        for(Character c : characters) {
            gc.drawImage(c.getSkin(), c.getX(), c.getY());
            c.update();
            c.move(now-lastTimeICheckedMyWatch);
        }
        lastTimeICheckedMyWatch = now;
        while(detectCollisions()){}
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

    public void removeEntity(Collection<Entity> entities) {
        for(Entity e : entities) {
            this.entities.remove(e);
            if(e instanceof Character) {
                characters.remove(e);
            }
        }
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
        if(entity instanceof Character) {
            characters.remove(entity);
        }
    }

    public KeyEventManager getKeyEventManager() { return keyEventManager; }

    public void addObserver(CollisionManager collisionManager) { collisionObserver.addObserver(collisionManager); }

    public boolean detectCollisions() {

        boolean collisionOccured = false;

        for(Character c : characters) {
            for(Entity e : entities) {
                if(c != e) {
                    double baseX1 = c.getX();
                    double baseY1 = c.getY();
                    double finX1 = baseX1 + c.getWidth() - 1;
                    double finY1 = baseY1 + c.getHeight() - 1;

                    double baseX2 = e.getX();
                    double baseY2 = e.getY();
                    double finX2 = baseX2 + e.getWidth() - 1;
                    double finY2 = baseY2 + e.getHeight() - 1;

                    if(!(finX1 < baseX2) && !(baseX1 > finX2) && !(finY1 < baseY2) && !(baseY1 > finY2)) {
                        //Alors il y a collision
                        CollisionEvent ce = new CollisionEvent(c, e, lastTimeICheckedMyWatch);
                        if(collisionObserver.notify(ce)) {
                            collisionOccured = true;
                        }
                    }
                }
            }
        }

        return collisionOccured;
    }
}
