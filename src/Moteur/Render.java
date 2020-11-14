package Moteur;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Render extends AnimationTimer {

    PacmanCharacter pacmanCharacter;
    PacmanEventManager pacmanEventManager;
    GraphicsContext gc;

    public Render(PacmanCharacter pacmanCharacter, PacmanEventManager pacmanEventManager, GraphicsContext gc) {
        this.pacmanCharacter = pacmanCharacter;
        this.pacmanEventManager = pacmanEventManager;
        this.gc = gc;
    }

    @Override
    public void handle(long now) {
        pacmanEventManager.handle(pacmanCharacter);
        gc.clearRect(0, 0, 10000, 10000); //À modifier plus tard
        gc.drawImage(pacmanCharacter.skin, pacmanCharacter.getX(), pacmanCharacter.getY());
    }
}
