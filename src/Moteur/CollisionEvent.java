package Moteur;

public class CollisionEvent {
    private final Entity entity1;
    private final Entity entity2;
    private final String type1;
    private final String type2;
    private final long time;

    public CollisionEvent(Entity entity1, Entity entity2, String type1, String type2, long time) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.type1 = type1;
        this.type2 = type2;
        this.time = time;
    }

    public Entity getEntity1() { return entity1; }
    public Entity getEntity2() { return entity2; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public long getTime() { return time; }
}
