package Gameplay;

import Moteur.Character;
import Moteur.MovableBehavior;

import java.io.FileNotFoundException;

public class Fantome extends Character {

    private static final double X = 11*30;
    private static final double Y = 14*30;

    /**
     * @param imagePath       Path of the {@code Image} associated to the fantom.
     * @param taille           Desired size of the fantom. Appearance is resized to this width.
     * @param movableBehavior The behavior associated to the fantom.
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Fantome(String imagePath, double taille, MovableBehavior movableBehavior) throws FileNotFoundException {
        super(X, Y, imagePath, taille, taille, movableBehavior);
    }
}
