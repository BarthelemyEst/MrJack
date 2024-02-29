package Jeu.CharactersRelative;

public class Character {
    public enum alibiName {
        Pink(2),
        Black(0),
        Orange(1),
        Purple(1),
        Green(1),
        Yellow(1),
        Blue(0),
        White(1),
        Grey(1);

        private final int hourglass;

        alibiName(int hourglass) {
            this.hourglass = hourglass;
        }
    }

    public alibiName name;
    public boolean isJack;

    public static int getHourGlass(alibiName alibiName) {
        return alibiName.hourglass;
    }

    public Character(alibiName name, boolean isJack) {
        this.name = name;
        this.isJack = isJack;
    }
}
