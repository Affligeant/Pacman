package Moteur;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Entity {
    private double x;
    private double y;
    private Image skin;
    private String imagePath;
    private double height;
    private double width;
    private boolean physical;
    private String type;

    public Entity(double x, double y, String imagePath, double height, double width, boolean physical, String type) throws FileNotFoundException {
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        this.height = height;
        this.width = width;
        this.skin = new Image(new FileInputStream(imagePath), width, height, false, false);
        this.physical = physical;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public Image getSkin() { return skin; }
    public void setPath(String path) throws FileNotFoundException {
        this.imagePath = path;
        this.skin = new Image(new FileInputStream(imagePath), width, height, false, false);
    }
    public void resize(double width, double height) throws FileNotFoundException {
        this.width = width;
        this.height = height;
        this.skin = new Image(new FileInputStream(imagePath), width, height, false, false);
    }
    public double getHeight() { return height; }
    public double getWidth() { return width; }
    public boolean isPhysical() { return physical; }
    public String getType() { return type; }
}
