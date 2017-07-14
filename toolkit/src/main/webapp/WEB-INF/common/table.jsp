
<%@ page contentType="text/html;charset=UTF-8"%>

	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
		<tr>
			<th width="2%"></th>
			<th width="5%">序号</th>
			<th width="8%">状态</th>
			<th width="8%">用户</th>
			<th width="12%">发布时间</th>
			<th width="20%">标题</th>
			<th width="20%">正文摘要</th>
			<th width="4%">评论数</th>
		</tr>
	</thead>
	<tbody>
<%-- 		<c:forEach items="${page.list}" var="data" varStatus="i"> --%>
		
		<c:forEach var="x" begin="0" end="3">
		
			<tr>
				<td>
				<input type="radio" name="ids" value='${data.id}'> 
				</td>

				<td>${data.orderby}</td>

				<td>
				
				<c:choose>
						<c:when test="${data.recommendType == 1}">
							<font color="#FF7F00">自动推荐</font>
						</c:when>
						<c:when test="${data.recommendType == 2}">
							<font color="#FF7F00">人工推荐</font>
						</c:when>
					</c:choose></td>

				<td title="${data.nickName}">${data.nickName}</td>
				<td title="${data.createDate}">${data.createDate}</td>
				
				<td style="word-wrap:break-word;word-break:break-all;white-space: pre-wrap;" title="${data.title}"><c:if test="${fn:length(data.title) > 30}"> ${fn:substring(data.title,0,30)}... </c:if><c:if test="${fn:length(data.title) <= 30}"> ${data.title} </c:if> <c:if test="${data.title == ''}"> 无 </c:if> </td>
				<td style="word-wrap:break-word;word-break:break-all;white-space: pre-wrap;" title="${data.summary}"><a
					href="javascript:infoDetail('${data.moduleId}', '${data.moduleEnum}')"><c:if test="${fn:length(data.summary) > 30}"> ${fn:substring(data.summary,0,30)}... </c:if><c:if test="${fn:length(data.summary) <= 30}"> ${data.summary} </c:if><c:if test="${data.summary == ''}"> 无 </c:if></a></td>
				<td>${data.commentNum == null ?'无' : data.commentNum}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
