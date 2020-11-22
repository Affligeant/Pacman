package Moteur;

public class BasicPhysicHandler extends CollisionManager {

    public BasicPhysicHandler() {
        super();
    }

    @Override
    public boolean update(CollisionEvent collisionEvent) {
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

        return false;
    }
}
