package Jeu;

import Jeu.ActionTokens.*;
import Jeu.CharactersRelative.AlibiCard;
import Jeu.CharactersRelative.Character;
import Jeu.Player.DetectivePlayer;
import Jeu.Player.MrJackPlayer;
import Jeu.Player.Player;

import static Jeu.DetectiveToken.detectiveNames;
import static Jeu.Game.startGame;

public class Main {

    public static void main(String[] args) {

        // Création des characters :
        Character[] characters = new Character[9];
        int numChar = 0;
        for (Character.alibiName name : Character.alibiName.values()) {
            characters[numChar] = new Character(name, false);
            numChar += 1;
        }

        // Création des DetectiveToken :
        int[][] positions = new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 }, { 2, 4 }, { 3, 4 }, { 4, 3 },
                { 4, 2 }, { 4, 1 }, { 3, 0 }, { 2, 0 }, { 1, 0 } };

        DetectiveToken sherlock = new DetectiveToken(detectiveNames.Sherlock, positions[0]);
        DetectiveToken watson = new DetectiveToken(detectiveNames.Watson, positions[1]);
        DetectiveToken toby = new DetectiveToken(detectiveNames.Toby, positions[2]);

        // Création des districts :
        District[] districts = new District[9];
        for (int i = 0; i < characters.length; i++) {
            districts[i] = new District(District.orientations.North, characters[i], true);
        }

        // Création des cellules :
        Cell[][] cellules = new Cell[5][5];
        int dis = 0;
        int dis2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cellules[i + 1][j + 1] = new Cell(districts[dis + dis2], new int[] { i + 1, j + 1 });
                dis2 += 1;
            }
            dis2 = 0;
            dis += 3;
        }

        // Création des cartes :

        AlibiCard[] cards = new AlibiCard[9];
        for (int i = 0; i < characters.length; i++) {
            cards[i] = new AlibiCard(characters[i]);
        }

        // Création des ActionsTokens :
        SherlockAlibiAction sherlockAlibiAction = new SherlockAlibiAction();
        SwapRotateAction swapRotateAction = new SwapRotateAction();
        WatsonTobyAction watsonTobyAction = new WatsonTobyAction();
        JokerRotateAction jokerRotateAction = new JokerRotateAction();

        // Création des Players :
        MrJackPlayer mrJack = new MrJackPlayer();
        DetectivePlayer detective = new DetectivePlayer();

        Board board = new Board(cellules, new DetectiveToken[] { watson, toby, sherlock }, characters, cards, districts,
                new ActionToken[] { sherlockAlibiAction, swapRotateAction, watsonTobyAction, jokerRotateAction });

        Game game = new Game(board, new Player[] { detective, mrJack }, positions);
        startGame(game);
    }
}
