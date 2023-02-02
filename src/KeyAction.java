import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAction extends KeyAdapter {
    GamePanel gamePanel;
    private Timer movementTimer;

    KeyAction(GamePanel panel) {
        gamePanel = panel;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Snek snek = gamePanel.snek;
        if (gamePanel.gameState == GamePanel.state.PLAY && gamePanel.canMove)
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (snek.direction != Direction.DOWN) {
                        gamePanel.canMove = false;
                        snek.direction = Direction.UP;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snek.direction != Direction.UP) {
                        snek.direction = Direction.DOWN;
                        gamePanel.canMove = false;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snek.direction != Direction.LEFT) {
                        gamePanel.canMove = false;
                        snek.direction = Direction.RIGHT;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (snek.direction != Direction.RIGHT) {
                        gamePanel.canMove = false;
                        snek.direction = Direction.LEFT;
                    }
                    break;
                default:
            }
        startTimer();

        if (gamePanel.gameState == GamePanel.state.MENU) {
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

    private void startTimer() {
        if (movementTimer != null && movementTimer.isRunning())
            movementTimer.stop();

        movementTimer = new Timer(GamePanel.MILLISECONDS_SPEED, e -> {
        });
        movementTimer.start();
    }
}

