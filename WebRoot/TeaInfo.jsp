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
                if(today.getHours()>=22||today.getHours()<5)document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"-"+MM +"-"+ dd +" " + hh+":"+mm+":"+ss+"   " + day+" 夜深了，请早入睡"; 
                if(today.getHours()>=6&&today.getHours()<9)document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"-"+MM +"-"+ dd +" " + hh+":"+mm+":"+ss+"   " + day+" 一日之际在于晨，美好的一天从早上开始";
                if(today.getHours()>=9&&today.getHours()<22)document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"-"+MM +"-"+ dd +" " + hh+":"+mm+":"+ss+"   " + day+" 美好的一天，与阅读为伴";				
                setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法 
            }   

            function checkTime(i)   
            {   
                if (i<10){
                    i="0" + i;
                }   
                  return i;
            }  
        </script>
</head>
<body onload="startTime()">
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="TeaMenu.jsp?username=<%=request.getAttribute("username") %>">欢迎使用课程注册系统</a></div>
        <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">教师信息面板</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="TeaPwd.jsp?username=<%=request.getAttribute("username")%>">修改密码</a></dd>
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
                            <a href="./TeaQualiServlet?username=<%=request.getAttribute("username")%>&option=1">
                                <i class="iconfont">&#xe6a7;</i>
                                选课界面
                            </a>
                        </li>
						<li>
                            <a href="./TeaListServlet?username=<%=request.getAttribute("username")%>&option=1">
                                <i class="iconfont">&#xe6a7;</i>
                                已选教授课程列表
                            </a>
                        </li>
						<li>
                            <a href="./TeaQualiServlet?username=<%=request.getAttribute("username")%>&option=2">
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
                            <a href="./TeaListServlet?username=<%=request.getAttribute("username")%>&option=2">
                                <i class="iconfont">&#xe6a7;</i>
                                获取花名册
                            </a>
                        </li>
                        <li>
                            <a href="./TeaListServlet?username=<%=request.getAttribute("username")%>&option=3">
                                <i class="iconfont">&#xe6a7;</i>
                                录入成绩
                            </a>
                        </li>
						<li>
                            <a href="./TeaListServlet?username=<%=request.getAttribute("username")%>&option=4">
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
                欢迎使用课程注册系统！现在是北京时间：<font><span id="nowDateTimeSpan"></span></font> 
            </blockquote>
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a name="default">提示信息</a></legend>
            </fieldset>
			<%=information%>
            <!-- 右侧内容框架，更改从这里结束 -->
			<div>
			<%String option=(String)request.getAttribute("option");
			if(option.equals("changegrade"))
			{
				int CID=(int)request.getAttribute("CID");
			%>
			
			<a href="./GetStuListServlet?username=<%=request.getAttribute("username")%>&CID=<%=CID%>&option=3">
			<%}
			else if(option.equals("choosecourse"))
			{
			%>
			<a href="./TeaQualiServlet?username=<%=request.getAttribute("username")%>&option=1">
			<%}
			else if(option.equals("changepass"))
			{%>
			<a href="./login.html">
			<%}
			else 
			{%>
			<a href="TeaMenu.jsp?username=<%=request.getAttribute("username")%>">
			<%}%>
			
		   <button class="btn btn-warning pull-right" lay-submit lay-filter="login"  type="submit">确定</button>
		   </a>
		  </div>
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