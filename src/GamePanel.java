import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //Config
    static final int UNIT = 40;
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int INFO_BOARD_WIDTH = 200;
    static final int COLUMNS = WIDTH / UNIT;
    static final int ROWS = HEIGHT / UNIT;
    static final int MILLISECONDS_SPEED = 100;
    Timer updateSchedule;

    //Entities
    Burger borgar;
    Snek snek;

    //Management
    int score;

    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH + INFO_BOARD_WIDTH + 1, HEIGHT + 1));
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
        g.setFont(new Font("TimesRoman",Font.BOLD,30));
        writeToMenu("SCORE:", 4, (Graphics2D) g);
        writeToMenu(String.valueOf(score), 5, (Graphics2D) g);
        for (int i = 0; i < HEIGHT / UNIT + 1; i++) {
            for (int j = 0; j < WIDTH / UNIT + 1; j++) {
                g.drawLine(i * UNIT, 0, i * UNIT, HEIGHT);
                g.drawLine(0, j * UNIT, WIDTH, j * UNIT);
            }
        }

        borgar.draw(g);
        snek.draw(g);
    }

    private int getCenteredX(String text, Graphics2D g2d, int width) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return width / 2 - length / 2;
    }

    private void writeToMenu(String text, int rowNum, Graphics2D g) {
        int x = getCenteredX(text, g, INFO_BOARD_WIDTH);
        g.drawString(text, x + WIDTH, UNIT * rowNum);
    }
}
