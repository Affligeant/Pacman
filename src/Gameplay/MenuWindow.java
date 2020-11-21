package Gameplay;

import Moteur.Render;
import Moteur.Window;
import Moteur.Character;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class MenuWindow extends Window {

    public MenuWindow(double height, double width) {
        super(height, width);
    }

    public void init() {
        Button playButton = new Button("P L A Y");
        playButton.setMinSize(width/2, height/5);
        playButton.setAlignment(Pos.CENTER);
        playButton.setStyle("-fx-border-width: 0px; -fx-background-color: #e77f20;");
        playButton.setFont(new Font("Calibri", width/20));
        borderPane.setCenter(playButton);
        playButton.setOnAction(event -> {
            PacmanMovableBehavior pacmanMovableBehavior = new PacmanMovableBehavior();
            Character pacman;
            try {
                pacman = new Character(50, 50, "./src/Images/cercle_jaune.png", 55, 55, pacmanMovableBehavior);
            } catch (FileNotFoundException e) {
                System.out.println("Fichier non trouvé : ./src/Images/cercle_jaune.png");
                return;
            }
            GameWindow gameWindow = new GameWindow(height, width);
            Render render = new Render(gameWindow.getGraphicsContext(), gameWindow.getScene());
            render.getKeyEventManager().add(pacmanMovableBehavior, Arrays.asList("Z", "S", "D", "Q", "UP", "DOWN", "LEFT", "RIGHT"));
            render.addEntity(pacman);
            render.start();
            gameWindow.show();
            this.hide();
        });
    }
}
