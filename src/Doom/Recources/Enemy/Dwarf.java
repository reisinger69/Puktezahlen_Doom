package Doom.Recources.Enemy;

public final class Dwarf extends Enemy{

    int damage = 20;

    public Dwarf(int position, boolean dead, char sign) {
        super(position, dead, sign, 20);
    }

    @Override
    public boolean fight() {
        return false;
    }

    @Override
    public String toString() {
        return "Du triffst auf einen Dwarf: Er macht " + damage + " Schaden";
    }
}
