package Gameplay;

import Moteur.Character;
import Moteur.Entity;
import Moteur.Render;
import Moteur.Tools;
import Moteur.Window;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuWindow extends Window {

    public MenuWindow(double height, double width) { super(height, width); }

    public void init() {
        this.setBackground(Color.BLACK);
        Button playButton = new Button("P L A Y");
        playButton.setMinSize(width/2, height/5);
        playButton.setAlignment(Pos.CENTER);
        playButton.setStyle("-fx-border-width: 0px; -fx-background-color: #e77f20;");
        playButton.setFont(new Font("Calibri", width/20));
        borderPane.setCenter(playButton);
        playButton.setOnAction(event -> {
            ArrayList<Entity> entityArrayList;
            try {
                entityArrayList = Tools.mapFromFile("./src/Gameplay/niveau1.map", 30);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
            double[] positions; //position par défaut
            positions = Tools.getSpecial_areas().get("spawn");
            if (positions == null) {
                System.out.println("Impossible de trouver le point de spawn dans le fichier");
                return;
            }
            PacmanMovableBehavior pacmanMovableBehavior = new PacmanMovableBehavior();
            Character pacman;
            try {
                pacman = new Character(positions[0], positions[1], "./src/Images/cercle_jaune.png", 30, 30, pacmanMovableBehavior, "Pacman");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }
            GameWindow gameWindow = new GameWindow(height, width);
            gameWindow.setBackground(Color.BLACK);
            Render render = new Render(gameWindow.getGraphicsContext(), gameWindow.getScene());
            render.getKeyEventManager().add(pacmanMovableBehavior, Arrays.asList("Z", "S", "D", "Q", "UP", "DOWN", "LEFT", "RIGHT"));
            render.addEntity(pacman);
            if(entityArrayList != null) { render.addEntity(entityArrayList); }
            try {
                render.addEntity(new Entity(positions[0], positions[1], "./src/Images/chemin.png", 30, 30, false,"Chemin"));
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }
            render.addObserver(new PacmanCollisionManager());
            render.start();
            gameWindow.show();
            this.hide();
        });
    }
}
