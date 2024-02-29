package Jeu;

import Jeu.CharactersRelative.Character;

public class District {
    public enum orientations {North, South, East, West}

    public orientations orientation;
    public Character character;
    public boolean isRecto;

    public District(orientations orientation, Character character, boolean isRecto) {
        this.orientation = orientation;
        this.character = character;
        this.isRecto = isRecto;
    }
}
