package Doom;

import Doom.Recources.Move;

import java.util.Scanner;

public class Doom {

    int positionOfPlayer = playfield.indexOf('@');
    final int WIDTH = 40;

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

    public void movePlayer(Move move) {
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
