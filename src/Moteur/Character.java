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
    private long old_move;

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
    public void setvX(double vX) { this.vX = vX; }
    public void setvY(double vY) { this.vY = vY; }
    public void setOld_vX(double old_vX) { this.old_vX = old_vX; }
    public void setOld_vY(double old_vY) { this.old_vY = old_vY; }
    public double getOld_vX() { return old_vX; }
    public double getOld_vY() { return old_vY; }
    public long getOld_move() { return old_move; }

    public void update() { movableBehavior.update(this); }

    @Override
    public void move(long time) {
        old_move = time;
        long temps = 1; //time / 8000000;
        this.setX(this.getX() + (this.getvX() * temps));
        this.setY(this.getY() + (this.getvY() * temps));
    }

    public void back() {
        long temps = 1; //old_move / 8000000;
        this.setX(this.getX() - (this.getvX() * temps));
        this.setY(this.getY() - (this.getvY() * temps));
    }
}
