package Moteur;

/**
 * {@code Class CollisionEvent} represents a collision between 2 entites at a given time.
 */
public class CollisionEvent {
    private final Entity entity1;
    private final Entity entity2;
    private final long time;

    /**
     * @param entity1 The first {@code Entity} involved in the collision.
     * @param entity2 The second {@code Entity} involved in the collision.
     * @param time The time at which the collision occurred.
     */
    public CollisionEvent(Entity entity1, Entity entity2, long time) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.time = time;
    }

    public Entity getEntity1() { return entity1; }
    public Entity getEntity2() { return entity2; }
    public long getTime() { return time; }
}
