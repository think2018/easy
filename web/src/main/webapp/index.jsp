<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<head>

<title>My Test PAGE</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
	function test() {

		var urls = [];
		var environmentAll = document.all("environment");
		var circleAll = document.all("circle");
		var modelAll = document.all("modelName");
		var interfaceAll = document.all("interface");

		var environmentList = [];
		var circleList = [];
		var modelList = [];
		var interfaceList = [];

		for (i = 0; i < environmentAll.length; i++) {
			if (environmentAll[i].checked) {
				environmentList.push(environmentAll[i].value);
			}
		}

		for (i = 0; i < circleAll.length; i++) {
			if (circleAll[i].checked) {
				circleList.push(circleAll[i].value);
			}
		}

		for (i = 0; i < modelAll.length; i++) {
			if (modelAll[i].checked) {
				modelList.push(modelAll[i].value);
			}
		}

		for (i = 0; i < interfaceAll.length; i++) {
			if (interfaceAll[i].checked) {
				interfaceList.push(interfaceAll[i].value);
			}
		}

		if (environmentList.length == 0) {
			alert("environmentList is null")
			return;
		}
		// 			alert(environmentList.length);
		if (circleList.length == 0) {
			alert("circleList is null")
			return;
		}
		// 			alert(circleList.length);
		if (modelList.length == 0) {
			alert("modelList is null")
			return;
		}
		// 			alert(modelList.length);
		if (interfaceList.length == 0) {
			alert("interfaceList is null")
			return;
		}
		// 			alert(interfaceList.length);

		var url = "services/test/";
		var data = {
			"environmentList" : environmentList,
			"circleList" : circleList,
			"modelList" : modelList,
			"interfaceList" : interfaceList
		};

		alert(JSON.stringify(data));

		$.ajax({
			type : "post",
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(data),
			url : url,
			success : function(msg) {
				alert(msg.msg);
				alert(msg.result);
			},
			error : function(msg) {
				alert("error");
			}
		});

	}
</script>

</head>

<body background="">

	什么环境？
	<br>
	<input type="checkbox" name="environment"
		value="http://localhost:8080/" checked="checked" />本地环境
	<input type="checkbox" name="environment"
		value="http://localhost:8888/" />开发环境
	<input type="checkbox" name="environment"
		value="http://circle-dev.yryz.com/" />测试环境
	<input type="checkbox" name="environment"
		value="http://circle-produce.yryz.com/" />生产环境
	<br>
	<br> 什么项目？(圈子)
	<br>
	<input type="checkbox" name="circle" value="rest" checked="checked" />创业圈
	<input type="checkbox" name="circle" value="lccf" />理财圈
	<input type="checkbox" name="circle" value="yertx" />育儿圈
	<input type="checkbox" name="circle" value="jkys" />健康圈
	<input type="checkbox" name="circle" value="mcq" />萌宠圈
	<br>
	<br> 什么模块？
	<br>
	<input type="checkbox" name="modelName" value="test" checked="checked" />评论
	<input type="checkbox" name="modelName" value="user" />用户
	<br>
	<br> 什么接口？
	<br>
	<input type="checkbox" name="interface" value="message,get"
		checked="checked" />发布评论
	<input type="checkbox" name="interface" value="list,post" />评论列表
	<br>
	<br>

	<input type="button" onclick="test()" value="执行测试">
</body>

</html>
