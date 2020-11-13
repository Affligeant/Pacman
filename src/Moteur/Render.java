package Moteur;

import javafx.animation.AnimationTimer;

public class Render extends AnimationTimer {

    PacmanCharacter pacmanCharacter;
    PacmanEventManager pacmanEventManager;

    public Render(PacmanCharacter pacmanCharacter, PacmanEventManager pacmanEventManager) {
        this.pacmanCharacter = pacmanCharacter;
        this.pacmanEventManager = pacmanEventManager;
    }

    @Override
    public void handle(long now) {

    }
}
