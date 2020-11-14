package Moteur;

/**
 * Interface {@code Movable} définit un mouvement avec les méthodes move, changeDirection(Direction).
 * Définit les getters getX et getY.
 */
public interface Movable {
    enum Direction {
        BAS(0), GAUCHE(1), HAUT(2), DROITE(3);

        final int value;

        Direction(int value) {
            this.value = value;
        }
    }

    void move();
    void changeDirection(Direction direction);
}
