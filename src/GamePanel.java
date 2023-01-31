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
    static final int TOTAL_WIDTH = WIDTH + INFO_BOARD_WIDTH + 1;
    static final int TOTAL_HEIGHT = HEIGHT + 1;
    Timer updateSchedule;
    private final Image menuBgImg;
    private final Image snekIconImg;
    private final Color menuColor;

    //GameState
    enum state {PLAY, OVER, MENU}

    state gameState;

    //Entities
    Burger borgar;
    Snek snek;

    //Management
    int score;
    int cmdIndex;

    GamePanel() {
        this.setPreferredSize(new Dimension(TOTAL_WIDTH, TOTAL_HEIGHT));
        this.addKeyListener(new KeyAction(this));
        this.gameState = state.MENU;
        this.menuBgImg = new ImageIcon("resources\\snekbg.png").getImage();
        this.snekIconImg = new ImageIcon("resources\\snek-icon.png").getImage();
        menuColor = new Color(0, 102, 0);
        initializeSchedule();
    }

    private void newGame() {
        this.snek = new Snek(this);
        this.borgar = new Burger(this);
        score = 0;
    }

    private void gameOver() {
        updateSchedule.stop();
        newGame();
    }

    private void initializeSchedule() {
        updateSchedule = new Timer(MILLISECONDS_SPEED, e -> update());
        updateSchedule.setRepeats(true);
        updateSchedule.setCoalesce(true);
        updateSchedule.start();
    }

    private void update() {
        if (this.gameState == state.PLAY && snek != null) {
            snek.move();
            if (snek.isYikes()) {
                gameOver();
            }
        }
        repaint();
    }

    public void executeCommand() {
        switch (cmdIndex) {
            case 0 -> startCommand();
            case 1 -> exitCommand();
        }
    }

    private void startCommand() {
        gameState = state.PLAY;
        newGame();
    }

    private void exitCommand() {
        System.exit(0);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (gameState) {
            case PLAY -> paintGame(g);
            case MENU -> paintMenu(g);
        }
    }

    void paintMenu(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(menuColor);
        int y = 5 * UNIT;
        int x;
        g.drawImage(menuBgImg, 0, 0, null);
        //Draw Logo
        x = TOTAL_WIDTH / 2 - snekIconImg.getWidth(null) / 2;
        g2.drawImage(snekIconImg, x, y, null);
        y += UNIT * 7 + 10;
        g.setFont(new Font("Jokerman", Font.BOLD, 70));
        writeToMenu("SNEK", y, g, -1);
        y += UNIT * 3;


        g.setFont(new Font("SHOWCARD GOTHIC", Font.BOLD, 50));
        writeToMenu("START", y, g, 0);
        y += UNIT * 2;
        writeToMenu("QUIT", y, g, 1);


    }

    void paintGame(Graphics g) {
        setBackground(Color.GRAY);
        g.setColor(Color.white);
        paintInfoMenu(g);
        for (int i = 0; i < HEIGHT / UNIT + 1; i++) {
            for (int j = 0; j < WIDTH / UNIT + 1; j++) {
                g.drawLine(i * UNIT, 0, i * UNIT, HEIGHT);
                g.drawLine(0, j * UNIT, WIDTH, j * UNIT);
            }
        }

        borgar.draw(g);
        snek.draw(g);
    }

    private void paintInfoMenu(Graphics g) {
        g.setFont(new Font("Verdana", Font.BOLD, 30));
        int underLineX = writeToInfoMenu("SCORE", 4, (Graphics2D) g) + WIDTH;
        ((Graphics2D) g).setStroke(new BasicStroke(2F));
        g.drawLine(underLineX, 4 * UNIT + 2, underLineX + getFontMetrics((g.getFont())).stringWidth("SCORE"), 4 * UNIT + 2);
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        writeToInfoMenu(String.valueOf(score), 5, (Graphics2D) g);
    }

    private int getCenteredX(String text, Graphics2D g2d, int width) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return width / 2 - length / 2;
    }

    private void writeToMenu(String text, int y, Graphics g, int index) {
        int x = getCenteredX(text, (Graphics2D) g, TOTAL_WIDTH);
        g.drawString(text, x, y);
        if (index == cmdIndex) printArrow(x, y, g);

    }

    private void printArrow(int x, int y, Graphics g) {
        x -= 50;
        g.setFont(new Font("Segoe UI", Font.BOLD, 50));
        g.setColor(Color.DARK_GRAY);
        g.drawString("►", x + 2, y + 2);
        g.setColor(menuColor);
        g.drawString("►", x, y);
        g.setFont(new Font("SHOWCARD GOTHIC", Font.BOLD, 50));
    }

    private int writeToInfoMenu(String text, int rowNum, Graphics2D g) {
        int x = getCenteredX(text, g, INFO_BOARD_WIDTH);
        g.drawString(text, x + WIDTH, UNIT * rowNum);
        return x;
    }
}
