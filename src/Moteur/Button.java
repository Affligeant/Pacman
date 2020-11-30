package Moteur;

import javafx.scene.text.Font;

public class Button extends javafx.scene.control.Button implements WindowElement{

    public Button(String text) { super(text); }

    public void setSize(double width, double height) { setMinSize(width, height); }

    public void setColor(String hexaColor) { setStyle("-fx-background-color: " + hexaColor + ";"); }

    public void setTextSize(int size) { super.setFont(new Font(size));}

}
