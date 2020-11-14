package Moteur;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuWindow menuWindow = new MenuWindow(800, 800);
        menuWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
