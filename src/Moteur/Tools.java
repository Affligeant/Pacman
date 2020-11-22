package Moteur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Tools {

    public static ArrayList<Entity> mapFromFile(String path, double tailleCase, EntityFactory entityFactory) throws IOException {

        List<String> data = Files.readAllLines(Paths.get(path));

        if(isBadFormed(data)) {
            System.out.println("La matrice des ID du fichier donné en paramètres n'est pas correcte");
            return null;
        }

        //Ajout des entités

        ArrayList<Entity> entities = new ArrayList<>();

        String[] lineEntities;

        for(int i = 0; i < data.size(); i++) {
            lineEntities = data.get(i).split(" ");

            for(int j = 0; j < lineEntities.length; j++) {
                double x = j*tailleCase;
                double y = i*tailleCase;
                Entity e = entityFactory.create(Integer.valueOf(lineEntities[j]));
                e.setX(x);
                e.setY(y);
                e.resize(tailleCase, tailleCase);
                entities.add(e);
            }
        }
        return entities;
    }

    /** Méthode permettant d'extraire les ID de la matrice d'une carte donnée en paramètres.
     * @param path Le chemin d'accès du fichier
     * @return Un tableau de Integer en 2 dimensions représentant les ID de la matrice associée au fichier.
     * @throws IOException Si on ne trouve pas le fichier correspondant aux données
     */
    public static Integer[][] mapFromFile(String path) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(path));

        if(isBadFormed(data)) {
            System.out.println("La matrice des ID du fichier donné en paramètres n'est pas correcte");
            return null;
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

    /** Méthode permettant de vérifier la validité de la matrice d'ID du fichier.
     * @param data Matrice contenant les ID des entités
     * @return True si la matrice est rectangulaire, false si elle a des lignes dont la taille varie.
     */
    private static boolean isBadFormed(List<String> data) {
        int taille = data.get(0).length();
        for(String s : data) {
            if(s.length() != taille) { return true; }
        }
        return false;
    }
}
