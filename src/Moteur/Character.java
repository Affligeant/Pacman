package Moteur;

/**
 * Classe {@code Character} étend {@code Entity} et implémente {@code Movable}.
 * Principalement défini comme une entité mobile.
 */
public abstract class Character extends Entity implements Movable{
    Direction direction;
    double x;
    double y;

    @Override
    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
