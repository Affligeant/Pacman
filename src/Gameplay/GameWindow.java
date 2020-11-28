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

    public GameWindow(double height, double width) { super(height, width + 200); }

    public void init() {

        Canevas canevas = new Canevas(width-200, height);
        canevas.setBackground(Color.BLACK);
        setCenter(canevas);


        Text score = new Text("0");
        Text niveau = new Text("1");
        score.setSize(24);
        score.setFill(Color.ORANGERED);
        niveau.setSize(24);
        niveau.setFill(Color.ORANGERED);
        setRight(initInformationPanel(score, niveau));

        Render render = new Render(canevas.getGraphicContext(), this, width-200, height, EdgeType.WARP);

        Pacman pacman;
        Niveau niveau1;
        Niveau niveau2;
        Niveau niveau3;
        try {
            pacman = new Pacman(14 * 30, 23 * 30, TAILLE_CASE, score);
            niveau1 = new Niveau("src/Gameplay/niveau1.map", 1, Niveau.FruitType.POMME, 1, 1, TAILLE_CASE, width-200, height, pacman);
            niveau2 = new Niveau("src/Gameplay/niveau2.map", 1.2, Niveau.FruitType.PECHE, 6, 1, TAILLE_CASE, width-200, height, pacman);
            niveau3 = new Niveau("src/Gameplay/niveau3.map", 1.5, Niveau.FruitType.CERISES, 6, 1, TAILLE_CASE, width-200, height, pacman);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        render.getKeyEventManager().add((KeyObserver) pacman.getMovableBehavior(), Arrays.asList("UP", "DOWN", "LEFT", "RIGHT", "Z", "Q", "S", "D"));
        PacmanCollisionManager pacmanCollisionManager = new PacmanCollisionManager(pacman);
        render.addObserver(pacmanCollisionManager);
        ArrayList<Niveau> niveaux = new ArrayList<>(Arrays.asList(niveau1, niveau2, niveau3));

        render.addEntity(niveaux.get(0).getElements());
        for(Character c : niveaux.get(0).getCharacters()) {
            render.addEntity(c);
        }
        render.addEntity(pacman);
        pacmanCollisionManager.setScoreMultiplier(niveaux.get(0).scoreMultiplier);

        render.start();
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
