package Moteur;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Entity {
    double x;
    double y;
    double vX;
    double vY;
    Image skin;
    String imagePath;
    double height;
    double width;

    public Entity(double x, double y, String imagePath, double height, double width) throws FileNotFoundException {
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        this.height = height;
        this.width = width;
        this.vX = 0;
        this.vY = 0;
        this.skin = new Image(new FileInputStream(imagePath), width, height, false, false);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public Image getSkin() { return skin; }
    public void setvX(double vX) { this.vX = vX; }
    public void setvY(double vY) { this.vY = vY; }
    public void setPath(String path) throws FileNotFoundException {
        this.imagePath = path;
        this.skin = new Image(new FileInputStream(imagePath), width, height, false, false);
    }
    public void resize(double width, double height) throws FileNotFoundException {
        this.width = width;
        this.height = height;
        this.skin = new Image(new FileInputStream(imagePath), width, height, false, false);
    }
    public double getvX() { return vX; }
    public double getvY() { return vY; }
    public double getHeight() { return height; }
    public double getWidth() { return width; }
}
