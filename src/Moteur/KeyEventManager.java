package Moteur;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class KeyEventManager {

    HashMap<String, ArrayList<KeyObserver>> key;

    public KeyEventManager(Scene scene) {
        scene.setOnKeyPressed(e -> notify(e, true));
        scene.setOnKeyReleased(e -> notify(e, false));
        key = new HashMap<>();
        for(KeyCode c : KeyCode.values()) {
            key.put(c.toString(), new ArrayList<>());
        }
    }

    public void notify(KeyEvent keyEvent, boolean pressed) {
        String keyCode = keyEvent.getCode().toString();
        for(KeyObserver k : key.get(keyCode)) {
            k.update(keyCode, pressed);
        }
    }

    public void add(KeyObserver obs, String keyCode) {
        key.get(keyCode).add(obs);
    }

    public void add(Collection<KeyObserver> obs, String keyCode) {
        key.get(keyCode).addAll(obs);
    }

    public void add(KeyObserver obs, Collection<String> keyCodes) {
        for(String k : keyCodes) {
            key.get(k).add(obs);
        }
    }

    public void add(Collection<KeyObserver> obs, Collection<String> keyCodes) {
        for(String k : keyCodes) {
            key.get(k).addAll(obs);
        }
    }
}
