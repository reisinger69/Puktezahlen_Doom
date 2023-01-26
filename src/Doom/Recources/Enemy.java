package Doom.Recources;

public sealed class Enemy permits Dwarf, ELF, Rouge, Wizard {
    int position;
    boolean dead;
    char sign;

    public Enemy(int position, boolean dead, char sign) {
        this.position = position;
        this.dead = dead;
        this.sign = sign;
    }

    public int getPosition() {
        return position;
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
