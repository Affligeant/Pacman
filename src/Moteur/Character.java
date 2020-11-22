package Moteur;

import java.io.FileNotFoundException;

/**
 * Classe {@code Character} étend {@code Entity} et implémente {@code Movable}.
 * Principalement défini comme une entité mobile.
 */
public class Character extends Entity implements Movable{
    MovableBehavior movableBehavior;
    private double vX;
    private double vY;
    private double old_vX;
    private double old_vY;

    public Character(double x, double y, String imagePath, double width, double height, MovableBehavior movableBehavior, String type) throws FileNotFoundException {
        super(x, y, imagePath, height, width, false, type);
        this.movableBehavior = movableBehavior;
        this.vX = 0;
        this.vY = 0;
        this.old_vX = 0;
        this.old_vY = 0;
    }

    public double getvX() { return vX; }
    public double getvY() { return vY; }
    public void setvX(double vX) { this.old_vX = this.vX; this.vX = vX; }
    public void setvY(double vY) { this.old_vY = this.vY; this.vY = vY; }
    public double getOld_vX() { return old_vX; }
    public double getOld_vY() { return old_vY; }

    public void update() { movableBehavior.update(this); }

    @Override
    public void move(long time) {
        this.setX(this.getX() + this.getvX());
        this.setY(this.getY() + this.getvY());
    }
}
