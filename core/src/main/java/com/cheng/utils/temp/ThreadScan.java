package com.cheng.utils.temp;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * 实现扫描的主体程序，TreadScan
 * @author Administrator
 *
 */

public class ThreadScan {
    
    public static JFrame main = new JFrame("Scaner(V1.0)By Nevermore");    //注册框架类
    public static JTextArea Result = new JTextArea("", 4, 40);            //显示扫描结果
    public static JScrollPane resultPane = new 
            JScrollPane( Result, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    
    public static JTextField nameHost = new JTextField("localhost", 8 );//输入主机名文本框
    public static JTextField fromip1 = new JTextField("0", 3);            //输入IP地址前三位的文本框
    public static JTextField fromip2 = new JTextField("0", 3);            //输入IP地址4~6位的文本框
    public static JTextField fromip3 = new JTextField("0", 3);            //输入IP地址7~9位的文本框
    public static JTextField fromip4 = new JTextField("0", 3);            //输入IP地址后三位的文本框
    
    public static JTextField toip = new JTextField("0", 3);                //输入目标IP地址后四位
    
    public static JTextField minPort = new JTextField("0", 4);            //最小端口输入框
    public static JTextField maxPort = new JTextField("1000", 4);        //最大端口输入框
    
    public static JTextField maxThread = new JTextField("100", 3);        //最大线程数
    public static JDialog DLGError = new JDialog(main, "错误！");        //错误提示框
    public static JLabel DLGINFO = new JLabel("");
    public static JLabel type = new JLabel("请选择：");
    //扫描类型
    public static JRadioButton radioIP = new JRadioButton("IP地址：");
    public static JRadioButton radioHost = new JRadioButton("主机名：", true);
    //单选按钮组
    public static ButtonGroup group= new ButtonGroup();
    public static JLabel p1 = new JLabel("端口范围：");
    public static JLabel p2 = new JLabel("~");
    public static JLabel p3 = new JLabel("~");
    public static JLabel Pdot1 = new JLabel(".");
    public static JLabel Pdot2 = new JLabel(".");
    public static JLabel Pdot3 = new JLabel(".");
    public static JLabel TNUM = new JLabel("线程数：");
    public static JLabel RST = new JLabel("扫描结果：");
    public static JLabel con = new JLabel("");
    //定义按钮
    public static JButton Ok = new JButton("确定");
    public static JButton Submit = new JButton("开始扫描");
    public static JButton Cancel = new JButton("退出");
    public static JButton saveButton = new JButton("保存扫描结果");
    //菜单栏设计：这一块好好学习学习
    public static JMenuBar myBar = new JMenuBar();
    public static JMenu myMenu = new JMenu("文件(F)");
    public static JMenuItem saveItem = new JMenuItem("保存扫描结果(S)");
    public static JMenuItem exitItem = new JMenuItem("退出(Q)");
    public static JMenu myMenu2 = new JMenu("帮助");
    public static JMenuItem helpItem = new JMenuItem("阅读");
    
    /**
     * 主方法
     */
    public static void main( String[] argcs ){
        main.setSize(500, 400);
        main.setLocation(400, 400);
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DLGError.setSize(300, 100);
        DLGError.setLocation(400, 400);
        //添加"菜单栏"
        myMenu.add(saveItem);
        myMenu.add(exitItem);
        
        myMenu2.add(helpItem);
        
        myBar.add(myMenu);//将菜单条目添加到菜单
        myBar.add(myMenu2);
        
        main.setJMenuBar(myBar);//将菜单添加到框架
        //设置热键
        myMenu.setMnemonic('F');
        saveItem.setMnemonic('S');
        //为"另存为"组建设置快捷键CTRL + S
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        //采用表格包模式布局
        Container mPanel = main.getContentPane();
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 10);
        
        c.gridx = 0;// 设置表格坐标
        c.gridy = 0;
        c.gridwidth = 10;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(type, c);
        
        group.add(radioIP);
        group.add(radioHost);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(radioIP, c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        mPanel.add(fromip1, c);
        
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        mPanel.add(Pdot1, c);
        
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(fromip2, c);
        
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(Pdot2, c);
        
        c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(fromip3, c);
        
        c.gridy = 1;
        c.gridx = 6;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(Pdot3, c);
        
        
        c.gridx = 7;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(fromip4, c);
        
        c.gridx = 8;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(p2, c);
        
        c.gridx = 9;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(toip, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(radioHost, c);
        
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(nameHost, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(p1, c);
        
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(minPort, c);
        
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(p3, c);
        
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(maxPort, c);
        
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(TNUM, c);
        
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(maxThread, c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(Submit, c);
    
        
        c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(saveButton, c);
        
        c.gridx = 6;
        c.gridy = 5;
        c.gridwidth =4;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(Cancel, c);
        
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 10;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(RST, c);
        
        //设置文本域可以换行
        Result.setLineWrap(true);
        //设置文本域不可编辑
        Result.setEditable(false);
        
        
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 10;
        c.gridheight = 4;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.CENTER;
        mPanel.add(resultPane, c);
        
        Container dPanel = DLGError.getContentPane();
        dPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dPanel.add(DLGINFO);
        dPanel.add(Ok);
        
        
        Submit.addActionListener(new SubmitAction());
        Cancel.addActionListener(new CancelAction());
        Ok.addActionListener(new OkAction());
        
        //实现保存功能
        saveItem.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(null);
                //单击保存按钮
                if( 0 == returnVal ){
                    File saveFile = fc.getSelectedFile();
                    try{
                        FileWriter writeOut = new FileWriter( saveFile );
                        writeOut.write(ThreadScan.Result.getText());
                        writeOut.close();
                        
                    }catch( IOException ex ){ System.out.println("保存失败");}
                }
            }
        });
        //实现退出功能
        ActionListener li =  new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                System.exit(0);
            }
        };
        exitItem.addActionListener(li);
        //实现帮助功能
        
        ActionListener lil =  new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                new AboutDialog();//
            }
        };
        helpItem.addActionListener(lil);
        
        ActionListener lill =  new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(null);
                //单击保存按钮
                if( 0 == returnVal ){
                    File saveFile = fc.getSelectedFile();
                    try{
                        FileWriter writeOut = new FileWriter(saveFile);
                        writeOut.write(ThreadScan.Result.getText());
                        writeOut.close();
                    }catch(IOException ex ){ System.out.println("保存失败");}
                }else{
                    return;//单击取消
                }
                
            }
        };
        saveButton.addActionListener(lill);
        main.setVisible(true);
    }
}
/**
 * 
 * @author Nevermore
 *    实现取消功能
 */
class CancelAction implements ActionListener{
    public void actionPerformed( ActionEvent e){
        System.exit(0);
    }
}
/**
 * 
 * @author Nevermore
 *    实现确定按钮
 */
class SubmitAction implements ActionListener{
    public void actionPerformed( ActionEvent a){
        int minPort;
        int maxPort;
        int maxThread;
        
        int ip1 = 0;
        int ip2 = 0;
        int ip3 = 0;
        int ipstart = 0;
        int ipend = 0;
        
        String ipaddress = "";
        String nameHost = "";
        ThreadScan.Result.setText("");
        if( ThreadScan.Submit.isEnabled()){
            ThreadScan.Submit.setEnabled(false);
        }
        /**
         * 判断扫描类型
         * 根据IP地址扫描 type = 0
         *根据主机名称扫描type = 1
         */
        if( ThreadScan.radioIP.isSelected()){
            TcpThread.type = 0;
            //判断IP地址的前三位是否是int型
            try{
                ip1 = Integer.parseInt(ThreadScan.fromip1.getText());
            }catch( NumberFormatException e ){
                ThreadScan.DLGINFO.setText("错误的IP地址");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            try{
                ip2 = Integer.parseInt(ThreadScan.fromip2.getText());
            }catch( NumberFormatException e ){
                ThreadScan.DLGINFO.setText("错误的IP地址");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            try{
                ip3 = Integer.parseInt(ThreadScan.fromip3.getText());
            }catch( NumberFormatException e ){
                ThreadScan.DLGINFO.setText("错误的IP地址");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            try{
                ipstart = Integer.parseInt(ThreadScan.fromip4.getText());
            }catch( NumberFormatException e ){
                ThreadScan.DLGINFO.setText("错误的IP地址");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            try{
                ipend = Integer.parseInt(ThreadScan.toip.getText());
            }catch( NumberFormatException e ){
                ThreadScan.DLGINFO.setText("错误的IP地址");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            
            //判断是否是合理的IP地址
            if(ip1 < 0 || ip1 > 255 || ip2 < 0 || ip2 > 255 ||
               ip3 < 0 || ip3 > 255 || ipstart < 0 || ipstart > 255 ){
                ThreadScan.DLGINFO.setText("IP地址为0~255的整数");
                ThreadScan.DLGError.setVisible(true);
                return ;
            }else{
                TcpThread.ip1 = ip1;
                TcpThread.ip2 = ip2;
                TcpThread.ip3 = ip3;
                TcpThread.ipstart = ipstart;
            }
            //判断目标IP地是否合理
            if( ipend < 0 || ipend > 255 ){
                ThreadScan.DLGINFO.setText("目标IP地址的范围是0~255");
                ThreadScan.DLGError.setVisible(true);
                return;
            }else{
                TcpThread.ipend = ipend;
            }
            
            ipaddress = "" + ip1 + ip2 + ip3 + ipstart;
            
            /**
             * 判断IP地址的有效性
             */
            try{
                TcpThread.hostAddress = InetAddress.getByName(ipaddress);
            }catch( UnknownHostException e){
                ThreadScan.DLGINFO.setText("错误的IP或IP地址不可到达！");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            
            if( ThreadScan.radioHost.isSelected()){
                TcpThread.type = 1;
                /**
                 * 判断主机名的有效性
                 */
                try{
                    TcpThread.hostAddress = InetAddress.getByName(ThreadScan.nameHost.getText());
                }catch( UnknownHostException e){ 
                    ThreadScan.DLGINFO.setText("错误的域名或地址不可到达！");
                    ThreadScan.DLGError.setVisible(true);
                    return;
                }
            }
            /**
             * 判断端口号的有效性
             */
            try{
                minPort = Integer.parseInt(ThreadScan.minPort.getText());
                maxPort = Integer.parseInt(ThreadScan.maxPort.getText());
                maxThread = Integer.parseInt(ThreadScan.maxThread.getText());
            }catch( NumberFormatException e ){
                ThreadScan.DLGINFO.setText("错误的端口号或端口号和线程数必须为整数");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            /**
             * 判断最小端口号的有效范围
             * 判断条件大于0小于65535最大端口号大于最小端口号
             */
            if( minPort < 0 || minPort > 65535 || minPort > maxPort ){
                ThreadScan.DLGINFO.setText("端口号范围：0~65535,并且最大端口号应大于最小端口号！");
                ThreadScan.DLGError.setVisible(true);
                return;
            }else{
                TcpThread.MIN_port = minPort;
            }
            /**
             * 判断最大端口号的有效范围
             */
            if( maxPort < 0 || maxPort > 65535 || maxPort < minPort ){
                ThreadScan.DLGINFO.setText("端口号范围：0~65535,并且最大端口号应大于最小端口号！");
                ThreadScan.DLGError.setVisible(true);
                return;
            }else{
                TcpThread.MAX_port = maxPort;
            }
            /**
             * 判断线程数的有效范围
             * 判断条件 大于1且小于200
             */
            if( maxThread < 1 || maxThread > 200 ){
                ThreadScan.DLGINFO.setText("线程数的有效范围是1~200");
                ThreadScan.DLGError.setVisible(true);
                return;
            }
            ThreadScan.Result.append("线程数" + ThreadScan.maxThread.getText() + "\n");
            
            /**
             * 启动线程
             */
            for( int i = 0; i < maxThread; i++ ){
                new TcpThread("T" + i, i).start();
            }
        }    
    }
}
/**
 * 
 * @author Administrator
 *    错误对话框
 */
class OkAction implements ActionListener{
    public void actionPerformed( ActionEvent e){
        ThreadScan.DLGError.dispose();
    }
}