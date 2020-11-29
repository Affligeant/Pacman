package Moteur;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RadioButton extends javafx.scene.control.RadioButton implements WindowElement{

    public RadioButton(String label) { super(label); }

    public void setTextColor(String color) { super.setTextFill(Color.valueOf(color)); }

    public void setTextSize(int size) { super.setFont(new Font(size));}

    public String getLabel() { return super.getText(); }
}
