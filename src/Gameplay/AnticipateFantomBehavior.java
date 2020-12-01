package Gameplay;

import Moteur.*;

public class AnticipateFantomBehavior extends FantomMovableBehavior {

    Pacman pacman;
    double canevasWidth;
    double canevasHeight;

    AnticipateFantomBehavior(Graph g, Pacman pacman, double canevasWidth, double canevasHeight) {
        super(7, g);
        this.pacman = pacman;
        this.canevasWidth = canevasWidth;
        this.canevasHeight = canevasHeight;
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
        Node n2 = null;

        double x = fantome.getX();
        double y = fantome.getY();

        Node fantomNode = g.getNodeByPos(x, y);

        if (fantomNode != null) {
            if (fantomNode == pacmanNode[0]) {
                n2 = pacmanNode[1];
            } else if (fantomNode == pacmanNode[1]) {
                n2 = pacmanNode[0];
            } else {
                double xTest = pacman.getX();
                double yTest = pacman.getY();
                if (pacman.getvY() == 0 && pacman.getvX() == 0) {
                    n2 = g.getClosestNodeFromPos(pacman.getX(), pacman.getY());
                }
                while (n2 == null && xTest >= 0 && yTest >= 0 && xTest <= canevasWidth && yTest <= canevasHeight) {
                    n2 = g.getNodeByPos(xTest, yTest);
                    xTest += pacman.getvX();
                    yTest += pacman.getvY();
                }
                if (n2 == null) {
                    n2 = g.getClosestNodeFromPos(pacman.getX(), pacman.getY());
                }

                n2 = g.getNextNodeToReach(fantomNode, n2);
            }

            nextDestination = new double[]{n2.getX(), n2.getY()};
        } else {
            Node nClose = g.getClosestNodeFromPos(x, y);
            nextDestination = new double[]{nClose.getX(), nClose.getY()};
        }

        hasReachedNextDestination = false;
    }
}
