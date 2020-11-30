package Moteur;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;

public class Area extends BorderPane implements WindowElement {

    public enum Center {
        HORIZONTAL, VERTICAL
    }

    public Area(double width, double height) { this.setPrefSize(width, height); }

    public void setTop(WindowElement we) { super.setTop((Node) we);}

    public void setBottom(WindowElement we) { super.setBottom((Node) we);}

    public void setLeft(WindowElement we) { super.setLeft((Node) we);}

    public void setRight(WindowElement we) { super.setRight((Node) we);}

    public void setCenter(WindowElement we) { super.setCenter((Node) we);}

    public void setColor(String color) { super.setStyle("-fx-background-color: " + color + ";"); }

    public void setMargins(int value) { BorderPane.setMargin(this, new Insets(value)); }

    public static Area centerAll(Center centerType, double width, double height, WindowElement ... elements) {
        Area centered = new Area(width, height);

        Area left = new Area(width/2, height);
        Area right = new Area(width/2, height);

        if(elements.length == 1) {
            centered.setCenter(elements[0]);
            return centered;
        }

        else if(elements.length % 2 == 0) {
            WindowElement[] half = new WindowElement[elements.length/2];
            WindowElement[] half2 = new WindowElement[elements.length/2];
            for(int i = 0; i < elements.length/2; i++) {
                half[i] = elements[i];
                left = centerAll(centerType,width/2, height, half);
            }
            for(int i = elements.length/2; i < elements.length; i++) {
                half2[i-elements.length/2] = elements[i];
                right = centerAll(centerType, width/2, height, half2);
            }
        }
        else {
            //Nombre impair
            WindowElement[] leftHalf = new WindowElement[(elements.length + 1) / 2];
            WindowElement[] rightHalf = new WindowElement[(elements.length - 1) / 2];

            for(int i = 0; i < leftHalf.length; i++) {
                leftHalf[i] = elements[i];
                left = centerAll(centerType, width/2, height, leftHalf);
            }
            for(int i = leftHalf.length; i < elements.length; i++) {
                rightHalf[i- leftHalf.length] = elements[i];
                right = centerAll(centerType, width/2, height, rightHalf);
            }
        }

        switch (centerType) {
            case VERTICAL:
                centered.setTop((WindowElement) left);
                centered.setBottom((WindowElement) right);
                break;
            case HORIZONTAL:
                centered.setLeft((WindowElement) left);
                centered.setRight((WindowElement) right);
                break;
        }

        return centered;
    }
}
