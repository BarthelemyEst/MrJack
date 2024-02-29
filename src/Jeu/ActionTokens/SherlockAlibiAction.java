package Jeu.ActionTokens;

import Jeu.CharactersRelative.AlibiCard;
import Jeu.DetectiveToken;
import Jeu.Game;
import Jeu.Player.*;

import static Jeu.CharactersRelative.AlibiCard.drawCard;

import java.util.Scanner;

public class SherlockAlibiAction extends ActionToken {
    public static void moveSherlock(DetectiveToken sherlock, int[][] positions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("De combien bouger ?");
        int move = scanner.nextInt();
        while (move != 1 && move != 2) {
            System.out.println("De combien bouger ?");
            move = scanner.nextInt();
        }
        int pos = 0;
        while (positions[pos] != sherlock.position) {
            pos += 1;
        }
        if (pos + move >= 12) {
            pos = -1;
        }
        sherlock.position = positions[pos + move];
    }

    public static void draw(Game game, Player player) {
        AlibiCard card = drawCard(game.board.deck);
        System.out.println(card.character.name);
        if (player.isJackPlayer) {
            player.hourGlasses += card.hourGlass;
        } else {
            for (int i = 0; i < game.board.districts.length; i++) {
                if (game.board.districts[i].character == card.character) {
                    game.board.districts[i].isRecto = false;
                }
            }
        }
    }

    public SherlockAlibiAction() {
        this.name = "SherlockAlibiAction";
    }

    public void action(Game game, int[][] positions, Player player) {
        if (this.isRecto) {
            moveSherlock(game.board.detectiveTokens[2], positions);
        } else {
            draw(game, player);
        }
    }
}
