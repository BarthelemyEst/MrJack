package Jeu;

import Jeu.ActionTokens.ActionToken;
import Jeu.CharactersRelative.AlibiCard;
import Jeu.CharactersRelative.Character;


public class Board {
    public Cell[][] cells;
    public DetectiveToken[] detectiveTokens;
    public Character[] characters;
    public ActionToken[] actionTokens;
    public AlibiCard[] deck;
    public District[] districts;

    public Board(Cell[][] cells, DetectiveToken[] detectiveTokens, Character[] characters, AlibiCard[] deck, District[] districts, ActionToken[] actionTokens) {
        this.cells = cells;
        this.actionTokens = actionTokens;
        this.districts = districts;
        this.characters = characters;
        this.deck = deck;
        this.detectiveTokens = detectiveTokens;
    }
}