import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Burger extends Entity {
    private final Image img;

    public Burger(GamePanel gamePanel) {
        super(gamePanel);
        shuffle();
        String path = "burger.png";
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource(path))).getImage();
    }

    @Override
    void draw(Graphics g) {
        g.drawImage(img, x, y, GamePanel.UNIT, GamePanel.UNIT, null);
    }

    public void shuffle() {
        do {
            x = randomWithinRange(GamePanel.ROWS) * GamePanel.UNIT;
            y = randomWithinRange(GamePanel.COLUMNS) * GamePanel.UNIT;
        } while (isExcluded(x, y));
    }

    private boolean isExcluded(int x, int y) {
        Snek snek = this.gp.snek;

        for (SnekPart snekPart : snek.body) {
            if (snekPart.y == y && snekPart.x == x)
                return true;
        }
        return false;
    }

    private int randomWithinRange(int maxRange) {
        return (int) (Math.random() * (maxRange));
    }
}
