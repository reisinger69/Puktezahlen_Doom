package Doom.Recources.Enemy;

public final class Rouge extends Enemy{
    int damage = 10;

    public Rouge(int position, boolean dead, char sign) {
        super(position, dead, sign, 10);
    }

    @Override
    public boolean fight() {
        return false;
    }

    @Override
    public String toString() {
        return "Du triffst auf einen Rouge: Er macht " + damage + " Schaden";
    }
}
