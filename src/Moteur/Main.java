package Moteur;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Image imageCharacter = new Image(new FileInputStream("./src/Images/cercle_jaune.png"));
        PacmanCharacter pacman = new PacmanCharacter(50, 50, Movable.Direction.HAUT, imageCharacter);
        Render render = new Render(pacman, new PacmanEventManager());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
