import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Game extends JFrame {
    final String TITLE = "snek";
    GamePanel gp;

    Game() {
        gp = new GamePanel();
        gp.setFocusable(true);
        add(gp);

        setIconImage(new ImageIcon("resources\\snek-icon.png").getImage());

        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }


}
