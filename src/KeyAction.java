import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAction extends KeyAdapter {
    GamePanel gamePanel;

    KeyAction(GamePanel panel) {
        gamePanel = panel;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Snek snek = gamePanel.snek;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (snek.direction != Direction.DOWN)
                    snek.direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                if (snek.direction != Direction.UP)
                    snek.direction = Direction.DOWN;
                break;
            case KeyEvent.VK_RIGHT:
                if (snek.direction != Direction.LEFT)
                    snek.direction = Direction.RIGHT;
                break;
            case KeyEvent.VK_LEFT:
                if (snek.direction != Direction.RIGHT)
                    snek.direction = Direction.LEFT;
                break;
            default:
        }
    }
}

