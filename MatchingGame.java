import javax.swing.*;
import java.awt.*;

public class MatchingGame {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Matching Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800,600);
    frame.setBackground(new Color(230, 162, 46));
    frame.setForeground(new Color(230, 162, 46));
    frame.add(new GamePanel());
    frame.setLayout(new FlowLayout());
    frame.setVisible(true);
  }
}
