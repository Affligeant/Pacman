package Gameplay;

import Moteur.CollisionEvent;
import Moteur.CollisionManager;

public class PacmanCollisionManager extends CollisionManager {

    PacmanCollisionManager() {
        super();
    }

    @Override
    public boolean update(CollisionEvent collisionEvent) {
        return false;
    }
}
