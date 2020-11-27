package Moteur;

public class Arc {

    Node n2;
    double poids;

    /**
     * @param n2 The node this arc leads to
     * @param poids The weight of this arc (mostly it's length)
     */
    public Arc(Node n2, double poids) {
        this.n2 = n2;
        this.poids = poids;
    }

    public Node getN2() { return n2; }
}
