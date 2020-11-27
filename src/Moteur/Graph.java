package Moteur;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Graph represented by {@code Nodes} fixed in a plan.
 * Meant to be visual more than abstract since it
 * stores real positions of {@code Nodes}.
 */
public class Graph {
    ArrayList<Node> nodes;

    public Graph() { this.nodes = new ArrayList<>(); }

    /**
     * @param node The node to add
     * @return false if the position given is already taken by another node.
     */
    public boolean addNode(Node node) {
        if(getNodeByPos(node.x, node.y) != null) { return false; }
        this.nodes.add(node);
        return true;
    }

    /**
     * Adds an arc from the node n1 to the node n2.
     * Calculates the weight automatically from the
     * nodes position.
     *
     * @param n1 First node
     * @param n2 Second node
     */
    public void addArc(Node n1, Node n2) {
        double poids = calcDist(n1, n2);
        nodes.get(nodes.indexOf(n1)).arcs.add(new Arc(n2, poids));
    }

    /**
     * Prepares nodes for a shortest path algorithm.
     * Sets their previously best explored node to {@code null}
     * and their total cost to {@code Integer.MAX_VALUE}.
     */
    private void unexplore() {
        for(Node n : nodes) {
            n.previouslyExplored = null;
            n.totalCost = Integer.MAX_VALUE;
        }
    }

    /**
     * Returns the node at the position (x,y) if it exists in this Graph.
     *
     * @param x Position x
     * @param y Position y
     * @return Node at position (x,y) if there is one.
     */
    public Node getNodeByPos(double x, double y) {
        for(Node n : nodes) {
            if(n.x == x && n.y == y) {
                return n;
            }
        }

        return null;
    }

    /**
     * Calculates the absolute distance between 2 nodes
     * based upon their position
     *
     * @param n1 First node
     * @param n2 Second node
     * @return Absolute distance between the 2 nodes
     */
    public static double calcDist(Node n1, Node n2) {
        double xDist = (n1.x - n2.x) * (n1.x - n2.x);
        double yDist = (n1.y - n2.y) * (n1.y - n2.y);
        return Math.sqrt(xDist + yDist);
    }

    public ArrayList<Node> getNodes() { return nodes; }

    /**
     * Used to check if the specified node n1 has a link
     * to the node n2 in this Graph.
     *
     * @param n1 First node
     * @param n2 Second node
     * @return True if n1 has an arc towards n2
     */
    public boolean hasALinkWith(Node n1, Node n2) {
        for(Arc a : n1.getArcs()) {
            if(a.getN2() == n2) { return true; }
        }

        return false;
    }

    /**
     * This methode returns the next node from n1 in a shortest path from n1 to n2.
     *
     * @param n1 The starting node
     * @param n2 The ending node
     * @return The next node to reach in order to go from n1 to n2 with the lowest cost.
     */
    public Node getNextNodeToReach(Node n1, Node n2) {

        if(hasALinkWith(n1, n2)) {
            return n2;
        }

        unexplore();
        ArrayList<Node> nodeCopy = new ArrayList<>(nodes);

        n1.totalCost = 0;

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

    /**
     * Used in graphs search algorithms.
     *
     * @param nodes A collection of nodes
     * @return The node with the lowest totalCost value.
     */
    private static Node leastDistNode(Collection<Node> nodes) {
        Node min = new Node(0, 0);
        min.totalCost = Integer.MAX_VALUE;
        for(Node n : nodes) {
            if (n.totalCost < min.totalCost) { min = n; }
        }

        return min;
    }

    /**
     * Used to get the closest node which has an arc passing by the position given.
     *
     * @param x The x position of the point
     * @param y The y position of the point
     * @return Closest node.
     */
    public Node getClosestNodeFromPos(double x, double y) {

        Node[] framing = getFramingNodesFromPos(x, y);

        double distF = Graph.calcDist(framing[0], new Node(x, y));
        double distS = Graph.calcDist(framing[1], new Node(x, y));

        if(distF < distS) { return framing[0]; }
        else { return framing[1]; }
    }

    /**
     * Used to get the 2 closest nodes that have an arc passing by the position given.
     * If there is none, returns the closest node.
     *
     * @param x The x position of the point
     * @param y The y position of the point
     * @return An array of 2 nodes filled with the nodes framing the point given.
     * If the given point is a node itself or if we're past the last node at the edge,
     * the 2 nodes are the same.
     */
    public Node[] getFramingNodesFromPos(double x, double y) {

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


        if(first == null) {
            Node a = new Node(x, y);
            double distanceMin = Integer.MAX_VALUE;
            //la position se trouve entre un bord de l'écran et le node de bord d'écran
            //On récupère donc le node le plus proche !
            for(Node n : nodes) {
                double distNodes = calcDist(a, n);
                if(distNodes < distanceMin) {
                    distanceMin = distNodes;
                    first = n;
                    second = n;
                }
            }
        }

        return new Node[]{first, second};
    }
}
