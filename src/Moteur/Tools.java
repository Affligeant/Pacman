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
        Integer[][] matrice = new Integer[data.get(0).split(" ").length][data.size()];

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

    /**
     * @param tailleCase Length of each matrix entity.
     * @param posX The x index of the first node.
     * @param posY The y index of the first node.
     * @param blocking The list of IDs corresponding to blocking entities.
     * @param filePath The path of the file containing the matrix.
     * @return A Graph created from the matrix given representing paths.
     * @throws IOException If the file containing the matrix couldn't be found.
     */
    public static Graph constructGraph(double tailleCase, int posX, int posY, List<Integer> blocking, String filePath) throws IOException {

        Integer[][] matrix = mapFromFile(filePath);
        assert matrix != null;
        boolean[][] exploredMatrix = new boolean[matrix.length][matrix[0].length];

        if(blocking.contains(matrix[posX][posY])) {
            System.out.println("Erreur : La position de départ donnée est un obstacle");
            return null;
        }

        Graph graph = new Graph();
        Node firstNode = new Node(posX * tailleCase, posY * tailleCase);
        exploredMatrix[posX][posY] = true;

        graph.addNode(firstNode);
        ArrayList<Node> nodesLeft = new ArrayList<>();
        nodesLeft.add(firstNode);

        while(nodesLeft.size() > 0) {
            Node n = nodesLeft.get(0);
            nodesLeft.remove(n);
            ArrayList<Node> nodeList = detectNextNodes(tailleCase, n, matrix, exploredMatrix, blocking);
            for(Node node : nodeList) {
                if(graph.addNode(node)) {
                    nodesLeft.add(node);
                }
                else {
                    node = graph.getNodeByPos(node.x, node.y);
                }
                graph.addArc(n, node);
                graph.addArc(node, n);
            }
        }

        return graph;
    }

    /**
     * Searches for the next node in a direction given by the vectors xVect and yVect.
     *
     * @param tailleCase Length of each matrix entity.
     * @param posX The starting x index.
     * @param posY The starting y index.
     * @param matrix The matrix with the entities IDs
     * @param exploredMatrix The matrix of the already explored path.
     * @param blocking The list of IDs corresponding to blocking entities.
     * @param xVect The horizontal vector.
     * @param yVect The vertical vector.
     * @return The next node found in the direction given by the vectors.
     */
    private static Node nextNodeDirection(double tailleCase, int posX, int posY, Integer[][] matrix, boolean[][] exploredMatrix, List<Integer> blocking, int xVect, int yVect) {
        int distance = 1;
        int nxtX = posX + xVect;
        int nxtY = posY + yVect;

        if(nxtX < 0 || nxtY < 0 || nxtX >= matrix.length || nxtY >= matrix[0].length || exploredMatrix[nxtX][nxtY]) {
            return null;
        }

        while(nxtX >= 0 && nxtY >= 0 && nxtX < matrix.length && nxtY < matrix[0].length && !blocking.contains(matrix[nxtX][nxtY])) {
            exploredMatrix[nxtX][nxtY] = true;
            distance += 1;
            if(xVect != 0 && (nxtY + 1 < matrix[0].length && !blocking.contains(matrix[nxtX][nxtY + 1])) || xVect != 0 && (nxtY - 1 >= 0 && !blocking.contains(matrix[nxtX][nxtY - 1]))) {
                break;
            }
            if(yVect != 0 && (nxtX + 1 < matrix.length && !blocking.contains(matrix[nxtX + 1][nxtY])) || yVect != 0 && (nxtX - 1 >= 0 && !blocking.contains(matrix[nxtX - 1][nxtY]))) {
                break;
            }

            nxtX += xVect;
            nxtY += yVect;
        }

        if(distance > 1) {
            return new Node(tailleCase * (posX + (distance - 1) * xVect), tailleCase * (posY + (distance - 1) * yVect));
        }

        return null;
    }

    /**
     * Searches for the next nodes in every direction from the given actual node.
     *
     * @param tailleCase Length of each matrix entity
     * @param actualNode Node from which the detection takes place
     * @param matrix The matrix of the entity IDs
     * @param exploredMatrix The matrix of the already explored path.
     * @param blocking The list of IDs corresponding to blocking entities.
     * @return A list of the next new nodes discovered.
     */
    private static ArrayList<Node> detectNextNodes(double tailleCase, Node actualNode, Integer[][] matrix, boolean[][] exploredMatrix, List<Integer> blocking) {

        ArrayList<Node> nextNodes = new ArrayList<>();
        int posX = (int) (actualNode.x / tailleCase);
        int posY = (int) (actualNode.y / tailleCase);

        Node north = nextNodeDirection(tailleCase, posX, posY, matrix, exploredMatrix, blocking, 0, -1);
        if(north != null) { nextNodes.add(north); }
        Node south = nextNodeDirection(tailleCase, posX, posY, matrix, exploredMatrix, blocking, 0, 1);
        if(south != null) { nextNodes.add(south); }
        Node east = nextNodeDirection(tailleCase, posX, posY, matrix, exploredMatrix, blocking, 1, 0);
        if(east != null) { nextNodes.add(east); }
        Node west = nextNodeDirection(tailleCase, posX, posY, matrix, exploredMatrix, blocking, -1, 0);
        if(west != null) { nextNodes.add(west); }

        return nextNodes;
    }
}
