<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        <title>jQuery Validate验证框架详解</title>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/validate/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/validate/jquery.validate.min.js"></script>
        <script type="text/javascript">
        
        $(function(){
            var validate = $("#myform").validate({
                debug: true, //调试模式取消submit的默认提交功能   
                //errorClass: "label.error", //默认为错误的样式类为：error   
                focusInvalid: true, //当为false时，验证无效时，没有焦点响应  
                onkeyup: false,   
                submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
                    alert("提交表单");   
                    form.submit();   //提交表单   
                },   
                
                rules:{
                    myname:{
                        required:true
                    },
                    email:{
                        required:true,
                        email:true
                    },
                    password:{
                        required:true,
                        rangelength:[3,10]
                    },
                    confirm_password:{
                        equalTo:"#password"
                    }                    
                },
                messages:{
                    myname:{
                        required:"必填"
                    },
                    email:{
                        required:"必填",
                        email:"E-Mail格式不正确"
                    },
                    password:{
                        required: "不能为空",
                        rangelength: $.format("密码最小长度:{0}, 最大长度:{1}。")
                    },
                    confirm_password:{
                        equalTo:"两次密码输入不一致"
                    }                                    
                }
                          
            });    
    
        });
        </script>
    </head>

    <body>
        <form id="myform" method="post" action="a">
            <p>
                <label for="myname">用户名：</label>
                <!-- id和name最好同时写上 -->
                <input id="myname" name="myname" />
            </p>
            <p>
                <label for="email">E-Mail：</label>
                <input id="email" name="email" />
            </p>
            <p>
                <label for="password">登陆密码：</label>
                <input id="password" name="password" type="password" />
            </p>
            <p>
                <label for="confirm_password">确认密码：</label>
                <input id="confirm_password" name="confirm_password" type="password" />
            </p>
            <p>
                <input class="submit" type="submit" value="立即注册" />
            </p>
        </form>
    </body>
</html>