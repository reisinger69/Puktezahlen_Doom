package Doom.Recources;

public sealed class Wizard extends Enemy permits HardWizard, UndeadWizard {
    public Wizard(int position, boolean dead, char sign) {
        super(position, dead, sign);
    }
}
