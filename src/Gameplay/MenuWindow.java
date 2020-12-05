package Gameplay;

import Moteur.*;

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
        playButton.setTextSize((int) width/15);

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
        diffRadGroup.ajout(easy);
        diffRadGroup.ajout(classic);
        diffRadGroup.ajout(hard);

        Area difficultyArea = Area.centerAll(Area.Center.HORIZONTAL, width, height / 4, diffLabel, easy, classic, hard);
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
