package Gameplay;

import Moteur.Entity;

import java.io.FileNotFoundException;

public class Mur extends Entity {
    public Mur(double x, double y, double height, double width) throws FileNotFoundException {
        super(x, y, "./src/Images/mur.png", height, width, true);
    }

    public Mur() throws FileNotFoundException {
        this(0, 0, 0, 0);
    }
}