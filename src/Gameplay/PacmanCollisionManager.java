package Gameplay;

import Moteur.Character;
import Moteur.CollisionEvent;
import Moteur.CollisionManager;
import Moteur.Entity;

import java.util.ArrayList;

public class PacmanCollisionManager implements CollisionManager {

    Pacman pacman;
    double scoreMultiplier;
    GameWindow gameWindow;
    ArrayList<Entity> elements;

    PacmanCollisionManager(Pacman pacman, GameWindow gameWindow, ArrayList<Entity> elements) {
        super();
        this.pacman = pacman;
        this.gameWindow = gameWindow;
        this.elements = elements;
        this.scoreMultiplier = 1;
    }

    @Override
    public boolean update(CollisionEvent collisionEvent) {
        //Vérifie si la collision inclut pacman
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
        else if(e2 instanceof Fruit) {
            return pacmanCollidesFruit(pacman, (Fruit) e2);
        }
        return false;
    }

    private boolean pacmanCollidesFruit(Pacman pacman, Fruit fruit) {
        if(fruit.isAte()) { return false; }

        double xIn = xIn(pacman, fruit);
        double yIn = yIn(pacman, fruit);

        if(xIn > fruit.getWidth() / 5 && yIn > fruit.getHeight() / 5) {
            fruit.eat();
            pacman.addScore(fruit.getValue());
        }

        return false;
    }

    private boolean pacmanCollidesPellet(Pacman pacman, Pellet pellet) {
        if(pellet.isAte()) { return false; }

        double xIn = xIn(pacman, pellet);
        double yIn = yIn(pacman, pellet);

        if(xIn > pellet.getWidth() / 2 && yIn > pellet.getHeight() / 2) {
            pellet.eat();
            pacman.addScore(10 * scoreMultiplier);
            if(ateEverything()) { gameWindow.nextLevel(); }
        }
        return false;
    }

    private boolean pacmanCollidesSpecial(Pacman pacman, SpecialPellet special) {
        if(special.isAte()) { return false; }

        double xIn = xIn(pacman, special);
        double yIn = yIn(pacman, special);

        if(xIn > special.getWidth() / 3 && yIn > special.getHeight() / 3) {
            special.eat();
            pacman.addScore(50 * scoreMultiplier);
            if(ateEverything()) { gameWindow.nextLevel(); }
        }
        return false;
    }

    private double xIn(Entity e1, Entity e2) {

        double xIn;

        if(e1.getX() >= e2.getX() && e1.getX() < e2.getX() + e2.getWidth()) {
            xIn = e2.getX() + e2.getWidth() - e1.getX();
        }
        else if(e2.getX() > e1.getX() && e2.getX() < e1.getX() + e1.getWidth()){
            xIn = e1.getX() + e1.getWidth() - e2.getX();
        }
        else {
            xIn = 0;
        }

        return xIn;
    }

    private double yIn(Entity e1, Entity e2) {

        double yIn;

        if(e1.getY() >= e2.getY() && e1.getY() < e2.getY() + e2.getHeight()) {
            yIn = e2.getY() + e2.getWidth() - e1.getY();
        }
        else if(e2.getY() > e1.getY() && e2.getY() < e1.getY() + e1.getHeight()) {
            yIn = e1.getY() + e1.getWidth() - e2.getY();
        }
        else {
            yIn = 0;
        }

        return yIn;
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

    private boolean ateEverything() {

        for(Entity e : elements) {
            if(e instanceof Pellet && !((Pellet) e).isAte()) {
                return false;
            }

            if(e instanceof SpecialPellet && !((SpecialPellet) e).isAte()) {
                return false;
            }
        }

        return true;
    }

    public void setScoreMultiplier(double scoreMultiplier) { this.scoreMultiplier = scoreMultiplier; }
}
