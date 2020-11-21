package Gameplay;

import Moteur.KeyObserver;
import Moteur.Movable;
import Moteur.MovableBehavior;
import Moteur.Character;

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
                ((Character) entity).setvY(1);
                ((Character) entity).setvX(0);
                break;
            case "Q":
            case "LEFT":
                ((Character) entity).setvY(0);
                ((Character) entity).setvX(-1);
                break;
            case "Z":
            case "UP":
                ((Character) entity).setvY(-1);
                ((Character) entity).setvX(0);
                break;
            case "D":
            case "RIGHT":
                ((Character) entity).setvY(0);
                ((Character) entity).setvX(1);
                break;
            default:
                break;
        }
    }
}
