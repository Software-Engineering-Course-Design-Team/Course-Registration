<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*,java.util.*,java.io.* "%>
<% request.setCharacterEncoding("UTF-8");
   String information=(String)request.getAttribute("info");%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>欢迎使用课程注册系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
	<link rel="stylesheet" href="./css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
	
</head>
<body >
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="./TeaMenu.html">欢迎使用课程注册系统</a></div>
        <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">教师信息面板</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="TeaPwd.html">修改密码</a></dd>
              <dd><a href="./login.html">退出</a></dd>
            </dl>
        </ul>
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
    <div class="wrapper">
	<div class="left-nav">
          <div id="side-nav">
            <ul id="nav">
                <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        登记成绩
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                </li>
				<li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        管理课程信息
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                获取花名册
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                选择教授课程
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                查看教授课程
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                查看可教授课程
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                增加上课时间
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                删除上课时间
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                删除教授课程
                            </a>
                        </li>
					</ul>
                </li>
				 <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        查看成绩列表
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                </li>
            </ul>
          </div>
        </div>
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
        <div class="page-content">
          <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <blockquote class="layui-elem-quote">
                <%=information%>
            </blockquote>
          </div>
        </div>
        <!-- 右侧主体结束 -->
    </div>
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright">Copyright ©2020 XiaRui ZhangShiyao ShiJizhong WangHaiyan FengShenghui All Rights Reserved. </div>  
		<div class="copyright">
		 		<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=43060202000383" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="beian.png" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px;">湘公网安备 43060202000383号  湘ICP备20012047号</p></a>
		 	</div>
		 
    </div>
    <!-- 底部结束 -->

</body>

</html>