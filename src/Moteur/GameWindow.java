package Moteur;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameWindow extends Stage {

    private final double width;
    private final double height;
    private final BorderPane borderPane;
    private final Canvas canvas;
    private final Scene scene;

    public GameWindow(double width, double height) {
        this.width = width;
        this.height = height;
        this.borderPane = new BorderPane();
        this.canvas = new Canvas(width, height);
        this.scene = new Scene(borderPane, width, height);
        init();
    }

    private void init() {
        borderPane.setCenter(canvas);
        setScene(scene);
    }

    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }
}
