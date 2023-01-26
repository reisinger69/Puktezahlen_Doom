package Doom.Recources.Enemy;

public non-sealed class ELF extends Enemy{

    public ELF(int position, boolean dead, char sign) {
        super(position, dead, sign, 5);
    }

    @Override
    public boolean fight() {
        return false;
    }

    @Override
    public String toString() {
        return "Du triffst auf einen Elf: Er macht " + damage + " Schaden";
    }
}
