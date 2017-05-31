

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Temp
{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame();
    frame.setSize(300, 200);
    int windowWidth = frame.getWidth();
    int windowHeight = frame.getHeight();
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    frame.setLocation(screenWidth - windowWidth, 0);
    frame.setUndecorated(true);

    JPanel panel = new JPanel();
    JLabel label = new JLabel("显示一段文字");

    frame.add(label);

    frame.setVisible(true);
  }
}