package Moteur;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuWindow extends Stage {

    private final double width;
    private final double height;
    private final BorderPane borderPane;

    public MenuWindow(double width, double height) {
        this.width = width;
        this.height = height;
        this.borderPane = new BorderPane();
        init();
    }

    private void init() {
        setScene(new Scene(borderPane, width, height));
        Button playButton = new Button("P L A Y");
        playButton.setMinSize(width/2, height/5);
        playButton.setAlignment(Pos.CENTER);
        playButton.setStyle("-fx-border-width: 0px; -fx-background-color: #ff8c00;");
        playButton.setFont(new Font("Calibri", width/20));
        borderPane.setCenter(playButton);
        playButton.setOnAction(event -> {
            Image imageCharacter;
            try {
                imageCharacter = new Image(new FileInputStream("./src/Images/cercle_jaune.png"));
            } catch (FileNotFoundException e) {
                System.out.println("Fichier non trouvé : src/Images/cercle_jaune.png");
                return;
            }
            PacmanCharacter pacman = new PacmanCharacter(50, 50, Movable.Direction.HAUT, imageCharacter);
            GameWindow gameWindow = new GameWindow(width, height);
            Render render = new Render(pacman, new PacmanEventManager(gameWindow.getScene()), gameWindow.getGraphicsContext());
            render.start();
            gameWindow.show();
            this.hide();
        });
    }
}
