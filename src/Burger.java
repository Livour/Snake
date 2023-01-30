import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Burger extends Entity {
    private final String path = "resources\\burger.png";
    private Image img;

    public Burger(GamePanel gamePanel) {
        super(gamePanel);
        shuffle();
        img = new ImageIcon(path).getImage();
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
            excludedX.add(snekPart.x);
            excludedY.add(snekPart.y);
        });


        x = randomWithExclusions(excludedX, GamePanel.ROWS)*GamePanel.UNIT;
        y = randomWithExclusions(excludedY, GamePanel.COLUMNS)*GamePanel.UNIT ;
    }

    private int randomWithExclusions(ArrayList exclusions, int maxRange) {
        //The code below is literal garbage, please ignore.
        Random rng = new Random();
        int randNum;
        do {
            randNum = rng.nextInt(maxRange);
        } while (exclusions.contains(randNum));
        return randNum;
    }
}
