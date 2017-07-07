package com.cheng.utils.thread;

import org.apache.log4j.Logger;

public class SemphoreY {

	private static Logger logger = Logger.getLogger(SemphoreY.class);

	public void service() {
		logger.info("service");
	}

	public static void main(String[] args) {
		new SemphoreY().service();
	}
}
