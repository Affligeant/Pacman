package Moteur;

import javafx.scene.input.KeyEvent;

public class PacmanEventManager implements EventManager{

    @Override
    public void handle(KeyEvent event, Character character) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED) {
            switch(event.getText()) {
                case "S":
                case "DOWN":
                    character.changeDirection(Movable.Direction.BAS);
                    break;
                case "Q":
                case "LEFT":
                    character.changeDirection(Movable.Direction.GAUCHE);
                    break;
                case "Z":
                case "UP":
                    character.changeDirection(Movable.Direction.HAUT);
                    break;
                case "D":
                case "RIGHT":
                    character.changeDirection(Movable.Direction.DROITE);
                    break;
                default:
                    break;
            }
        }
    }
}
