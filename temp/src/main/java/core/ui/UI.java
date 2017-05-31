package core.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame
  implements ActionListener
{
  private static final long serialVersionUID = -1892671311186980965L;
  JLabel pattern;
  JLabel allInfo;
  JLabel toBeInfo;
  JTextField patternString;
  JButton submit;
  JTextArea allInfoArea;
  JTextArea result;
  GridBagLayout g = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();

  UI(String str)
  {
    super(str);
    setSize(500, 450);
    setDefaultCloseOperation(3);
    setLayout(this.g);
    setResizable(false);

    addComponent();
    this.submit.addActionListener(this);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  public void addComponent()
  {
    this.pattern = new JLabel("过虑规则:");
    this.c.insets = new Insets(0, 0, 10, 0);
    add(this.g, this.c, this.pattern, 0, 0, 1, 1);

    this.patternString = new JTextField(34);
    this.patternString.setText("*.*");
    this.c.insets = new Insets(-381, 60, 0, 0);
    add(this.g, this.c, this.patternString, 0, 0, 0, 0);

    this.allInfo = new JLabel("所有信息:");

    this.c.insets = new Insets(0, 0, 10, 0);
    add(this.g, this.c, this.allInfo, 0, 1, 1, 1);

    this.allInfoArea = new JTextArea(8, 40);
    this.allInfoArea.setText("all info");
    this.allInfoArea.setLineWrap(true);
    JScrollPane scrollByAllInfo = new JScrollPane(this.allInfoArea);
    add(this.g, this.c, scrollByAllInfo, 0, 2, 3, 3);

    this.submit = new JButton("开始");
    this.c.insets = new Insets(10, 200, 0, 0);
    add(this.g, this.c, this.submit, 0, 6, 1, 1);

    this.toBeInfo = new JLabel("结果:");
    this.c.insets = new Insets(0, 0, 10, 0);
    add(this.g, this.c, this.toBeInfo, 0, 7, 1, 1);

    this.result = new JTextArea(8, 40);
    this.result.setText("result");
    this.result.setLineWrap(true);
    JScrollPane scrollByResult = new JScrollPane(this.result);
    add(this.g, this.c, scrollByResult, 0, 8, 3, 3);
  }

  public void add(GridBagLayout g, GridBagConstraints c, JComponent jc, int x, int y, int gw, int gh)
  {
    c.gridx = x;
    c.gridy = y;
    c.anchor = 17;
    c.gridwidth = gw;
    c.gridheight = gh;
    g.setConstraints(jc, c);
    add(jc);
  }

  public void actionPerformed(ActionEvent arg0)
  {
    String allInfoTxt = this.allInfoArea.getText();
    String patternTxt = this.patternString.getText();
    this.result.setText(allInfoTxt + "\n" + patternTxt);
  }

  public static void main(String[] args)
  {
    new UI("信息过虑器");
  }
}