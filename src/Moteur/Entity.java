package Moteur;

import javafx.scene.image.Image;

public abstract class Entity {
    double x;
    double y;
    double vX;
    double vY;
    Image skin;
    double height;
    double width;

    public Entity(double x, double y, Image skin, double height, double width) {
        this.x = x;
        this.y = y;
        this.skin = skin;
        this.height = height;
        this.width = width;
        this.vX = 0;
        this.vY = 0;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public Image getSkin() { return skin; }
    public void setvX(double vX) { this.vX = vX; }
    public void setvY(double vY) { this.vY = vY; }
    public void setSkin(Image skin) { this.skin = skin; }
    public void setHeigth(double height) { this.height = height; }
    public void setWidth(double width) { this.width = width; }
    public double getvX() { return vX; }
    public double getvY() { return vY; }
    public double getHeight() { return height; }
    public double getWidth() { return width; }
}
