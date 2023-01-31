import java.awt.*;

public abstract class Entity {
    public int x;
    public int y;
    public GamePanel gp;

    public Entity(GamePanel gp, int x, int y) {
        this.x = x;
        this.y = y;
        this.gp = gp;
    }

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    abstract void draw(Graphics g);

    @Override
    public String toString() {
        return "Entity{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}