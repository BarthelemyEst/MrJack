package Jeu;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

import Jeu.ActionTokens.ActionToken;
import Jeu.CharactersRelative.Character;
import Jeu.Player.Player;

import javax.swing.*;

import static Jeu.Cell.exchange;

import static Jeu.DetectiveToken.*;
import static Jeu.Player.MrJackPlayer.*;

public class Game {
    public Board board;
    public Player[] players;
    public int[][] positions;

    public Game(Board board, Player[] players, int[][] positions) {
        this.board = board;
        this.players = players;
        this.positions = positions;
    }

    public static void shuffle(Game game) {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            exchange(game.board.cells[random.nextInt(3) + 1][random.nextInt(3) + 1], game.board.cells[random.nextInt(3) + 1][random.nextInt(3) + 1]);
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                int ori = random.nextInt(4);
                switch (ori) {
                    case 0 -> game.board.cells[i][j].district.orientation = District.orientations.North;
                    case 1 -> game.board.cells[i][j].district.orientation = District.orientations.South;
                    case 2 -> game.board.cells[i][j].district.orientation = District.orientations.West;
                    default -> game.board.cells[i][j].district.orientation = District.orientations.East;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            int pos = random.nextInt(12);
            game.board.detectiveTokens[i].position = game.positions[pos];
        }
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {
        }
    }

    public static void skip() {
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static JPanel majActionToken(JFrame frame, JPanel oldActionIconPanel, JPanel alibiActionPanel, ArrayList<ActionToken> tokens) {
        alibiActionPanel.remove(oldActionIconPanel);
        JPanel actionIconPanel = new JPanel();
        actionIconPanel.setPreferredSize(new Dimension(560, 140));
        actionIconPanel.setLayout((new GridLayout(1, 4)));
        String n;
        for (ActionToken token : tokens) {
            JButton actionIcon = new JButton();
            if (token.isRecto) {
                n = "_Recto.png";
            } else {
                n = "_Verso.png";
            }
            actionIcon.setIcon(new ImageIcon("Images/Action Token/" + token.name + n));
            actionIconPanel.add(actionIcon);
        }
        alibiActionPanel.add(actionIconPanel, BorderLayout.NORTH);
        frame.add(alibiActionPanel, BorderLayout.LINE_START);
        frame.setVisible(true);
        return actionIconPanel;
    }

    public static JPanel majTable(Game game, int[][] grid, JFrame frame, JPanel oldBoardPanel) {
        frame.remove(oldBoardPanel);
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(720, 720));
        boardPanel.setLayout(new GridLayout(5, 5));


        for (int i = 0; i < 26; i++) {
            String n = "Images/25";
            if ((i == 1) || (i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 0) || (i == 9) || (i == 10) || (i == 14) || (i == 15) || (i == 19) || (i == 20) || (i == 21) || (i == 22) || (i == 23) || (i == 24)) {
                for (int j = 0; j < 3; j++) {
                    if (game.board.detectiveTokens[j].position[0] == grid[i][0] && game.board.detectiveTokens[j].position[1] == grid[i][1]) {
                        switch (game.board.detectiveTokens[j].name) {
                            case Watson -> n = "Images/DetectiveTokens/Watson";
                            case Toby -> {
                                n = "Images/DetectiveTokens/Toby";
                                if (game.board.detectiveTokens[0].position == game.board.detectiveTokens[1].position) {
                                    n = "Images/DetectiveTokens/Toby_Watson";
                                }
                            }
                            case Sherlock -> {
                                n = "Images/DetectiveTokens/Sherlock";
                                if (game.board.detectiveTokens[0].position == game.board.detectiveTokens[2].position && game.board.detectiveTokens[1].position == game.board.detectiveTokens[2].position) {
                                    n = "Images/DetectiveTokens/Three";
                                } else if (game.board.detectiveTokens[1].position == game.board.detectiveTokens[2].position) {
                                    n = "Images/DetectiveTokens/Sherlock_Toby";
                                } else if (game.board.detectiveTokens[0].position == game.board.detectiveTokens[2].position) {
                                    n = "Images/DetectiveTokens/Sherlock_Watson";
                                }
                            }
                        }
                    }
                }
                JButton token = new JButton();
                token.setIcon(new ImageIcon(n + ".png"));
                token.setPreferredSize(new Dimension(10, 10));
                boardPanel.add(token);
            } else if ((i == 6) || (i == 7) || (i == 8) || (i == 11) || (i == 12) || (i == 13) || (i == 16) || (i == 17) || (i == 18)) {

                n = "0";
                for (int j = 1; j < 4; j++) {
                    for (int k = 1; k < 4; k++) {
                        if (game.board.cells[j][k].positions[0] == grid[i][0] && game.board.cells[j][k].positions[1] == grid[i][1]) {
                            if (!game.board.cells[j][k].district.isRecto) {
                                if (game.board.cells[j][k].district.character.name.equals(Character.alibiName.Grey)) {
                                    n = "Images/38";
                                } else if (game.board.cells[j][k].district.orientation == District.orientations.North) {
                                    n = "Images/Verso/Verso_North";
                                } else if (game.board.cells[j][k].district.orientation == District.orientations.South) {
                                    n = "Images/Verso/Verso_South";
                                } else if (game.board.cells[j][k].district.orientation == District.orientations.East) {
                                    n = "Images/Verso/Verso_East";
                                } else {
                                    n = "Images/Verso/Verso_West";
                                }
                            } else if (game.board.cells[j][k].district.orientation == District.orientations.North) {
                                n = "Images/" + game.board.cells[j][k].district.character.name + "/" + game.board.cells[j][k].district.character.name + "_North";
                            } else if (game.board.cells[j][k].district.orientation == District.orientations.South) {
                                n = "Images/" + game.board.cells[j][k].district.character.name + "/" + game.board.cells[j][k].district.character.name + "_South";
                            } else if (game.board.cells[j][k].district.orientation == District.orientations.East) {
                                n = "Images/" + game.board.cells[j][k].district.character.name + "/" + game.board.cells[j][k].district.character.name + "_East";
                            } else {
                                n = "Images/" + game.board.cells[j][k].district.character.name + "/" + game.board.cells[j][k].district.character.name + "_West";
                            }
                        }
                    }
                }

                JButton districtIcon = new JButton();
                districtIcon.setIcon(new ImageIcon(n + ".png"));
                boardPanel.add(districtIcon);
            }
        }
        frame.add(boardPanel);
        frame.setVisible(true);
        return (boardPanel);
    }

    public static void startGame(Game game) {
        drawMrJackCard(game.board.deck, game.board.characters);
        int tour = 0;
        sleep(500);
        skip();
        shuffle(game);

        JFrame frame = new JFrame("MrJackPocket");
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.pack();

        // Creation fenêtre de gauche pour les cartes alibis et les actions pour le tour du detective
        JPanel alibiActionPanel = new JPanel();
        alibiActionPanel.setPreferredSize(new Dimension(560, 720));
        frame.add(alibiActionPanel, BorderLayout.LINE_START);

        int[][] grid = new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}};

        JPanel oldActionIconPanel = new JPanel();
        JPanel oldBoardPanel = new JPanel();
        oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
        int race = 0;

        //Traque
        while (tour < 8 && ((!game.players[0].objective && !game.players[1].objective) || (game.players[0].objective && game.players[1].objective))) {
            tour += 1;
            System.out.println("Tour : " + tour);
            if (tour % 2 == 1) {
                System.out.println("Tour impair, le détective choisit en premier");
                //Le détective joue en premier
                launchTokens(game.board.actionTokens);
                ArrayList<ActionToken> tokens = new ArrayList<>();
                Collections.addAll(tokens, game.board.actionTokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                /* Le détective choisit un token */
                sleep(500);
                choseActionToken(game, game.positions, game.players[0], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
                //MrJack choisit deux tokens
                sleep(500);
                skip();
                choseActionToken(game, game.positions, game.players[1], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
                sleep(500);
                skip();
                choseActionToken(game, game.positions, game.players[1], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
                //Le détective choisit un token
                sleep(500);
                skip();
                choseActionToken(game, game.positions, game.players[0], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
            } else {
                System.out.println("Tour pair, le MrJack choisit en premier");
                //MrJack joue en premier
                returnTokens(game.board.actionTokens);
                ArrayList<ActionToken> tokens = new ArrayList<>();
                Collections.addAll(tokens, game.board.actionTokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                //MrJack choisit
                sleep(500);
                choseActionToken(game, game.positions, game.players[1], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
                //Détective en prend 2
                sleep(500);
                skip();
                choseActionToken(game, game.positions, game.players[0], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
                sleep(500);
                skip();
                choseActionToken(game, game.positions, game.players[0], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
                oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
                //MrJack choisit
                sleep(500);
                skip();
                choseActionToken(game, game.positions, game.players[1], tokens);
                oldActionIconPanel = majActionToken(frame, oldActionIconPanel, alibiActionPanel, tokens);
            }
            oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
            sleep(500);
            skip();
            Character[] visibleWatson = visibility(game.board.detectiveTokens[0], game.board.cells);
            Character[] visibleToby = visibility(game.board.detectiveTokens[1], game.board.cells);
            Character[] visibleSherlock = visibility(game.board.detectiveTokens[2], game.board.cells);
            ArrayList<Character> visible = Arrays.stream(visibleWatson).distinct().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
            Arrays.stream(visibleToby).filter(character -> !visible.contains(character)).filter(Objects::nonNull).forEach(visible::add);
            Arrays.stream(visibleSherlock).filter(character -> !visible.contains(character)).filter(Objects::nonNull).forEach(visible::add);
            isJackIn(game, visible);
            sleep(500);
            skip();
            oldBoardPanel = majTable(game, grid, frame, oldBoardPanel);
            System.out.println("nombre de sabliers (pour le MrJack) : " + game.players[1].hourGlasses);
            changeObjective(game);
            sleep(500);
            skip();
            skip();

            if (game.players[0].objective != game.players[1].objective) {
                if (game.players[0].objective) {
                    System.out.println("Le Détective gagne");
                } else {
                    System.out.println("MrJack gagne");
                }
            }

            if (tour <= 8 && game.players[0].objective && game.players[1].objective) {
                for (int i = 1; i < 4; i++) {
                    for (int j = 1; j < 4; j++) {
                        if (game.board.cells[i][j].district.character.isJack) {
                            if (visible.contains(game.board.cells[i][j].district.character)) {
                                System.out.println("Le détective gagne");
                                game.players[1].objective = false;
                            } else if (race == 0) {
                                System.out.println("la traque commence !");
                                race = 1;
                            }
                        }
                    }
                }
            }

            if (tour == 8 && game.players[0].objective && game.players[1].objective) {
                for (int i = 1; i < 4; i++) {
                    for (int j = 1; j < 4; j++) {
                        if (game.board.cells[i][j].district.character.isJack) {
                            if (visible.contains(game.board.cells[i][j].district.character)) {
                                System.out.println("Le détective gagne");
                                game.players[1].objective = false;
                            } else {
                                System.out.println("MrJack gagne car il reste caché");
                            }
                        }
                    }
                }
            }

            if (tour == 8 && !game.players[0].objective && game.players[1].objective) {
                System.out.println("MrJack gagne");
            }
        }
        //Fermeture interface graphique
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}