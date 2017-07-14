<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')}</title>
    <meta name="decorator" content="blank"/>
    <c:set var="tabmode" value="${empty cookie.tabmode.value ? '1' : cookie.tabmode.value}"/>
    <c:if test="${tabmode eq '1'}">
        <link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css"/>
        <script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script>
    </c:if>
    <script type="text/javascript" src="${ctxStatic}/ajaxUpload/jquery.blockUI.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctxStatic}/ajaxUpload/cookieUtil.js"></script>
    <style type="text/css">
        #main {
            padding: 0;
            margin: 0;
        }

        #main .container-fluid {
            padding: 0 4px 0 6px;
        }

        #header {
            margin: 0 0 8px;
            position: static;
        }

        #header li {
            font-size: 14px;
            _font-size: 12px;
        }

        #header .brand {
            font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
            font-size: 26px;
            padding-left: 33px;
        }

        #footer {
            margin: 8px 0 0 0;
            padding: 3px 0 0 0;
            font-size: 11px;
            text-align: center;
            border-top: 2px solid #0663A2;
        }

        #footer, #footer a {
            color: #999;
        }

        #left {
            overflow-x: hidden;
            overflow-y: auto;
        }

        #left .collapse {
            position: static;
        }

        #userControl > li > a {
            /*color:#fff;*/
            text-shadow: none;
        }

        #userControl > li > a:hover, #user #userControl > li.open > a {
            background: transparent;
        }

        #displayBox {
            display: none;
        }

        #userControl .select2-container {
            margin: 10px
        }
    </style>


    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
    <script type="text/javascript">
        function getMenus() {
            window.location.href = "${ctx}/sys/circle/getMenusByAppId?appId=" + $("#appId").val();
        }

        $(document).ready(function () {
            //初始化页签
            <c:if test="${tabmode eq '0'}">
            $.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: {'height': $('#right').height() - tabTitleHeight},
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: tabTitleHeight
            });
            </c:if>
            // 绑定菜单单击事件
            $("#menu a.menu").click(function () {
                // 一级菜单焦点
                $("#menu li.menu").removeClass("active");
                $(this).parent().addClass("active");
                // 左侧区域隐藏
                if ($(this).attr("target") == "mainFrame") {
                    $("#left,#openClose").hide();
                    wSizeWidth();
                    // <c:if test="${tabmode eq '1'}"> 隐藏页签
                    $(".jericho_tab").hide();
                    $("#mainFrame").show();//</c:if>
                    return true;
                }
                // 左侧区域显示
                $("#left,#openClose").show();
                if (!$("#openClose").hasClass("close")) {
                    $("#openClose").click();
                }
                // 显示二级菜单
                var menuId = "#menu-" + $(this).attr("data-id");
                if ($(menuId).length > 0) {
                    $("#left .accordion").hide();
                    $(menuId).show();
                    // 初始化点击第一个二级菜单
                    if (!$(menuId + " .accordion-body:first").hasClass('in')) {
                        $(menuId + " .accordion-heading:first a").click();
                    }
                    if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")) {
                        $(menuId + " .accordion-body a:first i").click();
                    }
                    // 初始化点击第一个三级菜单
                    $(menuId + " .accordion-body li:first li:first a:first i").click();
                } else {
                    // 获取二级菜单数据
                    $.post($(this).attr("data-href"), function (data) {
                        if (data.indexOf("id=\"loginForm\"") != -1) {
                            alert('未登录或登录超时。请重新登录，谢谢！');
                            top.location = "${ctx}";
                            return false;
                        }
                        $("#left .accordion").hide();
                        $("#left").append(data);
                        // 链接去掉虚框
                        $(menuId + " a").bind("focus", function () {
                            if (this.blur) {
                                this.blur()
                            }
                            ;
                        });
                        // 二级标题
                        $(menuId + " .accordion-heading a").click(function () {
                            $(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
                            if (!$($(this).attr('data-href')).hasClass('in')) {
                                $(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
                            }
                        });
                        // 二级内容
                        $(menuId + " .accordion-body a").click(function () {
                            setCookie("data", "");
                            $(menuId + " li").removeClass("active");
                            $(menuId + " li i").removeClass("icon-white");
                            $(this).parent().addClass("active");
                            $(this).children("i").addClass("icon-white");
                        });
                        // 展现三级
                        $(menuId + " .accordion-inner a").click(function () {
                            var href = $(this).attr("data-href");
                            if ($(href).length > 0) {
                                $(href).toggle().parent().toggle();
                                return false;
                            }
                            //打开显示页签
                            <c:if test="${tabmode eq '0'}">
                            return addTab($(this));
                            </c:if>
                        });
                        // 默认选中第一个菜单
                        $(menuId + " .accordion-body a:first i").click();
                        $(menuId + " .accordion-body li:first li:first a:first i").click();
                    });
                }
                // 大小宽度调整
                wSizeWidth();
                return false;
            });
            // 初始化点击第一个一级菜单
            $("#menu a.menu:first span").click();
            // <c:if test="${tabmode eq '1'}"> 下拉菜单以选项卡方式打开
            $("#userInfo .dropdown-menu a").mouseup(function () {
                return addTab($(this), true);
            });// </c:if>
            // 鼠标移动到边界自动弹出左侧菜单
            $("#openClose").mouseover(function () {
                if ($(this).hasClass("open")) {
                    $(this).click();
                }
            });
            // 获取通知数目  <c:set var="oaNotifyRemindInterval" value="${fns:getConfig('oa.notify.remind.interval')}"/>
            function getNotifyNum() {
// 				$.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
// 					var num = parseFloat(data);
// 					if (num > 0){
// 						$("#notifyNum,#notifyNum2").show().html("("+num+")");
// 					}else{
// 						$("#notifyNum,#notifyNum2").hide()
// 					}
// 				});
            }

// 			getNotifyNum(); //<c:if test="${oaNotifyRemindInterval ne '' && oaNotifyRemindInterval ne '0'}">
// 			setInterval(getNotifyNum, ${oaNotifyRemindInterval}); //</c:if>
        });
        //添加一个页签
        <c:if test="${tabmode eq '0'}">
        function addTab($this, refresh) {
            $(".jericho_tab").show();
            $("#mainFrame").hide();
            $.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
            return false;
        }
        </c:if>
    </script>
</head>
<body>
<div id="main">
    <div id="header" class="navbar navbar-fixed-top">
        <!--  <a href="http://127.0.0.1:8181/yryzBackEnd" target="_blank">创业圈后台管理系统</a> -->
        <div class="navbar-inner">
            <div class="brand"><span id="productName">${fns:getConfig('productName')}</span></div>
            <c:if test="${fn:length(apps) >=1}">
                <ul id="userControl" class="nav pull-right">
                    <li id="themeSwitch1" class="dropdown">
                        <a href="#" target="_blank" id="app-external" style="display: none;"></a>
                        <select class="input-medium" id="appId" onchange="getMenus(this)">
                            <c:forEach items="${apps}" var="dict">
                                <option value="${dict.circleId}" <c:if test="${appId == dict.circleId}">selected</c:if>>
                                        ${dict.circleName}
                                </option>
                            </c:forEach>
                        </select>
                    </li>
                </ul>
            </c:if>
            <ul id="userControl" class="nav pull-right">
                <li id="themeSwitch" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i
                            class="icon-th-large"></i></a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${fns:getDictList('theme')}" var="dict">
                            <li><a href="#"
                                   onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a>
                            </li>
                        </c:forEach>
                        <!--<li><a href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>  -->
                    </ul>
                    <!--[if lte IE 6]>
                    <script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
                </li>

                <li id="userInfo" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, ${fns:getUser().name}&nbsp;<span
                            id="notifyNum" class="label label-info hide"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a>
                        </li>
                        <%--<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp; 修改密码</a>
                        </li>--%>
                    </ul>
                </li>
                <li><a href="${ctx}/logout" title="退出登录">退出</a></li>
                <li>&nbsp;</li>
            </ul>

            <div class="nav-collapse">
                <ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
                    <c:set var="firstMenu" value="true"/>
                    <c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
                        <c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
                            <li class="menu ${not empty firstMenu && firstMenu ? ' active' : ''}">
                                <c:if test="${empty menu.href}">
                                    <a class="menu" href="javascript:"
                                       data-href="${ctx}/sys/menu/tree?parentId=${menu.id}"
                                       data-id="${menu.id}"><span>${menu.name}</span></a>
                                </c:if>
                                <c:if test="${not empty menu.href}">
                                    <a class="menu" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}"
                                       data-id="${menu.id}" target="mainFrame"><span>${menu.name}</span></a>
                                </c:if>
                            </li>
                            <c:if test="${firstMenu}">
                                <c:set var="firstMenuId" value="${menu.id}"/>
                            </c:if>
                            <c:set var="firstMenu" value="false"/>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
    <div class="container-fluid">
        <div id="content" class="row-fluid">
            <div id="left"><%--
					<iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
            </div>
            <div id="openClose" class="close">&nbsp;</div>
            <div id="right">
                <iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes"
                        frameborder="no" width="100%" height="650"></iframe>
            </div>
        </div>
        <div id="footer" class="row-fluid">
            Copyright &copy; 2015-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - Powered By
            融众网络 ${fns:getConfig('version')}
        </div>
    </div>
</div>
<script type="text/javascript">
    var leftWidth = 160; // 左侧窗口大小
    var tabTitleHeight = 33; // 页签的高度
    var htmlObj = $("html"), mainObj = $("#main");
    var headerObj = $("#header"), footerObj = $("#footer");
    var frameObj = $("#left, #openClose, #right, #right iframe");
    function wSize() {
        var minHeight = 500, minWidth = 980;
        var strs = getWindowSize().toString().split(",");
        htmlObj.css({
            "overflow-x": strs[1] < minWidth ? "auto" : "hidden",
            "overflow-y": strs[0] < minHeight ? "auto" : "hidden"
        });
        mainObj.css("width", strs[1] < minWidth ? minWidth - 10 : "auto");
        frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
        $("#openClose").height($("#openClose").height() - 5);// <c:if test="${tabmode eq '1'}">
        $(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); // </c:if>
        wSizeWidth();
    }
    function wSizeWidth() {
        if (!$("#openClose").is(":hidden")) {
            var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
            $("#right").width($("#content").width() - leftWidth - $("#openClose").width() - 5);
        } else {
            $("#right").width("100%");
        }
    }
    <c:if test="${tabmode eq '0'}">
    function openCloseClickCallBack(b) {
        $.fn.jerichoTab.resize();
    }
    </c:if>
</script>
<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>

<!-- 	<div id="displayBox">
         这里是弹出层显示的内容！！！<br /><br /><br /><a href="javascript:void(0);" onclick="$.unblockUI();return false;" title="点击关闭">点击关闭</a>
     </div> -->
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/common/taglib.jsp"%>
<%@include file="/WEB-INF/common/head.jsp" %>	

<%
	HttpSession s = request.getSession();
%>
<html>
<head>
<title>动态管理</title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function setPage() {
		$("#pageNo").val(1);
	}

	
	// 查询框重置 
	function formReset() {
		var nickName = document.getElementById("nickName");
		var content = document.getElementById("content");
		var startTime = document.getElementById("startTime");
		var endTime = document.getElementById("endTime");
		
		nickName.value = "";	
		content.value = "";	
		startTime.value = "";	
		endTime.value = "";	
	}
	
	// 设置内容为精华
	function setElite(recommendType) {
		var idsObj = document.getElementsByName("ids");
        var ids = null;
        for (var i = 0; i < idsObj.length; i++) {
            if (idsObj[i].checked) {
                ids = idsObj[i].value;
            }
        }
        
        var f = $("input[name='ids']").is(':checked');
        
		var	appId = $($("input[name='ids']:checked").parent()).find('input :eq(2)').val();
		var moduleId = $($("input[name='ids']:checked").parent()).find('input :eq(3)').val();
		var	moduleEnum = $($("input[name='ids']:checked").parent()).find('input :eq(4)').val();
		
		if (ids == null ) {
            alert("请先选择记录！");
            return;
        }
		
		var text = null;
		if (recommendType == 2) {
			text = "确定要设置精华推荐？";
		} else {
			text = "确定要取消精华推荐？";
		}
		
		var data = {
				id : ids,
				recommendType : recommendType,
				appId : appId,
				moduleId : moduleId, 
				moduleEnum : moduleEnum 
		};
		
		if(confirm(text)) {
		
        $.ajax({
            type : "post",
            url : "${ctx}/dynamic/setRecommend/",
            data : data,
            success : function(result) {
                if (result.msg == "success") {
                    window.location.href = "${ctx}/dynamic/recommendList";
				}
                if (result.msg == "fail") {
                	top.$.jBox.tip(result.data); 
                }
            }
        });
		}
	}
	
	
	
	// 推荐至APP首页 
	function recommendToApp() {
        var f = $("input[name='ids']").is(':checked');
        
		if (!f) {
            alert("请先选择记录！");
            return;
        }
        
		var	id = $($("input[name='ids']:checked").parent()).find('input :eq(0)').val();
		var	appId = $($("input[name='ids']:checked").parent()).find('input :eq(2)').val();
		var moduleId = $($("input[name='ids']:checked").parent()).find('input :eq(3)').val();
		var	moduleEnum = $($("input[name='ids']:checked").parent()).find('input :eq(4)').val();
// 		var	appId = $($("input[name='ids']:checked").parent()).find('input :eq(6)').val();
		
		var data = {
				id : id,
				appId : appId,
				moduleId : moduleId, 
				moduleEnum : moduleEnum,
// 				appId : appId 
				
		};
		
		var text = "确定要推荐至APP首页？";
		
		if(confirm(text)) {
		
        $.ajax({
            type : "post",
            url : "${ctx}/dynamic/recommendToApp/",
            data : data,
            success : function(result) {
                if (result.msg == "success") {
                	top.$.jBox.tip("已推荐至预推荐列表"); 
                }
            }
        });
		}
	}
	
	function infoDetail(moduleId, moduleEnum){
// 		window.location.href = "${ctx}/content/info/detail/" + parseInt(id);
		window.location.href = "${ctx}/dynamic/detail/" + moduleId + "/" + moduleEnum;
	}
	
	function setWrite(id){
		$("#" + id ).attr('readonly',null);
	}
	
	function onblurUpdate(id, old, moduleId, moduleEnum, appId){
		
		var sort = $("#" + id ).val(); 
		
		if ( isNaN(sort) ) { 
			alert("请输入数字"); 
			$("#" + id ).val(""); 
			return false; 
		}
		
		if(sort ==  null || sort == ""){
			return false;	
		}
		
		var data = {
			id : id,
			orderby : sort,
			appId : appId,
			moduleId : moduleId, 
			moduleEnum : moduleEnum 
		};
		
		if (sort != old ) {
			if(confirm("确定要修改序号？")) {
			    $.ajax({
			        type : "post",
			        url : "${ctx}/dynamic/setSort/",
			        data : data,
			        success : function(result) {
			            if (result.msg == "success") {
			                window.location.href = "${ctx}/dynamic/list";
			            }
			        }
			    });	
			}
		}
	}
	
	
	function resetSort() {
		
        var ids = "";
        var appIds = "";
        var moduleIds = "";
        var moduleEnums = "";
        var orderbys = "";
        
        var count = 0 ;
		$("table tbody tr").each(function(index, obj) {
			var recommend =	$($(obj).find('td :eq(0)')).find('input :eq(1)').val();
			if(recommend == 2 ){
				count++;				
			}
		})
		
        
		$("table tbody tr").each(function(index, obj) {
			var recommend =	$($(obj).find('td :eq(0)')).find('input :eq(1)').val();
			if(recommend == 2 ){
                var orderby = count - index ;
				$($(obj).find('td :eq(1)')).text(orderby);
				
                var id = $($(obj).find('td :eq(0)')).find('input :eq(0)').val();
                var appId = $($(obj).find('td :eq(0)')).find('input :eq(2)').val();
                var moduleId = $($(obj).find('td :eq(0)')).find('input :eq(3)').val();
                var moduleEnum = $($(obj).find('td :eq(0)')).find('input :eq(4)').val();
                
                ids += id + ",";
                appIds += appId + ",";
                moduleIds += moduleId + ",";
                moduleEnums += moduleEnum + ",";
                orderbys += orderby + ",";
			}
		})
		
		var data = {
				id : ids,
				appId : appIds,
				moduleId : moduleIds, 
				moduleEnum : moduleEnums,
				orderbys : orderbys 
		};
		
		var	text = "确定要移动此条数据吗？";
		
		if(confirm(text)) {
		
        $.ajax({
            type : "post",
            url : "${ctx}/dynamic/moveRecommend/",
            data : data,
            success : function(result) {
                if (result.msg == "success") {
                    window.location.href = "${ctx}/dynamic/recommendList";
//                 	top.$.jBox.tip(result.msg);  
                }
            }
        });
		}
	}
	
	function up() {
		var f = $("input[name='ids']").is(':checked');
		if(f){
			$.each($("table .td_ground"), function() {
				var obj = $(this);
				var up = obj.prev();
				if ($(up).has("td").size() == 0) {
					alert("顶级元素不能上移");
					return;
				}
				$(obj).after(up);
				resetSort();
			});
		}else{
			alert("请选中一行数据");
		}
	}
	
	
	function down() {
		var f = $("input[name='ids']").is(':checked');
		
		if(f){
			$.each($("table .td_ground"), function() {
				var obj = $(this);
				var down = obj.next();
				
				var recommend =	$(down).find('input :eq(1)').val();
				
				if (recommend != 2) {
					alert("只有人工推荐的可以上下移， 已经是最后一个人工推荐了，不能向下移动了");
					return;
				}
	
				if ($(down).has("td").size() == 0) {
					alert("最尾部了");
					return;
				}
				$(down).after(obj);
				resetSort();
			});
		}else{
			alert("请选中一行数据");
		}
	}
	
	
	$(function(){
		$("table tr").click(function() {
			if ($(this).has("td").size() > 0) {
				$(this).addClass('td_ground');
				$(this).children("td").find("input :eq(0)").attr("checked",true);
				check($(this).children("td").find("input :eq(1)"));
				$(this).siblings().removeClass("td_ground");
			}
		});
	});
	
	
	function check(gg) {
// 		var	id = $($("input[name='ids']:checked").parent()).find('input :eq(0)').val();
// 		var	recommend = $($("input[name='ids']:checked").parent()).find('input :eq(1)').val();
// 		var	appId = $($("input[name='ids']:checked").parent()).find('input :eq(2)').val();
// 		var moduleId = $($("input[name='ids']:checked").parent()).find('input :eq(3)').val();
// 		var	moduleEnum = $($("input[name='ids']:checked").parent()).find('input :eq(4)').val();
// 		var	appId = $($("input[name='ids']:checked").parent()).find('input :eq(6)').val();

		var recommend = $(gg).val();
		
// 		alert(recommend);
// 		return;
		
		if(recommend == 2 ){
			$("#setRecommend").attr("disabled", true);
			$("#cancelRecommend").attr("disabled", false);
			
			$("#up").attr("disabled", false);
			$("#down").attr("disabled", false);
		} else {
			$("#setRecommend").attr("disabled", false);
			$("#cancelRecommend").attr("disabled", true);
			
			$("#up").attr("disabled", true);
			$("#down").attr("disabled", true);
		}
		
	}
	
	function och(gg){
		var f= $(gg).next().val();
		var g= $(gg).next().next().val()
// 		alert(g);
		if(f == 0){
			$("#goup").attr("disabled",true);
			$("#godown").removeAttr("disabled");
			if(g == 1){
				$("#recom").attr("disabled",true);
				$("#disrecom").removeAttr("disabled");
			}else if(g == 0){
				$("#disrecom").attr("disabled",true);
				$("#recom").removeAttr("disabled");
			}
		}else if (f == 1){
			$("#godown").attr("disabled",true);
			$("#goup").removeAttr("disabled");
			$("#recom").attr("disabled",true);
			$("#disrecom").attr("disabled",true);
		}
	}
	
</script>
<style type="text/css">

.content_width {
	width: 150px;
}

.content_li_width {
	width: 650px;
}

table {
	table-layout: fixed;
}

td {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dynamic/list">圈子首页"全部动态"</a></li>
		<li class="active"><a href="${ctx}/dynamic/recommendList">圈子首页"精华推荐"</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="dynamic"
		action="${ctx}/dynamic/recommendList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />

		<ul class="ul-form">
			<li class="content_li_width"><select id="moduleEnum"
				name="moduleEnum" class="input-medium">
					<option value="">所有功能</option>
					<c:forEach items="${moduleEnumList}" var="list">
						<option value="${list.moduleEnum}" class="input-medium"
							<c:if test="${dynamic.moduleEnum == list.moduleEnum}"> selected="selected"</c:if>>${list.name}
						</option>
					</c:forEach>
			</select> <select id="classifyId" name="classifyId" class="input-medium">
					<option value="">所有一级分类</option>
					<c:forEach items="${classifyIdList}" var="list">
						<option value="${list.id}" class="input-medium"
							<c:if test="${dynamic.classifyId== list.id}"> selected="selected"</c:if>>${list.categoryName}
						</option>
					</c:forEach>
			</select> <select id="classifyItemId" name="classifyItemId"
				class="input-medium">
					<option value="">所有二级分类</option>
					<c:forEach items="${classifyItemIdList}" var="list">
						<option value="${list.id}" class="input-medium"
							<c:if test="${dynamic.classifyItemId== list.id}"> selected="selected"</c:if>>${list.categoryName}
						</option>
					</c:forEach>
			</select></li>

			<li class="content_li_width"><label>发布时间：</label> <input
				id="startTime" name="startTime" class="Wdate content_width"
				type="text"
				onclick="WdatePicker({skin:'whyGreen',minDate:'2016-12-11',maxDate:'2025-12-11', dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				value="${dynamic.startTime}" /> - <input id="endTime"
				class="Wdate content_width" type="text" name="endTime"
				onclick="WdatePicker({skin:'whyGreen',minDate:'2016-12-11',maxDate:'2025-12-11', dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				value="${dynamic.endTime}" /></li>

			<li class="content_li_width"><label>昵称：</label> <input
				id="nickName" name="nickName" class="" value="${dynamic.nickName}"
				type="text" /></li>

			<li class="content_li_width"><label>关键字：</label> <input
				id="content" name="content" class="" value="${dynamic.content}"
				type="text" /> <c:forEach var="x" begin="0" end="20">
				   &nbsp;
				</c:forEach> <input id="btnSubmit" class="btn btn-primary" type="submit"
				onclick="setPage()" value="查询"> <c:forEach var="x" begin="0"
					end="3">
				   &nbsp;
				</c:forEach> <input id="btnReset"
					class="btn btn-primary" type="button" onclick="formReset()" value="重置">
			</li>
		</ul>
	</form:form>

	<input class="btn btn-primary" id="setRecommend" type="button" value="设为精华"
		onclick="setElite(2)">
	<c:forEach var="x" begin="0" end="3">
	   &nbsp;
	</c:forEach>

	<input class="btn btn-primary" id="cancelRecommend" type="button" value="取消精华"
		onclick="setElite(1)">
	<c:forEach var="x" begin="0" end="3">
	   &nbsp;
	</c:forEach>

	<input class="btn btn-primary" type="button" id="up" value="上移" onclick="up()">
	<c:forEach var="x" begin="0" end="3">
	   &nbsp;
	</c:forEach>

	<input class="btn btn-primary" type="button"  id ="down" value="下移"
		onclick="down()">
	<c:forEach var="x" begin="0" end="3">
	   &nbsp;
	</c:forEach>

	<input class="btn btn-primary" type="button" value="推荐至APP首页"
		onclick="recommendToApp()">
	<c:forEach var="x" begin="0" end="3">
	   &nbsp;
	</c:forEach>

	<br>
	<br>

	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th width="2%"></th>
				<th width="5%">序号</th>
				<th width="8%">状态</th>
				<th width="8%">功能</th>
				<th width="8%">一级分类</th>
				<th width="8%">二级分类</th>
				<th width="8%">用户</th>
				<th width="12%">发布时间</th>
				<th width="20%">标题</th>
				<th width="20%">正文摘要</th>
				<th width="4%">点赞数</th>
				<th width="4%">评论数</th>
				<th width="4%">收藏数</th>
				<th width="4%">分享数</th>
				<th width="6%">热度值</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="data" varStatus="i">
				<tr>
					<td>
					<input type="radio" name="ids" value='${data.id}'> 
					<input name="recommendType" type="hidden" value="${data.recommendType}" /> 
					<input name="appId" type="hidden" value="${data.appId}" /> 
					<input name="moduleId" type="hidden" value="${data.moduleId}" /> 
					<input name="moduleEnum" type="hidden" value="${data.moduleEnum}" /> 
					<input name="recommendId" type="hidden" value="" /> 
					</td>

					<td>${data.orderby}</td>

					<td><c:choose>
							<c:when test="${data.recommendType == 1}">
								<font color="#FF7F00">自动推荐</font>
							</c:when>
							<c:when test="${data.recommendType == 2}">
								<font color="#FF7F00">人工推荐</font>
							</c:when>
						</c:choose></td>


							
					<td title="${data.moduleEnum}">${moduleEnumMap.get(data.moduleEnum) == null?data.moduleEnum : moduleEnumMap.get(data.moduleEnum)}</td>
					
					<td title="${data.classifyId}">${data.classifyId == "" ?'无' :classifyIdMap.get(data.classifyId) }</td>
					<td title="${data.classifyItemId}">${data.classifyItemId == "" ?'无' : classifyItemIdMap.get( data.classifyItemId)}</td>
					
					<td title="${data.nickName}">${data.nickName}</td>
					<td title="${data.createDate}">${data.createDate}</td>
					<td style="word-wrap:break-word;word-break:break-all;white-space: pre-wrap;" title="${data.title}"><c:if test="${fn:length(data.title) > 30}"> ${fn:substring(data.title,0,30)}... </c:if><c:if test="${fn:length(data.title) <= 30}"> ${data.title} </c:if> <c:if test="${data.title == ''}"> 无 </c:if> </td>
					<td style="word-wrap:break-word;word-break:break-all;white-space: pre-wrap;" title="${data.summary}"><a
						href="javascript:infoDetail('${data.moduleId}', '${data.moduleEnum}')"><c:if test="${fn:length(data.summary) > 30}"> ${fn:substring(data.summary,0,30)}... </c:if><c:if test="${fn:length(data.summary) <= 30}"> ${data.summary} </c:if><c:if test="${data.summary == ''}"> 无 </c:if></a></td>
					<td>${data.likeNum == null ?'无' : data.likeNum}</td>
					<td>${data.commentNum == null ?'无' : data.commentNum}</td>
					<td>${data.collectNum == null ?'无' : data.collectNum}</td>
					<td>${data.shareNum == null ?'无' : data.shareNum}</td>
					<td>${data.heat == null ?'无' : data.heat}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>