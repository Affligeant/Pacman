package Gameplay;

import Moteur.*;
import Moteur.Character;

public class AnticipateFantomBehavior extends FantomMovableBehavior {

    Character pacman;
    double canevasWidth;
    double canevasHeight;

    AnticipateFantomBehavior(Graph g, Character pacman, double canevasWidth, double canevasHeight) {
        super(7, g);
        this.pacman = pacman;
        this.canevasWidth = canevasWidth;
        this.canevasHeight = canevasHeight;
    }

    @Override
    public void update(Movable entity) {
        if(!started) {
            moveAlongPattern((Character) entity);
        }
        else {
            if(hasReachedNextDestination) {

                Node[] pacmanNode = g.getFramingNodesFromPos(pacman.getX(), pacman.getY());
                Node n2 = null;

                double x = ((Character) entity).getX();
                double y = ((Character) entity).getY();

                Node fantomNode = g.getNodeByPos(x, y);

                if(fantomNode != null) {
                    if(fantomNode == pacmanNode[0]) { n2 = pacmanNode[1]; }
                    else if(fantomNode == pacmanNode[1]) { n2 = pacmanNode[0]; }
                    else {
                        double xTest = pacman.getX();
                        double yTest = pacman.getY();
                        if(pacman.getvY() == 0 && pacman.getvX() == 0) {
                            n2 = g.getClosestNodeFromPos(pacman.getX(), pacman.getY());
                        }
                        while(n2 == null && xTest >= 0 && yTest >= 0 && xTest <= canevasWidth && yTest <= canevasHeight) {
                            n2 = g.getNodeByPos(xTest, yTest);
                            xTest += pacman.getvX();
                            yTest += pacman.getvY();
                        }
                        if(n2 == null) { n2 = g.getClosestNodeFromPos(pacman.getX(), pacman.getY()); }

                        n2 = g.getNextNodeToReach(fantomNode, n2);
                    }

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
