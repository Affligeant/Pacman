package Gameplay;

import Moteur.*;

import java.util.ArrayList;
import java.util.Random;

public class RandomFantomMovableBehavior extends FantomMovableBehavior {

    Pacman pacman;

    RandomFantomMovableBehavior(Graph g, Pacman pacman) {
        super(2, g);
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

        double x = fantome.getX();
        double y = fantome.getY();

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
}
