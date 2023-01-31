import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Game extends JFrame {
    final String TITLE = "snek";
    GamePanel gp;

    Game() {
        gp = new GamePanel();
        gp.setFocusable(true);

        this.add(gp);

        //General frame config
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("snek-icon.png"))).getImage());
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


}
