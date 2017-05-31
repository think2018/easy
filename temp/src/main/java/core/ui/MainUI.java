package core.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.task.Remind;

public class MainUI extends JFrame
{
  private static final long serialVersionUID = -6520337550844207302L;

  public MainUI(String str)
  {
    super(str);
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(3);
    Container container = getContentPane();
    container.setBackground(Color.GREEN);

    JPanel jPanel = new JPanel();

    FlowLayout flowLayout = new FlowLayout();
    jPanel.setLayout(flowLayout);

    container.add(jPanel);

    JLabel jLabel = new JLabel("用户名:");
    jPanel.add(jLabel);

    setVisible(true);

    init();
  }

  private void init()
  {
    Remind.open();
  }
}