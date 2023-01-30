import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

public class Snek extends KeyAdapter {
    public ArrayList<SnekPart> body = new ArrayList<>();
    public Direction direction;
    public GamePanel gamePanel;


    Snek(GamePanel gp) {
        this.gamePanel = gp;
        body.add(0, new SnekPart(gp, GamePanel.COLUMNS / 2 * GamePanel.UNIT, GamePanel.ROWS / 2 * GamePanel.UNIT));
    }

    void draw(Graphics g) {
        for (SnekPart part : body) {
            part.draw(g);
        }
    }

    public void eat() {
        Burger borgar = gamePanel.borgar;
        SnekPart head = body.get(body.size() - 1);
        if (borgar.x == head.x && borgar.y == head.y) {
            gamePanel.score++;
            borgar.shuffle();
            increaseSize();
        }
    }

    public boolean isYikes() {
        SnekPart head = body.get(body.size() - 1);
        for (int i = 0; i < body.size() - 1; i++) {
            SnekPart part = body.get(i);
            if (head.x == part.x && head.y == part.y)
                return true;
        }

        return head.x < 0 || head.x > GamePanel.WIDTH-1 || head.y < 0 || head.y > GamePanel.HEIGHT-1;
    }

    public void move() {
        if (direction == null) return;

        for (int i = 0; i < body.size() - 1; i++) {
            SnekPart part = body.get(i);
            SnekPart nextPart = body.get(i + 1);
            part.x = nextPart.x;
            part.y = nextPart.y;
        }
        moveFront();
        eat();
    }

    private void increaseSize() {
        SnekPart tail = body.get(0);

        switch (direction) {
            case UP:
                body.add(0, new SnekPart(gamePanel, tail.x, tail.y + GamePanel.UNIT));
                break;
            case DOWN:
                body.add(0, new SnekPart(gamePanel, tail.x, tail.y - GamePanel.UNIT));
                break;
            case RIGHT:
                body.add(0, new SnekPart(gamePanel, tail.x - GamePanel.UNIT, tail.y));
                break;
            case LEFT:
                body.add(0, new SnekPart(gamePanel, tail.x + GamePanel.UNIT, tail.y));
                break;
        }
    }

    private void moveFront() {
        SnekPart head = body.get(body.size() - 1);
        switch (direction) {
            case UP -> {
                head.y -= GamePanel.UNIT;
            }
            case DOWN -> {
                head.y += GamePanel.UNIT;
            }
            case LEFT -> {
                head.x -= GamePanel.UNIT;
            }
            case RIGHT -> {
                head.x += GamePanel.UNIT;
            }
        }
    }
}
