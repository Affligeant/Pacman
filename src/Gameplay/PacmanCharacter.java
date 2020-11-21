package Gameplay;

import Moteur.Character;
import Moteur.MovableBehavior;
import javafx.scene.image.Image;

public class PacmanCharacter extends Character {

    public PacmanCharacter(double x, double y, Direction direction, Image skin, MovableBehavior movableBehavior) {
        super(x, y, direction, skin, movableBehavior);
    }

    @Override
    public void move() {
        switch(getDirection()) {
            case HAUT:
                this.setY(this.getY()-1);
                break;
            case DROITE:
                this.setX(this.getX()+1);
                break;
            case BAS:
                this.setY(this.getY()+1);
                break;
            case GAUCHE:
                this.setX(this.getX()-1);
                break;
            default:
                break;
        }
    }
}
