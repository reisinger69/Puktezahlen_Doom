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
            moveAllEnemys();
        }
    }

    private void moveAllEnemys() {
        System.out.println(enemy1);
        enemy1 = moveEnemy(enemy1);
        enemy2 = moveEnemy(enemy2);
        enemy3 = moveEnemy(enemy3);
        enemy4 = moveEnemy(enemy4);
        enemy5 = moveEnemy(enemy5);
        enemy6 = moveEnemy(enemy6);

    }

    private int moveEnemy(int enemy) {
        StringBuilder field = new StringBuilder(playfield);
        int move = (int)(Math.random() * 4) + 1;
        switch (move) {
            case 1 -> {
                if (playfield.charAt(enemy-1) == '#') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy, ' ');
                    field.setCharAt(enemy - 1, 'X');
                    playfield = field.toString();
                    return enemy-1;
                }
            }
            case 2 -> {
                if (playfield.charAt(enemy+1) == '#') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy, ' ');
                    field.setCharAt(enemy + 1, 'X');
                    playfield = field.toString();
                    return enemy+1;
                }
            }
            case 3 -> {
                if (playfield.charAt(enemy-41) == '#') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy, ' ');
                    field.setCharAt(enemy - 41, 'X');
                    playfield = field.toString();
                    return enemy - 41;
                }
            }
            case 4 -> {
                if (playfield.charAt(enemy+41) == '#') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy, ' ');
                    field.setCharAt(enemy + 41, 'X');
                    playfield = field.toString();
                    return enemy + 41;
                }
            }
        }
        return enemy;
    }

    private void movePlayer(Move move) {
        switch (move) {
            case LEFT -> {
                if (playfield.charAt(positionOfPlayer-1) == '#' || playfield.charAt(positionOfPlayer-1) == '@' || playfield.charAt(positionOfPlayer-1) == 'S') {
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
                if (playfield.charAt(positionOfPlayer+1) == '#' || playfield.charAt(positionOfPlayer-1) == '@' || playfield.charAt(positionOfPlayer-1) == 'S') {
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
                if (playfield.charAt(positionOfPlayer-WIDTH) == '#' || playfield.charAt(positionOfPlayer-1) == '@' || playfield.charAt(positionOfPlayer-1) == 'S') {
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
                if (playfield.charAt(positionOfPlayer+WIDTH) == '#' || playfield.charAt(positionOfPlayer-1) == '@' || playfield.charAt(positionOfPlayer-1) == 'S') {
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
