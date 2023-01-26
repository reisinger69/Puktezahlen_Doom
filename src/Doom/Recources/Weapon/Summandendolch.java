package Doom.Recources.Weapon;

import java.util.Scanner;

public final class Summandendolch extends Weapon{

    public Summandendolch() {
        super(1);
    }

    @Override
    public boolean fight() {
        Scanner sc = new Scanner(System.in);
        int zahl1 = (int) (Math.random()*100);
        int zahl2 = (int) (Math.random()*100);
        int erg = zahl1+zahl2;
        System.out.println("Was ergibt " + zahl1 + "+" + zahl2 + "?");
        String input = sc.nextLine();
        int userErg;
        try {
            userErg = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Falscher Input. Du verlierst");
            return false;
        }
        return userErg == erg;
    }
}
