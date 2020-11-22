package Gameplay;

import Moteur.Entity;

import java.io.FileNotFoundException;

public class Porte_Enclos extends Entity {

    public Porte_Enclos(double x, double y, double height, double width) throws FileNotFoundException {
        super(x, y, "./src/Images/porte_enclos.png", height, width, true);
    }

    public Porte_Enclos() throws FileNotFoundException {
        this(0, 0, 0, 0);
    }
}
