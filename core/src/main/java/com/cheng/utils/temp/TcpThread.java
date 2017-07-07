package com.cheng.utils.temp;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpThread extends Thread{
    
    //定义变量
    public static InetAddress hostAddress;//主机IP地址
    public static int MIN_port;            //The minimal number of port
    public static int MAX_port;            //The maximal number of port
    private int NUM_thread;                //线程总数
    public static int type;                //查询方式，0为IP方式1为主机名查询方式
    
    public static int ip1;//IP前三位
    public static int ip2;//IP4~6位
    public static int ip3;//IP7~9位
    public static int ipstart;//起始IP地址最后四位
    public static int ipend;//结束IP地址最后四位
    public static String ipAll;//完整的IP地址
    
    String nameHost = "";//扫描的主机名称或IP地址
    String typeport = "0";//端口类别
    
    /**
     * 构造函数
     */
    public TcpThread( String name, int numthread ){
        super(name);
        this.NUM_thread = numthread;
    }
    
    /**
     * run()运行函数
     */
    public void run( ) {
        
        int h = 0;//IP address
        int i = 0;//port number
        
        Socket theTcpSocket;
        
        //根据IP地址进行扫描
        if( 0 == type ){
            //IP地址循环扫描
            for( h = ipstart; h <= ipend; h++){
                ipAll = "" + ip1 + "." + ip2 + "." + ip3 + "." + h;
                nameHost = ipAll;
                
                try{
                    hostAddress = InetAddress.getByName( ipAll );
                }catch( UnknownHostException e){}
                
                //不同端口循环扫描
                for( i = MIN_port; i < MAX_port + Integer.parseInt( ThreadScan.maxThread.getText() );
                        i += Integer.parseInt(ThreadScan.maxThread.getText() ) ){
                    try{
                        theTcpSocket = new Socket(hostAddress, i);
                        theTcpSocket.close();
                        
                        ThreadScan.Result.append( nameHost +":"+i);
                        
                        switch( i ){//其实这儿可以不用switch，直接用个hash表记录就可以
                            case 21:
                                typeport = "(FTP)";
                                break;
                            case 23:
                                typeport = "(TELNET)";
                                break;
                            case 25:
                                typeport = "SMTP";
                                break;
                            case 80:
                                typeport = "HTTP";
                                break;
                            case 110:
                                typeport = "POP";
                                break;
                            case 139:
                                typeport = "netBIOS";
                                break;
                            case 1433:
                                typeport = "SQL Server";
                                break;
                            case 3389:
                                typeport = "Terminal Service";
                                break;
                            case 443:
                                typeport = "HTTPS";
                                break;
                            case 1521:
                                typeport = "Oracle";
                                break;
                        }
                        
                        //端口没有特定类型
                        if( typeport.equals("0")){
                            ThreadScan.Result.append("\n");
                        }else{
                            ThreadScan.Result.append(":" + typeport + "\n");
                        }            
                    }catch( IOException e){}
                }
            }
            if( i == MAX_port + Integer.parseInt(ThreadScan.maxThread.getText())){
                ThreadScan.Result.append("\n" + "扫描完成...");
                //请"确定"按钮设置为可用
                ThreadScan.Submit.setEnabled(true);
//                if( !ThreadScan.Submit.setEnable(true));
            }
        }
        
        //按主机名进行端口扫描
        if( 1 == type ){
            for( i = MIN_port + NUM_thread; i < MAX_port + Integer.parseInt(ThreadScan.maxThread.getText());
                    i += Integer.parseInt( ThreadScan.maxThread.getText( ) ) ) {
                try{
                    theTcpSocket = new Socket( hostAddress, i );
                    theTcpSocket.close();
                    ThreadScan.Result.append(" " + i);
                    switch( i ){//其实这儿可以不用switch，直接用个hash表记录就可以
                        case 21:
                            typeport = "(FTP)";
                            break;
                        case 23:
                            typeport = "(TELNET)";
                            break;
                        case 25:
                            typeport = "SMTP";
                            break;
                        case 80:
                            typeport = "HTTP";
                            break;
                        case 110:
                            typeport = "POP";
                            break;
                        case 139:
                            typeport = "netBIOS";
                            break;
                        case 1433:
                            typeport = "SQL Server";
                            break;
                        case 3389:
                            typeport = "Terminal Service";
                            break;
                        case 443:
                            typeport = "HTTPS";
                            break;
                        case 1521:
                            typeport = "Oracle";
                            break;
                    }
                    if( typeport.equals("0") ){
                        ThreadScan.Result.append("\n");
                    }else{
                        ThreadScan.Result.append(":" + typeport + "\n");
                    }
                }catch( IOException e){
                    
                }
            }
            if( i == MAX_port + Integer.parseInt(ThreadScan.maxThread.getText())){
                ThreadScan.Result.append("\n" + "扫描完成...");
                if( !ThreadScan.Submit.isEnabled()){
                    ThreadScan.Submit.setEnabled( true );
                }
            }        
        }//End of if
    }
}