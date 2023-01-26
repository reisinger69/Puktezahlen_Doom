package Doom.Recources.Enemy;

public final class UndeadWizard extends Wizard{
    int damage = 15;

    public UndeadWizard(int position, boolean dead, char sign) {
        super(position, dead, sign, 15);
    }

    @Override
    public boolean fight() {
        return false;
    }

    @Override
    public String toString() {
        return "Du triffst auf einen Undead Wizard: Er macht " + damage + " Schaden";
    }
}
