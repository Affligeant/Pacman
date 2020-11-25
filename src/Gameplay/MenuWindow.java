package Gameplay;

import Moteur.Window;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MenuWindow extends Window {

    public MenuWindow(double height, double width) { super(height, width); }

    public void init() {

        Button playButton = initButton();
        borderPane.setCenter(playButton);

        playButton.setOnAction(event -> {
            GameWindow gameWindow = new GameWindow(height, width);
            gameWindow.show();
            this.hide();
        });
    }

    private Button initButton() {
        Button playButton = new Button("P L A Y");
        playButton.setMinSize(width/2, height/5);
        playButton.setAlignment(Pos.CENTER);
        playButton.setStyle("-fx-border-width: 0px; -fx-background-color: #e77f20;");
        playButton.setFont(new Font("Calibri", width/20));

        return playButton;
    }
}
