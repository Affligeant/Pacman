package Gameplay;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuWindow menuWindow = new MenuWindow(800, 800);
        menuWindow.show();
    }

    public static void main(String[] args) { launch(args); }
}
