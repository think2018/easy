package core.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemindUI extends JFrame
{
  private static final long serialVersionUID = -1892671311186980965L;

  public RemindUI()
  {
    setSize(300, 200);
    setUndecorated(true);
    setAlwaysOnTop(true);
    setDefaultCloseOperation(3);

    Container container = getContentPane();
    container.setBackground(Color.GREEN);

    JPanel jPanel = new JPanel();

    FlowLayout flowLayout = new FlowLayout();
    jPanel.setLayout(flowLayout);

    container.add(jPanel);

    JLabel jLabel = new JLabel("1.每天5点半的提醒，想想还有什么事");
    jLabel.setLocation(700, 0);
    jLabel.setBackground(Color.BLACK);
    jPanel.add(jLabel);

    setVisible(true);
  }

  public RemindUI(String str)
  {
    super(str);
    setSize(300, 200);

    Container container = getContentPane();

    JPanel jPanel = new JPanel();

    FlowLayout flowLayout = new FlowLayout();
    jPanel.setLayout(flowLayout);

    container.add(jPanel);

    JLabel jLabel = new JLabel("用户名:");
    jPanel.add(jLabel);

    int windowWidth = getWidth();
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenWidth = screenSize.width;
    setLocation(screenWidth - windowWidth, 0);
    setUndecorated(true);
    setAlwaysOnTop(true);
    container.setBackground(Color.GREEN);
    setVisible(true);
    try
    {
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    dispose();
  }
}