package Gameplay;

import Moteur.Character;
import Moteur.Graph;
import Moteur.Image;

import java.io.FileNotFoundException;

public class Fruit extends Character {

    private boolean ate;
    private final Image eatenImage;
    private final double value;

    /**
     * @param g               Graph of the level
     * @param imagePath       Path of the {@code Image} associated to the character.
     * @param size            Size of the fruit
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Fruit(Graph g, String imagePath, double size, double value) throws FileNotFoundException {
        super(g.getNodes().get(0).getX(), g.getNodes().get(0).getY(), imagePath, size, size, new FruitMovableBehavior(g));
        eatenImage = new Image("src/Images/chemin.png", size, size);
        ate = false;
        this.value = value;
    }

    public void eat() { ate = true; }
    public boolean isAte() { return ate; }
    public double getValue() { return value; }

    @Override
    public Image getSkin() {
        if(ate) {
            return eatenImage;
        }
        return super.getSkin();
    }
}
