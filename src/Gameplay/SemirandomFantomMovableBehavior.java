package Gameplay;

import Moteur.*;
import Moteur.Character;

import java.util.ArrayList;
import java.util.Random;

public class SemirandomFantomMovableBehavior extends FantomMovableBehavior {

    Character pacman;

    SemirandomFantomMovableBehavior(Graph g, Character pacman) {
        super(4, g);
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

                double distF = Graph.calcDist(pacmanNode[0], new Node(pacman.getX(), pacman.getY()));
                double distS = Graph.calcDist(pacmanNode[1], new Node(pacman.getX(), pacman.getY()));
                Node n2;
                Node fantomNode = g.getNodeByPos(((Character) entity).getX(), ((Character) entity).getY());
                if(fantomNode == pacmanNode[0]) { n2 = pacmanNode[1]; }
                else if(fantomNode == pacmanNode[1]) { n2 = pacmanNode[0]; }
                else if(distF < distS) { n2 = g.getNextNodeToReach(fantomNode, pacmanNode[0]); }
                else { n2 = g.getNextNodeToReach(fantomNode, pacmanNode[1]); }

                ArrayList<Node> randomOthers = new ArrayList<>();
                for(Arc a : fantomNode.getArcs()) {
                    if(a.getN2() != n2) { randomOthers.add(a.getN2()); }
                }

                if(new Random().nextInt(100) >= 45 && randomOthers.size() > 0) {
                    n2 = randomOthers.get(new Random().nextInt(randomOthers.size()));
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
