package Gameplay;

import Moteur.Entity;
import Moteur.Image;

import java.io.FileNotFoundException;

public class Pellet extends Entity {

    private boolean ate;
    private final Image eatenImage;

    public Pellet(double x, double y, double height, double width) throws FileNotFoundException {
        super(x, y, "./src/Images/boule_simple.png", height, width, false);
        eatenImage = new Image("./src/Images/chemin.png", width, height);
        ate = false;
    }

    public Pellet() throws FileNotFoundException {
        this(0, 0, 0, 0);
    }

    public void eat() { ate = true; }
    public boolean isAte() { return ate; }

    @Override
    public Image getSkin() {
        if(ate) {
            return eatenImage;
        }
        return super.getSkin();
    }
}
