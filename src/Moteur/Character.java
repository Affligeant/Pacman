package Moteur;

import java.io.FileNotFoundException;

/**
 * {@code Class Character} extends {@code Entity} and implements {@code Movable}.
 * Is thus seen as the basic movable entity.
 */
public class Character extends Entity implements Movable{
    MovableBehavior movableBehavior;
    private double vX;
    private double vY;
    private double old_vX;
    private double old_vY;
    private long old_move;

    /**
     * @param x Position {@code x} of the character.
     * @param y Position {@code y} of the character.
     * @param imagePath Path of the {@code Image} associated to the character.
     * @param width Desired {@code width} of the character. Appearance is resized to this width.
     * @param height Desired {@code height} of the character. Appearance is resized to this height.
     * @param movableBehavior The behavior associated to the character.
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Character(double x, double y, String imagePath, double width, double height, MovableBehavior movableBehavior) throws FileNotFoundException {
        super(x, y, imagePath, height, width, false);
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

    /**
     * Used to move the character depending on it's position vectors.
     *
     * @param time The actual time in nanoseconds elapsed between the last call to this method and now.
     */
    @Override
    public void move(long time) {
        old_move = time;
        long temps = 1; //time / 8000000;
        this.setX(this.getX() + (this.getvX() * temps));
        this.setY(this.getY() + (this.getvY() * temps));
    }

    /**
     * Used to do the movement in the other direction, can be either
     * used to move backwards or to cancel a previous movement.
     */
    public void back() {
        long temps = 1; //old_move / 8000000;
        this.setX(this.getX() - (this.getvX() * temps));
        this.setY(this.getY() - (this.getvY() * temps));
    }
}
