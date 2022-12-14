import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final int UNIT = 40;
    private final int WIDTH = 800;
    private final int HEIGHT = 800;

    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);

        g.setColor(Color.white);

        for (int i = 0; i < HEIGHT / UNIT; i++) {
            for (int j = 0; j < WIDTH / UNIT; j++) {
                g.drawLine(i * UNIT, 0, i * UNIT, HEIGHT);
                g.drawLine(0,j*UNIT,WIDTH,j*UNIT);
            }
        }


    }
}
