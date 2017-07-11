package com.cheng.utils.temp;

import java.net.Socket;

public class PortScan2 extends Thread {
	// private static Integer count = 1;
	private int[] p;
	static Socket ss = null;

	public PortScan2(int[] p) {
		this.p = p;
	}

	public static void main(String[] args) {
//		for (int n = 0; n < 10; n++) {
			for (int i = 0; i < 65535; i = i + 100) {
//				new PortScan2(new int[] { i + 1, i + 100, n }).start();
				new PortScan2(new int[] { i + 1, i + 100 }).start();
			}
//		}
	}

	@Override
	public void run() {
		for (int i = p[0]; i < p[1]; i++) {
			try {
				// ss = new Socket("127.0.0.1", i);
				// ss = new Socket("192.168.30.199", i);
//				ss = new Socket("192.168.30." + p[2], i);
				ss = new Socket("120.24.45.31", i);
				// System.out.println(ss.isConnected());
				String name = ss.getInetAddress().getCanonicalHostName();
				System.out.println("HostName : " + name + ", 192.168.30." + p[2] + ", 扫描到端口： " + i);

			} catch (Exception e) {
				 System.out.println("PORT : (" + i + ")" + e.getMessage());
			}
		}
	}
}