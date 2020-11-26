package Moteur;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Canevas extends Canvas {

    public GraphicsContext getGraphicContext() { return this.getGraphicsContext2D(); }
    public void setBackground(Color c) { this.getGraphicsContext2D().setFill(c); }

}
