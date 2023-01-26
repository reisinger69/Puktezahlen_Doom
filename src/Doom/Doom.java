package Doom;

import Doom.Recources.Enemy.*;
import Doom.Recources.Move;
import Doom.Recources.Weapon.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Doom {

    int positionOfPlayer = playfield.indexOf('@');
    final int WIDTH = 41;

    int playerLives = 100;

    ArrayList<Enemy> enemys = new ArrayList<>();


    static String playfield = """
            ########################################
            #  #  # #     # #    ###     ###       #
            #  #  # #     # #    ###     ###       #
            #  #  # #     # #    ###     ###       #
            #  #  # ####### #    ########### S     #
            #  #  #         #    #         #       #
            ####  #         #    ###########       #
            #     #         #                      #
            #     ###########                      #
            #                 ######  ##########   #
            #     ##########  #    #  #        #   #
            #     #        #  #    #  #        #   #
            #     #        #  #    #  #        #   #
            #     #        #  #    #  #        #   #
            #@    #        #  #    #  #        #   #
            ########################################
            """;

    public void play() {
        enemys.add(new Dwarf(182, false, 'D'));
        enemys.add(new ELF(283, false, 'E'));
        enemys.add(new Rouge(310, false, 'R'));
        enemys.add(new HardWizard(363, false, 'H'));
        enemys.add(new UndeadWizard(372, false, 'U'));

        String infomessage = """
                Dein Ziel ist es zum Schatz zu kommen(S).
                Vermeide Kontakt mit Gegnern: Diese besitzen 4 Lebenspunkte und verursachen verschieden viel Schaden.
                Um die Gegner zu besiegen kannst du zwischen 4 Waffen entscheiden. Je nach Waffe kommt eine unterschiedlich schwierige Matheaufgabe.
                Wenn du diese löst werden dem Gegner entsprechend viele Leben abgezogen.
                Danach ist der Gegner am Zug und fügt dir Schaden zu(außer du besiegst ihn vorher).
                Löst du eine Matheaufgabe nicht korrekt verlierst du sofort! 
                (beim Dividieren wird immer abgerundet auf ganze Zahlen!)""";

        System.out.println(infomessage);
        System.out.println(playfield);

        while (true) {
            System.out.println("Aktuelle Lebensanzahl: " + playerLives);
            printMenu();
            if(movePlayer(getInput())){
                break;
            }
            System.out.println(playfield);
            if (!checkForFight())  {
                    System.out.println("You Loose!");
                    System.exit(0);
            }
                moveAllEnemys();
            System.out.println(playfield);
            if (!checkForFight())  {
                    System.out.println("You Loose!");
                    System.exit(0);
            }
        }

        System.out.println("You win!");
    }


    private void printMenu() {
        String menu = """
                FORWARD     => goes one field Richtung Nie
                RIGHT       => goes one field Richtung Ohne
                BACKWARD    => goes one field Richtung Seife
                LEFT        => goes one field Richtung Waschen
                """;
        System.out.println(menu);
    }

    private boolean checkForFight() { //retursn true when player wins a fight or of there is no enemy
        for (int i = 0; i < enemys.size(); i++) {
            if (!enemys.get(i).isDead()) {
                if (playfield.charAt(enemys.get(i).getPosition() - 1) == '@' || playfield.charAt(enemys.get(i).getPosition() + 1) == '@' || playfield.charAt(enemys.get(i).getPosition() - 41) == '@' || playfield.charAt(enemys.get(i).getPosition() + 41) == '@') {
                    System.out.println(enemys.get(i));
                    return fight(i);
                }
            }
        }
        return true;
    }

    private boolean fight(int positionOfEnemyInArray) {
        StringBuilder sb = new StringBuilder(playfield);
        Enemy e = enemys.get(positionOfEnemyInArray);
        Weapon weapon;
        while (true) {
            weapon = getWeaponToUse();
            if (weapon == null) {
                System.out.println("Falsche Eingabe. Du verlierst!");
                return false;
            }
            if (weapon.fight()) {
                e.reduceLives(weapon.getDamage());
                if (e.isDead()) {
                    e.setDead(true);
                    sb.setCharAt(e.getPosition(), ' ');
                    playfield = sb.toString();
                    System.out.println("Sehr gut! Du hast den Gegner besiegt!");
                    break;
                }
                System.out.println("Der Gegner hat jetzt noch " + e.getLives() + " Leben");
                System.out.println();
                System.out.println("Der Gegner hat dir " + e.getDamage() + " Schaden zugefügt!");
                playerLives-=e.getDamage();
                System.out.println("Aktuelle Leben: " + playerLives);
                if (playerLives <= 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        System.out.println(playfield);
        return true;
    }

    private Weapon getWeaponToUse() {
        Scanner sc =new Scanner(System.in);
        System.out.println("Welche Waffe Wollen sie nutzen?");
        System.out.println("1 - Quotienten-Zepter (-4 Leben)");
        System.out.println("2 - Faktoren-Speer (-3 Leben)" );
        System.out.println("3 - Subtrahenden-Schwert (-2 Leben)");
        System.out.println("4 - Summanden-Dolch (-1 Leben)");
        int input = 0;
        try {
            input = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return null;
        }
        return switch (input) {
            case 1 -> new QuotientenZepter();
            case 2 -> new Faktorenspeer();
            case 3 -> new Subtrahendenschwert();
            case 4 -> new Summandendolch();
            default -> null;
        };
    }

    /*private boolean fight(int positionOfEnemy) {
        System.out.println("Fight!");
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
                        enemys.get(enemys.indexOf(positionOfEnemy)).setDead(true);
                        System.out.println("Right number!");
                        return true;
                    }
                    tries++;
                    System.out.println("Wrong number. Trys left: " + (3-tries));
                }else{
                    System.out.println("Invalid input, please enter a number between 1 and 5");
                }
            }else{
                System.out.println("Invalid input, please enter a number between 1 and 5");
                scanner.next();
            }
        }
        return false;
    } */

    private void moveAllEnemys() {
        for (Enemy enemy : enemys) {
            enemy.setPosition(moveEnemy(enemy));
        }
    }

    private int moveEnemy(Enemy enemy) {
        StringBuilder field = new StringBuilder(playfield);
        int move = (int) (Math.random() * 4) + 1;
        switch (move) {
            case 1 -> {
                if (enemy.isDead()) {
                    return 0;
                } else if (playfield.charAt(enemy.getPosition() - 1) == '#' || playfield.charAt(enemy.getPosition() - 1) == '@' || playfield.charAt(enemy.getPosition() - 1) == 'S') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy.getPosition(), ' ');
                    field.setCharAt(enemy.getPosition() - 1, enemy.getSign());
                    playfield = field.toString();
                    return enemy.getPosition() - 1;
                }
            }
            case 2 -> {
                if (enemy.isDead()) {
                    return 0;
                } else if (playfield.charAt(enemy.getPosition() + 1) == '#' || playfield.charAt(enemy.getPosition() + 1) == '@' || playfield.charAt(enemy.getPosition() + 1) == 'S') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy.getPosition(), ' ');
                    field.setCharAt(enemy.getPosition() + 1, enemy.getSign());
                    playfield = field.toString();
                    return enemy.getPosition() + 1;
                }
            }
            case 3 -> {
                if (enemy.isDead()) {
                    return 0;
                } else if (playfield.charAt(enemy.getPosition() - 41) == '#' || playfield.charAt(enemy.getPosition() - 41) == '@' || playfield.charAt(enemy.getPosition() - 41) == 'S') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy.getPosition(), ' ');
                    field.setCharAt(enemy.getPosition() - 41, enemy.getSign());
                    playfield = field.toString();
                    return enemy.getPosition() - 41;
                }
            }
            case 4 -> {
                if (enemy.isDead()) {
                    return 0;
                } else if (playfield.charAt(enemy.getPosition() + 41) == '#' || playfield.charAt(enemy.getPosition() + 41) == '@' || playfield.charAt(enemy.getPosition() + 41) == 'S') {
                    return moveEnemy(enemy);
                } else {
                    field.setCharAt(enemy.getPosition(), ' ');
                    field.setCharAt(enemy.getPosition() + 41, enemy.getSign());
                    playfield = field.toString();
                    return enemy.getPosition() + 41;
                }
            }
        }
        return 0;
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
                return Move.valueOf(input.toUpperCase());
            }catch (IllegalArgumentException e) {
                System.out.println("Ungültige Eingabe. Versuchen Sie es erneut");
            }
        }
    }
}
