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
    MenuWindow.Difficulty difficulty;

    public Niveau(String filepath, double scoreMultiplier, FruitType fruitType, int firstNodeX, int firstNodeY, double tailleCase, double xMax, double yMax, Pacman pacman, MenuWindow.Difficulty difficulty) throws IOException {
        this.graph = Tools.constructGraph(tailleCase, firstNodeX, firstNodeY, Arrays.asList(1, 4), filepath);
        this.elements = Tools.mapFromFile(filepath, tailleCase, new PacmanEntityFactory());
        this.scoreMultiplier = scoreMultiplier;
        characters = new ArrayList<>();
        this.difficulty = difficulty;
        initCharacters(xMax, yMax, pacman, tailleCase, fruitType);
    }

    private void initCharacters(double xMax, double yMax, Pacman pacman, double tailleChar, FruitType fruitType) throws FileNotFoundException {

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

        switch (difficulty) {
            case EASY:
                RandomFantomMovableBehavior inkyB = new RandomFantomMovableBehavior(graph, pacman);
                RandomFantomMovableBehavior inky2B = new RandomFantomMovableBehavior(graph, pacman);
                RandomFantomMovableBehavior inky3B = new RandomFantomMovableBehavior(graph, pacman);

                Fantome inkyE = new Fantome("src/Images/inky.png", tailleChar, inkyB);
                Fantome inkyE2 = new Fantome("src/Images/inky.png", tailleChar, inky2B);
                Fantome inkyE3 = new Fantome("src/Images/inky.png", tailleChar, inky3B);

                characters.addAll(Arrays.asList(inkyE, inkyE2, inkyE3, fruit));
                break;

            case CLASSIC:
                RandomFantomMovableBehavior inkyCB = new RandomFantomMovableBehavior(graph, pacman);
                RandomFantomMovableBehavior inkyC2B = new RandomFantomMovableBehavior(graph, pacman);
                SemirandomFantomMovableBehavior clydeCB = new SemirandomFantomMovableBehavior(graph, pacman);
                SemirandomFantomMovableBehavior clydeC2B = new SemirandomFantomMovableBehavior(graph, pacman);

                Fantome inkyC = new Fantome("src/Images/inky.png", tailleChar, inkyCB);
                Fantome inkyC2 = new Fantome("src/Images/inky.png", tailleChar, inkyC2B);
                Fantome clydeC = new Fantome("src/Images/clyde.png", tailleChar, clydeCB);
                Fantome clydeC2 = new Fantome("src/Images/clyde.png", tailleChar, clydeC2B);

                characters.addAll(Arrays.asList(inkyC, inkyC2, clydeC, clydeC2));
                break;

            case HARD:
                RandomFantomMovableBehavior inkyBehavior = new RandomFantomMovableBehavior(graph, pacman);
                SemirandomFantomMovableBehavior clydeBehavior = new SemirandomFantomMovableBehavior(graph, pacman);
                AnticipateFantomBehavior pinkyBehavior = new AnticipateFantomBehavior(graph, pacman, xMax, yMax);
                FollowFantomBehavior blinkyBehavior = new FollowFantomBehavior(graph, pacman);

                Fantome inky = new Fantome("src/Images/inky.png", tailleChar, inkyBehavior);
                Fantome clyde = new Fantome("src/Images/clyde.png", tailleChar, clydeBehavior);
                Fantome pinky = new Fantome("src/Images/pinky.png", tailleChar, pinkyBehavior);
                Fantome blinky = new Fantome("src/Images/blinky.png", tailleChar, blinkyBehavior);

                characters.addAll(Arrays.asList(inky, clyde, pinky, blinky, fruit));
                break;
        }
    }

    public ArrayList<Entity> getElements() { return elements; }
    public ArrayList<Character> getCharacters() { return characters; }
    public double getScoreMultiplier() { return scoreMultiplier; }
}
