package Moteur;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class KeyEventManager {

    HashMap<String, ArrayList<KeyObserver>> key;

    /**
     * @param scene JavaFX scene to which is applied the keyboard input listening.
     */
    public KeyEventManager(Scene scene) {
        scene.setOnKeyPressed(e -> notify(e, true));
        scene.setOnKeyReleased(e -> notify(e, false));
        key = new HashMap<>();
        for(KeyCode c : KeyCode.values()) {
            key.put(c.toString(), new ArrayList<>());
        }
    }

    /**
     * Method used to adress the key event to all the matched KeyObservers.
     *
     * @param keyEvent The key event received by the scene.
     * @param pressed True if the key was pressed, else false if it was released.
     */
    public void notify(KeyEvent keyEvent, boolean pressed) {
        String keyCode = keyEvent.getCode().toString();
        for(KeyObserver k : key.get(keyCode)) {
            k.update(keyCode, pressed);
        }
    }

    /**
     * Matches an observer and a key. The observer will receive key events
     * from this key.
     *
     * @param obs The observer.
     * @param keyCode The key.
     */
    public void add(KeyObserver obs, String keyCode) { key.get(keyCode).add(obs); }

    /**
     * Matches a collection of observers to a key.
     *
     * @param obs The collection of observers.
     * @param keyCode The key to be matched to.
     */
    public void add(Collection<KeyObserver> obs, String keyCode) { key.get(keyCode).addAll(obs); }

    /**
     * Matches an observer to a list of keys.
     *
     * @param obs The observer.
     * @param keyCodes The collection of keys.
     */
    public void add(KeyObserver obs, Collection<String> keyCodes) {
        for(String k : keyCodes) {
            key.get(k).add(obs);
        }
    }

    /**
     * Matches a collection observers and a collection of key.
     *
     * @param obs Collection of observers receiving the key events.
     * @param keyCodes Collection of Key to match the observers to.
     */
    public void add(Collection<KeyObserver> obs, Collection<String> keyCodes) {
        for(String k : keyCodes) {
            key.get(k).addAll(obs);
        }
    }
}
