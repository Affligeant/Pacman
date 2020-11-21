package Moteur;

import javafx.scene.image.Image;

/**
 * Classe {@code Character} étend {@code Entity} et implémente {@code Movable}.
 * Principalement défini comme une entité mobile.
 */
public abstract class Character extends Entity implements Movable{
    Direction direction;
    MovableBehavior movableBehavior;

    public Character(double x, double y, Direction direction, Image skin, MovableBehavior movableBehavior) {
        super(x, y, skin);
        this.direction = direction;
        this.movableBehavior = movableBehavior;
    }

    @Override
    public void changeDirection(Direction direction) { this.direction = direction; }

    public Direction getDirection() { return direction; }
}
