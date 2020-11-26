package Moteur;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Render extends AnimationTimer {

    ArrayList<Character> characters;
    ArrayList<Entity> entities;
    KeyEventManager keyEventManager;
    GraphicsContext gc;
    long lastTimeICheckedMyWatch;
    CollisionObserver collisionObserver;
    double width;
    double height;
    Color backgroundColor;
    EdgeType edgeType;

    public enum EdgeType {
        NONE, BLOCK, WARP;
    }

    /**
     * Main class of a graphical game, used to display entities and notify observers.
     * This constructor needs the graphical context and it's size as well as the scene associated.
     *
     * @param gc The graphicsContext in which the Render is display entities.
     * @param scene The scene associated with the graphicsContext, used to link the KeyEventManager.
     * @param width Width of the display space, to refresh it.
     * @param height Height of the display space, to refresh it.
     * @param edgeType Type of collision triggered with the edge of the graphical area.
     */
    public Render(GraphicsContext gc, Scene scene, double width, double height, Color backgroundColor, EdgeType edgeType) {
        this.gc = gc;
        this.keyEventManager = new KeyEventManager(scene);
        characters = new ArrayList<>();
        entities = new ArrayList<>();
        lastTimeICheckedMyWatch = System.nanoTime();
        collisionObserver = new CollisionObserver();
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.edgeType = edgeType;
    }

    /**
     * Main method of rendering, triggered at each frame.
     *
     * @param now Time given by Timers from extending AnimationTimer.
     */
    @Override
    public void handle(long now) {
        gc.clearRect(0, 0, width, height);
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, width, height);

        for(Entity e : entities) {
            if(!(e instanceof Character)) {
                gc.drawImage(e.getSkin(), e.getX(), e.getY());
            }
        }

        for(Character c : characters) {
            c.update();
            c.move(now-lastTimeICheckedMyWatch);

            if(c.getX() > width) {
                switch (edgeType) {
                    case BLOCK:
                        c.setX(width);
                        break;
                    case WARP:
                        c.setX(0);
                        break;
                    case NONE:
                    default:
                        break;
                }
            }
            else if(c.getX() < 0) {
                switch (edgeType) {
                    case BLOCK:
                        c.setX(0);
                        break;
                    case WARP:
                        c.setX(width);
                        break;
                    case NONE:
                    default:
                        break;
                }
            }

            if(c.getY() > height) {
                switch (edgeType) {
                    case BLOCK:
                        c.setY(height);
                        break;
                    case WARP:
                        c.setY(0);
                        break;
                    case NONE:
                    default:
                        break;
                }
            }
            else if(c.getY() < 0) {
                switch (edgeType) {
                    case BLOCK:
                        c.setY(0);
                        break;
                    case WARP:
                        c.setY(height);
                        break;
                    case NONE:
                    default:
                        break;
                }
            }

        }

        lastTimeICheckedMyWatch = now;
        while(detectCollisions()){}

        for(Character c : characters) {
            gc.drawImage(c.getSkin(), c.getX(), c.getY());
        }
    }

    /**
     * Adds a single entity to the Render.
     *
     * @param entity The entity to add.
     */
    public void addEntity(Entity entity) {
        this.entities.add(entity);
        if(entity instanceof Character) {
            characters.add((Character) entity);
        }
    }

    /**
     * Adds a collection of entities to the Render.
     *
     * @param entities The entities to add.
     */
    public void addEntity(Collection<Entity> entities) {
        for(Entity e : entities) {
            this.entities.add(e);
            if(e instanceof Character) {
                characters.add((Character) e);
            }
        }
    }

    /**
     * Removes a collection of entities.
     *
     * @param entities The entities to remove.
     */
    public void removeEntity(Collection<Entity> entities) {
        for(Entity e : entities) {
            this.entities.remove(e);
            if(e instanceof Character) {
                characters.remove(e);
            }
        }
    }

    /**
     * Removes a single entity.
     *
     * @param entity The entity to remove.
     */
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
        if(entity instanceof Character) {
            characters.remove(entity);
        }
    }

    /**
     * Used to add keys to listen to.
     *
     * @return A {@code KeyEventManager}.
     */
    public KeyEventManager getKeyEventManager() { return keyEventManager; }

    /** Add an CollisionManager observer, which will be notified at every collision.
     * @param collisionManager The collisionManager to add.
     */
    public void addObserver(CollisionManager collisionManager) { collisionObserver.addObserver(collisionManager); }

    /** Used to detect collisions between fixed Entities and moving Entities,
     * and between moving Entities.
     * @return {@code True} if the notifying of a collision has potentially
     * triggered a new collision and needs re-check.
     */
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
