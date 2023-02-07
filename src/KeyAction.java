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

        switch (gamePanel.gameState) {

            case PLAY -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (gamePanel.firstMove != Direction.DOWN) {
                            snek.direction = Direction.UP;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (gamePanel.firstMove != Direction.UP) {
                            snek.direction = Direction.DOWN;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (gamePanel.firstMove != Direction.LEFT) {
                            snek.direction = Direction.RIGHT;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (gamePanel.firstMove != Direction.RIGHT) {
                            snek.direction = Direction.LEFT;
                        }
                        break;
                    default:
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

