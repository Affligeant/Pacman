package Moteur;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class PacmanEventManager implements EventManager {

    private KeyEvent event;

    public PacmanEventManager(Scene scene) {
        scene.setOnKeyPressed(e -> event = e);
        scene.setOnKeyReleased(e -> {
            if(event != null && event.getCode().toString().equals(e.getCode().toString())) { event = null; }
        });
    }

    @Override
    public void handle(Character character) {
        if(event != null) {
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                switch (event.getCode().toString()) {
                    case "S":
                    case "DOWN":
                        character.changeDirection(Movable.Direction.BAS);
                        character.move();
                        break;
                    case "Q":
                    case "LEFT":
                        character.changeDirection(Movable.Direction.GAUCHE);
                        character.move();
                        break;
                    case "Z":
                    case "UP":
                        character.changeDirection(Movable.Direction.HAUT);
                        character.move();
                        break;
                    case "D":
                    case "RIGHT":
                        character.changeDirection(Movable.Direction.DROITE);
                        character.move();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
