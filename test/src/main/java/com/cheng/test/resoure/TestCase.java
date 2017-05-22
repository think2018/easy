package com.cheng.test.resoure;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cheng.test.dto.Comment;

/**
 * @author chengyunfei
 * @version 1.0
 * @date : 2017-04-03 16:59:17
 * @Description ch ...
 */

@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestCase  {

	
	String URL_HEAD = "http://circle-dev.yryz.com/lccf/services/";  //测试环境
	
	final String url = URL_HEAD + "comment/delete/-1";

	@POST
	@Path("")
	public Comment deleteById(Comment id) {
		System.out.println(id.toString());
		String result;
		try {
//			result = HTTPClientUtils.delete(url);
			result = "OK";
			System.out.println(result);
			
			return new Comment(1L, "robot", "2017");
//			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
