package Moteur;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Encapsulates the JavaFX Canvas
 */
public class Canevas extends Canvas implements WindowElement{

    public Canevas(double width, double height) {
        super(width, height);
    }

    public GraphicsContext getGraphicContext() { return this.getGraphicsContext2D(); }

    /**
     * Used to change the color used to fill the canevas.
     * Uses {@code javafx.scene.paint.Color} constants.
     * @param c Color desired.
     */
    public void setBackground(Color c) { this.getGraphicsContext2D().setFill(c); }

}
