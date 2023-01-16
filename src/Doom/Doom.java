package Doom;

import Doom.Recources.Move;

import java.util.Random;
import java.util.Scanner;

public class Doom {

    int positionOfPlayer = playfield.indexOf('@');
    final int WIDTH = 41;

    int playerLives = 3;

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
            if(movePlayer(getInput())){
                break;
            }
            if (!checkForFight())  {
                playerLives--;
                if (playerLives == 0) {
                    System.out.println("You Loose!");
                    System.exit(0);
                }
            }
            checkForFight();
            moveAllEnemys();
        }

        System.out.println("You win!");
    }

    private boolean checkForFight() { //retursn true when player wins a fight or of there is no enemy
        if (playfield.charAt(positionOfPlayer-1) == 'X') {
            return fight(positionOfPlayer-1);
        } else if(playfield.charAt(positionOfPlayer+1) == 'X') {
            return fight(positionOfPlayer+1);
        } else if(playfield.charAt(positionOfPlayer+41) == 'X') {
            return fight(positionOfPlayer+41);
        } else if(playfield.charAt(positionOfPlayer-41) == 'X') {
            return fight(positionOfPlayer-41);
        } else {
            return true;
        }
    }

    private boolean fight(int positionOfEnemy) {
        StringBuilder sb = new StringBuilder(playfield);
        Random rand = new Random();
        int randomNum = rand.nextInt(5) + 1;
        Scanner scanner = new Scanner(System.in);
        int guess;
        int tries = 0;
        while (tries < 3) {
            System.out.println("Guess a number between 1 and 5:");
            if(scanner.hasNextInt()){
                guess = scanner.nextInt();
                if(guess > 0 && guess < 6){
                    if (guess == randomNum) {
                        sb.setCharAt(positionOfEnemy, ' ');
                        playfield = sb.toString();
                        return true;
                    }
                    tries++;
                }else{
                    System.out.println("Invalid input, please enter a number between 1 and 5");
                }
            }else{
                System.out.println("Invalid input, please enter a number between 1 and 5");
                scanner.next();
            }
        }
        return false;
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
                if (enemy == 0) {
                    return 0;
                } else if (playfield.charAt(enemy-1) == '#'  || playfield.charAt(enemy-1) == '@' || playfield.charAt(enemy-1) == 'S') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy, ' ');
                    field.setCharAt(enemy - 1, 'X');
                    playfield = field.toString();
                    return enemy-1;
                }
            }
            case 2 -> {
                if (enemy == 0) {
                    return 0;
                } else if (playfield.charAt(enemy+1) == '#' || playfield.charAt(enemy+1) == '@' || playfield.charAt(enemy+1) == 'S') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy, ' ');
                    field.setCharAt(enemy + 1, 'X');
                    playfield = field.toString();
                    return enemy+1;
                }
            }
            case 3 -> {
                if (enemy == 0) {
                    return 0;
                } else if (playfield.charAt(enemy-41) == '#'  || playfield.charAt(enemy-41) == '@' || playfield.charAt(enemy-41) == 'S') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy, ' ');
                    field.setCharAt(enemy - 41, 'X');
                    playfield = field.toString();
                    return enemy - 41;
                }
            }
            case 4 -> {
                if (enemy == 0) {
                    return 0;
                } else if (playfield.charAt(enemy+41) == '#'  || playfield.charAt(enemy+41) == '@' || playfield.charAt(enemy+41) == 'S') {
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

    private void updatePosition(int positionToMove) {
        StringBuilder sb = new StringBuilder(playfield);
        sb.setCharAt(positionOfPlayer, ' ');
        sb.setCharAt(positionToMove, '@');
        positionOfPlayer = positionOfPlayer-(positionOfPlayer-positionToMove);
        playfield = sb.toString();
    }

    private boolean movePlayer(Move move) {
        switch (move) {
            case LEFT -> {
                if (playfield.charAt(positionOfPlayer-1) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return false;
                } else if(playfield.charAt(positionOfPlayer-1) == 'S') {
                    updatePosition(positionOfPlayer-1);
                    return true;
                }
                updatePosition(positionOfPlayer-1);

            }
            case RIGHT -> {
                if (playfield.charAt(positionOfPlayer+1) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return false;
                }else if(playfield.charAt(positionOfPlayer+1) == 'S') {
                    updatePosition(positionOfPlayer+1);
                    return true;
                }
                updatePosition(positionOfPlayer+1);
            }
            case FORWARD -> {
                if (playfield.charAt(positionOfPlayer-WIDTH) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return false;
                }else if(playfield.charAt(positionOfPlayer-WIDTH) == 'S') {
                    updatePosition(positionOfPlayer-WIDTH);
                    return true;
                }
                updatePosition(positionOfPlayer-WIDTH);
            }
            case BACKWARD -> {
                if (playfield.charAt(positionOfPlayer+WIDTH) == '#') {
                    System.out.println("Hier geht es nicht weiter"); // Wand gefunden
                    return false;
                }else if(playfield.charAt(positionOfPlayer+WIDTH) == 'S') {
                    updatePosition(positionOfPlayer+WIDTH);
                    return true;
                }
                updatePosition(positionOfPlayer+WIDTH);
            }

        }
        return false;
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
