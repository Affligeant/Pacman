package Moteur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Tools {

    private static HashMap<String, double[]> special_areas;

    /**
     * @param path Le chemin d'accès du fichier.
     * @param tailleCase La taille voulue de chaque entité de la matrice à l'écran.
     * @return Une ArrayList avec toutes les entités se trouvant dans la matrice,
     * et avec les paramètres spécifiés en début de fichier selon la forme suivante :
     *
     *      ID:./chemin/vers/image:physic:type
     *
     * ID est numérique, ensuite vient le chemin vers l'image associée à la ressource,
     * puis le boléen déterminant si la physique de collision de déplacement lui est
     * appliquée, et pour finir le type d'entité (son nom habituellement).
     *
     * @throws IOException Si on ne trouve pas le fichier spécifié ou une des images associées
     * aux entités.
     */
    public static ArrayList<Entity> mapFromFile(String path, double tailleCase) throws IOException {

        //Dictionnaire d'entités
        HashMap<Integer, String[]> entityAttributes = new HashMap<>();

        List<String> data = Files.readAllLines(Paths.get(path));

        //Initialisation du dictionnaire d'images

        int nbLignes = 0;

        String[] entityLineAttributes;
        for(String s : data) {
            entityLineAttributes = s.split(":");
            if(entityLineAttributes.length == 4) {
                //On a trouvé une association Id:ImagePath:Physic:Name, on l'ajoute au dictionnaire
                if(entityAttributes.containsKey(Integer.valueOf(entityLineAttributes[0]))) {
                    System.out.println("Erreur : Id " + entityLineAttributes[0] + " déjà associé à une image.");
                    return null;
                }
                String[] attributes = new String[]{entityLineAttributes[1], entityLineAttributes[2], entityLineAttributes[3]};
                entityAttributes.put(Integer.valueOf(entityLineAttributes[0]), attributes);
                nbLignes++;
            }
            else if(entityLineAttributes.length == 2) {
                //Si la taille est égale à 2, on a une zone spéciale
                if (entityAttributes.containsKey(Integer.valueOf(entityLineAttributes[0]))) {
                    System.out.println("Erreur : Id " + entityLineAttributes[0] + " déjà associé à une image.");
                    return null;
                }
                String[] attributes = new String[]{entityLineAttributes[1]};
                entityAttributes.put(Integer.valueOf(entityLineAttributes[0]), attributes);
                nbLignes++;
            }
            else {
                //Si la taille n'est ni 2 ni 4 (normalement 1), alors on a pas trouvé d'autre association Id:ImagePath.
                if(s.equals("\n") || s.equals("")) {
                    nbLignes++;
                }
                break;
            }
        }

        if (nbLignes > 0) {
            data.subList(0, nbLignes).clear();
        }

        if(!isWellFormed(data)) {
            System.out.println("La matrice des ID du fichier donné en paramètres n'est pas correcte");
            return null;
        }

        //Ajout des entités

        ArrayList<Entity> entities = new ArrayList<>();
        special_areas = new HashMap<>();

        String[] lineEntities;

        for(int i = 0; i < data.size(); i++) {
            lineEntities = data.get(i).split(" ");

            for(int j = 0; j < lineEntities.length; j++) {
                if(!entityAttributes.containsKey(Integer.valueOf(lineEntities[j]))) {
                    System.out.println("Erreur : impossible de trouver d'image associée à l'identiticateur " + lineEntities[j]);
                    return null;
                }
                String[] attributes = entityAttributes.get(Integer.valueOf(lineEntities[j]));
                double x = j*tailleCase;
                double y = i*tailleCase;
                if(attributes.length > 1) {
                    String imagePath = attributes[0];
                    boolean physic = attributes[1].equals("true");
                    String type = attributes[2];
                    Entity e = new Entity(x, y, imagePath, tailleCase, tailleCase, physic, type);
                    entities.add(e);
                }
                else {
                    //On a une zone spéciale
                    String type = attributes[0];
                    special_areas.put(type, new double[]{x, y});
                }
            }
        }
        return entities;
    }

    /** Méthode permettant de récupérer la liste des zones spéciales
     * définies dans le fichier simplement par
     *
     *    ID:type
     *
     * ID étant un numérique, et type le nom de la zone.
     *
     * @return Liste des zones spéciales.
     * Sous la forme d'un HashMap<String,double[]> avec en
     * clé le nom de la zone et en valeur sa position x/y
     * dans un tableau de double de 2 éléments.
     */
    public static HashMap<String, double[]> getSpecial_areas() {
        if (special_areas == null) {
            System.out.println("Erreur : Vous devez avoir lancé mapFromFile(String path, double tailleCase) au moins une fois avec succès avant de pouvoir accéder à special_areas.");
        }
        return special_areas;
    }

    /** Méthode permettant d'extraire les ID de la matrice d'une carte donnée en paramètres.
     * @param path Le chemin d'accès du fichier
     * @return Un tableau de Integer en 2 dimensions représentant les ID de la matrice associée au fichier.
     * @throws IOException Si on ne trouve pas le fichier correspondant aux données
     */
    public static Integer[][] mapFromFile(String path) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(path));

        int nbLignes = 0;

        String[] entityImage;
        for(String s : data) {
            entityImage = s.split(":");
            if(entityImage.length == 4) {
                //Association Id:ImagePath
                nbLignes++;
            }
            else if(entityImage.length == 2) {
                //Association Id:ZoneSpeciale
                nbLignes++;
            }
            else {
                //Si la taille n'est ni 2 ni 4 (normalement 1), alors on a pas trouvé d'autre association Id:ImagePath.
                if(s.equals("\n") || s.equals("")) {
                    nbLignes++;
                }
                break;
            }
        }

        if (nbLignes > 0) {
            data.subList(0, nbLignes).clear();
        }

        if(!isWellFormed(data)) {
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
    private static boolean isWellFormed(List<String> data) {
        int taille = data.get(0).length();
        for(String s : data) {
            if(s.length() != taille) { return false; }
        }
        return true;
    }
}
