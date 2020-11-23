package Moteur;

/**
 * Interface {@code EntityFactory} used to generate entities automatically, when implemented
 * and passed as a parameter to the Tools.mapFromFile() method.
 *
 * Can also be used for personal automatic generation.
 */
public interface EntityFactory {

    /**
     * @param ID The ID of the entity.
     * @return An {@code Entity} created from the ID.
     */
    Entity create(Integer ID);
}
