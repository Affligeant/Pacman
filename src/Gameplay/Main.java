package Gameplay;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuWindow menuWindow = new MenuWindow(31*30, 28*30);
        menuWindow.show();
    }

    public static void main(String[] args) { launch(args); }
}
