package Gameplay;

import Moteur.*;
import Moteur.Character;

import java.util.ArrayList;
import java.util.Random;

public class FruitMovableBehavior implements MovableBehavior {

    Graph g;
    double[] nextDestination;
    boolean hasReachedNextDestination;

    public FruitMovableBehavior(Graph g) {
        nextDestination = null;
        hasReachedNextDestination = true;
        this.g = g;
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

    @Override
    public void update(Movable entity) {
        if(hasReachedNextDestination) {
            double x = ((Entity) entity).getX();
            double y = ((Entity) entity).getY();

            Node fruitNode = g.getNodeByPos(x, y);
            if(fruitNode != null) {
                ArrayList<Arc> arcs = fruitNode.getArcs();
                Node n2 = arcs.get(new Random().nextInt(arcs.size())).getN2();
                nextDestination = new double[]{n2.getX(), n2.getY()};
            }
            else {
                Node nClose = g.getClosestNodeFromPos(x, y);
                nextDestination = new double[]{nClose.getX(), nClose.getY()};
            }

            hasReachedNextDestination = false;
        }
        else {
            moveToNextDestination((Character) entity);
            if(((Entity) entity).getX() == nextDestination[0] && ((Entity) entity).getY() == nextDestination[1]) {
                hasReachedNextDestination = true;
            }
        }
    }
}
