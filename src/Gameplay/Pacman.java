package Gameplay;

import Moteur.Character;
import Moteur.Image;
import Moteur.Text;

import java.io.FileNotFoundException;

public class Pacman extends Character {

    Text score;

    int xSpeedFactor;
    int ySpeedFactor;
    long accelerateStateEnd;
    boolean accelerated;
    Image bas;
    Image haut;
    Image droite;
    Image gauche;
    int frameSkinCycle;

    /**
     * @param x               Position {@code x} of Pacman.
     * @param y               Position {@code y} of Pacman.
     * @param size           Desired size of Pacman
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Pacman(double x, double y, double size, Text score) throws FileNotFoundException {
        super(x, y, "src/Images/cercle_jaune.png", size, size, new PacmanMovableBehavior());
        this.score = score;
        xSpeedFactor = 1;
        ySpeedFactor = 1;
        haut = new Image("src/Images/pacman_haut.png", size, size);
        gauche = new Image("src/Images/pacman_gauche.png", size, size);
        droite = new Image("src/Images/pacman_droit.png", size, size);
        bas = new Image("src/Images/pacman_bas.png", size, size);
        frameSkinCycle = 0;
    }

    public void addScore(double amount) {
        double actualScore = Double.parseDouble(score.getText());
        int increasedScore = (int) (actualScore + amount);
        score.setText(String.valueOf(increasedScore));
    }

    public void accelerate(long duration) {
        if(!accelerated) {
            xSpeedFactor = 2;
            ySpeedFactor = 2;
            accelerateStateEnd = System.currentTimeMillis() + duration;
            accelerated = true;
            setvY(getvY() * 2);
            setvX(getvX() * 2);
            setX((int) getX() / 2 * 2);
            setY((int) getY() / 2 * 2);
        }
        else {
            accelerateStateEnd += duration;
        }
    }

    public void stopAcceleration() {
        xSpeedFactor = 1;
        ySpeedFactor = 1;
        accelerated = false;
        setvX(getvX() / 2);
        setvY(getvY() / 2);
    }

    public boolean isAccelerated() { return accelerated; }

    @Override
    public Image getSkin() {
        if(frameSkinCycle < 10) {
            return super.getSkin();
        }
        else {
            if(getvY() > 0) {
                return bas;
            }
            else if (getvY() < 0) {
                return haut;
            }
            else if(getvX() > 0) {
                return droite;
            }
            else if(getvX() < 0) {
                return gauche;
            }
            else {
                return super.getSkin();
            }
        }
    }

    public void addFrameSkinCycle() { frameSkinCycle = (frameSkinCycle + 1) % 20; }
}
