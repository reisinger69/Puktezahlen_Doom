package Doom.Recources;

public class Enemy {
    int position;
    boolean dead;

    public Enemy(int position, boolean dead) {
        this.position = position;
        this.dead = dead;
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
