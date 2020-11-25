package Moteur;

import java.util.ArrayList;
import java.util.Collection;

public class Graph {
    ArrayList<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public boolean addNode(Node node) {
        for(Node n : nodes) {
            if(n.x == node.x && n.y == node.y) {
                return false;
            }
        }
        this.nodes.add(node);
        return true;
    }

    public void addArc(Node n1, Node n2) {
        double poids = calcDist(n1, n2);
        nodes.get(nodes.indexOf(n1)).arcs.add(new Arc(n2, poids));
    }

    public void unexplore() {
        for(Node n : nodes) {
            n.explored = false;
            n.previouslyExplored = null;
            n.totalCost = 9999;
        }
    }

    public Node getNodeByPos(double x, double y) {
        for(Node n : nodes) {
            if(n.x == x && n.y == y) {
                return n;
            }
        }

        return null;
    }

    public static double calcDist(Node n1, Node n2) {
        double xDist = (n1.x - n2.x) * (n1.x - n2.x);
        double yDist = (n1.y - n2.y) * (n1.y - n2.y);
        return Math.sqrt(xDist + yDist);
    }

    public ArrayList<Node> getNodes() { return nodes; }

    public boolean hasALinkWith(Node n1, Node n2) {
        for(Arc a : n1.getArcs()) {
            if(a.getN2() == n2) { return true; }
        }

        return false;
    }

    public Node getNextNodeToReach(Node n1, Node n2) {

        if(hasALinkWith(n1, n2)) {
            return n2;
        }

        unexplore();
        ArrayList<Node> nodeCopy = new ArrayList<>(nodes);

        n1.totalCost = 0;
        n1.explored = true;

        while(nodeCopy.size() > 0) {
            Node chosen = leastDistNode(nodeCopy);

            nodeCopy.remove(chosen);

            if(chosen == n2) {
                while(chosen.previouslyExplored.previouslyExplored != null) {
                    chosen = chosen.previouslyExplored;
                }

                return chosen;
            }

            for(Arc a : chosen.getArcs()) {
                if(nodeCopy.contains(a.n2)) {
                    double maxCost = chosen.totalCost + a.poids;
                    if(maxCost < a.n2.totalCost) {
                        a.n2.totalCost = maxCost;
                        a.n2.previouslyExplored = chosen;
                    }
                }
            }
        }

        return null;
    }

    public Node leastDistNode(Collection<Node> nodes) {
        Node min = new Node(0, 0);
        min.totalCost = Integer.MAX_VALUE;
        for(Node n : nodes) {
            if (n.totalCost < min.totalCost) { min = n; }
        }

        return min;
    }

    public Node[] getClosestNodesFromPos(double x, double y) {

        Node res = getNodeByPos(x, y);

        if(res != null) { return new Node[]{res, res}; }

        ArrayList<Node> potentialNodes = new ArrayList<>();

        for(Node n : nodes) {
            if(n.getX() == x || n.getY() == y) {
                potentialNodes.add(n);
            }
        }

        Node first = null;
        Node second = null;

        for(Node n : potentialNodes) {
            for(Node n2 : potentialNodes) {
                if(n != n2) {
                    if(hasALinkWith(n, n2) && n.getX() <= x && n2.getX() >= x && n.getY() <= y && n2.getY() >= y) {
                        first = n;
                        second = n2;
                    }
                }
            }
        }

        return new Node[]{first, second};
    }
}
