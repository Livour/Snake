import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //config
    static final int UNIT = 40;
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int COLUMNS = WIDTH / UNIT;
    static final int ROWS = HEIGHT / UNIT;
    static final int MILLISECONDS_SPEED = 100;
    Timer updateSchedule;

    //entities
    Burger borgar;
    Snek snek;

    //general
    int score;

    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(new KeyAction(this));
        initializeSchedule();
        newGame();
    }

    private void newGame() {
        this.snek = new Snek(this);
        this.borgar = new Burger(this);
        score = 0;
    }

    private void gameOver() {

        newGame();
    }

    private void initializeSchedule() {
        updateSchedule = new Timer(MILLISECONDS_SPEED, e -> update());
        updateSchedule.setRepeats(true);
        updateSchedule.setCoalesce(true);
        updateSchedule.start();
    }

    private void update() {
        snek.move();
        if (snek.isYikes()) {
            gameOver();
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);

        g.setColor(Color.white);

        for (int i = 0; i < HEIGHT / UNIT; i++) {
            for (int j = 0; j < WIDTH / UNIT; j++) {
                g.drawLine(i * UNIT, 0, i * UNIT, HEIGHT);
                g.drawLine(0, j * UNIT, WIDTH, j * UNIT);
            }
        }

        borgar.draw(g);
        snek.draw(g);
    }
}
