package Gameplay;

import Moteur.Character;
import Moteur.Entity;
import Moteur.Render;
import Moteur.Tools;
import Moteur.Window;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuWindow extends Window {

    public MenuWindow(double height, double width) { super(height, width); }

    public void init() {

        Button playButton = initButton();
        borderPane.setCenter(playButton);

        playButton.setOnAction(event -> {

            double[] positions = {14*30, 23*30}; //position par défaut de pacman
            PacmanMovableBehavior pacmanMovableBehavior = new PacmanMovableBehavior();
            Character pacman;
            ArrayList<Entity> entityArrayList;

            GameWindow gameWindow = new GameWindow(height, width);
            Render render = new Render(gameWindow.getGraphicsContext(), gameWindow.getScene(), width, height);
            render.getKeyEventManager().add(pacmanMovableBehavior, Arrays.asList("Z", "S", "D", "Q", "UP", "DOWN", "LEFT", "RIGHT"));

            try {
                pacman = new Character(positions[0], positions[1], "./src/Images/cercle_jaune.png", 30, 30, pacmanMovableBehavior);
                entityArrayList = Tools.mapFromFile("./src/Gameplay/niveau1.map", 30, new PacmanEntityFactory());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }

            render.addEntity(pacman);
            render.addObserver(new PacmanCollisionManager(pacman));
            if(entityArrayList != null) { render.addEntity(entityArrayList); }

            try {
                render.addEntity(new Chemin(positions[0], positions[1], 30, 30));
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }

            render.start();
            gameWindow.show();
            this.hide();
        });
    }

    private Button initButton() {
        Button playButton = new Button("P L A Y");
        playButton.setMinSize(width/2, height/5);
        playButton.setAlignment(Pos.CENTER);
        playButton.setStyle("-fx-border-width: 0px; -fx-background-color: #e77f20;");
        playButton.setFont(new Font("Calibri", width/20));

        return playButton;
    }
}
