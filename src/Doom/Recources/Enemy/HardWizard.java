package Doom.Recources.Enemy;

public non-sealed class HardWizard extends Wizard{
    int damage = 15;

    public HardWizard(int position, boolean dead, char sign) {
        super(position, dead, sign, 15);
    }

    @Override
    public boolean fight() {
        return false;
    }

    @Override
    public String toString() {
        return "Du triffst auf einen Hard Wizard: Er macht " + damage + " Schaden";
    }
}
