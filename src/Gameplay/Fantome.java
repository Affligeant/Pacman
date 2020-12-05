package Gameplay;

import Moteur.Character;
import Moteur.Image;
import Moteur.MovableBehavior;

import java.io.FileNotFoundException;

public class Fantome extends Character {

    public static final double X = 11*30;
    public static final double Y = 14*30;

    boolean scared;
    Image scaredSkin;
    long scareEnd;
    boolean eaten;

    /**
     * @param imagePath       Path of the {@code Image} associated to the fantom.
     * @param taille          Desired size of the fantom. Appearance is resized to this width.
     * @param movableBehavior The behavior associated to the fantom.
     * @throws FileNotFoundException When the image files can't be found.
     */
    public Fantome(String imagePath, double taille, MovableBehavior movableBehavior) throws FileNotFoundException {
        super(X, Y, imagePath, taille, taille, movableBehavior);
        scared = false;
        scaredSkin = new Image("src/Images/scared.png", taille, taille);
        eaten = false;
    }

    public boolean isScared() { return scared; }

    @Override
    public Image getSkin() {
        if(isScared()) {
            return scaredSkin;
        }
        return super.getSkin();
    }

    public void scare(long duration) {
        if(((FantomMovableBehavior) this.getMovableBehavior()).hasStarted()) {
            if(isScared()) {
                scareEnd += duration;
            }
            else {
                scareEnd = System.currentTimeMillis() + duration;
                scared = true;
            }
        }
    }

    public void stopScare() { this.scared = false; }

    public void eat() {
        this.eaten = true;
        this.setY(((int) this.getY() / 15) * 15);
        this.setX(((int) this.getX() / 15) * 15);
    }

    public boolean isEaten() { return eaten; }

    @Override
    public void move(long time) {
        if(!isEaten()) {
            super.move(time);
        }
        else {
            this.setX(this.getX() + (this.getvX() * 15));
            this.setY(this.getY() + (this.getvY() * 15));
        }
    }

    public void notEaten() { eaten = false; }
}
