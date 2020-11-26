package Gameplay;

import Moteur.*;
import Moteur.Character;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWindow extends Window {

    private Canvas canvas;

    public GameWindow(double height, double width) { super(height, width); }

    public void init() {
        this.canvas = new Canvas(width, height);
        borderPane.setCenter(canvas);

        Render render = new Render(getGraphicsContext(), getScene(), width, height, Color.BLACK, Render.EdgeType.WARP);
        initCharacters(render);

        ArrayList<Entity> entityArrayList;

        try {
            entityArrayList = Tools.mapFromFile("./src/Gameplay/niveau3.map", 30, new PacmanEntityFactory());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        if(entityArrayList != null) { render.addEntity(entityArrayList); }

        render.start();
    }

    private void initCharacters(Render render) {

        Graph g = null;
        try {
            g = Tools.constructGraph(30, 6, 1, Arrays.asList(1, 4),"src/Gameplay/niveau3.map");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Character pacman = null;
        Character inky = null;
        Character clyde = null;
        Character blinky = null;
        Character pinky = null;
        PacmanMovableBehavior pacmanMovableBehavior = new PacmanMovableBehavior();

        render.getKeyEventManager().add(pacmanMovableBehavior, Arrays.asList("UP", "DOWN", "LEFT", "RIGHT", "Z", "Q", "S", "D"));

        try {
            pacman = new Character(14*30, 23*30, "./src/Images/cercle_jaune.png", 30, 30, pacmanMovableBehavior);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RandomFantomMovableBehavior inkyBehavior = new RandomFantomMovableBehavior(g);
        SemirandomFantomMovableBehavior clydeBehavior = new SemirandomFantomMovableBehavior(g, pacman);
        AnticipateFantomBehavior pinkyBehavior = new AnticipateFantomBehavior(g, pacman);
        FollowFantomBehavior blinkyBehavior = new FollowFantomBehavior(g, pacman);

        try {
            inky = new Character(11*30, 14*30, "src/Images/inky.png", 30, 30, inkyBehavior);
            clyde = new Character(11*30, 14*30, "src/Images/clyde.png", 30, 30, clydeBehavior);
            pinky = new Character(11*30, 14*30, "src/Images/pinky.png", 30, 30, pinkyBehavior);
            blinky = new Character(11*30, 14*30, "src/Images/blinky.png", 30, 30, blinkyBehavior);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        render.addEntity(inky);
        render.addEntity(clyde);
        render.addEntity(pinky);
        render.addEntity(blinky);
        render.addEntity(pacman);
        render.addObserver(new PacmanCollisionManager(pacman));

    }

    public GraphicsContext getGraphicsContext() { return canvas.getGraphicsContext2D(); }
}
