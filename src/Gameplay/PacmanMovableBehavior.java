package Gameplay;

import Moteur.KeyObserver;
import Moteur.Movable;
import Moteur.MovableBehavior;
import Moteur.Character;

public class PacmanMovableBehavior implements MovableBehavior, KeyObserver {

    String input = "";

    @Override
    public void update(String key, boolean pressed) {
        if(pressed) { input = key; }
        else if(input.equals(key)) { input = ""; }
    }

    @Override
    public void update(Movable entity) {
        Character c = (Character) entity;

        c.setOld_vX(c.getvX());
        c.setOld_vY(c.getvY());

        switch (input) {
            case "S":
            case "DOWN":
                c.setvY(3);
                c.setvX(0);
                break;
            case "Q":
            case "LEFT":
                c.setvY(0);
                c.setvX(-3);
                break;
            case "Z":
            case "UP":
                c.setvY(-3);
                c.setvX(0);
                break;
            case "D":
            case "RIGHT":
                c.setvY(0);
                c.setvX(3);
                break;
            default:
                break;
        }
    }
}
