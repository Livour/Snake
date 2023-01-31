import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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
        excludedX.add(this.x);
        excludedY.add(this.y);

        snek.body.forEach(snekPart -> {
            excludedX.add(snekPart.x/GamePanel.UNIT);
            excludedY.add(snekPart.y/GamePanel.UNIT);
        });


        x = randomWithExclusions(excludedX, GamePanel.ROWS)*GamePanel.UNIT;
        y = randomWithExclusions(excludedY, GamePanel.COLUMNS)*GamePanel.UNIT ;
    }

    private int randomWithExclusions(ArrayList<Number> exclusions, int maxRange) {
        //The code below is literal garbage, please ignore.
        Random rng = new Random();
        int randNum;
        do {
            randNum = rng.nextInt(maxRange);
        } while (exclusions.contains(randNum));
        return randNum;
    }
}
