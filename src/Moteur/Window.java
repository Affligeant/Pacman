package Moteur;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class Window extends Stage {

    public final double height;
    public final double width;
    public final BorderPane borderPane;

    public Window(double height, double width) {
        this.height = height;
        this.width = width;
        this.borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, width, height);
        setScene(scene);
        init();
    }

    public void setBackground(Color color) {
        getScene().setFill(color);
    }

    /**
     * Called when constructing the window. Specify here everything that needs to be instantiated
     * within the window.
     */
    public abstract void init();
}
