package Jeu.CharactersRelative;


import java.util.Random;

import static Jeu.CharactersRelative.Character.*;

public class AlibiCard {
    public Character character;
    public int hourGlass;
    public boolean isDrawn;

    public AlibiCard(Character character) {
        this.hourGlass = getHourGlass(character.name);
        this.isDrawn = false;
        this.character = character;
    }

    static Random random = new Random();

    public static AlibiCard drawCard(AlibiCard[] cards) {
        int cardNum = random.nextInt(cards.length);
        AlibiCard card = cards[cardNum];
        while (card.isDrawn) {
            cardNum = random.nextInt(cards.length);
            card = cards[cardNum];
        }
        card.isDrawn = true;
        return card;
    }
}
