package Gameplay;

import Moteur.Window;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameWindow extends Window {

    private Canvas canvas;

    public GameWindow(double height, double width) { super(height, width); }

    public void init() {
        this.canvas = new Canvas(width, height);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        borderPane.setCenter(canvas);
    }

    public GraphicsContext getGraphicsContext() { return canvas.getGraphicsContext2D(); }
}
