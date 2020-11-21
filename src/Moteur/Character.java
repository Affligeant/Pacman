package Moteur;

import javafx.scene.image.Image;

/**
 * Classe {@code Character} étend {@code Entity} et implémente {@code Movable}.
 * Principalement défini comme une entité mobile.
 */
public class Character extends Entity implements Movable{
    MovableBehavior movableBehavior;

    public Character(double x, double y, Image skin, double width, double height, MovableBehavior movableBehavior) {
        super(x, y, skin, height, width);
        this.movableBehavior = movableBehavior;
    }

    public void update() {
        movableBehavior.update(this);
    }

    @Override
    public void move(long time) {
        this.setX(this.getX() + this.getvX());
        this.setY(this.getY() + this.getvY());
    }
}
