package Moteur;

import javafx.scene.image.Image;

public abstract class Entity {
    double x;
    double y;
    Image skin;

    public Entity(double x, double y, Image skin) {
        this.x = x;
        this.y = y;
        this.skin = skin;
    }

    public double getX() {
        return x;
    }
    public double getY() { return y; }
}
