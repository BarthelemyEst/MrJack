package Jeu.ActionTokens;

import Jeu.Game;
import Jeu.Player.Player;

public class ActionToken {
    public boolean isRecto;
    public String name;

    public ActionToken() {
        this.isRecto = true;
    }

    public void action(Game game, int[][] positions, Player player){ // héritage
    }
}

