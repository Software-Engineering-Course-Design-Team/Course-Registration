<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*,javax.servlet.http.HttpSession"%>
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
</head>
<body onload="startTime()"> 
    <div class="container">
        <div class="logo"><a href="./index-root.jsp">欢迎使用课程注册系统</a></div>
        <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">学生信息面板</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="StuPwd.html">修改密码</a></dd>
              <dd><a href="./login.html">
              	退出
              	</a></dd>
            </dl>
        </ul>
    </div>
    <!-- 中部开始 -->
    <div class="wrapper">
	<div class="left-nav">
          <div id="side-nav">
            <ul id="nav">
                <li class="list">
                    <a href="<%=request.getContextPath() +"/StudFeeServlet?username="+request.getParameter("username") %>">
                        <i class="iconfont">&#xe761;</i>
                        获取学费账单
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
                                选择必选课程
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                选择候选课程
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                删除所选课程
                            </a>
                        </li>
						<li>
                            <a href="javascript:;">
                                <i class="iconfont">&#xe6a7;</i>
                                查看课程列表
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
        <!-- 右侧主体开始 -->
        <div class="page-content">
          <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <blockquote class="layui-elem-quote">
                欢迎使用课程注册系统！现在是北京时间<font><span id="nowDateTimeSpan"></span></font> 
            </blockquote>
           <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a name="default">吉林大学介绍</a></legend>
            </fieldset>
			吉林大学（Jilin University）简称“吉大”，位于吉林省省会长春，是教育部直属、中央直管副部级建制的全国重点大学，国家“双一流”、“211工程”、“985工程”重点建设，入选珠峰计划、2011计划、111计划、卓越法律人才教育培养计划、卓越工程师教育培养计划、卓越医生教育培养计划、卓越农林人才教育培养计划、国家建设高水平大学公派研究生项目、国家大学生创新性实验计划、新工科研究与实践项目、国家级大学生创新创业训练计划、国家创新人才培养示范基地、全国深化创新创业教育改革示范高校、中国政府奖学金来华留学生接收院校，首批建立研究生院的22所大学之一，亚太国际教育协会、21世纪学术联盟、中俄交通大学联盟、粤港澳大湾区物流与供应链创新联盟、医学双一流建设联盟成员。
吉林大学始建于1946年，1952年经院系调整成为建国后中国共产党亲手创建的第一所综合性大学，1960年被国务院列为国家重点大学。2000年，原吉林大学、吉林工业大学、白求恩医科大学、长春科技大学、长春邮电学院合并组建新吉林大学。2004年，原中国人民解放军军需大学转隶并入。
截至2019年6月，学校6个校区7个校园占地611万多平方米，建筑面积276万平方米；下设46个学院；教师6624人，在校全日制学生72376人；本科专业139个，一级学科硕士点60个，一级学科博士学位授权点48个，博士后科研流动站42个；一级学科国家重点学科4个（覆盖17个二级学科），二级学科国家重点学科15个，国家重点（培育）学科4个。
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