package Gameplay;

import Moteur.Entity;

import java.io.FileNotFoundException;

public class Pellet extends Entity {

    boolean eaten;
    public Pellet(double x, double y, String imagePath, double height, double width, boolean physical, String type) throws FileNotFoundException {
        super(x, y, imagePath, height, width, physical, type);
        eaten = false;
    }

    public void eat() {
        eaten = true;
    }
}
