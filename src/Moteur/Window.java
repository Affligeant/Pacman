package Moteur;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

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

    public void setTop(WindowElement we) {
        borderPane.setTop((Node) we);
    }

    public void setBottom(WindowElement we) {
        borderPane.setBottom((Node) we);
    }

    public void setLeft(WindowElement we) {
        borderPane.setLeft((Node) we);
    }

    public void setCenter(WindowElement we) {
        borderPane.setCenter((Node) we);
    }

    public void setRight(WindowElement we) {
        borderPane.setRight((Node) we);
    }

    public void setColor(String color) { borderPane.setStyle("-fx-background-color: " + color + ";"); }

    /**
     * Called when constructing the window. Specify here everything that needs to be instantiated
     * within the window.
     */
    public abstract void init();
}
