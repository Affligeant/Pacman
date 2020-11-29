package Gameplay;

import Moteur.*;
import Moteur.Character;
import Moteur.Render.EdgeType;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWindow extends Window {

    public final int TAILLE_CASE = 30;

    private Render render;
    private ArrayList<Niveau> niveaux;
    private Pacman pacman;
    private Text niveau;
    private Entity number1;
    private Entity number2;
    private Entity number3;

    public GameWindow(double height, double width) { super(height, width + 200); }

    public void init() {

        Canevas canevas = new Canevas(width-200, height);
        canevas.setBackground(Color.BLACK);
        setCenter(canevas);

        // INFORMATION PANEL
        //
        Text score = new Text("0");
        niveau = new Text("0");

        score.setSize(24);
        score.setFill(Color.ORANGERED);
        niveau.setSize(24);
        niveau.setFill(Color.ORANGERED);

        setRight(initInformationPanel(score, niveau));
        //
        // END OF INFORMATION PANEL

        render = new Render(canevas.getGraphicContext(), this, width-200, height, EdgeType.WARP);

        Niveau niveau1;
        Niveau niveau2;
        Niveau niveau3;
        try {
            pacman = new Pacman(14 * 30, 23 * 30, TAILLE_CASE, score);
            niveau1 = new Niveau("src/Gameplay/niveau1.map", 1, Niveau.FruitType.POMME, 1, 1, TAILLE_CASE, width-200, height, pacman);
            niveau2 = new Niveau("src/Gameplay/niveau2.map", 1.2, Niveau.FruitType.PECHE, 6, 1, TAILLE_CASE, width-200, height, pacman);
            niveau3 = new Niveau("src/Gameplay/niveau3.map", 1.5, Niveau.FruitType.CERISES, 6, 1, TAILLE_CASE, width-200, height, pacman);
            render.getKeyEventManager().add(new PauseObserver(render, width-200, height), "P");
            double centerXnumber = (width-200) / 2 - 30;
            double centerYnumber = height/2 - 80;
            number1 = new Entity(centerXnumber, centerYnumber, "src/Images/number1.png", 100, 60, false);
            number2 = new Entity(centerXnumber, centerYnumber, "src/Images/number2.png", 100, 60, false);
            number3 = new Entity(centerXnumber, centerYnumber, "src/Images/number3.png", 100, 60, false);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        render.getKeyEventManager().add((KeyObserver) pacman.getMovableBehavior(), Arrays.asList("UP", "DOWN", "LEFT", "RIGHT", "Z", "Q", "S", "D"));
        niveaux = new ArrayList<>(Arrays.asList(niveau1, niveau2, niveau3));

        render.start();
    }

    public void nextLevel() {

        //reset de la position de pacman
        pacman.setX(14 * 30);
        pacman.setY(23 * 30);
        pacman.setvX(0);
        pacman.setvY(0);

        //Retrait de toutes les entités et collisionManager
        render.removeAll();
        render.removeObservers();

        //Ajout des entités du niveau suivant
        Niveau n = niveaux.remove(0);
        niveau.setText(String.valueOf(Integer.parseInt(niveau.getText()) + 1));
        render.addEntity(n.getElements());
        for(Character c : n.getCharacters()) {
            render.addEntity(c);
        }
        render.addEntity(pacman);

        PacmanCollisionManager pacmanCollisionManager = new PacmanCollisionManager(pacman, this, n.getElements());
        render.addObserver(pacmanCollisionManager);
        pacmanCollisionManager.setScoreMultiplier(n.scoreMultiplier);

        if(!render.isPaused()) { render.togglePause(); }

        render.addEntity(number3);
        render.delayExecution(1000, () -> {
            render.removeEntity(number3);
            render.addEntity(number2);
        });
        render.delayExecution(2000, () -> {
            render.removeEntity(number2);
            render.addEntity(number1);
        });
        render.delayExecution(3000, () -> {
            render.removeEntity(number1);
            render.togglePause();
        });
    }

    private Area initInformationPanel(Text score, Text niveau) {

        //Cadre
        Area informationPanelBox = new Area(200, height);
        informationPanelBox.setColor("purple");

        //Zone centrale
        Area informationPanel = new Area(200, height);
        informationPanel.setMargins(12);
        informationPanel.setColor("black");
        informationPanelBox.setCenter((WindowElement) informationPanel);

        //Zone vide en haut
        Area zoneVide = new Area(200, height / 4);
        zoneVide.setColor("black");
        informationPanel.setTop((WindowElement) zoneVide);

        //Zone vide en bas
        Area zoneVide2 = new Area(200, height/4);
        zoneVide2.setColor("black");
        informationPanel.setBottom((WindowElement) zoneVide2);

        //Zone centrale avec les 2 zones d'informations
        Area informationArea = new Area(200, height/2);
        informationArea.setColor("black");
        informationArea.setMargins(15);
        informationPanel.setCenter((WindowElement) informationArea);

        //Première zone d'infos : le niveau
        Area levelInfos = new Area(200, height/5);
        levelInfos.setColor("black");
        Text niveauLabel = new Text("Niveau :");
        niveauLabel.setSize(28);
        niveauLabel.setFill(Color.ORANGE);
        levelInfos.setTop((WindowElement) niveauLabel);
        levelInfos.setCenter((WindowElement) niveau);
        informationArea.setTop((WindowElement) levelInfos);

        //Zone de vide entre les infos
        Area zoneVide3 = new Area(200, height/10);
        zoneVide3.setColor("black");
        informationArea.setCenter((WindowElement) zoneVide3);

        //2eme zone d'infos : le score
        Area scoreInfos = new Area(200, height/5);
        scoreInfos.setColor("black");
        Text scoreLabel = new Text("Score :");
        scoreLabel.setFill(Color.ORANGE);
        scoreLabel.setSize(28);
        scoreInfos.setTop((WindowElement) scoreLabel);
        scoreInfos.setCenter((WindowElement) score);
        informationArea.setBottom((WindowElement) scoreInfos);

        return informationPanelBox;
    }
}
