package Moteur;

import java.util.ArrayList;

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
