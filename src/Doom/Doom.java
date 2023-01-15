package Doom;

import Doom.Recources.Move;

import java.util.Scanner;

public class Doom {

    int positionOfPlayer = playfield.indexOf('@');
    final int WIDTH = 41;

    int enemy1 = 182;
    int enemy2 = 283;
    int enemy3 = 310;
    int enemy4 = 363;
    int enemy5 = 372;
    int enemy6 = 434;

    static String playfield = """
            ########################################
            #  #  # #     # #    ###     ###       #
            #  #  # #     # #    ###     ###       #
            #  #  # #     # #    ###     ###       #
            #  #  # ####### # X  ########### S     #
            #  #  #         #    #         #       #
            ####  #         #    ###########     X #
            #     #         #      X               #
            #     ###########                  X   #
            #  X              ######  ##########   #
            #     ##########  #    #X #        #   #
            #     #        #  #    #  #        #   #
            #     #        #  #    #  #        #   #
            #     #        #  #    #  #        #   #
            #@    #        #  #    #  #        #   #
            ########################################
            """;

    public void play() {
        while (true) {
            System.out.println(playfield);
            movePlayer(getInput());
        }
    }

    private void moveEnemy() {

    }

    private void movePlayer(Move move) {
        switch (move) {
            case LEFT -> {
                if (playfield.charAt(positionOfPlayer-1) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return;
                }
                StringBuilder sb = new StringBuilder(playfield);
                sb.setCharAt(positionOfPlayer, ' ');
                sb.setCharAt(positionOfPlayer-1, '@');
                positionOfPlayer--;
                playfield = sb.toString();
            }
            case RIGHT -> {
                if (playfield.charAt(positionOfPlayer+1) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return;
                }
                StringBuilder sb = new StringBuilder(playfield);
                sb.setCharAt(positionOfPlayer, ' ');
                sb.setCharAt(positionOfPlayer+1, '@');
                positionOfPlayer++;
                playfield = sb.toString();
            }
            case FORWARD -> {
                if (playfield.charAt(positionOfPlayer-WIDTH) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return;
                }
                StringBuilder sb = new StringBuilder(playfield);
                sb.setCharAt(positionOfPlayer, ' ');
                sb.setCharAt(positionOfPlayer-WIDTH, '@');
                positionOfPlayer-=WIDTH;
                playfield = sb.toString();
            }
            case BACKWARD -> {
                if (playfield.charAt(positionOfPlayer+WIDTH) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return;
                }
                StringBuilder sb = new StringBuilder(playfield);
                sb.setCharAt(positionOfPlayer, ' ');
                sb.setCharAt(positionOfPlayer+WIDTH, '@');
                positionOfPlayer+=WIDTH;
                playfield = sb.toString();
            }

        }
    }



    private Move getInput() {
        Scanner s = new Scanner(System.in);
        while(true) {
            String input = s.nextLine();
            try {
                return Move.valueOf(input);
            }catch (IllegalArgumentException e) {
                System.out.println("Ungültige Eingabe. Versuchen Sie es erneut");
            }
        }
    }
}
