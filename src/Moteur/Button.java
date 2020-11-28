package Moteur;

public class Button extends javafx.scene.control.Button implements WindowElement{

    public Button(String text) {
        super(text);
    }

    public void setSize(double width, double height) {
        setMinSize(width, height);
    }

    public void setColor(String hexaColor) {
        setStyle("-fx-background-color: " + hexaColor + ";");
    }

}
