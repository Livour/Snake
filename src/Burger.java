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
        Snek snek = this.gp.snek;
        ArrayList<Number> excludedX = new ArrayList<>();
        ArrayList<Number> excludedY = new ArrayList<>();

        snek.body.forEach(snekPart -> {
            excludedX.add(snekPart.x / GamePanel.UNIT);
            excludedY.add(snekPart.y / GamePanel.UNIT);
        });

        do {
            x = randomWithinRange(GamePanel.ROWS) * GamePanel.UNIT;
            y = randomWithinRange(GamePanel.COLUMNS) * GamePanel.UNIT;
        } while (excludedX.contains(x) && excludedY.contains(y));
    }

    private int randomWithinRange(int maxRange) {
        return (int) (Math.random() * (maxRange));
    }
}
