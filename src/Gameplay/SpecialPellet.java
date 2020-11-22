package Gameplay;

import Moteur.Entity;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpecialPellet extends Entity {

    private boolean ate;
    private final Image eatenImage;

    public SpecialPellet(double x, double y, double height, double width) throws FileNotFoundException {
        super(x, y, "./src/Images/boule_speciale.png", height, width, false);
        eatenImage = new Image(new FileInputStream("./src/Images/chemin.png"), width, height, false, false);
        ate = false;
    }

    public SpecialPellet() throws FileNotFoundException {
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
