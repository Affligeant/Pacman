package Moteur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Tools {

    public static ArrayList<Entity> mapFromFile(String path, double tailleCase) throws IOException {

        //Dictionnaire d'images
        HashMap<Integer, String> imagePaths = new HashMap<>();

        List<String> data = Files.readAllLines(Paths.get(path));

        //Initialisation du dictionnaire d'images

        int nbLignes = 0;

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
                nbLignes++;
            }
            else {
                //Si la taille est inférieure à 2, alors on a pas trouvé d'autre association Id:ImagePath.
                if(s.equals("\n") || s.equals("")) {
                    nbLignes++;
                }
                break;
            }
        }

        if (nbLignes > 0) {
            data.subList(0, nbLignes).clear();
        }

        //Ajout des entités

        ArrayList<Entity> entities = new ArrayList<>();

        String[] lineEntities;

        for(int i = 0; i < data.size(); i++) {
            lineEntities = data.get(i).split(" ");

            for(int j = 0; j < lineEntities.length; j++) {
                double x = j*tailleCase;
                double y = i*tailleCase;
                if(!imagePaths.containsKey(Integer.valueOf(lineEntities[j]))) {
                    System.out.println("Erreur : impossible de trouver d'image associée à l'identiticateur " + lineEntities[j]);
                    return null;
                }
                String imagePath = imagePaths.get(Integer.valueOf(lineEntities[j]));
                Entity e = new Entity(x, y, imagePath, tailleCase, tailleCase);
                entities.add(e);
            }
        }
        return entities;
    }

    public static Integer[][] mapFromFile(String path) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(path));

        int nbLignes = 0;

        String[] entityImage;
        for(String s : data) {
            entityImage = s.split(":");
            if(entityImage.length == 2) {
                //Association Id:ImagePath
                nbLignes++;
            }
            else {
                //Si la taille est inférieure à 2, alors on a pas trouvé d'autre association Id:ImagePath.
                if(s.equals("\n") || s.equals("")) {
                    nbLignes++;
                }
                break;
            }
        }

        if (nbLignes > 0) {
            data.subList(0, nbLignes).clear();
        }

        //Largeur = taille d'une ligne. Longueur = taille d'une colonne.
        Integer[][] matrice = new Integer[data.get(0).length()][data.size()];

        String[] lineEntities;

        for(int i = 0; i < data.size(); i++) {
            lineEntities = data.get(i).split(" ");
            for(int j = 0; j < lineEntities.length; j++) {
                matrice[j][i] = Integer.valueOf(lineEntities[j]);
            }
        }

        return matrice;
    }
}
