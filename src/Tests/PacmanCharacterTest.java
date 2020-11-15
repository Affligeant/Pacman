package Tests;

import Moteur.Movable;
import Moteur.PacmanCharacter;
import org.junit.Assert;
import org.junit.Test;


public class PacmanCharacterTest {

    /*** Test si le constructeur d'un pacmanCharacter instancie bien avec les bonnes valeurs ***/
    @Test
    public void TestConstructeurPacmanCharacter(){
        PacmanCharacter pacmanCharacter = new PacmanCharacter(50,50,Movable.Direction.BAS,null);

        Assert.assertNotNull(pacmanCharacter.getDirection());
        Assert.assertSame(Movable.Direction.BAS,pacmanCharacter.getDirection());
    }

    /*** Test si la fonction changeDirection modofie correctement la valeur Direction du pacmanCharacter ***/
    @Test
    public void TestChangeDirection(){
        PacmanCharacter pacmanCharacter = new PacmanCharacter(50,50,Movable.Direction.BAS,null);

        pacmanCharacter.changeDirection(Movable.Direction.GAUCHE);
        Assert.assertSame(Movable.Direction.GAUCHE,pacmanCharacter.getDirection());

        pacmanCharacter.changeDirection(Movable.Direction.DROITE);
        Assert.assertSame(Movable.Direction.DROITE,pacmanCharacter.getDirection());

        pacmanCharacter.changeDirection(Movable.Direction.HAUT);
        Assert.assertSame(Movable.Direction.HAUT,pacmanCharacter.getDirection());

    }
}
