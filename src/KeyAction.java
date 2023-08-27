import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;

public class KeyAction extends KeyAdapter {
    GamePanel gamePanel;


    KeyAction(GamePanel panel) {
        gamePanel = panel;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (gamePanel.gameState) {
            case PLAY -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> gamePanel.direction = Direction.UP;
                    case KeyEvent.VK_DOWN -> gamePanel.direction = Direction.DOWN;
                    case KeyEvent.VK_RIGHT -> gamePanel.direction = Direction.RIGHT;
                    case KeyEvent.VK_LEFT -> gamePanel.direction = Direction.LEFT;
                    case KeyEvent.VK_ESCAPE -> gamePanel.gameState = GamePanel.state.PAUSED;

                }
            }

            case PAUSED -> {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gamePanel.gameState = GamePanel.state.PLAY;
                }
            }

            case MENU -> {
                {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN -> gamePanel.cmdIndex++;
                        case KeyEvent.VK_UP -> gamePanel.cmdIndex--;
                        case KeyEvent.VK_ENTER -> gamePanel.executeCommand();
                    }

                    if (gamePanel.cmdIndex < 0)
                        gamePanel.cmdIndex = 1;
                    if (gamePanel.cmdIndex > 1)
                        gamePanel.cmdIndex = 0;
                }
            }

            case OVER -> {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    gamePanel.startCommand();
                }
            }
        }
    }

}

