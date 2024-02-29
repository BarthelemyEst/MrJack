package Jeu;

import java.util.ArrayList;

import Jeu.CharactersRelative.Character;

import static Jeu.Game.skip;

public class DetectiveToken {
    public enum detectiveNames {Watson, Sherlock, Toby}

    public int[] position;
    public detectiveNames name;

    public DetectiveToken(detectiveNames name, int[] position) {
        this.name = name;
        this.position = position;
    }

    public static Character[] visibility(DetectiveToken detectiveToken, Cell[][] cells) {
        Character[] visible = new Character[3];
        if (detectiveToken.position[0] == 0) {
            int column = detectiveToken.position[1];
            for (int i = 1; i < 4; i++) {
                if (cells[i][column].district.orientation != District.orientations.South) {
                    if (cells[i][column].district.isRecto) {
                        visible[i - 1] = cells[i][column].district.character;
                    }
                    if (cells[i][column].district.orientation == District.orientations.North) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (detectiveToken.position[0] == 4) {
            int column = detectiveToken.position[1];
            for (int i = 3; i > 0; i--) {
                if (cells[i][column].district.orientation != District.orientations.North) {
                    if (cells[i][column].district.isRecto) {
                        visible[3 - i] = cells[i][column].district.character;
                    }
                    if (cells[i][column].district.orientation == District.orientations.South) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (detectiveToken.position[1] == 0) {
            int line = detectiveToken.position[0];
            for (int i = 1; i < 4; i++) {
                if (cells[line][i].district.orientation != District.orientations.East) {
                    if (cells[line][i].district.isRecto) {
                        visible[i - 1] = cells[line][i].district.character;
                    }
                    if (cells[line][i].district.orientation == District.orientations.West) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } else {
            int line = detectiveToken.position[0];
            for (int i = 3; i > 0; i--) {
                if (cells[line][i].district.orientation != District.orientations.West) {
                    if (cells[line][i].district.isRecto) {
                        visible[3 - i] = cells[line][i].district.character;
                    }
                    if (cells[line][i].district.orientation == District.orientations.East) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return visible;
    }

    public static void isJackIn(Game game, ArrayList<Character> visible) {
        System.out.println("Voici la liste des personnages visibles par les détectives : ");
        for (Character character : visible) {
            System.out.println(character.name);
        }
        skip();
        int test = 0;
        for (int i = 0; i < visible.size(); i++) {
            if (visible.get(i).isJack) {
                test += 1;
                System.out.println("MrJack est visible");
                for (int j = 1; j < 4; j++) {
                    for (int k = 1; k < 4; k++) {
                        game.board.cells[j][k].district.isRecto = false;
                    }
                }
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (visible.contains(game.board.cells[j + 1][k + 1].district.character)) {
                            game.board.cells[j + 1][k + 1].district.isRecto = true;
                        }
                    }
                }
                if (!visible.contains(game.board.characters[8])) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            if (game.board.cells[j + 1][k + 1].district.character.name == Character.alibiName.Grey) {
                                game.board.cells[j + 1][k + 1].district.orientation = null;
                            }
                        }
                    }
                }
            }
        }
        if (test == 0) {
            game.players[1].hourGlasses += 1;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (visible.contains(game.board.cells[j + 1][k + 1].district.character)) {
                        game.board.cells[j + 1][k + 1].district.isRecto = false;
                        if (game.board.cells[j + 1][k + 1].district.character.name.equals(Character.alibiName.Grey)) {
                            game.board.cells[j + 1][k + 1].district.orientation = null;
                        }
                    }
                }
            }
        }
    }
}
