package Jeu.Player;
import Jeu.ActionTokens.ActionToken;
import Jeu.Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Player {
    public int hourGlasses;
    public boolean isJackPlayer;
    public boolean objective;

    public Player(){
    }


    public static void launchTokens(ActionToken[] actionTokens){
        Random random = new Random();
        for (ActionToken actionToken : actionTokens) {
            int a = random.nextInt(2);
            actionToken.isRecto = a != 0;
        }
    }

    public static void returnTokens(ActionToken[] actionTokens){
        for (ActionToken actionToken : actionTokens) {
            actionToken.isRecto = !actionToken.isRecto;
        }
    }

    public static void choseActionToken(Game game,int[][] positions, Player player, ArrayList<ActionToken> actionToken){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel token voulez vous ? : (entre 1 et "+actionToken.size()+")");
        int token = scanner.nextInt();
        if (token > actionToken.size() || token<1){
            System.out.println("Numéro de token invalide");
            token = scanner.nextInt();
        }
        System.out.println();
        System.out.println(actionToken.get(token-1).name);
        actionToken.get(token-1).action(game, positions, player);
        actionToken.remove(token-1);
    }

    public static void changeObjective(Game game){
        if (game.players[1].hourGlasses >= 6){
            game.players[1].objective=true;
        }
        int detec = 1;
        for (int i=0;i<game.board.districts.length;i++){
            if (!game.board.districts[i].character.isJack){
                if (!game.board.districts[i].isRecto){
                    detec=0;
                } else {
                    detec=1;
                    break;
                }
            }
        }
        if (detec==0){
            game.players[0].objective=true;
        }
    }
}
