package Gameplay;

import Moteur.Graph;
import Moteur.MovableBehavior;
import Moteur.Character;

import java.util.ArrayList;

public abstract class FantomMovableBehavior implements MovableBehavior {

    ArrayList<int[]> startingPattern;
    boolean started;
    double[] nextDestination;
    boolean hasReachedNextDestination;
    Graph g;

    FantomMovableBehavior(int runsToWait, Graph g) {
        startingPattern = new ArrayList<>();
        started = false;
        nextDestination = null;
        hasReachedNextDestination = false;
        this.g = g;
        init(runsToWait);
    }

    private void init(int runsToWait) {
        for(int i = 0; i < runsToWait * 20; i++) {
            startingPattern.add(new int[]{0, 0});
        }
        for(int j = 0; j < runsToWait; j++) {
            for (int i = 0; i < 150; i++) {
                startingPattern.add(new int[]{1, 0});
            }
            for (int i = 0; i < 150; i++) {
                startingPattern.add(new int[]{-1, 0});
            }
        }

        for(int i = 0; i < 90; i++) {
            startingPattern.add(new int[]{1, 0});
        }

        for(int i = 0; i < 90; i++) {
            startingPattern.add(new int[]{0, -1});
        }

        for(int i = 0; i < 30; i++) {
            startingPattern.add(new int[]{1, 0});
        }
        startingPattern.add(new int[]{0, 0});
    }

    public void moveAlongPattern(Character fantom) {
        fantom.setvX(startingPattern.get(0)[0]);
        fantom.setvY(startingPattern.get(0)[1]);
        startingPattern.remove(0);
        if(startingPattern.size() == 0) { started = true; hasReachedNextDestination = true; }
    }

    public void moveToNextDestination(Character character) {
        if(character.getX() < nextDestination[0]) {
            character.setvX(1);
            character.setvY(0);
        }
        else if(character.getX() > nextDestination[0]) {
            character.setvX(-1);
            character.setvY(0);
        }
        else if(character.getY() < nextDestination[1]) {
            character.setvX(0);
            character.setvY(1);
        }
        else if(character.getY() > nextDestination[1]){
            character.setvX(0);
            character.setvY(-1);
        }
        else {
            character.setvX(0);
            character.setvY(0);
        }
    }
}
