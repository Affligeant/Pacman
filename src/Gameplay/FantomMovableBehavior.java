package Gameplay;

import Moteur.*;
import Moteur.Character;

import java.util.ArrayList;

public abstract class FantomMovableBehavior implements MovableBehavior {

    ArrayList<int[]> startingPattern;
    boolean started;
    double[] nextDestination;
    boolean hasReachedNextDestination;
    Graph g;

    FantomMovableBehavior(int runsToWait, Graph g) {
        startingPattern = new ArrayList<>();
        nextDestination = null;
        hasReachedNextDestination = false;
        this.g = g;
        init(runsToWait);
    }

    abstract void fantomUpdate(Fantome fantome);

    @Override
    public void update(Movable entity) {

        Fantome f = (Fantome) entity;

        if(!started) {
            moveAlongPattern(f);
        }
        else {
            if(f.isScared() && f.scareEnd < System.currentTimeMillis()) {
                f.stopScare();
            }
            if(hasReachedNextDestination) {
                fantomUpdate(f);
            }
            else {
                moveToNextDestination((Character) entity);
                if(f.getX() == nextDestination[0] && f.getY() == nextDestination[1]) {
                    hasReachedNextDestination = true;
                }
            }
        }
    }

    private void init(int runsToWait) {
        started = false;

        for(int i = 0; i < runsToWait * 20; i++) {
            startingPattern.add(new int[]{0, 0});
        }
        for(int j = 0; j < runsToWait; j++) {
            for (int i = 0; i < 150; i++) {
                startingPattern.add(new int[]{1, 0});
            }
            for (int i = 0; i < 150; i++) {
                startingPattern.add(new int[]{-1, 0});
            }
        }

        for(int i = 0; i < 90; i++) {
            startingPattern.add(new int[]{1, 0});
        }

        for(int i = 0; i < 90; i++) {
            startingPattern.add(new int[]{0, -1});
        }

        for(int i = 0; i < 30; i++) {
            startingPattern.add(new int[]{1, 0});
        }
        startingPattern.add(new int[]{0, 0});
    }

    public void moveAlongPattern(Fantome fantom) {
        fantom.setvX(startingPattern.get(0)[0]);
        fantom.setvY(startingPattern.get(0)[1]);
        startingPattern.remove(0);
        if(startingPattern.size() == 0) { started = true; hasReachedNextDestination = true; }
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

    public void setRunawayDestination(Pacman pacman, Fantome fantome) {
        ArrayList<Node> nodesConsidered = new ArrayList<>();
        Node fantomNode = g.getNodeByPos(fantome.getX(), fantome.getY());
        Node aim;

        if(fantomNode != null) {
            if(fantome.getX() - pacman.getX() <= 0 && pacman.getY() >= fantome.getY()) {
                //Pacman se trouve en bas / à droite du fantôme
                for(Node n : g.getNodes()) {
                    if(fantome.getX() - n.getX() >= 0 && n.getY() <= fantome.getY()) {
                        nodesConsidered.add(n);
                    }
                }
            }
            else if(pacman.getX() < fantome.getX() && pacman.getY() > fantome.getY()) {
                //Pacman se trouve en bas / à gauche du fantôme
                for(Node n : g.getNodes()) {
                    if(n.getX() > fantome.getX() && n.getY() < fantome.getY()) {
                        nodesConsidered.add(n);
                    }
                }
            }
            else if(pacman.getX() > fantome.getX() && pacman.getY() < fantome.getY()) {
                //Pacman se trouve en haut / à droite du fantôme
                for(Node n : g.getNodes()) {
                    if(n.getX() < fantome.getX() && n.getY() > fantome.getY()) {
                        nodesConsidered.add(n);
                    }
                }
            }
            else {
                //Pacman se trouve en haut / à gauche du fantôme
                for(Node n : g.getNodes()) {
                    if(n.getX() >= fantome.getX() && n.getY() >= fantome.getY()) {
                        nodesConsidered.add(n);
                    }
                }
            }

            if(nodesConsidered.size() == 0) {
                hasReachedNextDestination = true;
                return;
            }
            Node mostForeign = nodesConsidered.get(0);
            double maxDistance = 0;
            Node pos = new Node(fantome.getX(), fantome.getY());

            for(Node n : nodesConsidered) {
                double dist = Graph.calcDist(n, pos);
                if(dist > maxDistance) {
                    maxDistance = dist;
                    mostForeign = n;
                }
            }
            if(fantomNode != mostForeign) {
                aim = g.getNextNodeToReach(fantomNode, mostForeign);
            }
            else {
                aim = fantomNode;
            }
        }
        else {
            aim = g.getClosestNodeFromPos(fantome.getX(), fantome.getY());
        }

        nextDestination = new double[]{aim.getX(), aim.getY()};
        hasReachedNextDestination = false;
    }

    public boolean hasStarted() { return started; }

    public void setBackDestination(Graph g, Fantome f) {

        Node fantomNode = g.getNodeByPos(f.getX(), f.getY());
        Node aim = g.getClosestNodeFromPos(15*30,11*30);
        if(fantomNode != null) {
            if(fantomNode == aim) {
                nextDestination = new double[]{aim.getX() - 60, aim.getY()};
                hasReachedNextDestination = false;
            }
            else if(fantomNode.getX() == aim.getX() - 60 && fantomNode.getY() == aim.getY()) {
                f.setX(Fantome.X);
                f.setY(Fantome.Y);
                init(1);
                f.notEaten();
                f.stopScare();
            }
            else {
                aim = g.getNextNodeToReach(fantomNode, aim);
                nextDestination = new double[]{aim.getX(), aim.getY()};
                hasReachedNextDestination = false;
            }
        }
        else if(f.getX() == aim.getX() - 60 && f.getY() == aim.getY()) {
            f.setX(Fantome.X);
            f.setY(Fantome.Y);
            init(1);
            f.notEaten();
            f.stopScare();
        }
        else {
            aim = g.getClosestNodeFromPos(f.getX(), f.getY());
            nextDestination = new double[]{aim.getX(), aim.getY()};
            hasReachedNextDestination = false;
        }
    }
}
