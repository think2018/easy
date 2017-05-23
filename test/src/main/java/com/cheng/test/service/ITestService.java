package com.cheng.test.service;

public interface ITestService {
	String testGet(String url);
	String testPut(String url, String data);
	String testPost(String url, String data);
	String testDelete(String url);
}
