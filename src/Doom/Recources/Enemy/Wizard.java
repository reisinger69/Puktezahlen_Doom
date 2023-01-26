package Doom.Recources.Enemy;

public sealed abstract class Wizard extends Enemy permits HardWizard, UndeadWizard {
    public Wizard(int position, boolean dead, char sign, int damage) {
        super(position, dead, sign, damage);
    }
}
