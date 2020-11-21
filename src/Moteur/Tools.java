package Moteur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Tools {

    public ArrayList<Entity> mapFromFile(String path, double tailleCase) throws IOException {

        //Dictionnaire d'images
        HashMap<Integer, String> imagePaths = new HashMap<>();

        List<String> data = Files.readAllLines(Paths.get(path));

        //Initialisation du dictionnaire d'images

        String[] entityImage;
        for(String s : data) {
            entityImage = s.split(":");
            if(entityImage.length == 2) {
                //On a trouvé une association Id:ImagePath, on l'ajoute au dictionnaire
                if(imagePaths.containsKey(Integer.valueOf(entityImage[0]))) {
                    System.out.println("Erreur : Id " + entityImage[0] + " déjà associé à une image.");
                    return null;
                }
                imagePaths.put(Integer.valueOf(entityImage[0]), entityImage[1]);
                data.remove(s);
            }
            else {
                //Si la taille est inférieure à 2, alors on a pas trouvé d'autre association Id:ImagePath.
                if(s.equals("\n")) {
                    data.remove(s);
                }
                break;
            }
        }

        //Ajout des entités

        ArrayList<Entity> entities = new ArrayList<>();

        String[] lineEntities;

        for(int i = 0; i < data.size(); i++) {
            lineEntities = data.get(i).split(" ");

            for(int j = 0; j < lineEntities.length; j++) {
                double x = i*tailleCase;
                double y = j*tailleCase;
                String imagePath = imagePaths.get(Integer.valueOf(lineEntities[j]));
                Entity e = new Entity(x, y, imagePath, tailleCase, tailleCase);
                entities.add(e);
            }
        }
        return entities;
    }

    public Integer[][] mapFromFile(String path) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(path));

        String[] entityImage;
        for(String s : data) {
            entityImage = s.split(":");
            if(entityImage.length == 2) {
                //Association Id:ImagePath
                data.remove(s);
            }
            else {
                //Si la taille est inférieure à 2, alors on a pas trouvé d'autre association Id:ImagePath.
                if(s.equals("\n")) {
                    data.remove(s);
                }
                break;
            }
        }

        //Largeur = taille d'une ligne. Longueur = taille d'une colonne.
        Integer[][] matrice = new Integer[data.get(0).length()][data.size()];

        String[] lineEntities;

        for(int i = 0; i < data.size(); i++) {
            lineEntities = data.get(i).split(" ");
            for(int j = 0; j < lineEntities.length; j++) {
                matrice[i][j] = Integer.valueOf(lineEntities[j]);
            }
        }

        return matrice;
    }
}
