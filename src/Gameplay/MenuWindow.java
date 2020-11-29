package Gameplay;

import Moteur.*;
import javafx.scene.text.Font;

public class MenuWindow extends Window {

    enum Difficulty {
        EASY, CLASSIC, HARD
    }

    Difficulty difficulty;

    public MenuWindow(double height, double width) { super(height, width); }

    public void init() {

        Button playButton = initButton();
        setColor("black");
        setCenter(playButton);
        setBottom(initDifficulty());

        playButton.setOnAction(event -> {
            GameWindow gameWindow = new GameWindow(height, width, difficulty);
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

    private Area initDifficulty() {

        this.difficulty = Difficulty.CLASSIC;

        Text diffLabel = new Text("Difficulté :");
        diffLabel.setSize(24);
        diffLabel.setColor("Coral");

        RadioButton easy = new RadioButton("Facile");
        easy.setTextSize(18);
        easy.setTextColor("Coral");

        RadioButton classic = new RadioButton("Classique");
        classic.setTextSize(18);
        classic.setTextColor("Coral");
        classic.setSelected(true);

        RadioButton hard = new RadioButton("Difficile");
        hard.setTextSize(18);
        hard.setTextColor("Coral");

        RadioButtonGroup diffRadGroup = new RadioButtonGroup();
        easy.setToggleGroup(diffRadGroup);
        classic.setToggleGroup(diffRadGroup);
        hard.setToggleGroup(diffRadGroup);

        Area difficultyArea = new Area(width, height/4);

        Area centerLeftLeftPart = new Area(width/4, height/4);
        centerLeftLeftPart.setCenter((WindowElement) diffLabel);
        Area centerLeftRightPart = new Area(width/4, height/4);
        centerLeftRightPart.setCenter((WindowElement) easy);
        Area centerLeftPart = new Area(width/2, height/4);
        centerLeftPart.setLeft((WindowElement) centerLeftLeftPart);
        centerLeftPart.setRight((WindowElement) centerLeftRightPart);
        difficultyArea.setLeft((WindowElement) centerLeftPart);

        Area centerRightLeftPart = new Area(width/4, height/4);
        centerRightLeftPart.setCenter((WindowElement) classic);
        Area centerRightRightPart = new Area(width/4, height/4);
        centerRightRightPart.setCenter((WindowElement) hard);
        Area centerRightPart = new Area(width/2, height/4);
        centerRightPart.setLeft((WindowElement) centerRightLeftPart);
        centerRightPart.setRight((WindowElement) centerRightRightPart);
        difficultyArea.setRight((WindowElement) centerRightPart);

        difficultyArea.setBottom((WindowElement) new Area(width, height/6));

        diffRadGroup.selectedToggleProperty().addListener((ob, ov, nv) -> {
            if(diffRadGroup.getSelectedToggle() != null) {
                String selectedButton = ((RadioButton) diffRadGroup.getSelectedToggle()).getLabel();
                if(selectedButton.equals(easy.getLabel())) {
                    difficulty = Difficulty.EASY;
                }
                else if(selectedButton.equals(classic.getLabel())) {
                    difficulty = Difficulty.CLASSIC;
                }
                else {
                    difficulty = Difficulty.HARD;
                }
            }
        });

        return difficultyArea;

    }
}
