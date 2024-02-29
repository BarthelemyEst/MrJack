package Jeu.Player;

import Jeu.CharactersRelative.AlibiCard;
import Jeu.CharactersRelative.Character;

import static Jeu.CharactersRelative.AlibiCard.drawCard;

public class MrJackPlayer extends Player{


    public MrJackPlayer(){
        this.isJackPlayer = true;
        this.objective=false;
    }

    public static void drawMrJackCard(AlibiCard[] deck, Character[] characters){
        AlibiCard mrJackCard = drawCard(deck);
        System.out.println("Pour le Player MrJack, le personnage MrJack est : " + mrJackCard.character.name);
        for (int i=0; i<characters.length;i++){
            if (characters[i].name == mrJackCard.character.name){
                characters[i].isJack=true;
                break;
            }
        }
    }
}
