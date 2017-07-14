<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/common/taglib.jsp"%>
<%@ include file="/WEB-INF/common/head.jsp"%>

<script type="text/javascript" src="/js/core.js"></script>
<link type="text/css" rel="stylesheet" href="core.css"></link>

<html>
<head>
<title>###title###</title>
</head>
<body>
	<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/uri/a">标签A</a></li>
	<li class=""><a href="${ctx}/uri/b">标签B</a></li>
</ul>

	<%@ include file="/WEB-INF/common/search.jsp" %>
	<%@ include file="/WEB-INF/common/func.jsp" %>
	<%@ include file="/WEB-INF/common/table.jsp" %>
	<%@ include file="/WEB-INF/common/page.jsp" %>
</body>
</html>