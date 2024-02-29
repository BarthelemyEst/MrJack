package Jeu.ActionTokens;

import Jeu.DetectiveToken;
import Jeu.Game;
import Jeu.Player.Player;

import java.util.Scanner;


public class WatsonTobyAction extends ActionToken {
    public static void moveWatson(DetectiveToken watson, int[][] positions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("De combien bouger ?");
        int move = scanner.nextInt();
        while (move != 1 && move != 2) {
            System.out.println("De combien bouger ?");
            move = scanner.nextInt();
        }
        int pos = 0;
        while (positions[pos] != watson.position) {
            pos += 1;
        }
        if (pos + move >= 12) {
            pos = -1;
        }
        watson.position = positions[pos + move];
    }

    public static void moveToby(DetectiveToken toby, int[][] positions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("De combien bouger ?");
        int move = scanner.nextInt();
        while (move != 1 && move != 2) {
            System.out.println("De combien bouger ?");
            move = scanner.nextInt();
        }
        int pos = 0;
        while (positions[pos] != toby.position) {
            pos += 1;
        }
        if (pos + move >= 12) {
            pos = -1;
        }
        toby.position = positions[pos + move];
    }

    public WatsonTobyAction() {
        this.name = "WatsonTobyAction";
    }

    public void action(Game game, int[][] positions, Player player) {
        if (this.isRecto) {
            moveWatson(game.board.detectiveTokens[0], positions);
        } else {
            moveToby(game.board.detectiveTokens[1], positions);
        }
    }
}
