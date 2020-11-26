package Moteur;

public class Image extends javafx.scene.image.Image {

    public Image(String url, double requestedWidth, double requestedHeight) {
        super(url, requestedWidth, requestedHeight, false, false);
    }
}
