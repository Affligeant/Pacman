package Moteur;

import javafx.scene.image.Image;

/**
 * Classe {@code Character} étend {@code Entity} et implémente {@code Movable}.
 * Principalement défini comme une entité mobile.
 */
public abstract class Character extends Entity implements Movable{
    Direction direction;

    public Character(double x, double y, Direction direction, Image skin) {
        super(x, y, skin);
        this.direction = direction;
    }

    @Override
    public void changeDirection(Direction direction) {
        this.direction = direction;
    }
}
