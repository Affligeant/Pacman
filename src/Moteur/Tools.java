package Moteur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Tools {

    /** That method takes a file containing a matrix of IDs, a factory, and the length
     * of an entity to automatically generate each entity depending on their ID.
     *
     * @param path Path to the file containing the IDs. Must be a numeric Matrix separated with spaces.
     * @param tailleCase The length of an entity. Both it's height and width.
     * @param entityFactory The Factory used to build the entities. Must implement EntityFactory.
     * @return Returns an ArrayList of Entity objects filled with their x, y and size.
     * @throws IOException If the file of the matrix can't be located, or if the construction of the Entity throws it.
     */
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

    /** Method used to extract the IDs from the matrix of a given file.
     * @param path Path to the file.
     * @return A 2 dimensional {@code Integer} Array, filled with the IDs found in the file.
     * @throws IOException If the file can't be located.
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

    /** Method used to verify the format of a given file.
     * @param data Matrix of the Entitys IDs
     * @return {@code True} If the matrix is rectangular. {@code False} if it's lines' length vary.
     */
    private static boolean isBadFormed(List<String> data) {
        int taille = data.get(0).length();
        for(String s : data) {
            if(s.length() != taille) { return true; }
        }
        return false;
    }
}
