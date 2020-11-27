package Moteur;

import java.util.ArrayList;

/**
 * Class {@code Node}, used to represent a node in a {@code Graph}.
 * Has a list of {@code Arcs} each leading to other nodes, and a
 * position in a plan.
 */
public class Node {
    double x;
    double y;

    double totalCost;
    Node previouslyExplored;
    ArrayList<Arc> arcs;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
        this.arcs = new ArrayList<>();
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public ArrayList<Arc> getArcs() { return arcs; }
}
