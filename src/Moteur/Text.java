package Moteur;

import javafx.scene.text.Font;

public class Text extends javafx.scene.text.Text implements WindowElement{
    public Text(String texte) { super(texte); }

    public void setSize(int size) { super.setFont(new Font(size));}
}
