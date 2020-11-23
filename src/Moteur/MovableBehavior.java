package Moteur;

public interface MovableBehavior {

    /**
     * Figures the next move of a given entity and applies it.
     *
     * @param entity The entity this behaviour must be applied to.
     */
    void update(Movable entity);
}
