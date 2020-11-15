package Tests;


import Moteur.Movable;
import Moteur.PacmanCharacter;
import Moteur.PacmanEventManager;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Assert;


public class PacmanEventManagerTests extends Application {
    public PacmanEventManager pacmanEventManager;
    public Scene scene;
    FXRobot robot;
    PacmanCharacter pacmanCharacter;

    public void testPressS() {
        System.out.println("Test des changements de direction :  \n");
        robot.keyPress(KeyCode.Z);
        System.out.println(pacmanCharacter.getDirection());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(new BorderPane(), 800, 800);
        robot = FXRobotFactory.createRobot(scene);

        pacmanEventManager = new PacmanEventManager(scene);

        pacmanCharacter = new PacmanCharacter(400,400, Movable.Direction.BAS, null);
        pacmanEventManager.handle(pacmanCharacter);

        Assert.assertSame(Movable.Direction.BAS, pacmanCharacter.getDirection());

        System.out.println(pacmanCharacter.getDirection());

        System.out.println("Test des changements de direction :  \n");
        robot.keyPress(KeyCode.Z);
        System.out.println(pacmanCharacter.getDirection());

    }
}
