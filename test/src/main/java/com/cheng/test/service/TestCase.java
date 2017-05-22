package com.cheng.test.service;

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
//@Consumes("application/json")
//@Produces("application/json")

//@ApplicationPath("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestCase  {

	
	String URL_HEAD = "http://circle-dev.yryz.com/lccf/services/";  //测试环境
	
	final String url = URL_HEAD + "comment/delete/-1";

	@POST
	@Path("")
	public Comment deleteById(Comment id) {
//	public Comment deleteById(String id) {

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
	
	
	
//	public static void main(String[] args) throws Exception {
//		new TestCase().deleteById(1L);
//	}
	
//	private static final String SUCCESS = "success";
//	
//	private static final String I_ADDR = "接口地址 : ";
//	private static final String I_DESC= "接口描述 : ";
//	private static final String I_PARAMS = "接口参数 : ";
//	private static final String I_RESULT = "接口返回值 : ";
//	
//	private static final String CUSTID = "8kl87wgttq";
//	private static final String TOKEN = "JCCWmIr91490341211445";
//	private static final Integer PAGENO = 1;
//	private static final Integer PAGESIZE = 10;
//	private static Gson GSON = new Gson();
//	
//	private static final long serialVersionUID = 1L;
//
//	private static Log logger = LogFactory.getLog(TestComment.class);
//
//	private static CommentDto commentDto = null;
//	
//	private static String P_URL = FileUtils.class.getClassLoader().getResource("params.txt").getPath();
//	
//	String URL_HEAD = "http://localhost:80/cyq/services/"; //本地环境
//	
//	String URL_HEAD = "http://circle-dev.yryz.com/cyq/services/";  //测试环境
//	String URL_HEAD = "http://circle-dev.yryz.com/jkys/services/";  //测试环境
//	String URL_HEAD = "http://circle-dev.yryz.com/yertx/services/";  //测试环境
//	String URL_HEAD = "http://circle-dev.yryz.com/mcq/services/";  //测试环境
//	String URL_HEAD = "http://circle-dev.yryz.com/lccf/services/";  //测试环境
//
//	/**
//	 * 发布评论
//	 */
//	@Test
//	public void testCommentToInsert() {
//
//		logger.debug(I_ADDR + "发布评论");
//
//		final String URL = URL_HEAD + "comment/single";
//
//		logger.debug("接口地址 : " + URL);
//
////		String requestJson = "{\"columnCode\": \"100-1001\", \"targetId\": 4, \"targetUserId\": \"1x33fj5cj5\", \"pId\": 0, \"comment\": \"AAA\", \"lastUpdateUserId\": \"guotlym3\", \"rootId\": 0, \"type\": 0, \"contentUrl\": \"http://www-dev.yryz.com/cyq/idea/details/4\", \"anchor\": \"0-4\" }";
//		String requestJson = FileUtils.readTxtFile(P_URL);
//		
//		System.out.println(requestJson);
//
//		CommentInput commentInput = (CommentInput) GSON.fromJson(requestJson, CommentInput.class);
//
//		commentInput.setCustId(CUSTID);
//		commentInput.setToken(TOKEN);
//
//		logger.debug(I_PARAMS + GSON.toJson(commentInput));
//
//		CreateCommentResult result;
//
//		try {
//			result = HTTPClientUtils.getPostResponseJsonToMap(URL, commentInput, CreateCommentResult.class);
//
//			System.out.println(I_RESULT + GSON.toJson(result));
//			logger.debug(I_RESULT + GSON.toJson(result));
//			logger.debug("****************************************************************************************");
//			if (null != result) {
//				assertEquals(SUCCESS, result.getMsg());
//				commentDto = result.getData();
//			}
//		} catch (Exception e) {
//			logger.error("调用接口失败：" + URL + "******" + e);
//		}
//
//	}
//
//	/**
//	 * 评论的更新
//	 */
//	@Test
//	public void testCommentToUpdateStatus() {
//
//		logger.debug(I_DESC + "评论的更新，包括修改评论内容，删除与恢复（上下架）");
//
//		final String url = URL_HEAD + "comment/updateStatus";
//
//		logger.debug(I_ADDR + url);
//
//		commentDto.setStatus(1);
//
//		logger.debug(I_PARAMS + GSON.toJson(commentDto));
//
//		CreateCommentResult result;
//
//		try {
//			result = HTTPClientUtils.getPutResponseJsonToMap(url, commentDto, CreateCommentResult.class);
//			logger.debug(I_RESULT + GSON.toJson(result));
//			logger.debug("****************************************************************************************");
//			if (null != result) {
//				assertEquals(SUCCESS, result.getMsg());
//			}
//		} catch (Exception e) {
//			logger.error("调用接口失败：" + url + "******" + e);
//		}
//
//	}
//
//	@Test
//	public void testDelete() {
//
//		logger.debug(I_DESC + "级联更新");
//
//		final String url = URL_HEAD + "comment/delete/" + "id";
//
//		logger.debug(I_ADDR + url);
//
//		logger.debug(I_PARAMS + url);
//
//		String result;
//
//		try {
//			result = HTTPClientUtils.delete(url);
//			logger.debug(I_RESULT + GSON.toJson(result));
//			logger.debug("****************************************************************************************");
//			// assertEquals(SUCCESS, result.getMsg());
//		} catch (Exception e) {
//			logger.error("调用接口失败：" + url + "******" + e);
//		}
//
//	}
//
//	/**
//	 * 前端评论列表(根评论分页与下级所有评论形成上下级关系)
//	 */
//	@Test
//	public void testListToFront() {
//
//		logger.debug(I_DESC + "前端评论列表(根据内容ID查找根评论并分页显示以级下级所有评论形成上下级关系)");
//
//		final String url = URL_HEAD + "comment/listToFront";
//
//		logger.debug(I_ADDR + url);
//
//		CommentInput commentInput = new CommentInput();
//		commentInput.setCustId(CUSTID);
//		commentInput.setToken(TOKEN);
//
//		commentInput.setPageNo(PAGENO);
//		commentInput.setPageSize(100);
//
//		commentInput.setId(450);
//		commentInput.setTargetId(1);
//
//		logger.debug(I_PARAMS + GSON.toJson(commentInput));
//
//		CommentResult result;
//
//		try {
//			result = HTTPClientUtils.getPostResponseJsonToMap(url, commentInput, CommentResult.class);
//			logger.debug(I_RESULT + GSON.toJson(result));
//			logger.debug("****************************************************************************************");
//
//			if (null != result) {
//				assertEquals(SUCCESS, result.getMsg());
//			}
//		} catch (Exception e) {
//			logger.error("调用接口失败：" + url + "******" + e);
//		}
//
//	}
//
//	/**
//	 * 后台评论列表(按昵称与内容搜索，分页)
//	 */
//	@Test
//	public void testCommentToListByParams() {
//
//		logger.debug(I_DESC + "评论列表，目前可根据昵称，评论内容搜索，如没有填写搜索条件则搜索全部评论，可分页默认分页为10条");
//
//		String url = URL_HEAD + "comment/listByParams";
//
//		logger.debug(I_ADDR + url);
//
//		CommentInput commentInput = new CommentInput();
//		commentInput.setCustId(CUSTID);
//		commentInput.setToken(TOKEN);
//
//		commentInput.setPageNo(PAGENO);
//		commentInput.setPageSize(PAGESIZE);
//		// commentInput.setNickName("");
//		commentInput.setComment("评论");
//		// commentInput.setTargetId(2);
//		// commentInput.setCircleId(1);
//		// commentInput.setCurrentUserId(24);
//		// commentInput.setStatus(1);
//		// commentInput.setUserType("front");
//
//		logger.debug(I_PARAMS + GSON.toJson(commentInput));
//
//		CommentResult result;
//
//		try {
//			result = HTTPClientUtils.getPostResponseJsonToMap(url, commentInput, CommentResult.class);
//
//			logger.debug(I_RESULT + GSON.toJson(result));
//
//			if (null != result) {
//				assertEquals(SUCCESS, result.getMsg());
//			}
//
//			logger.debug("****************************************************************************************");
//
//		} catch (Exception e) {
//			logger.error("调用接口失败：" + url + "******" + e);
//		}
//
//	}
//
//	/**
//	 *
//	 * 1.根据内容ID查找所有的评论数 2.根据时间查找该内容下所有的大于这个时间的评论数
//	 * 
//	 * @param targetId
//	 * @param createDate
//	 * @return
//	 */
//	@Test
//	public void testGetCountByTargetId() {
//
//		logger.debug("接口描述 : " + "1.根据内容ID查找所有的评论数; 2.根据时间查找该内容下所有的大于这个时间的评论数");
//
//		String url = URL_HEAD + "comment/countByTarget/1/1491022977";
//
//		logger.debug("接口地址 : " + url);
//
//		logger.debug("参数 : " + GSON.toJson("/1/2017-04-01 13:02:57"));
//
//		CommentResult result;
//
//		try {
//			String value = HTTPClientUtils.get(url);
//			result = JsonUtils.fromJson(value, CommentResult.class);
//			if (null != result) {
//				assertEquals(SUCCESS, result.getMsg());
//			}
//			logger.debug("返回值 : " + GSON.toJson(result));
//		} catch (Exception e) {
//			logger.error("调用接口失败：" + url + "******" + e);
//		}
//
//		logger.debug("****************************************************************************************");
//
//	}
}
