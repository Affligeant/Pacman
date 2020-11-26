package Gameplay;

import Moteur.*;
import Moteur.Character;

public class FollowFantomBehavior extends FantomMovableBehavior {

    Character pacman;

    FollowFantomBehavior(Graph g, Character pacman) {
        super(10, g);
        this.pacman = pacman;
    }

    @Override
    public void update(Movable entity) {
        if(!started) {
            moveAlongPattern((Character) entity);
        }
        else {
            if(hasReachedNextDestination) {

                Node[] pacmanNode = g.getFramingNodesFromPos(pacman.getX(), pacman.getY());
                double x = ((Character) entity).getX();
                double y = ((Character) entity).getY();

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
            else {
                moveToNextDestination((Character) entity);
                if(((Entity) entity).getX() == nextDestination[0] && ((Entity) entity).getY() == nextDestination[1]) {
                    hasReachedNextDestination = true;
                }
            }
        }
    }
}
