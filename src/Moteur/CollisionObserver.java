package Moteur;

import java.util.ArrayList;
import java.util.Collection;

public class CollisionObserver {

    ArrayList<CollisionManager> observers;

    public CollisionObserver() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(CollisionManager collisionManager) {
        observers.add(collisionManager);
    }

    public void addObserver(Collection<CollisionManager> collisionManagers) {
        observers.addAll(collisionManagers);
    }

    public void notify(CollisionEvent collisionEvent) {
        for(CollisionManager cm : observers) {
            cm.update(collisionEvent);
        }
    }

}
