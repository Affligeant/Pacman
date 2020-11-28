package Gameplay;

import Moteur.Character;
import Moteur.Text;

import java.io.FileNotFoundException;

public class Pacman extends Character {

    Text score;

    /**
     * @param x               Position {@code x} of Pacman.
     * @param y               Position {@code y} of Pacman.
     * @param size           Desired size of Pacman
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Pacman(double x, double y, double size, Text score) throws FileNotFoundException {
        super(x, y, "src/Images/cercle_jaune.png", size, size, new PacmanMovableBehavior());
        this.score = score;
    }

    public void addScore(double amount) {
        double actualScore = Double.parseDouble(score.getText());
        int increasedScore = (int) (actualScore + amount);
        score.setText(String.valueOf(increasedScore));
    }
}
