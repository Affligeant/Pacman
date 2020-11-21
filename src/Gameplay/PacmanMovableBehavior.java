package Gameplay;

import Moteur.KeyObserver;
import Moteur.Movable;
import Moteur.MovableBehavior;

public class PacmanMovableBehavior implements MovableBehavior, KeyObserver {

    String input = "";

    @Override
    public void update(String key) {
        if(input.equals(key)) { input = ""; }
        else { input = key; }
    }

    @Override
    public void update(Movable entity) {
        switch (input) {
            case "S":
            case "DOWN":
                entity.changeDirection(Movable.Direction.BAS);
                entity.move();
                break;
            case "Q":
            case "LEFT":
                entity.changeDirection(Movable.Direction.GAUCHE);
                entity.move();
                break;
            case "Z":
            case "UP":
                entity.changeDirection(Movable.Direction.HAUT);
                entity.move();
                break;
            case "D":
            case "RIGHT":
                entity.changeDirection(Movable.Direction.DROITE);
                entity.move();
                break;
            default:
                break;
        }
    }
}
