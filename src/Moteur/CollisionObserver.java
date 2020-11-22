package Moteur;

import java.util.ArrayList;
import java.util.Collection;

public class CollisionObserver {

    ArrayList<CollisionManager> observers;

    public CollisionObserver() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(CollisionManager collisionManager) { observers.add(collisionManager); }

    public void addObserver(Collection<CollisionManager> collisionManagers) { observers.addAll(collisionManagers); }

    public boolean notify(CollisionEvent collisionEvent) {
        boolean triggered = false;
        for(CollisionManager cm : observers) {
            triggered = cm.autoPhysicHandle(collisionEvent);
        }
        return triggered;
    }

    public boolean notify(Collection<CollisionEvent> collisionEvents) {
        boolean triggered = false;
        for(CollisionManager cm : observers) {
            if(cm.autoPhysicHandle(collisionEvents)) {
                triggered = true;
            }
        }
        return triggered;
    }

}
