package Gameplay;

import Moteur.Character;
import Moteur.CollisionEvent;
import Moteur.CollisionManager;
import Moteur.Entity;
import Moteur.Render;

public class PacmanCollisionManager extends CollisionManager {

    Render render;

    PacmanCollisionManager(Render render) {
        //On ne veut pas gérer les collisions avec les murs automatiquement
        super(false);
        this.render = render;
    }

    @Override
    public boolean update(CollisionEvent collisionEvent) {
        //Récupère Pacman si il est présent dans la collision
        Character pacman = collisionEvent.getEntity1().getType().equalsIgnoreCase("pacman")?(Character) collisionEvent.getEntity1():(Character) collisionEvent.getEntity2();
        if(pacman == null) { return false; }
        Entity e2 = collisionEvent.getEntity1() == pacman?collisionEvent.getEntity2():collisionEvent.getEntity1();
        String type2 = e2.getType();

        if(type2.equalsIgnoreCase("mur") || type2.equalsIgnoreCase("porte_enclos")) {
            return pacmanCollidesWall(pacman, e2);
        }
        else if(type2.equalsIgnoreCase("boule_simple")) {
            return pacmanCollidesPellet(pacman, e2);
        }
        else if(type2.equalsIgnoreCase("boule_speciale")) {
            return pacmanCollidesSpecial(pacman, e2);
        }
        return false;
    }

    private boolean pacmanCollidesPellet(Character pacman, Entity pellet) {
        return false;
    }

    private boolean pacmanCollidesSpecial(Character pacman, Entity special) {
        return false;
    }

    private void rectifyPlacement(Character pacman) {
        pacman.back();
        pacman.setvY(pacman.getOld_vY());
        pacman.setvX(pacman.getOld_vX());
        pacman.move(pacman.getOld_move());
    }

    private boolean pacmanCollidesWall(Character pacman, Entity wall) {
        if(pacman.getvX() > 0) {
            if(pacman.getOld_vX() > 0) {
                pacman.setX(wall.getX() - pacman.getWidth());
                pacman.setvX(0);
            }
            else {
                rectifyPlacement(pacman);
                return true;
            }
        }
        else if(pacman.getvX() < 0) {
            if(pacman.getOld_vX() < 0) {
                pacman.setX(wall.getX() + wall.getWidth());
                pacman.setvX(0);
            }
            else {
                rectifyPlacement(pacman);
                return true;
            }
        }
        if(pacman.getvY() > 0) {
            if(pacman.getOld_vY() > 0) {
                pacman.setY(wall.getY() - pacman.getHeight());
                pacman.setvY(0);
            }
            else {
                rectifyPlacement(pacman);
                return true;
            }
        }
        else if(pacman.getvY() < 0) {
            if(pacman.getOld_vY() < 0) {
                pacman.setY(wall.getY() + wall.getHeight());
                pacman.setvY(0);
            }
            else {
                rectifyPlacement(pacman);
                return true;
            }
        }

        return false;
    }
}
