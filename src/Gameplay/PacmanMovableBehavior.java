package Gameplay;

import Moteur.KeyObserver;
import Moteur.Movable;
import Moteur.MovableBehavior;

public class PacmanMovableBehavior implements MovableBehavior, KeyObserver {

    String input = "";

    @Override
    public void update(String key, boolean pressed) {
        if(pressed) { input = key; }
        else if(input.equals(key)) { input = ""; }
    }

    @Override
    public void update(Movable entity) {
        Pacman c = (Pacman) entity;
        c.addFrameSkinCycle();

        c.setOld_vX(c.getvX());
        c.setOld_vY(c.getvY());

        if(c.isAccelerated() && System.currentTimeMillis() > c.accelerateStateEnd) {
            c.stopAcceleration();
        }

        switch (input) {
            case "S":
            case "DOWN":
                c.setvY(c.ySpeedFactor);
                c.setvX(0);
                break;
            case "Q":
            case "LEFT":
                c.setvY(0);
                c.setvX(-1 * c.xSpeedFactor);
                break;
            case "Z":
            case "UP":
                c.setvY(-1 * c.ySpeedFactor);
                c.setvX(0);
                break;
            case "D":
            case "RIGHT":
                c.setvY(0);
                c.setvX(c.xSpeedFactor);
                break;
            default:
                break;
        }
    }
}
