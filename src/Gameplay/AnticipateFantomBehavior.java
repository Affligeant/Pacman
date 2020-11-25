package Gameplay;

import Moteur.*;
import Moteur.Character;

public class AnticipateFantomBehavior extends FantomMovableBehavior {

    Character pacman;

    AnticipateFantomBehavior(Graph g, Character pacman) {
        super(7, g);
        this.pacman = pacman;
    }

    @Override
    public void update(Movable entity) {
        if(!started) {
            moveAlongPattern((Character) entity);
        }
        else {
            if(hasReachedNextDestination) {

                Node[] pacmanNode = g.getClosestNodesFromPos(pacman.getX(), pacman.getY());
                Node n2 = null;
                Node fantomNode = g.getNodeByPos(((Character) entity).getX(), ((Character) entity).getY());
                if(fantomNode == pacmanNode[0]) { n2 = pacmanNode[1]; }
                else if(fantomNode == pacmanNode[1]) { n2 = pacmanNode[0]; }
                else {
                    double xTest = pacman.getX();
                    double yTest = pacman.getY();
                    if(pacman.getvY() == 0 && pacman.getvX() == 0) {
                        double distF = Graph.calcDist(pacmanNode[0], new Node(pacman.getX(), pacman.getY()));
                        double distS = Graph.calcDist(pacmanNode[1], new Node(pacman.getX(), pacman.getY()));
                        if(distF < distS) { n2 = pacmanNode[0]; }
                        else { n2 = pacmanNode[1]; }
                    }
                    while(n2 == null) {
                        n2 = g.getNodeByPos(xTest, yTest);
                        xTest += pacman.getvX();
                        yTest += pacman.getvY();
                    }

                    n2 = g.getNextNodeToReach(fantomNode, n2);
                }

                nextDestination = new double[]{n2.getX(), n2.getY()};
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
