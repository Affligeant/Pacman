package Moteur;

import java.io.FileNotFoundException;

public class Entity {
    private double x;
    private double y;
    private Image skin;
    private String imagePath;
    private double height;
    private double width;
    private final boolean physical;

    /**
     * @param x Position {@code x} of the entity.
     * @param y Position {@code y} of the entity.
     * @param imagePath Path of the {@code Image} associated to the entity.
     * @param height Desired {@code height} of the entity. Appearance is resized to this height.
     * @param width Desired {@code width} of the entity. Appearance is resized to this width.
     * @param physical Specifies if the entity must be physically blocking other moving
     *                 entities. Still triggers collision even if {@code false}. If correctly
     *                 filled, can be used with the {@code CollisionManager BasicPhysicHandler}
     *                 from the package to get a default behaviour from the moving entities,
     *                 for example.
     * @throws FileNotFoundException When the image file can't be found.
     */
    public Entity(double x, double y, String imagePath, double height, double width, boolean physical) throws FileNotFoundException {
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        this.height = height;
        this.width = width;
        this.skin = new Image(imagePath, width, height);
        this.physical = physical;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public Image getSkin() { return skin; }

    /**
     * Automatically re-loads the skin of the Entity using the image given as parameter.
     *
     * @param path The image file path.
     * @throws FileNotFoundException If the path isn't valid.
     */
    public void setPath(String path) throws FileNotFoundException {
        this.imagePath = path;
        this.skin = new Image(imagePath, width, height);
    }

    /**
     * @param width Width of the new size.
     * @param height Height of the new size.
     * @throws FileNotFoundException When the original imagePath is no longer valid.
     */
    public void resize(double width, double height) throws FileNotFoundException {
        this.width = width;
        this.height = height;
        this.skin = new Image(imagePath, width, height);
    }
    public double getHeight() { return height; }
    public double getWidth() { return width; }

    /**
     * @return {@code True} if the entity is supposed to physically block other moving entities.
     * Still triggers collisions even if {@code false}.
     */
    public boolean isPhysical() { return physical; }
}
