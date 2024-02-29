package Jeu.ActionTokens;

import Jeu.Game;
import Jeu.Player.Player;

import static Jeu.Cell.*;

import java.util.Scanner;

public class SwapRotateAction extends ActionToken {
    public static void swap(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Position de la première cellule : ");
        int posx1 = scanner.nextInt();
        int posy1 = scanner.nextInt();
        System.out.println("Position de la deuxième cellule : ");
        int posx2 = scanner.nextInt();
        int posy2 = scanner.nextInt();

        while (posx1 == posx2 && posy1 == posy2 || posx1 < 1 || posx1 > 3 || posx2 < 1 || posx2 > 3 || posy1 < 1 || posy1 > 3) {
            System.out.println("Erreur dans le choix des cellules (identiques ou hors board)");
            System.out.println("Position de la première cellule : ");
            posx1 = scanner.nextInt();
            posy1 = scanner.nextInt();
            System.out.println("Position de la deuxième cellule : ");
            posx2 = scanner.nextInt();
            posy2 = scanner.nextInt();
        }
        exchange(game.board.cells[posx1][posy1], game.board.cells[posx2][posy2]);
    }

    public static void rotate(Game game) {
        rotation(game);
    }

    public SwapRotateAction() {
        this.name = "SwapRotateAction";
    }

    public void action(Game game, int[][] positions, Player player) {
        if (this.isRecto) {
            swap(game);
        } else {
            rotate(game);
        }
    }
}
