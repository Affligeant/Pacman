package Gameplay;

import Moteur.*;

public class FollowFantomBehavior extends FantomMovableBehavior {

    Pacman pacman;

    FollowFantomBehavior(Graph g, Pacman pacman) {
        super(10, g);
        this.pacman = pacman;
    }

    @Override
    void fantomUpdate(Fantome fantome) {
        if(fantome.isEaten()) {
            setBackDestination(g, fantome);
            return;
        }
        else if(fantome.isScared()) {
            setRunawayDestination(pacman, fantome);
            hasReachedNextDestination = false;
            return;
        }

        Node[] pacmanNode = g.getFramingNodesFromPos(pacman.getX(), pacman.getY());
        double x = fantome.getX();
        double y = fantome.getY();

        Node n2;
        Node fantomNode = g.getNodeByPos(x, y);
        if(fantomNode != null) {

            if(fantomNode == pacmanNode[0]) { n2 = pacmanNode[1]; }
            else if(fantomNode == pacmanNode[1]) { n2 = pacmanNode[0]; }
            else { n2 = g.getNextNodeToReach(fantomNode, g.getClosestNodeFromPos(pacman.getX(), pacman.getY())); }

            nextDestination = new double[]{n2.getX(), n2.getY()};
        }
        else {
            Node nClose = g.getClosestNodeFromPos(x, y);
            nextDestination = new double[]{nClose.getX(), nClose.getY()};
        }

        hasReachedNextDestination = false;
    }
}
