package Moteur;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;

public class Area extends BorderPane implements WindowElement {

    public Area(double width, double height) {
        this.setPrefSize(width, height);
    }

    public void setTop(WindowElement we) { super.setTop((Node) we);}

    public void setBottom(WindowElement we) { super.setBottom((Node) we);}

    public void setLeft(WindowElement we) { super.setLeft((Node) we);}

    public void setRight(WindowElement we) { super.setRight((Node) we);}

    public void setCenter(WindowElement we) { super.setCenter((Node) we);}

    public void setColor(String color) { super.setStyle("-fx-background-color: " + color + ";"); }

    public void setMargins(int value) { BorderPane.setMargin(this, new Insets(value)); }
}
