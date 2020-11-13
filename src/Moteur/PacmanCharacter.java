package Moteur;

import javafx.scene.image.Image;

public class PacmanCharacter extends Character {

    private double x;
    private double y;
    private Direction direction;
    Image skin;

    public PacmanCharacter(double x, double y, Direction direction, Image skin) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.skin = skin;
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
