package Gameplay;

import Moteur.Entity;

import java.io.FileNotFoundException;

public class Chemin extends Entity {

    public Chemin(double x, double y, double height, double width) throws FileNotFoundException {
        super(x, y, "./src/Images/chemin.png", height, width, false);
    }

    public Chemin() throws FileNotFoundException {
        this(0, 0, 0, 0);
    }
}
