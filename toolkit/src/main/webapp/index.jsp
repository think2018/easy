
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="utf-8" />
    <meta name="Robots" content="all" />
    <meta name="Author" content="KudyChen" />
    <meta name="Copyright" content="KudyStudio.Com" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>演示：jbox演示30种不同的调用方法</title>
    <script type="text/javascript" src="http://www.sucaihuo.com/jquery/2/263/demo/jbox/jquery-1.4.2.min.js"></script>
<!--     <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
    <!--jbox-->
<!--     <link id="skin" rel="stylesheet" href="http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/jbox.css" /> -->
    <script type="text/javascript" src="http://www.sucaihuo.com/jquery/2/263/demo/jbox/jquery.jbox-2.3.min.js"></script>
    <script type="text/javascript" src="http://www.sucaihuo.com/jquery/2/263/demo/jbox/i18n/jquery.jbox-zh-CN.js"></script>
    <!--demo测试代码-->
    <script type="text/javascript" src="http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo-depends/demo.js"></script>
    <!--demo代码高亮-->
    <script type="text/javascript" src="http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo-depends/highlight/highlight.pack.js"></script>
    <script type="text/javascript" src="http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo-depends/highlight/languages/javascript.js"></script>
    <link rel="stylesheet" type="text/css" href="http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo-depends/highlight/styles/magula.css" />
    <script type="text/javascript">
        hljs.initHighlightingOnLoad();
    </script>
    <style type="text/css">
        /*页面样式*/
        body {margin:0;padding:0;font:12px Verdana, Geneva, sans-serif;background:#66C6F6;}
        ul{ margin:0; padding:0; list-style:none;}
        a{color:#ff6600; text-decoration:none;}
        a:hover{color:#ff9900; text-decoration:underline;}
        .grid{ width:auto;width:920px !important;max-width:920px;min-width:800px; background:#FFF; margin:10px auto; border:10px solid #60B7DE;}
        .logo{ width:460px; font:bold 40px "Comic Sans MS", cursive;  margin:10px 5px 5px 25px;}
        .logo span{ font-size:13px; color:#999; margin:0 5px;}
        .demo{ padding:5px;}
        fieldset{ margin:20px; padding:16px;}
        .content{ margin:10px 0 0 0;}
        .content li { margin:5px;}
        .btn{ border:0; background:#4D77A7; color:#FFF; font-size:12px; padding:6px 10px;  margin:5px 0;}
        .copy{ height:30px; background:#E6EFF8; text-align:center; line-height:30px;}
        .t{padding:20px;}
        .update{padding:0 20px 20px 20px;}
        .t li,.update li{ margin:10px 0; line-height:20px;}
        .update{color:#069;padding-bottom:0;}
        .sel-skin{position:absolute;top:300px;right:10px;width:120px; height:80px; padding:10px; background-color:#fff;color:#fff;}
        /*jox应用测试样式*/
        div.msg-div{ padding: 10px; }
        div.msg-div p{ padding: 0px; margin:5px 0 0 0; }
        div.msg-div .field{ padding: 0px; }
        div.msg-div .field textarea{ width: 90%; height: 50px; resize:none; font-size:12px; }
        .errorBlock{ background-color: #FFC6A5; border: solid 1px #ff0000; color: #ff0000; margin: 10px 10px 0 10px; padding:7px; font-weight: bold; }
   
   
   
   
   @charset "utf-8";
/*
  提示：CSS 样式只允许修改颜色属性，或图片的地址（图片大小要和默认的一致）。border:dotted solid double dashed
*/
*:focus {outline: none;}
/* fade */
.jbox-fade{background-color:#000000;}
/* drag */
.jbox-drag{border:1px dashed #A5C11B;}
/* jbox */
div.jbox {padding:0px;border:none;font-size:12px;}
/* border */
div.jbox .jbox-border{background: none repeat scroll 0 0 #000000;filter:alpha(opacity=20);-moz-opacity:0.2;opacity:0.2;}
/* container */
div.jbox .jbox-container{background-color:#ffffff;border:1px solid #999999;}
/* title-panel */
div.jbox .jbox-title-panel{background:#A5C11B;border-bottom:1px solid #CCCCCC;}
div.jbox .jbox-title{font-weight:bold;color:#ffffff;}
div.jbox .jbox-title-icon{background:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/images/jbox-title-icon.gif) no-repeat scroll 3px 5px transparent;}
div.jbox .jbox-close,div.jbox .jbox-close-hover{background:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/images/jbox-close1.gif) no-repeat scroll 0px 0px transparent;}
div.jbox .jbox-close-hover{background-position:-16px 0;}
/* content */
div.jbox .jbox-content{min-height:24px;line-height:18px;color:#444444;}
div.jbox .jbox-content-loading{background-color:#E6E6E6;}
div.jbox .jbox-content-loading-image{background:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/images/jbox-content-loading.gif) no-repeat bottom center;}
/* button-panel */
div.jbox .jbox-button-panel{border-top:1px solid #CCCCCC;background-color: #EEEEEE;}
div.jbox .jbox-bottom-text{text-indent:10px;color:#444444;}
div.jbox .jbox-button{background:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/images/jbox-button2.png) repeat-x transparent;border:#AAAAAA 1px solid;color:#888888;border-radius:3px 3px 3px 3px;margin:1px 7px 0px 0px;height:22px;cursor:default;}
div.jbox .jbox-button-hover{background-position:0px -20px;color:#666666;}
div.jbox .jbox-button-active{background-position:0px -40px;}
div.jbox-warning .jbox .jbox-button-panel{background-color: #FFFFFF;}
/* tip-color */
div.jbox .jbox-tip-color{background-color:#A5C11B;border-color:#A5C11B;border-radius:3px 3px 3px 3px;color:#ffffff;}
/* icons */
div.jbox span.jbox-icon{background:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/images/jbox-icons.png) no-repeat scroll 0 0 transparent;_background:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/images/jbox-icons-ie6.gif) no-repeat scroll 0 0 transparent;}
div.jbox span.jbox-icon-info {background-position:0 0;}
div.jbox span.jbox-icon-question {background-position:-36px 0;}
div.jbox span.jbox-icon-success {background-position:-72px 0;}
div.jbox span.jbox-icon-warning {background-position:-108px 0;}
div.jbox span.jbox-icon-error {background-position:-144px 0;}
div.jbox span.jbox-icon-none {display: none; overflow:hidden;}
div.jbox span.jbox-icon-loading {background:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox/skins/Default/images/jbox-loading1.gif) no-repeat scroll 0 0 transparent;}
   
   
   
   
    </style>
</head>
<body>
    <div class="sel-skin">
        <ul>
            <li>分组：<a href="http://www.sucaihuo.com/jquery/2/263/demo/index.html" style="color:#fff">Skin</a> | <a href="http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo2.html" style="color:#fff">Skin2</a>
            <br /><br />皮肤：<select id="select-skin" name="select-skin" style="font-size:12px" onchange="changeSkin(this.value);">
                    <option value="Default|#A5C11B" style="background-color:#A5C11B;color:White;">默认</option>
                    <option value="Blue|#5F9AD4" style="background-color:#5F9AD4;color:White;">蓝色</option>
                    <option value="ShareBlue|#376EA5" style="background-color:#376EA5;color:White;">深蓝色</option>
                    <option value="GreyBlue|#546C83" style="background-color:#546C83;color:White;">灰蓝色</option>
                    <option value="Green|#818E49" style="background-color:#818E49;color:White;">绿色</option>
                    <option value="ShareGreen|#4D5A14" style="background-color:#4D5A14;color:White;">深绿色</option>
                    <option value="Red|#B8311F" style="background-color:#B8311F;color:White;">红色</option>
                    <option value="ShareRed|#6D0D00" style="background-color:#6D0D00;color:White;">深红色</option>
                    <option value="Purple|#824E85" style="background-color:#824E85;color:White;">紫色</option>
                    <option value="SharePurple|#511B55" style="background-color:#511B55;color:White;">深紫色</option>
                    <option value="Brown|#9B6E42" style="background-color:#9B6E42;color:White;">棕色</option>
                    <option value="ShareBrown|#60350B" style="background-color:#60350B;color:White;">深棕色</option>
                    <option value="Gray|#4C4C4C" style="background-color:#4C4C4C;color:White;">灰色</option>
                    <option value="GrayCool|#4C4C4C" style="background-color:#4C4C4C;color:White;">灰色cool</option>
                </select><br /><br />下载：<a href="http://www.sucaihuo.com/js/263.html" style="color:#fff" target="_blank"><img src="http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo-depends/down.png" width="16" style="width:16px;border:0; vertical-align:middle;" />jbox 2.3</a>
            </li>
        </ul>
    </div>

	<div class="grid">
       <div>
            <div class="logo">jbox<span>v2.3 beta</span></div>
       </div>
       <div style="padding: 20px 20px; clear:both;"><div style="width:877px;height:124px;background-image:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo-depends/jbox.png);"></div></div>

       <div style="padding: 0px 20px 20px 20px; clear:both;"><div style="width:877px;height:124px;background-image:url(http://www.sucaihuo.com/jquery/2/263/demo/jbox-demo-depends/jbox2.png);"></div></div>

     
       
      
      
       <div class="t">
          <ul>
        	<li><strong>插件说明</strong></li>
        	<li style="text-indent:2em"> - jbox 是一款基于 jQuery 的多功能对话框插件，能够实现网站的整体风格效果，给用户一个新的视觉享受。</li>
            <li><strong>运行环境</strong></li>
            <li style="text-indent:2em"> - 兼容 IE6+、Firefox、Chrome、Safari、Opera 等主流浏览器。<font color="#ff0000">备注：IE不支持边框的圆角样式，不推荐大家使用蛋痛的IE浏览器。</font></li>
         </ul>
    </div>

       <div style="padding: 0 20px">
    	<ul>
        	<li><strong>使用方法：</strong></li>
            <li>
              <pre>
              <code class="javascript">
  &lt;script type=&quot;text/javascript&quot; src=&quot;jbox/jquery-1.4.2.min.js&quot;&gt;&lt;/script&gt;
  &lt;script type=&quot;text/javascript&quot; src=&quot;jbox/jquery.jbox-2.3.min.js&quot;&gt;&lt;/script&gt;
  &lt;script type=&quot;text/javascript&quot; src=&quot;jbox/i18n/jquery.jbox-zh-CN.js&quot;&gt;&lt;/script&gt;

  &lt;link type=&quot;text/css&quot; rel=&quot;stylesheet&quot; href=&quot;jbox/skins/皮肤文件夹/jbox.css&quot;/&gt;
  // 或
  &lt;link type=&quot;text/css&quot; rel=&quot;stylesheet&quot; href=&quot;jbox/skins2/皮肤文件夹/jbox.css&quot;/&gt;
              </code>
              </pre>
            </li>
        </ul>
       </div>

       <fieldset>
        <input class="btn" type="button" onclick="show_jbox_set_defaults();" value="设置全局选项" />
        <input class="btn" type="button" onclick="show_jbox_set_border(5);" value="边框设置为 5" />
        <input class="btn" type="button" onclick="show_jbox_set_border(0);" value="边框设置为 0" />
        <br />请根据自己的喜好修改全局设置以减少方法时传递的参数。<font color="red">另外，jbox的皮肤是很容易制作的，最简单的就是只修改CSS样式里的颜色，在会ＰＳ的情况下更是可以制作多样化的皮肤。</font>
       </fieldset>

       <fieldset><legend>$.jbox()</legend>
        <div class="content">
            <ul>
           	    <li>函数原型：</li>
             	<li style="text-indent:2em"><strong>$.jbox(content, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox(content, options);</li>
                <li>参数说明：</li>
                <li style="text-indent:2em"> - <strong>content</strong>　(string,json)</li>
                <li style="color:#999999;">　　　└ 可以是string或json。当是string时，需要加上前缀标识（html:、id:、get:、post:、iframe:），如果没有加标识，系统会自动加上html:，具体请看应用例子。当是json时，表示一个或多个状态，每个状态的默认值为 <a href="javascript:viewConfig('stateDefaults');" title="点击查看">$.jbox.stateDefaults</a>。
                </li>
                <li style="text-indent:2em"> - <strong>options</strong>　[可选] (json)</li>
                <li style="color:#999999;">　　　└ 其它参数选项，默认值为 <a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults</a>。</li>

                <li style="color:#F00">
                    <br /><strong>备注：如果想手动关闭jbox（不包括下面的tip与messager，它们另有方法），请调用　$.jbox.close(token)　方法。</strong>
                </li>
            </ul>
        </div>
        <br />

示例（一）:    
<pre>
<code class="javascript">
// 此例省略了前缀html:，前缀标识是不区分大小写的，也可以是HTML:
var info = 'jQuery jbox&lt;br /&gt;&lt;br /&gt;版本：v2.0&lt;br /&gt;日期：2011-7-24&lt;br /&gt;';
info += '官网：&lt;a target="_blank" href="http://kudystudio.com/jbox"&gt;http://kudystudio.com/jbox&lt;/a&gt;';
$.jbox.info(info);
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_1_1();" value="运　行" /></div>

示例（二）:    
<pre>
<code class="javascript">
// 显示id为id-html的div内部html，同时设置了bottomText
$.jbox('id:id-html', { bottomText: '这是底部文字' });
</code>
</pre>
<div id="id-html" style="display:none;">
    <div style='padding:10px;'>这里是id为id-html的div内部html，同时设置了bottomText</div>
</div>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_1_2();" value="运　行" /></div>

示例（三）:    
<pre>
<code class="javascript">
// ajax get 页面ajax.html的内容并显示，例如要提交id=1，则地址应该为 ajax.html?id=1，post:前缀的使用与get:的一样
$.jbox("get:ajax.html");
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_1_3();" value="运　行" /></div>

示例（四）:    
<pre>
<code class="javascript">
// 用iframe显示http://www.baidu.com的内容，并设置了标题、宽与高、按钮
$.jbox("iframe:http://www.baidu.com", {
    title: "百度一下",
    width: 800,
    height: 350,
    buttons: { '关闭': true }
});
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_1_4();" value="运　行" /></div>

示例（五）:  
<pre>
<code class="javascript">
var content = {
    state1: {
        content: '状态一',
        buttons: { '下一步': 1, '取消': 0 },
        buttonsFocus: 0,
        submit: function (v, h, f) {
            if (v == 0) {
                return true; // close the window
            }
            else {
                $.jbox.nextState(); //go forward
                // 或 $.jbox.goToState('state2')
            }
            return false;
        }
    },
    state2: {
        content: '状态二，请关闭窗口哇：）',
        buttons: { '上一步': 1, '取消': 0 },
        buttonsFocus: 0,
        submit: function (v, h, f) {
            if (v == 0) {
                return true; // close the window
            } else {
                $.jbox.prevState() //go back
                // 或 $.jbox.goToState('state1');
            }

            return false;
        }
    }
};

$.jbox(content);
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_1_5();" value="运　行" /></div>

示例（六）:    
<pre>
<code class="javascript">
var html = "&lt;div style='padding:10px;'&gt;输入姓名：&lt;input type='text' id='yourname' name='yourname' /&gt;&lt;/div&gt;";
var submit = function (v, h, f) {
    if (f.yourname == '') {
        $.jbox.tip("请输入您的姓名。", 'error', { focusId: "yourname" }); // 关闭设置 yourname 为焦点
        return false;
    }

    $.jbox.tip("你叫：" + f.yourname);
    //$.jbox.tip("你叫：" + h.find("#yourname").val());
    //$.jbox.tip("你叫：" + h.find(":input[name='yourname']").val());

    return true;
};

$.jbox(html, { title: "你叫什么名字？", submit: submit });
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_1_6();" value="运　行" /></div>
       </fieldset>

       <fieldset><legend>$.jbox.open()</legend>
        <div class="content">
            <ul>
           	    <li>函数原型：</li>
             	<li style="text-indent:2em"><strong>$.jbox.open(content, title, width, height, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.open(content, title, width, height, options);</li>
                <li>参数说明：</li>
                <li style="text-indent:2em"> - <strong>content</strong>　(string,json)</li>
                <li style="color:#999999;">　　　└ 可以是string或json。当是string时，需要加上前缀标识（html:、id:、get:、post:、iframe:），如果没有加标识，系统会自动加上html:，具体请看应用例子。当是json时，表示一个或多个状态，每个状态的默认值为 <a href="javascript:viewConfig('stateDefaults');" title="点击查看">$.jbox.stateDefaults</a>。</li>
                <li style="text-indent:2em"> - <strong>title</strong>　[可选] (string)</li>
                <li style="color:#999999;">　　　└ 窗口标题，值为null时为不显示标题，默认值为 <a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults.title</a>。</li>
                <li style="text-indent:2em"> - <strong>width</strong>　[可选] (string,number)</li>
                <li style="color:#999999;">　　　└ 窗口宽度，值为'auto'或具体像素值（例如350），默认值为 <a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults.width</a>。</li>
                <li style="text-indent:2em"> - <strong>height</strong>　[可选] (string,number)</li>
                <li style="color:#999999;">　　　└ 窗口高度，值为'auto'或具体像素值（例如100），默认值为 <a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults.height</a>。</li>
                <li style="text-indent:2em"> - <strong>options</strong>　[可选] (json)</li>
                <li style="color:#999999;">　　　└ 其它参数选项，默认值为 <a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults</a>。</li>
                <li style="color:#F00">
                    <br /><strong>备注：$.jbox.open() 只是 $.jbox() 的一个扩展，方便 title、width、height 参数的传递。</strong>
                </li>
            </ul>
        </div>
        <br />

示例（一）:    
<pre>
<code class="javascript">
$.jbox.open("iframe:http://www.baidu.com", "百度一下", 800, 350, { buttons: { '关闭': true} });
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_2_1();" value="运　行" /></div>

示例（二）: <font color="#ff0000;">（content为Json对象，比较复杂一点的例子）</font>   
<pre>
<code class="javascript">
var html1 = '&lt;div class="msg-div"&gt;' +
            '&lt;p&gt;购买数量：&lt;/p&gt;&lt;div class="field"&gt;&lt;input type="text" id="amount" name="amount" value="1" /&gt;&lt;/div&gt;' +
            '&lt;p&gt;收货地址：&lt;/p&gt;&lt;div class="field"&gt;&lt;textarea id="address" name="address"&gt;&lt;/textarea&gt;&lt;/div&gt;' +
            '&lt;div class="errorBlock" style="display: none;"&gt;&lt;/div&gt;' +
            '&lt;/div&gt;';

var html2 = '&lt;div class="msg-div"&gt;' +
            '&lt;p&gt;给卖家留言：&lt;/p&gt;&lt;div class="field"&gt;&lt;textarea id="message" name="message"&gt;&lt;/textarea&gt;&lt;/div&gt;' +
            '&lt;/div&gt;';

var data = {};
var states = {};
states.state1 = {
    content: html1,
    buttons: { '下一步': 1, '取消': 0 },
    submit: function (v, h, f) {
        if (v == 0) {
            return true; // close the window
        }
        else {
            h.find('.errorBlock').hide('fast', function () { $(this).remove(); });

            data.amount = f.amount; //或 h.find('#amount').val();
            if (data.amount == '' || parseInt(data.amount) &lt; 1) {
                $('&lt;div class="errorBlock" style="display: none;"&gt;请输入购买数量！&lt;/div&gt;').prependTo(h).show('fast');
                return false;
            }
            data.address = f.address;
            if (data.address == '') {
                $('&lt;div class="errorBlock" style="display: none;"&gt;请输入收货地址！&lt;/div&gt;').prependTo(h).show('fast');
                return false;
            }

            $.jbox.nextState(); //go forward
            // 或 $.jbox.goToState('state2')
        }

        return false;
    }
};
states.state2 = {
    content: html2,
    buttons: { '上一步': -1, '提交': 1, '取消': 0 },
    buttonsFocus: 1, // focus on the second button
    submit: function (v, o, f) {
        if (v == 0) {
            return true; // close the window
        } else if (v == -1) {
            $.jbox.prevState() //go back
            // 或 $.jbox.goToState('state1');
        }
        else {
            data.message = f.message;

            // do ajax request here
            $.jbox.nextState('&lt;div class="msg-div"&gt;正在提交...&lt;/div&gt;');
            // 或 $.jbox.goToState('state3', '&lt;div class="msg-div"&gt;正在提交...&lt;/div&gt;')

            // asume that the ajax is done, than show the result
            var msg = [];
            msg.push('&lt;div class="msg-div"&gt;');
            msg.push('&lt;p&gt;下面是提交的数据&lt;/p&gt;');
            for (var p in data) {
                msg.push('&lt;p&gt;' + p + ':' + data[p] + '&lt;/p&gt;');
            }
            msg.push('&lt;/div&gt;');
            window.setTimeout(function () { $.jbox.nextState(msg.join('')); }, 2000);
        }

        return false;
    }
};
states.state3 = {
    content: '',
    buttons: {} // no buttons
};
states.state4 = {
    content: '',
    buttons: { '确定': 0 }
};

$.jbox.open(states, '提交订单', 450, 'auto');
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_2_2();" value="运　行" /></div>
       </fieldset>

       <fieldset><legend>$.jbox.prompt()</legend>
        <div class="content">
            <ul>
           	    <li>函数原型：</li>
             	<li style="text-indent:2em"><strong>$.jbox.prompt(content, title, icon, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.prompt(content, title, icon, options);</li>
                <li>参数说明：</li>
                <li style="text-indent:2em"> - <strong>content</strong>　(string)</li>
                <li style="color:#999999;">　　　└ 只能是string，不支持前缀标识，默认值为''。</li>
                <li style="text-indent:2em"> - <strong>title</strong>　[可选] (string)</li>
                <li style="color:#999999;">　　　└ 窗口标题，值为null时为不显示标题，默认值为 <a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults.title</a>。</li>
                <li style="text-indent:2em"> - <strong>icon</strong>　[可选] (string)</li>
                <li style="color:#999999;">　　　└ <font color="red">内容图标</font>，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error'，默认值为'none'。</li>
                <li style="text-indent:2em"> - <strong>options</strong>　[可选] (json)</li>
                <li style="color:#999999;">　　　└ 其它参数选项，默认值为 <a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults</a>。</li>

                <li style="color:#F00">
                    <br /><strong>备注：以下几个方法由　$.jbox.prompt()　扩展而来，参数类似，请看下面的例子。</strong>
                </li>
             	<li style="text-indent:2em"><strong>$.jbox.alert(content, title, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.alert(content, title, options);</li>
             	<li style="text-indent:2em"><strong>$.jbox.info(content, title, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.info(content, title, options);</li>
             	<li style="text-indent:2em"><strong>$.jbox.success(content, title, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.success(content, title, options);</li>
             	<li style="text-indent:2em"><strong>$.jbox.error(content, title, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.error(content, title, options);</li>
             	<li style="text-indent:2em"><strong>$.jbox.confirm(content, title, submit, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.confirm(content, title, submit, options);</li>
             	<li style="text-indent:2em"><strong>$.jbox.warning(content, title, submit, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.warning(content, title, submit, options);</li>
                
                <li style="color:#F00;">　　　 └ 上面方法中默认按钮的文字设置在 <a href="javascript:javascript:viewConfig('languageDefaults');" title="点击查看">$.jbox.languageDefaults</a></li>
            </ul>
        </div>
        <br />

示例（一）:    
<pre>
<code class="javascript">
//加了个其它参数closed
$.jbox.prompt('Hello jbox', 'jbox', 'info', { closed: function () { alert('prompt is closed.'); } });
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_1();" value="运　行" /></div>

示例（二）:    
<pre>
<code class="javascript">
$.jbox.alert('Hello jbox', 'jbox');
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_2();" value="运　行" /></div>

示例（三）:    
<pre>
<code class="javascript">
$.jbox.info('Hello jbox', 'jbox');
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_3();" value="运　行" /></div>

示例（四）:    
<pre>
<code class="javascript">
$.jbox.success('Hello jbox', 'jbox');
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_4();" value="运　行" /></div>

示例（五）:  
<pre>
<code class="javascript">
$.jbox.error('Hello jbox', 'jbox');
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_5();" value="运　行" /></div>

示例（六）:  
<pre>
<code class="javascript">
var submit = function (v, h, f) {
    if (v == 'ok')
        jbox.tip(v, 'info');
    else if (v == 'cancel')
        jbox.tip(v, 'info');

    return true; //close
};

$.jbox.confirm("确定吗？", "提示", submit);
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_6();" value="运　行" /></div>

示例（六02）:  
<pre>
<code class="javascript">
var submit = function (v, h, f) {
    if (v == true)
        jbox.tip("恩", 'info');
    else
        jbox.tip("好吖", 'info');

    return true;
};
// 自定义按钮
$.jbox.confirm("天使，做我女朋友吧？", "表白提示", submit, { buttons: { '恩': true, '好吖': false} });
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_6_2();" value="运　行" /></div>

示例（七）:  
<pre>
<code class="javascript">
var submit = function (v, h, f) {
    if (v == 'yes') {
        $.jbox.tip('已保存。', 'success');
    }
    if (v == 'no') {
        $.jbox.tip('没保存。');
    }
    if (v == 'cancel') {
        $.jbox.tip('已取消。');
    }

    return true;
};
// 可根据需求仿上例子定义按钮
$.jbox.warning("内容已修改，是否保存？", "提示", submit);
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_3_7();" value="运　行" /></div>
       </fieldset>

       <fieldset><legend>$.jbox.tip()</legend>
        <div class="content">
            <ul>
           	    <li>函数原型：</li>
             	<li style="text-indent:2em"><strong>$.jbox.tip(content, icon, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.tip(content, icon, options);</li>
                <li>参数说明：</li>
                <li style="text-indent:2em"> - <strong>content</strong>　(string)</li>
                <li style="color:#999999;">　　　└ 只能是string，不支持前缀标识，默认值为''。</li>
                <li style="text-indent:2em"> - <strong>icon</strong>　[可选] (string)</li>
                <li style="color:#999999;">　　　└ <font color="red">内容图标</font>，可选值有'info'、'success'、'warning'、'error'、'loading'，默认值为'info'，当为'loading'时，timeout值会被设置为0，表示不会自动关闭。</li>
                <li style="text-indent:2em"> - <strong>options</strong>　[可选] (json)</li>
                <li style="color:#999999;">　　　└ 其它参数选项，默认值为 <a href="javascript:viewConfig('tipDefaults');" title="点击查看">$.jbox.tipDefaults</a>。</li>

                <li style="color:#F00">
                    <br /><strong>备注：如果想手动关闭tip，请调用　$.jbox.closeTip()　方法。</strong>
                </li>
            </ul>
        </div>
        <br />

示例（一）:    
<pre>
<code class="javascript">
$.jbox.tip('Hello jbox');
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_4_1();" value="运　行" /></div>

示例（二）:    
<pre>
<code class="javascript">
//加了个其它参数focusId
$.jbox.tip('关闭后设置输入框为焦点', 'info', { focusId: 'tip-input' });
</code>
</pre>
<div style="text-align:right">输入框：<input type="text" id="tip-input" /> <input class="btn" type="button" onclick="demo_4_2();" value="运　行" /></div>

示例（三）:    
<pre>
<code class="javascript">
//加了个其它参数closed
$.jbox.tip('关闭后设置输入框为已选择', 'error', { closed: function () { $('#tip-input2').select(); } });
</code>
</pre>
<div style="text-align:right">输入框：<input type="text" id="tip-input2" value="内容" /> <input class="btn" type="button" onclick="demo_4_3();" value="运　行" /></div>

示例（四）:    
<pre>
<code class="javascript">
$.jbox.tip("正在XX，你懂的...", 'loading');
// 模拟2秒后完成操作
window.setTimeout(function () { $.jbox.tip('XX已完成。', 'success'); }, 2000);
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_4_4();" value="运　行" /></div>

示例（五）:    
<pre>
<code class="javascript">
var submit = function (v, h, f) {
    if (v == 'ok') {
        $.jbox.tip("正在删除数据...", 'loading');
        // 模拟2秒后完成操作
        window.setTimeout(function () { $.jbox.tip('删除成功。', 'success'); }, 2000);
    }
    else if (v == 'cancel') {
        // 取消
    }

    return true; //close
};

$.jbox.confirm("确定要删除数据吗？", "提示", submit);
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_4_5();" value="运　行" /></div>
       </fieldset>

       <fieldset><legend>$.jbox.messager()</legend>
        <div class="content">
            <ul>
           	    <li>函数原型：</li>
             	<li style="text-indent:2em"><strong>$.jbox.messager(content, title, timeout, options);</strong></li>
                <li style="color:#999">　　　 └ 或者 jbox.messager(content, title, timeout, options);</li>
                <li>参数说明：</li>
                <li style="text-indent:2em"> - <strong>content</strong>　(string)</li>
                <li style="color:#999999;">　　　└ 只能是string，不支持前缀标识，默认值为''。</li>
                <li style="text-indent:2em"> - <strong>title</strong>　[可选] (string)</li>
                <li style="color:#999999;">　　　└ 窗口标题，值为null时为不显示标题，默认值为 <a href="javascript:viewConfig('messagerDefaults');" title="点击查看">$.jbox.messagerDefaults.title</a>。</li>
                <li style="text-indent:2em"> - <strong>timeout</strong>　[可选] (number)</li>
                <li style="color:#999999;">　　　└ 显示多少毫秒后自动关闭，如果为0则不自动关闭，默认值为 <a href="javascript:viewConfig('messagerDefaults');" title="点击查看">$.jbox.messagerDefaults.timeout</a>。</li>
                <li style="text-indent:2em"> - <strong>options</strong>　[可选] (json)</li>
                <li style="color:#999999;">　　　└ 其它参数选项，默认值为 <a href="javascript:viewConfig('messagerDefaults');" title="点击查看">$.jbox.messagerDefaults</a>。</li>

                <li style="color:#F00">
                    <br /><strong>备注：如果想手动关闭messager，请调用　$.jbox.closeMessager()　方法。</strong>
                </li>
            </ul>
        </div>
        <br />

示例（一）:    
<pre>
<code class="javascript">
$.jbox.messager('Hello jbox', 'jbox');
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_5_1();" value="运　行" /></div>

示例（二）:    
<pre>
<code class="javascript">
$.jbox.messager("Hello jbox 2", "my title", null, { width: 350, showType: 'fade' });
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_5_2();" value="运　行" /></div>

示例（三）:    
<pre>
<code class="javascript">
$.jbox.messager("Hello jbox 3", "my title", 3000, {
    width: 350,
    icon: 'info',
    showType: 'show',
    buttons: { '去看看': true },
    submit: function (v, h, f) {
        $.jbox.info('看个蛋蛋？');
        return true;
    }
});
</code>
</pre>
<div style="text-align:right"><input class="btn" type="button" onclick="demo_5_3();" value="运　行" /></div>
       </fieldset>

       <fieldset><legend>jbox 其它成员</legend>
        <div class="content">
            <ul>
           	    <li>全局设置：</li>
             	<li style="text-indent:2em"><strong><a href="javascript:viewConfig('defaults');" title="点击查看">$.jbox.defaults</a></strong></li>
             	<li style="text-indent:2em"><strong><a href="javascript:viewConfig('stateDefaults');" title="点击查看">$.jbox.stateDefaults</a></strong></li>
             	<li style="text-indent:2em"><strong><a href="javascript:viewConfig('tipDefaults');" title="点击查看">$.jbox.tipDefaults</a></strong></li>
             	<li style="text-indent:2em"><strong><a href="javascript:viewConfig('messagerDefaults');" title="点击查看">$.jbox.messagerDefaults</a></strong></li>
             	<li style="text-indent:2em"><strong><a href="javascript:viewConfig('languageDefaults');" title="点击查看">$.jbox.languageDefaults</a></strong></li>
                <li>其它函数：</li>
                <li style="text-indent:2em"> - <strong>$.jbox.setDefaults(configs);</strong></li>
                <li style="color:#999999;">　　　└ 设置全局设置，请参考 demo.js 里的使用。</li>
                <li style="text-indent:2em"> - <strong>$.jbox.getBox();</strong></li>
                <li style="color:#999999;">　　　└ 获取最前面打开的窗口jQuery对象。</li>
                <li style="text-indent:2em"> - <strong>$.jbox.getIframe(jboxId);</strong></li>
                <li style="color:#999999;">　　　└ 获取最前面打开的或指定ID的窗口里的 iframe jQuery对象。(方便与iframe的交互)</li>
                <li style="text-indent:2em"> - <strong>$.jbox.getContent();</strong></li>
                <li style="color:#999999;">　　　└ 获取最前面打开的窗口的内容html。</li>
                <li style="text-indent:2em"> - <strong>$.jbox.setContent(content);</strong></li>
                <li style="color:#999999;">　　　└ 设置最前面打开的窗口的内容html。</li>
                <li style="text-indent:2em"> - <strong>$.jbox.getState(stateNmae);</strong></li>
                <li style="color:#999999;">　　　└ 获取最前面打开的窗口可见状态内容。（content为多状态下）</li>
                <li style="text-indent:2em"> - <strong>$.jbox.getStateName();</strong></li>
                <li style="color:#999999;">　　　└ 获取最前面打开的窗口可见状态的名称。（content为多状态下）</li>
                <li style="text-indent:2em"> - <strong>$.jbox.goToState(stateName, stateContent);</strong></li>
                <li style="color:#999999;">　　　└ 显示最前面打开的窗口的指定状态，并可设置状态内容。（content为多状态下）</li>
                <li style="text-indent:2em"> - <strong>$.jbox.nextState(stateContent);</strong></li>
                <li style="color:#999999;">　　　└ 显示最前面打开的窗口的下一个状态，并可设置状态内容。（content为多状态下）</li>
                <li style="text-indent:2em"> - <strong>$.jbox.prevState(stateContent);</strong></li>
                <li style="color:#999999;">　　　└ 显示最前面打开的窗口的上一个状态，并可设置状态内容。（content为多状态下）</li>
                <li style="text-indent:2em"> - <strong>$.jbox.close(token);</strong></li>
                <li style="color:#999999;">　　　└ 关闭最前面打开的窗口，token可以是指定jbox的ID或布尔值，如果是true显示关闭所有已打开的窗口。</li>
                <li style="text-indent:2em"> - <strong>$.jbox.closeTip();</strong></li>
                <li style="color:#999999;">　　　└ 关闭提示（由 $.jbox.tip() 打开的）。</li>
                <li style="text-indent:2em"> - <strong>$.jbox.closeMessager();</strong></li>
                <li style="color:#999999;">　　　└ 关闭提示（由 $.jbox.messager() 打开的）。</li>
            </ul>
        </div>
        <br />

示例（iframe）:    
<pre>
<code class="javascript">
// 调父窗口请用 parent 或 top，如果是多层iframe，需要调用多个parent
var html = "&lt;div style='padding:10px;'&gt;输入点什么：&lt;input type='text' id='some' name='some' /&gt;&lt;/div&gt;";
var submit = function (v, h, f) {
    if (f.some == '') {
        // f.some 或 h.find('#some').val() 等于 top.$('#some').val()
        top.$.jbox.tip("请输入点什么。", 'error', { focusId: "some" }); // 关闭设置 some 为焦点
        return false;
    }
    top.$.jbox.info("你输入了：" + f.some);

    return true;
};

top.$.jbox(html, { title: "输入", submit: submit });
</code>
</pre>
<div style="text-align:right">
<iframe src="http://www.sucaihuo.com/jquery/2/263/demo/iframe.html" width="100%" height="50"></iframe>
</div>

       </fieldset>

<!-- 全局配置说明 -->
<div id="defaults" style="display:none;">  
<pre class="config">
<code class="javascript">
$.jbox.defaults = {
    id: null, /* 在页面中的唯一id，如果为null则自动生成随机id,一个id只会显示一个jbox */
    top: '15%', /* 窗口离顶部的距离,可以是百分比或像素(如 '100px') */
    border: 5, /* 窗口的外边框像素大小,必须是0以上的整数 */
    opacity: 0.1, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
    timeout: 0, /* 窗口显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
    showType: 'fade', /* 窗口显示的类型,可选值有:show、fade、slide */
    showSpeed: 'fast', /* 窗口显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
    showIcon: true, /* 是否显示窗口标题的图标，true显示，false不显示，或自定义的CSS样式类名（以为图标为背景） */
    showClose: true, /* 是否显示窗口右上角的关闭按钮 */
    draggable: true, /* 是否可以拖动窗口 */
    dragLimit: true, /* 在可以拖动窗口的情况下，是否限制在可视范围 */
    dragClone: false, /* 在可以拖动窗口的情况下，鼠标按下时窗口是否克隆窗口 */
    persistent: true, /* 在显示隔离层的情况下，点击隔离层时，是否坚持窗口不关闭 */
    showScrolling: true, /* 是否显示浏览的滚动条 */
    ajaxData: {},  /* 在窗口内容使用get:或post:前缀标识的情况下，ajax post的数据，例如：{ id: 1 } 或 "id=1" */
    iframeScrolling: 'auto', /* 在窗口内容使用iframe:前缀标识的情况下，iframe的scrolling属性值，可选值有：'auto'、'yes'、'no' */

    title: 'jbox', /* 窗口的标题 */
    width: 350, /* 窗口的宽度，值为'auto'或表示像素的整数 */
    height: 'auto', /* 窗口的高度，值为'auto'或表示像素的整数 */
    bottomText: '', /* 窗口的按钮左边的内容，当没有按钮时此设置无效 */
    buttons: { '确定': 'ok' }, /* 窗口的按钮 */
    buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
    loaded: function (h) { }, /* 窗口加载完成后执行的函数，需要注意的是，如果是ajax或iframe也是要等加载完http请求才算窗口加载完成，
    参数h表示窗口内容的jQuery对象 */
    submit: function (v, h, f) { return true; },
    /* 点击窗口按钮后的回调函数，返回true时表示关闭窗口，
    参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
    closed: function () { } /* 窗口关闭后执行的函数 */
};
</code>
</pre>
</div>

<div id="stateDefaults" style="display:none;">  
<pre class="config">
<code class="javascript">
$.jbox.stateDefaults = {
    content: '', /* 状态的内容，不支持前缀标识 */
    buttons: { '确定': 'ok' }, /* 状态的按钮 */
    buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
    submit: function (v, h, f) { return true; } 
    /* 点击状态按钮后的回调函数，返回true时表示关闭窗口，
    参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
};
</code>
</pre>
</div>

<div id="tipDefaults" style="display:none;">  
<pre class="config">
<code class="javascript">
$.jbox.tipDefaults = {
    content: '', /* 提示的内容，不支持前缀标识 */
    icon: 'info', /* 提示的图标，可选值有'info'、'success'、'warning'、'error'、'loading'，
    默认值为'info'，当为'loading'时，timeout值会被设置为0，表示不会自动关闭。 */
    top: '40%', /* 提示离顶部的距离,可以是百分比或像素(如 '100px') */
    width: 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
    height: 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
    timeout: 2000, /* 提示显示多少毫秒后自动关闭,必须是大于0的整数 */
    closed: function () { } /* 提示关闭后执行的函数 */
};
</code>
</pre>
</div>

<div id="messagerDefaults" style="display:none;">  
<pre class="config">
<code class="javascript">
$.jbox.messagerDefaults = {
    content: '', /* 信息的内容，不支持前缀标识 */
    title: 'jbox', /* 信息的标题 */
    icon: 'none', /* 信息图标，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error' */
    width: 350, /* 信息的高度，值为'auto'或表示像素的整数 */
    height: 'auto', /* 信息的高度，值为'auto'或表示像素的整数 */
    timeout: 3000, /* 信息显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
    showType: 'slide', /* 信息显示的类型,可选值有:show、fade、slide */
    showSpeed: 600, /* 信息显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
    border: 0, /* 信息的外边框像素大小,必须是0以上的整数 */
    buttons: {}, /* 信息的按钮 */
    buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
    loaded: function (h) { }, /* 窗口加载完成后执行的函数，参数h表示窗口内容的jQuery对象 */
    submit: function (v, h, f) { return true; },
    /* 点击信息按钮后的回调函数，返回true时表示关闭窗口，
    参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
    closed: function () { } /* 信息关闭后执行的函数 */
};
</code>
</pre>
</div>

<div id="languageDefaults" style="display:none;">
<pre class="config">
<code class="javascript">
$.jbox.languageDefaults = {
    close: '关闭', /* 窗口右上角关闭按钮提示 */
    ok: '确定', /* $.jbox.prompt() 系列方法的“确定”按钮文字 */
    yes: '是', /* $.jbox.warning() 方法的“是”按钮文字 */
    no: '否', /* $.jbox.warning() 方法的“否”按钮文字 */
    cancel: '取消' /* $.jbox.confirm() 和 $.jbox.warning() 方法的“取消”按钮文字 */
};
</code>
</pre>
</div>

    
    
    </div>
</body>
</html>

<div style="width:728px;margin:0 auto">
    <script type="text/javascript">
        /*700*90 创建于 2015-06-27*/
        var cpro_id = "u2182156";
    </script>
    <script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
</div>