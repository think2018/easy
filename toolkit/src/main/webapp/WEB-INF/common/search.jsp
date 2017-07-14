
<%@ page contentType="text/html;charset=UTF-8"%>

<form:form id="searchForm" modelAttribute="dynamic"
	action="${ctx}/dynamic/recommendList" method="post"
	class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
	<input id="pageSize" name="pageSize" type="hidden"
		value="${page.pageSize}" />

	<ul class="ul-form">
		<li class="content_li_width">
			<label>列表：</label> 
			<select id="moduleEnum" name="moduleEnum" class="input-medium">
				<option value="">所有</option>
			</select>
		</li>

		<li class="content_li_width">
			<label>时间：</label> 
			<input id="startTime" name="startTime" class="Wdate content_width" type="text"
			onclick="WdatePicker({skin:'whyGreen',minDate:'2016-12-11',maxDate:'2025-12-11', dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			value="${dynamic.startTime}" />
			 - 
			<input id="endTime" name="endTime" class="Wdate content_width" type="text"
			onclick="WdatePicker({skin:'whyGreen',minDate:'2016-12-11',maxDate:'2025-12-11', dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			value="${dynamic.endTime}" />
		</li>

		<li class="content_li_width">
			<label>昵称：</label> 
			<input id="nickName" name="nickName" class="" value="${dynamic.nickName}" type="text" />
		</li>

		<li class="content_li_width" >
			<label></label> 
			<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="setPage()" value="查询">
			
			<c:forEach var="x" begin="0" end="3">
			   &nbsp;
			</c:forEach>
	
			<input id="btnReset" class="btn btn-primary" type="button" onclick="formReset()" value="重置">
		</li>
		
	</ul>
</form:form>
