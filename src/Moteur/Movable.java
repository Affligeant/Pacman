package Moteur;

/**
 * Interface {@code Movable} used to specify a movement. Usually implemented along with Entity.
 */
public interface Movable {

    /**
     * @param time The actual time in nanoseconds elapsed between the last call to this method and now.
     *             Can be used to implement the speed as a vector using time as a parameter.
     */
    void move(long time);
}
