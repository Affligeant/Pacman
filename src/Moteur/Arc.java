package Moteur;

public class Arc {

    Node n2;

    double poids;

    public Arc(Node n2, double poids) {
        this.n2 = n2;
        this.poids = poids;
    }

    public Node getN2() {
        return n2;
    }
}
