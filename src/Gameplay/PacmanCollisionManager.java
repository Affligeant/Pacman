package Gameplay;

import Moteur.Character;
import Moteur.CollisionEvent;
import Moteur.CollisionManager;
import Moteur.Entity;

public class PacmanCollisionManager extends CollisionManager {

    Character pacman;

    PacmanCollisionManager(Character pacman) {
        //On ne veut pas gérer les collisions avec les murs automatiquement
        super();
        this.pacman = pacman;
    }

    @Override
    public boolean update(CollisionEvent collisionEvent) {
        //Récupère Pacman si il est présent dans la collision
        if(pacman != collisionEvent.getEntity1() && pacman != collisionEvent.getEntity2()) { return false; }
        Entity e2 = collisionEvent.getEntity1() == pacman?collisionEvent.getEntity2():collisionEvent.getEntity1();

        if(e2 instanceof Mur || e2 instanceof Porte_Enclos) {
            return pacmanCollidesWall(pacman, e2);
        }
        else if(e2 instanceof Pellet) {
            return pacmanCollidesPellet(pacman, (Pellet) e2);
        }
        else if(e2 instanceof SpecialPellet) {
            return pacmanCollidesSpecial(pacman, (SpecialPellet) e2);
        }
        return false;
    }

    private boolean pacmanCollidesPellet(Character pacman, Pellet pellet) {
        double xIn;
        double yIn;

        if(pacman.getX() >= pellet.getX() && pacman.getX() < pellet.getX() + pellet.getWidth()) {
            xIn = pellet.getX() + pellet.getWidth() - pacman.getX();
        }
        else if(pellet.getX() > pacman.getX() && pellet.getX() < pacman.getX() + pacman.getWidth()){
            xIn = pacman.getX() + pacman.getWidth() - pellet.getX();
        }
        else {
            xIn = 0;
        }

        if(pacman.getY() >= pellet.getY() && pacman.getY() < pellet.getY() + pellet.getHeight()) {
            yIn = pellet.getY() + pellet.getWidth() - pacman.getY();
        }
        else if(pellet.getY() > pacman.getY() && pellet.getY() < pacman.getY() + pacman.getHeight()) {
            yIn = pacman.getY() + pacman.getWidth() - pellet.getY();
        }
        else {
            yIn = 0;
        }

        if(xIn > pellet.getWidth() / 2 && yIn > pellet.getHeight() / 2) {
            pellet.eat();
        }
        return false;
    }

    private boolean pacmanCollidesSpecial(Character pacman, SpecialPellet special) {
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
