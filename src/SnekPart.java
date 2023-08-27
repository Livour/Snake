import java.awt.*;

public class SnekPart extends Entity {
    public SnekPart(GamePanel gp, int x, int y) {
        super(gp, x, y);
    }

    public SnekPart(GamePanel gp){
        super(gp);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, GamePanel.UNIT + 1, GamePanel.UNIT + 1);
    }
}
