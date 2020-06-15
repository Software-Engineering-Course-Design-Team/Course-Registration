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
        <div class="logo"><a href="TeaMenu.jsp?username=<%=request.getParameter("username") %>">欢迎使用课程注册系统</a></div>
        <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">教师信息面板</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="TeaPwd.jsp?username=<%=request.getParameter("username") %>">修改密码</a></dd>
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
                        课程信息管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        
						<li>
                            <a href="./TeaQualiServlet?username=<%=request.getParameter("username") %>&option=1">
                                <i class="iconfont">&#xe6a7;</i>
                                选课界面
                            </a>
                        </li>
						<li>
                            <a href="./TeaListServlet?username=<%=request.getParameter("username") %>&option=1">
                                <i class="iconfont">&#xe6a7;</i>
                                已选教授课程列表
                            </a>
                        </li>
						<li>
                            <a href="./TeaQualiServlet?username=<%=request.getParameter("username") %>&option=2">
                                <i class="iconfont">&#xe6a7;</i>
                                查看可教授课程
                            </a>
                        </li>
					</ul>
                </li>
				 <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                       成绩管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        
						<li>
                            <a href="./TeaListServlet?username=<%=request.getParameter("username") %>&option=2">
                                <i class="iconfont">&#xe6a7;</i>
                                获取花名册
                            </a>
                        </li>
                        <li>
                            <a href="./TeaListServlet?username=<%=request.getParameter("username") %>&option=3">
                                <i class="iconfont">&#xe6a7;</i>
                                录入成绩
                            </a>
                        </li>
						<li>
                            <a href="./TeaListServlet?username=<%=request.getParameter("username") %>&option=4">
                                <i class="iconfont">&#xe6a7;</i>
                                查看成绩列表
                            </a>
                        </li>
					</ul>
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
            <form class="layui-form" action="ChangeGradeServlet?username=<%=request.getParameter("username") %>" method="post">
			   <input type="hidden" name="SID" value=<%=request.getAttribute("SID")%>>
			   <input type="hidden" name="CID" value=<%=request.getAttribute("CID")%>>
			   <input type="hidden" name="SName" value=<%=request.getAttribute("SName")%>>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                        <span class="x-red"></span>学生姓名：
                    </label>
                    <label for="L_repass" class="layui-form-label">
                        <span class="x-red"></span><%= request.getAttribute("SName")%>
                    </label>
                </div>
                <div class="layui-form-item">
                    <label for="L_pass" class="layui-form-label">
                        <span class="x-red"></span>学号：
                    </label>
                    <label for="L_repass" class="layui-form-label">
                        <span class="x-red"></span><%= request.getAttribute("SID")%>
                    </label>
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                        <span class="x-red">*</span>成绩：
                    </label>
                    <div class="layui-input-inline">
                    <%String grade=(String)request.getAttribute("grade");
                    if(grade.equals(""))
                    	{%>
                  <input id="L_count" name="grade"  required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
                        <%}
                    else{%>
                    <input id="L_count" name="grade" value=<%= request.getAttribute("grade")%> required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
                        <%} %>
                    </div>
                    
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <button  class="layui-btn" lay-filter="save" lay-submit="">
                        确定
                    </button>
                </div>
            </form>
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