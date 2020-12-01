package Gameplay;

import Moteur.Character;
import Moteur.Text;

import java.io.FileNotFoundException;

public class Pacman extends Character {

    Text score;

    int xSpeedFactor;
    int ySpeedFactor;
    long accelerateStateEnd;
    boolean accelerated;

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
}
