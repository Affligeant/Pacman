package Moteur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Encapsulates the Javafx image. Sets all ratio/smooth at false
 * and resizes it at the requested values.
 */
public class Image extends javafx.scene.image.Image implements WindowElement {

    public Image(String url, double requestedWidth, double requestedHeight) throws FileNotFoundException {
        super(new FileInputStream(url), requestedWidth, requestedHeight, false, false);
    }
}
