package Moteur;

/**
 * Interface {@code CollisionManager} used to receive collisions events.
 *
 * Must be implemented in a class then added to the {@code Render}'s CollisionManagers
 * using {@code render.addObserver(collisionManager)} in order to receive events.
 */
public interface CollisionManager {

    /**
     * Receives the collisions events associated to it's {@code Render}.
     *
     * @param collisionEvent The event.
     */
    boolean update(CollisionEvent collisionEvent);
}
