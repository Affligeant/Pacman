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
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) { this.y = y; }
    public Image getSkin() { return skin; }
}
