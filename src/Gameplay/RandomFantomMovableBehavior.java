package Gameplay;

import Moteur.*;
import Moteur.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomFantomMovableBehavior extends FantomMovableBehavior {

    RandomFantomMovableBehavior(Graph g) {
        super(2, g);
    }

    @Override
    public void update(Movable entity) {
        if(!started) {
            moveAlongPattern((Character) entity);
        }
        else {
            if(hasReachedNextDestination) {
                double x = ((Entity) entity).getX();
                double y = ((Entity) entity).getY();

                Node fantomNode = g.getNodeByPos(x, y);
                if(fantomNode != null) {
                    ArrayList<Arc> arcs = fantomNode.getArcs();
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
}
