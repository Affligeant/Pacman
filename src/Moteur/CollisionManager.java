package Moteur;

public abstract class CollisionManager {

    boolean autoPhysicHandled;

    /**
     * @param autoPhysicHandled Permet de gérer automatiquement les cas de collision entre les entités physiques
     * (bloquantes) et les entités mobiles et d'en annuler le déplacement si passé à True.
     */
    public CollisionManager(boolean autoPhysicHandled) {
        this.autoPhysicHandled = autoPhysicHandled;
    }

    /**
     * Constructeur qui instancie automatiquement le booléen autoPhysicHandled à true.
     */
    //Gère par défaut les collisions avec les entités "physiques"
    public CollisionManager() { this(true); }

    public abstract boolean update(CollisionEvent collisionEvent);

    public boolean autoPhysicHandle(CollisionEvent collisionEvent) {
        if(autoPhysicHandled) {
            Entity e1 = collisionEvent.getEntity1();
            Entity e2 = collisionEvent.getEntity2();
            if(e1.isPhysical() || e2.isPhysical()) {
                //On différencie l'entité mobile de l'entité fixe
                Character d;
                Entity nD;
                if(e1 instanceof Character) {
                    d = (Character) e1;
                    nD = e2;
                }
                else {
                    d = (Character) e2;
                    nD = e1;
                }
                if(d.getvX() > 0) {
                    d.setX(nD.getX() - d.getWidth());
                }
                else if(d.getvX() < 0) {
                    d.setX(nD.getX() + nD.getWidth());
                }
                if(d.getvY() > 0) {
                    d.setY(nD.getY() - d.getHeight());
                }
                else if(d.getvY() < 0) {
                    d.setY(nD.getY() + nD.getHeight());
                }
                d.setvX(0);
                d.setvY(0);
            }
        }
        return update(collisionEvent);
    }
}
