package Gameplay;

import Moteur.*;

import java.util.ArrayList;
import java.util.Random;

public class SemirandomFantomMovableBehavior extends FantomMovableBehavior {

    Pacman pacman;

    SemirandomFantomMovableBehavior(Graph g, Pacman pacman) {
        super(4, g);
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

            ArrayList<Node> randomOthers = new ArrayList<>();
            for(Arc a : fantomNode.getArcs()) {
                if(a.getN2() != n2) { randomOthers.add(a.getN2()); }
            }

            if(new Random().nextInt(100) >= 45 && randomOthers.size() > 0) {
                n2 = randomOthers.get(new Random().nextInt(randomOthers.size()));
            }

            nextDestination = new double[]{n2.getX(), n2.getY()};
        }
        else {
            Node nClose = g.getClosestNodeFromPos(x, y);
            nextDestination = new double[]{nClose.getX(), nClose.getY()};
        }

        hasReachedNextDestination = false;
    }
}
