package Gameplay;

import Moteur.Graph;

import java.io.FileNotFoundException;

public class Pomme extends Fruit {

    /**
     * @param g         Graph of the level
     * @param size      Size of the fruit
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Pomme(Graph g, double size) throws FileNotFoundException {
        super(g, "./src/Images/pomme.png", size, 600);
    }
}
