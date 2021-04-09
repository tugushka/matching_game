import java.awt.*;
import javax.swing.*;

public class StopwatchPanel extends JPanel {
  JLabel timeLabel;
  int elapsedTime;
  Timer timer;

  StopwatchPanel() {
    timeLabel = new JLabel();
    elapsedTime = 0;

    timer = new Timer(1000, l ->{
      elapsedTime++;
      updateTimeLabel();
    });

    updateTimeLabel();
    // timeLabel.setBounds(100, 100, 200, 100);
    timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
    timeLabel.setBorder(BorderFactory.createBevelBorder(1));
    timeLabel.setOpaque(true);
    timeLabel.setHorizontalAlignment(JTextField.CENTER);
  
    this.add(timeLabel);
      
    this.setSize(100, 100);
    this.setLayout(new FlowLayout());
    this.setVisible(true);
  }

  private void updateTimeLabel() {
    timeLabel.setText(String.format("%02d:%02d", elapsedTime/60, elapsedTime%60));
  }

  public void start() {
    timer.start();
  }

  public void stop() {
    timer.stop();
  }

  public void reset() {
    timer.stop();
    elapsedTime = 0;
    updateTimeLabel();
  }
}