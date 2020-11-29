package Gameplay;

import Moteur.Button;
import Moteur.Window;
import javafx.scene.text.Font;

public class MenuWindow extends Window {

    public MenuWindow(double height, double width) { super(height, width); }

    public void init() {

        Button playButton = initButton();
        setColor("black");
        setCenter(playButton);

        playButton.setOnAction(event -> {
            GameWindow gameWindow = new GameWindow(height, width);
            gameWindow.show();
            this.hide();
            gameWindow.nextLevel();
        });
    }

    private Button initButton() {
        Button playButton = new Button("P L A Y");
        playButton.setSize(width/2, height/5);
        playButton.setColor("#e77f20");
        playButton.setFont(new Font("Calibri", width/20));

        return playButton;
    }
}
