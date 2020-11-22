package Gameplay;

import Moteur.Entity;
import Moteur.EntityFactory;

import java.io.IOException;

public class PacmanEntityFactory implements EntityFactory {

    enum entity {
        MUR(1), BOULE_SIMPLE(0), CHEMIN(5), PORTE_ENCLOS(4), BOULE_SPECIALE(3);

        public final int value;

        entity(int value) {
            this.value = value;
        }
    }

    @Override
    public Entity create(Integer ID) {
        try {
            if (ID == entity.MUR.value) {
                return new Mur();
            }
            else if(ID == entity.BOULE_SIMPLE.value) {
                return new Pellet();
            }
            else if(ID == entity.PORTE_ENCLOS.value) {
                return new Porte_Enclos();
            }
            else if(ID == entity.CHEMIN.value) {
                return new Chemin();
            }
            else if(ID == entity.BOULE_SPECIALE.value) {
                return new SpecialPellet();
            }
            else {
                System.out.println("Entité non reconnue");
                return null;
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
