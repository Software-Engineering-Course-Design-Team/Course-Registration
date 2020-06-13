<!doctype html>
<%@page import="com.model.javabean.Student"%>
<%@page import="com.model.javabean.DepInfo"%>
<%@page import="com.control.DB.DepartOP"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*,com.model.*,java.util.ArrayList.*"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎使用课程注册系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <link rel="stylesheet" href="./lib/layui/css/layui.css" media="all">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script language="JavaScript">
            function startTime()   
            {   
                var today=new Date();//定义日期对象   
                var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年    
                var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年    
                var dd = today.getDate();//通过日期对象的getDate()方法返回年     
                var hh=today.getHours();//通过日期对象的getHours方法返回小时   
                var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟   
                var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒   
                // 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09   
                MM=checkTime(MM);
                dd=checkTime(dd);
                mm=checkTime(mm);   
                ss=checkTime(ss);    
                var day; //用于保存星期（getDay()方法得到星期编号）
                if(today.getDay()==0)   day   =   "星期日 " 
                if(today.getDay()==1)   day   =   "星期一 " 
                if(today.getDay()==2)   day   =   "星期二 " 
                if(today.getDay()==3)   day   =   "星期三 " 
                if(today.getDay()==4)   day   =   "星期四 " 
                if(today.getDay()==5)   day   =   "星期五 " 
                if(today.getDay()==6)   day   =   "星期六 " 
                if(today.getHours()>=22||today.getHours()<5)document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"年"+MM +"月"+ dd +"日 " + hh+":"+mm+":"+ss+"   " + day+" 夜深了，请早入睡"; 
                if(today.getHours()>=6&&today.getHours()<9)document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"年"+MM +"月"+ dd +"日 " + hh+":"+mm+":"+ss+"   " + day+" 一日之际在于晨，美好的一天从早上开始";
                if(today.getHours()>=9&&today.getHours()<22)document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"年"+MM +"月"+ dd +"日 " + hh+":"+mm+":"+ss+"   " + day+" 美好的一天，与阅读为伴";				
                setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法 
            }   

            function checkTime(i)   
            {   
                if (i<10){
                    i="0" + i;
                }   
                  return i;
            }  
            function confirmDel(){
                if(!window.confirm("您确定要删除吗?")){
              	  window.event.returnValue = false;
                }
      		}
        </script>
</head>
<body onload="startTime()"> 
   <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="RootMenu.html">欢迎使用课程注册系统</a></div>
        <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">管理员信息面板</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="AdminPwd.html">修改密码</a></dd>
              <dd><a href="./login.html">退出</a></dd>
            </dl>
        </ul>
    </div>
    <!-- 中部开始 -->
    <div class="wrapper">
	<div class="left-nav">
          <div id="side-nav">
            <ul id="nav">
                <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        维护学生信息
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="./AddStuServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                增加学生信息
                            </a>
                        </li>
						<li>
                            <a href="./FindStuServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                查询学生信息
                            </a>
                        </li>
						<li>
                            <a href="./UpdateStuServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                修改学生信息
                            </a>
                        </li>
						<li>
                            <a href="./DeleteStuServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                删除学生信息
                            </a>
                        </li>
					</ul>
                </li>
				<li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        维护课程信息
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                增加课程信息
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                查询课程信息
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                修改课程信息
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
                                删除课程信息
                            </a>
                        </li>
					</ul>
                </li>
				<li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        维护教授信息
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="./AddTeaServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                增加教授信息
                            </a>
                        </li>
						<li>
                            <a href="./FindTeaServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                查询教授信息
                            </a>
                        </li>
						<li>
                            <a href="./UpdateTeaServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                修改教授信息
                            </a>
                        </li>
						<li>
                            <a href="./DeleteTeaServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                删除教授信息
                            </a>
                        </li>
					</ul>
                </li>
				<li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        维护部门信息
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="./AddDepServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                增加部门信息
                            </a>
                        </li>
						<li>
                            <a href="./FindorDeleteDepServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                查询部门信息
                            </a>
                        </li>
						<li>
                            <a href="./UpdateDepServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                修改部门信息
                            </a>
                        </li>
						<li>
                            <a href="./FindorDeleteDepServlet.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                删除部门信息
                            </a>
                        </li>
					</ul>
                </li>
				<li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        维护学费信息
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                更新学费状态
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                查询学费信息
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                查看未缴费名单
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                删除学费信息
                            </a>
                        </li>
					</ul>
                </li>
				<li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        维护课程资格
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                增加课程资格
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                删除课程资格
                            </a>
                        </li>
					</ul>
                </li>
				<li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        注册状态管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        <li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                开启注册状态
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                关闭注册状态
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
          </div>
        </div>
        <!-- 右侧主体开始 -->
        <div class="page-content">
          <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <blockquote class="layui-elem-quote">
                欢迎使用课程注册系统！现在是北京时间<font><span id="nowDateTimeSpan"></span></font> 
            </blockquote>
            <form class="layui-form" action="DeleteStuServlet" method="post">
                <div class="layui-form-item">
                    <label for="L_SID" class="layui-form-label">
                        <span class="x-red">*</span>学号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_SID" name="SID" required="" lay-verify="SID"
                        autocomplete="off" class="layui-input" value="${stuInfo.getSID()}" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <button class="btn btn-warning pull-right" name="action" value="search" type="submit">
                        显示学生信息
                    </button>
                </div>
                <table class="layui-table">
                <thead>
                    <tr>
                    <th>
                            学号
                        </th>
                        <th>
                            姓名
                        </th>
                        <th>
                            性别
                        </th>
                        <th>
                            学院
                        </th>
                        <th>
                            身份证号
                        </th>
                        <th>
                            生日
                        </th>
                        <th>
                            毕业日期
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                </thead>
				<tbody>
				  <%
				  if(request.getAttribute("stuInfo")!=null){
					  Student stu=(Student)request.getAttribute("stuInfo");
					  DepartOP depOp = new DepartOP();
					  DepInfo dep = new DepInfo();
					  dep.setDID(stu.getDID());
					  DepInfo temp = depOp.FindDepartDID(dep);
						%>
						<tr>
                        <th>
                            <%=stu.getSID()%>
                        </th>
                        <th>
                            <%=stu.getName()%>
                        </th>
                        <th>
                            <%=stu.getSex()%>
                        </th>
                        <th>
                            <%=temp.getName()%>
                        </th>
                        <th>
                            <%=stu.getIdcard()%>
                        </th>
                        <th>
                            <%=stu.getBirthday()%>
                        </th>
                        <th>
                            <%=stu.getGradDate()%>
                        </th>
                        <th>
                        	<form action="DeleteStuServlet" method="post">
                            <input type="submit" style="background: transparent;border:none;
    outline:none;font-size: 13px;color:#fff;background: #9A6159;padding:8px 11px;cursor: pointer;
    border-radius:10px;" name="action" value="delete" onclick="confirmDel()">
                            </form>
                        </th>
                    </tr>
                    <% } %>
				</tbody>
            </table>
            </form>
            <!-- 右侧内容框架，更改从这里结束 -->
          </div>
        </div>
        </div>
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
