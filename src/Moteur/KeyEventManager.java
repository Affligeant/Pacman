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
        scene.setOnKeyPressed(this::notify);
        scene.setOnKeyReleased(this::notify);
        key = new HashMap<>();
        for(KeyCode c : KeyCode.values()) {
            key.put(c.toString(), new ArrayList<>());
        }
    }

    public void notify(KeyEvent keyEvent) {
        String keyCode = keyEvent.getCode().toString();
        for(KeyObserver k : key.get(keyCode)) {
            k.update(keyCode);
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
