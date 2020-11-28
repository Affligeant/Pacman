package Gameplay;

import Moteur.Graph;

import java.io.FileNotFoundException;

public class Cerises extends Fruit {

    /**
     * @param g         Graph of the level
     * @param size      Size of the fruit
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Cerises(Graph g, double size) throws FileNotFoundException {
        super(g, "./src/Images/cerises.png", size, 1200);
    }
}
