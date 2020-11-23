package Moteur;

/**
 * Interface {@code KeyObserver} used to receive certain key events.
 *
 * Must be implemented in a class then added to the Render's KeyEventManager
 * in order to receive the key events.
 *
 * If the key events are still not triggered, you may check if the scene
 * given to the Render is valid.
 */
public interface KeyObserver {

    /**
     * Receives the key events associated to it's KeyEventManager.
     *
     * @param key The string corresponding to the key pressed.
     * @param pressed True if the key was pressed, else false if it was released.
     */
    void update(String key, boolean pressed);
}
