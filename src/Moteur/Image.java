package Moteur;

/**
 * Encapsulates the Javafx image. Sets all ratio/smooth at false
 * and resizes it at the requested values.
 */
public class Image extends javafx.scene.image.Image {

    public Image(String url, double requestedWidth, double requestedHeight) {
        super(url, requestedWidth, requestedHeight, false, false);
    }
}
