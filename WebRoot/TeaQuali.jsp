<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*,java.util.*,java.io.* ,com.model.javabean.Course,com.control.DB.CourseOP,com.action.LoginCheckServlet"%>
<%int id=(int)request.getAttribute("username"); %>
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
<body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="./TeaMenu.jsp?username=<%=id%>">欢迎使用课程注册系统</a></div>
        <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">教师信息面板</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="TeaPwd.jsp&username=<%=id%>">修改密码</a></dd>
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
                            <a href="./TeaQualiServlet?username=<%=id%>&option=1">
                                <i class="iconfont">&#xe6a7;</i>
                                选课界面
                            </a>
                        </li>
						<li>
                            <a href="./TeaListServlet?username=<%=id%>&option=1">
                                <i class="iconfont">&#xe6a7;</i>
                                已选教授课程列表
                            </a>
                        </li>
						<li>
                            <a href="./TeaQualiServlet?username=<%=id%>&option=2">
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
                            <a href="./TeaListServlet?username=<%=id%>&option=2">
                                <i class="iconfont">&#xe6a7;</i>
                                获取花名册
                            </a>
                        </li>
                        <li>
                            <a href="./TeaListServlet?username=<%=id%>&option=3">
                                <i class="iconfont">&#xe6a7;</i>
                                录入成绩
                            </a>
                        </li>
						<li>
                            <a href="./TeaListServlet?username=<%=id%>&option=4">
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
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                            课程代号
                        </th>
                        <th>
                       课程名     
                        </th>
                        
                        <th>
                            开设学期
                        </th>
                        
             <%  if((request.getAttribute("ablecou")!=null)&&(request.getAttribute("unablecou")!=null)&&(request.getAttribute("option")!=null))
				  {
					  ArrayList<Course> able=(ArrayList<Course>)request.getAttribute("ablecou");
					  ArrayList<Course> unable=(ArrayList<Course>)request.getAttribute("unablecou");
					  
					  int option=(int)request.getAttribute("option");
					  if(option==1){
						  %>
						  <th>
                            操作
                        </th>
						  <%
					  }
					  else
					  {
						  %>
						  <th>
                            状态
                        </th>
						  <%}
					  %>
                    </tr>
                </thead>
				<tbody>
				  <%
				  
				  
					for(int i=0;i<able.size();i++){
						Course c=able.get(i);
						String term="";
						String stauts="";
						int cstauts;
						if(c.getTerm()%2==0)
						{
							term="第1学期";
						
						}
						else
							term="第2学期";
						if(c.getPID()==0)
						{
							stauts="可教授";
							cstauts=1;
						}
						else if(c.getPID()==id)
						{
							stauts="已选择授课";
							cstauts=2;
						}
						else
						{
							stauts="已由其他教师授课";
							cstauts=3;
						}
						
						%>
						<tr>
                        <th>
                            <%=String.valueOf(c.getCID())%>
                        </th>
                        <th>
                            <%=c.getName()%>
                        </th>
                        
                        <th>
                            <%=term%>
                        </th>
                        
                        <%
				  if (option==1&&(cstauts!=3))
                  {
                  if(cstauts==1){%>
                        <th>
                       	<form action="TeaChooseCouServlet?username=<%=id%>" method="post">
                       	<input type="hidden" name="CID" value=<%=c.getCID()%>>
                       	<input type="hidden" name="CName" value=<%=c.getName()%>>
                       	<input type="hidden" name="option" value=<%=cstauts%>>
                       	<input type="submit" name=""style="background: transparent;border:none;
    outline:none;font-size: 13px;color:#fff;background: #9A6159;padding:8px 11px;cursor: pointer;
    border-radius:10px;"  value="选课">
                        	</form>
                        	</th>
                        	<%}
                  else{%>
                  <th>
                 	<form action="TeaChooseCouServlet?username=<%=id%>" method="post">
                 	<input type="hidden" name="CID" value=<%=c.getCID()%>>
                 	<input type="hidden" name="CName" value=<%=c.getName()%>>
                       	<input type="hidden" name="option" value=<%=cstauts%>>
                 	<input type="submit" name=""style="background: transparent;border:none;
outline:none;font-size: 13px;color:#fff;background: #9A6159;padding:8px 11px;cursor: pointer;
border-radius:10px;"  value="退课">
                  	</form>
                  	</th>
                  	<%
                	  }
                  }
				  else{%>
					  <th>
                  <%=stauts%>
              		</th>
					  
                       <% }%> 
						
                    </tr>
					<%
					}
				  if(option==2){
					for(int i=0;i<unable.size();i++){
						Course c=unable.get(i);
						String term="";
						if(c.getTerm()%2==0)
						{
							term="第2学期";
						
						}
						else
							term="第1学期";
						
						
						%>
						<tr>
                        <th>
                            <%=""%>
                        </th>
                        <th>
                            <%=c.getName()%>
                        </th>
                        
                        <th>
                            <%=""%>
                        </th>
                        <th>
                            <%="尚未开设本门课程"%>
                        </th>
						
                    </tr>
					<%
					}
					
				  }
				  }
				  else
				  {
					  if(request.getAttribute("ablecou")!=null)
						  System.out.println("able null!!");
					  if(request.getAttribute("unablecou")!=null)
						  System.out.println("unable null!!");
				  }
				  
					%>
				</tbody>
            </table>
            <!-- 右侧内容框架，更改从这里结束 -->
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