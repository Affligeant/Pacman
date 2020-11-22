package Gameplay;

import Moteur.CollisionEvent;
import Moteur.CollisionManager;
import Moteur.Character;
import Moteur.Entity;

public class PacmanCollisionManager extends CollisionManager {

    PacmanCollisionManager() {
        //On ne veut pas gérer les collisions avec les murs automatiquement
        super(false);
    }

    @Override
    public boolean update(CollisionEvent collisionEvent) {
        //Récupère Pacman si il est présent dans la collision
        Character pacman = collisionEvent.getEntity1().getType().equalsIgnoreCase("pacman")?(Character) collisionEvent.getEntity1():(Character) collisionEvent.getEntity2();
        if(pacman == null) { return false; }
        Entity e2 = collisionEvent.getEntity1() == pacman?collisionEvent.getEntity2():collisionEvent.getEntity1();

        if(e2.isPhysical()) {
            if(pacman.getvX() > 0) {
                if(pacman.getOld_vX() > 0) {
                    pacman.setX(e2.getX() - pacman.getWidth());
                    pacman.setvX(0);
                }
                else {
                    pacman.back();
                    pacman.setvY(pacman.getOld_vY());
                    pacman.setvX(pacman.getOld_vX());
                    pacman.move(pacman.getOld_move());
                    return true;
                }
            }
            else if(pacman.getvX() < 0) {
                if(pacman.getOld_vX() < 0) {
                    pacman.setX(e2.getX() + e2.getWidth());
                    pacman.setvX(0);
                }
                else {
                    pacman.back();
                    pacman.setvY(pacman.getOld_vY());
                    pacman.setvX(pacman.getOld_vX());
                    pacman.move(pacman.getOld_move());
                    return true;
                }
            }
            if(pacman.getvY() > 0) {
                if(pacman.getOld_vY() > 0) {
                    pacman.setY(e2.getY() - pacman.getHeight());
                    pacman.setvY(0);
                }
                else {
                    pacman.back();
                    pacman.setvY(pacman.getOld_vY());
                    pacman.setvX(pacman.getOld_vX());
                    pacman.move(pacman.getOld_move());
                    return true;
                }
            }
            else if(pacman.getvY() < 0) {
                if(pacman.getOld_vY() < 0) {
                    pacman.setY(e2.getY() + e2.getHeight());
                    pacman.setvY(0);
                }
                else {
                    pacman.back();
                    pacman.setvY(pacman.getOld_vY());
                    pacman.setvX(pacman.getOld_vX());
                    pacman.move(pacman.getOld_move());
                    return true;
                }
            }
        }
        return false;
    }
}
