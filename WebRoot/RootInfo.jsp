<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*,com.model.javabean.DepInfo,com.model.javabean.CouTime,java.util.ArrayList.*"%>
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
       </script>
       
         <%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader("Expires",0);
%>
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
                            <a href="AdminInsClass">
                                <i class="iconfont">&#xe6a7;</i>
                                增加课程信息
                            </a>
                        </li>
						<li>
                            <a href="AdminFinClass">
                                <i class="iconfont">&#xe6a7;</i>
                                管理课程信息
                            </a>
                        </li>
                        <li>
                            <a href="AdminManServlet">
                                <i class="iconfont">&#xe6a7;</i>
                                管理选课记录
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
                    <a href="RootStuFee.html">
                        <i class="iconfont">&#xe761;</i>
                        维护学费信息
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                </li>
				<li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe761;</i>
                        维护课程资格
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
					<ul class="sub-menu" style="display:none">
                        <li>
                            <a href="./AdminAddQuali.html">
                                <i class="iconfont">&#xe6a7;</i>
                                增加课程资格
                            </a>
                        </li>
						<li>
                            <a href="./AdminDeleteQuali.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                删除课程资格
                            </a>
                        </li>
					</ul>
                </li>

				<li class="list" >
                    <a href="./GetDepServlet">
                        <i class="iconfont">&#xe6a3;</i>
                        注册状态管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>

                </li>
            </ul>
          </div>
        </div>
        <!-- 右侧主体开始 -->
        <div class="page-content">
          <div class="content">
            <blockquote class="layui-elem-quote">
                欢迎使用课程注册系统！现在是北京时间<font><span id="nowDateTimeSpan"></span></font> 
            </blockquote>
            <%
            if((int)request.getAttribute("RootInfo")==8){
            %>
                                          删除失败：请确保您要删除的课程在选课列表里已经没有记录！<br>
            <%
            }else if((int)request.getAttribute("RootInfo")==7){
            %>
                                            恭喜您！修改成功！<br>
            <%
            }else if((int)request.getAttribute("RootInfo")==6){
            %>
            	恭喜您！删除成功！<br>
            <%
            }else if((int)request.getAttribute("RootInfo")==3){
            %>
            	恭喜您！插入成功！<br>
            <%
            	}else if((int)request.getAttribute("RootInfo")==1){
            %>
            	您的课程信息自身存在时间上的冲突！请修改后重新提交！<br>
            <%
            	}else if((int)request.getAttribute("RootInfo")==2){
            		String cname=(String)request.getAttribute("cname");
					CouTime temp=(CouTime)request.getAttribute("ctinfo");
            %>
            	您的课程与其他课程存在地点上的冲突！请修改后重新提交！<br>
            	冲突的课程信息为：<br>
            	课程名：<%=cname%><br>
            	时间：星期<%=temp.getWeekDay()%>,第<%=temp.getBeginC()%>-<%=temp.getEndC()%>节<br>
            	地点：<%=temp.getAddress()%><br>
            <%
            	}else if((int)request.getAttribute("RootInfo")==4){
            %>
            	您的课程信息中应至少包含一条上课的时间信息！<br>
            <%
            	}else if((int)request.getAttribute("RootInfo")==5){
            %>
            	您的课程信息并不完整！请您确认您已经保存了课程信息和上课信息之后再提交！<br>
            <%
            	}
            %>
            <% if((int)request.getAttribute("RootInfo")<6){ %>
            <form action="AdminInsClass" method="post">
            <input type="submit" style="background: transparent;border:none;
    outline:none;font-size: 13px;color:#fff;background: #9A6159;padding:8px 11px;cursor: pointer;
    border-radius:10px;" value="返回操作页面">
             </form>
             <% }else{%>
             <form action="AdminFinClass" method="post">
            <input type="submit" style="background: transparent;border:none;
    outline:none;font-size: 13px;color:#fff;background: #9A6159;padding:8px 11px;cursor: pointer;
    border-radius:10px;" value="返回查询页面">
             </form>
             <%} %>
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