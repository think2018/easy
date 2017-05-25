package com.cheng.client.qq;

import java.awt.AWTException;
import java.awt.Robot;

public class ClientQQ{
	public static void main(String[] args) {
		Robot robot;
		try {

			robot = new Robot();
			autoLogin(robot);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	// 自动登录
	public static void autoLogin(Robot robot) {
		try {
			// ProcessBuilder proc = new ProcessBuilder("C:\\Program
			// Files\\Tencent\\QQ\\Bin\\QQ.exe", "");

			// ProcessBuilder proc = new
			// ProcessBuilder("/Applications/QQ.app/Contents/MacOS/QQ", "");
			ProcessBuilder proc = new ProcessBuilder("ipconfig","/all");

			// ProcessBuilder proc = new ProcessBuilder("open
			// //Applications//QQ.app", "");

			proc.start();
			// robot.delay(5000);
			//
			//// String username = "2982778925";
			//// String password = "tomtop2014";
			//
			// String username = "3376788865";
			// String password = "cyf860201";
			//
			// // String username = "233171118";
			// // String password = "tkggfcunui...";
			//
			// keyPressWithShift(robot, KeyEvent.VK_TAB);
			// keyPressString(robot, username);
			// robot.keyPress(KeyEvent.VK_TAB);
			//
			// char[] s = password.toUpperCase().toCharArray();
			// for (char c : s) {
			// int currentNum = (int) c;
			// robot.keyPress(currentNum);
			// }
			//
			// robot.delay(100);
			// robot.keyPress(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
