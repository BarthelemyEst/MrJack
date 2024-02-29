package Jeu.ActionTokens;

import Jeu.DetectiveToken;
import Jeu.Game;
import Jeu.Player.Player;

import java.util.Scanner;

import static Jeu.Cell.rotation;

public class JokerRotateAction extends ActionToken {
    public static void rotate(Game game){
        rotation(game);
    }
    public static void joker(DetectiveToken[] detectiveToken, int[][] positions, Player player){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel détective souhaitez-vous bouger (Toby, Watson, Sherlock)?");
        String token = scanner.next();
        while (!token.equals("Watson") && !token.equals("Toby") && !token.equals("Sherlock")){
            System.out.println("Quel détective souhaitez-vous bouger (Toby, Watson, Sherlock)?");
            token = scanner.next();
        }
        DetectiveToken detective = switch (token) {
            case "Toby" -> detectiveToken[1];
            case "Sherlock" -> detectiveToken[2];
            default -> detectiveToken[0];
        };
        int pos=0;
        while (positions[pos]!= detective.position){
            pos+=1;
        }
        int move=1;
        if (player.isJackPlayer){
            System.out.println("MrJack joue, de combien voulez-vous bouger ?");
            move=scanner.nextInt();
            while (move!=0 && move!=1) {
                System.out.println("De combien voulez-vous bouger ?");
                move=scanner.nextInt();
            }
        }
        if (pos+move==12){
            pos=-1;
        }
        detective.position=positions[pos+move];
    }

    public JokerRotateAction(){
        this.name="JokerRotateAction";
    }

    public void action(Game game, int[][] positions, Player player){
        if (this.isRecto){
            joker(game.board.detectiveTokens, positions, player);
        } else {
            rotate(game);
        }
    }
}

