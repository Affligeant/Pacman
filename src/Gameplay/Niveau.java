package Gameplay;

import Moteur.Character;
import Moteur.Entity;
import Moteur.Graph;
import Moteur.Tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Niveau {

    enum FruitType {
        POMME, PECHE, CERISES
    }

    Fruit fruit;
    Graph graph;
    ArrayList<Character> characters;
    ArrayList<Entity> elements;
    double scoreMultiplier;

    public Niveau(String filepath, double scoreMultiplier, FruitType fruitType, int firstNodeX, int firstNodeY, double tailleCase, double xMax, double yMax, Character pacman) throws IOException {
        this.graph = Tools.constructGraph(tailleCase, firstNodeX, firstNodeY, Arrays.asList(1, 4), filepath);
        this.elements = Tools.mapFromFile(filepath, tailleCase, new PacmanEntityFactory());
        this.scoreMultiplier = scoreMultiplier;
        characters = new ArrayList<>();
        initCharacters(xMax, yMax, pacman, tailleCase, fruitType);
    }

    private void initCharacters(double xMax, double yMax, Character pacman, double tailleChar, FruitType fruitType) throws FileNotFoundException {

        RandomFantomMovableBehavior inkyBehavior = new RandomFantomMovableBehavior(graph);
        SemirandomFantomMovableBehavior clydeBehavior = new SemirandomFantomMovableBehavior(graph, pacman);
        AnticipateFantomBehavior pinkyBehavior = new AnticipateFantomBehavior(graph, pacman, xMax, yMax);
        FollowFantomBehavior blinkyBehavior = new FollowFantomBehavior(graph, pacman);

        Fantome inky = new Fantome("src/Images/inky.png", tailleChar, inkyBehavior);
        Fantome clyde = new Fantome("src/Images/clyde.png", tailleChar, clydeBehavior);
        Fantome pinky = new Fantome("src/Images/pinky.png", tailleChar, pinkyBehavior);
        Fantome blinky = new Fantome("src/Images/blinky.png", tailleChar, blinkyBehavior);

        switch(fruitType) {
            case PECHE:
                fruit = new Peche(graph, tailleChar);
                break;
            case POMME:
                fruit = new Pomme(graph, tailleChar);
                break;
            case CERISES:
                fruit = new Cerises(graph, tailleChar);
                break;
            default:
                System.out.println("Fruit impossible à déterminer");
                break;
        }

        characters.addAll(Arrays.asList(inky, clyde, pinky, blinky, fruit));

    }

    public ArrayList<Entity> getElements() { return elements; }
    public ArrayList<Character> getCharacters() { return characters; }
    public double getScoreMultiplier() { return scoreMultiplier; }
}
