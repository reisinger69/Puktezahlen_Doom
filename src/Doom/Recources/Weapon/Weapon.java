package Doom.Recources.Weapon;

public abstract sealed class Weapon permits Faktorenspeer, QuotientenZepter, Subtrahendenschwert, Summandendolch {
    int damage;
    public Weapon(int damage) {
        this.damage = damage;
    }

    public abstract boolean fight();

    public int getDamage() {
        return damage;
    }
}
