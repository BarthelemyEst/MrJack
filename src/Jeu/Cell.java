package Jeu;

import java.util.Scanner;

public class Cell {
    int[] positions;
    District district;


    public Cell(District district, int[] positions) {
        this.district = district;
        this.positions = positions;
    }

    public static void exchange(Cell Depart, Cell End) {
        District exchange = End.district;
        End.district = Depart.district;
        Depart.district = exchange;
    }

    public static void rotation(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Posx");
        int posx = scanner.nextInt();
        System.out.println("Posy");
        int posy = scanner.nextInt();
        while (posx < 1 || posx > 3 || posy < 1 || posy > 3) {
            System.out.println("Erreur dans le choix de la cellule (hors board)");
            System.out.println("Posx");
            posx = scanner.nextInt();
            System.out.println("Posy");
            posy = scanner.nextInt();
        }
        int num = 0;
        while (num == 0) {
            System.out.println("Orientation (North, South, East, West)");
            String ori = scanner.next();
            District.orientations orientation = game.board.cells[posx][posy].district.orientation;
            while ((!ori.equals("North")) && (!ori.equals("South")) & (!ori.equals("East")) && (!ori.equals("West"))) {
                System.out.println("Ce n'est pas une orientation valide : ");
                ori = scanner.next();
            }
            switch (ori) {
                case "North" -> orientation = District.orientations.North;
                case "South" -> orientation = District.orientations.South;
                case "East" -> orientation = District.orientations.East;
                case "West" -> orientation = District.orientations.West;
            }
            if (game.board.cells[posx][posy].district.orientation == null) {
                num = 1;
            } else if (orientation != game.board.cells[posx][posy].district.orientation) {
                game.board.cells[posx][posy].district.orientation = orientation;
                num = 1;
            } else {
                System.out.println("Déjà cette orientation");
            }
        }
    }
}
