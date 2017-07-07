package com.cheng.test.service.impl;

import com.cheng.test.service.ITestService;
import com.cheng.utils.http.HTTPClientUtils;

public class TestService implements ITestService {

	public String testGet(String url) {
		String result = "";
		try {
			result = HTTPClientUtils.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String testPut(String url, String data) {
		String result = "";
		try {
			result = HTTPClientUtils.put(url, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String testPost(String url, String data) {
		String result = "";
		try {
			result = HTTPClientUtils.post(url, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String testDelete(String url) {
		String result = "";
		try {
			result = HTTPClientUtils.delete(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
