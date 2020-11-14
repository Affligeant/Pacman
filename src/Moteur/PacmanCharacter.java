package Moteur;

import javafx.scene.image.Image;

public class PacmanCharacter extends Character {

    public PacmanCharacter(double x, double y, Direction direction, Image skin) {
        super(x, y, direction, skin);
    }

    @Override
    public void move() {
        switch(direction) {
            case HAUT:
                this.y -= 1;
                break;
            case DROITE:
                this.x += 1;
                break;
            case BAS:
                this.y += 1;
                break;
            case GAUCHE:
                this.x -= 1;
                break;
            default:
                break;
        }
    }
}
