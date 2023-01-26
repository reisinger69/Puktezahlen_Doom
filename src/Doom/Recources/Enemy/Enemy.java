package Doom.Recources.Enemy;

public abstract sealed class Enemy permits Dwarf, ELF, Rouge, Wizard {
    int position;
    boolean dead;
    char sign;
    int lives;
    int damage;

    public Enemy(int position, boolean dead, char sign, int damage) {
        this.position = position;
        this.dead = dead;
        this.sign = sign;
        this.lives = 4;
        this.damage = damage;
    }

    public void reduceLives(int damage) {
        this.lives-=damage;
        if (this.lives <= 0) {
            this.dead = true;
        }
    }


    public abstract boolean fight();

    public int getPosition() {
        return position;
    }

    public int getLives() {
        return lives;
    }

    public int getDamage() {
        return damage;
    }

    public char getSign() {
        return sign;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
