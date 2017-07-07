package com.cheng.utils.temp;
import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel JMainPane = new JPanel();
    JTabbedPane jTabbedPane = new JTabbedPane();
    
    private JPanel JPanel1 = new JPanel();
    private JPanel JPanel2 = new JPanel();
    
    private JTextArea jt1 = new JTextArea(6, 6);
    private JTextArea jt2 = new JTextArea(6, 6);
    
    /**
     * 构造函数
     */
     public AboutDialog(){
         setTitle("Scaner");
         setSize(300,200);
         setResizable(false);
         setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE);
         
         Container c = this.getContentPane();
         
         jt1.setSize(260,200);
         jt2.setSize(260,200);
         jt1.setEditable(false);
         jt2.setEditable(false);
         jt1.setLineWrap(true);
         jt2.setLineWrap(true);
         
         jt1.setText("");
         jt1.setFont(new Font("楷体_GB2312", java.awt.Font.BOLD, 13));
         jt2.setText("");
         jt2.setFont(new Font("楷体_GB2312", java.awt.Font.BOLD, 13));
         
         jt1.setForeground(Color.black);
         jt2.setForeground(Color.black);
         
         JPanel1.add(jt1);
         JPanel2.add(jt2);
         
         jTabbedPane.setSize(300,200);
         jTabbedPane.addTab("扫描原理", null, JPanel1, null);
         jTabbedPane.addTab("使用说明", null, JPanel2, null);
         JMainPane.add(jTabbedPane);
         c.add(JMainPane);
         pack();
         this.setVisible(true);
     }
}