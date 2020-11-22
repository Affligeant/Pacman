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

    public boolean notify(CollisionEvent collisionEvent) {
        boolean triggered = false;
        for(CollisionManager cm : observers) {
            triggered = cm.update(collisionEvent);
        }
        return triggered;
    }

}
