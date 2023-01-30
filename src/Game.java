import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    final String TITLE = "snek";
    GamePanel gp;

    Game() {
        gp = new GamePanel();
        gp.setFocusable(true);

        this.add(gp);

        //General frame config
        setIconImage(new ImageIcon("resources\\snek-icon.png").getImage());
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


}
