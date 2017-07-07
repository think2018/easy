<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>jQuery Validate验证框架详解-metadata用法</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <script type="text/javascript" src="<%=request.getContextPath()%>/validate/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/validate/jquery.validate.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/validate/jquery.metadata.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/validate/messages_zh.js"></script>
        <script type="text/javascript">
        $(function(){
            $("#myform").validate();
        });
        </script>
    </head>

    <body>
        <form id="myform" method="post" action="">
            <p>
                <label for="myname">用户名：</label>
                <!-- id和name最好同时写上 -->
                <input id="myname" name="myname" class="required" />
            </p>
            <p>
                <label for="email">E-Mail：</label>
                <input id="email" name="email" class="required email" />
            </p>
            <p>
                <label for="password">登陆密码：</label>
                <input id="password" name="password" type="password"
                    class="{required:true,minlength:5}" />
            </p>
            <p>
                <label for="confirm_password">确认密码：</label>
                <input id="confirm_password" name="confirm_password" type="password"
                    class="{required:true,minlength:5,equalTo:'#password'}" />
            </p>
            <p>
                <label for="confirm_password">性别：</label>
                <!-- 表示必须选中一个 -->
                <input  type="radio" id="gender_male" value="m" name="gender" class="{required:true}" />
                <input  type="radio" id="gender_female" value="f" name="gender"/>
            </p>
            <p>
                <label for="confirm_password">爱好：</label>
                <!-- checkbox的minlength表示必须选中的最小个数,maxlength表示最大的选中个数,rangelength:[2,3]表示选中个数区间  -->
                <input type="checkbox" id="spam_email" value="email" name="spam[]" class="{required:true, minlength:2}" />
                <input type="checkbox" id="spam_phone" value="phone" name="spam[]" />
                <input type="checkbox" id="spam_mail" value="mail" name="spam[]" />
            </p>
            <p>
                <label for="confirm_password">城市：</label>
                <select id="jungle" name="jungle" title="Please select something!" class="{required:true}">
                    <option value=""></option>
                    <option value="1">厦门</option>
                    <option value="2">泉州</option>
                <option value="3">Oi</option>
            </select>
            </p>
            <p>
                <input class="submit" type="submit" value="立即注册" />
            </p>
        </form>
    </body>
</html>