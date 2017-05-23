package com.cheng.test.resoure;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cheng.test.constant.TestConstant;
import com.cheng.test.dto.RequestData;
import com.cheng.test.service.ITestService;
import com.cheng.test.service.impl.TestService;

@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestCase {

	String URL_HEAD = "http://circle-dev.yryz.com/lccf/services/"; // 测试环境
	final String url = URL_HEAD + "comment/delete/-1";

	@Autowired
	private ITestService testService;

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}

	private List<String> getRequestUrls(List<String> urls, List<String> paths) {

		List<String> tempUrls = new ArrayList<String>();

		for (String url : urls) {
			for (String path : paths) {
				tempUrls.add(url + path + "/");
			}
		}
		return tempUrls;
	}

	@POST
	@Path("")
	public String requestTest(RequestData requestData) {
		System.out.println(requestData.toString());

		List<String> circles = new ArrayList<String>();
		for (String circle : requestData.getCircleList()) {
			circles.add(circle + "/services");
		}

		List<String> urls1 = getRequestUrls(requestData.getEnvironmentList(), circles);

		System.out.println(urls1);

		List<String> urls2 = getRequestUrls(urls1, requestData.getModelList());

		System.out.println(urls2);

		List<String> urls3 = getRequestUrls(urls2, requestData.getInterfaceList());

		System.out.println("urls3 size : " + urls3.size());

		String result = "hi";
		
		testService = new TestService();

		for (String url : urls3) {
			String[] urlAtype = StringUtils.split(url, ",");
			String type = StringUtils.trim(urlAtype[1].replace("/", ""));
			switch (type) {
			case TestConstant.get:
				 result += testService.testGet(urlAtype[0]);
				break;
			case TestConstant.put:
				 result += testService.testPut(urlAtype[0], null);
				break;
			case TestConstant.post:
				 result += testService.testPost(urlAtype[0], null);
				break;
			case TestConstant.delete:
				 result += testService.testDelete(urlAtype[0]);
				break;

			default:
				break;
			}
		}
		return result;
	}
}
