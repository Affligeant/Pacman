package Moteur;

import javafx.scene.control.ToggleGroup;

public class RadioButtonGroup extends ToggleGroup implements WindowElement{

    public void ajout(RadioButton radioButton) {
        radioButton.setToggleGroup(this);
    }
}
