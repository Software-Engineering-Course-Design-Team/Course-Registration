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
            function confirmDel(){
                if(!window.confirm("您确定要删除这条记录吗")){
              	  window.event.returnValue = false;
                }
      		}
            function confirmUpdate(){
                if(!window.confirm("请您核实缴费情况，若缴费成功，请确认")){
              	  window.event.returnValue = false;
                }
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
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a name="default">填写基本信息</a></legend>
            </fieldset>
            <form class="layui-form" action="AdminTemInfo" Id="form1" method="post">
                <div class="layui-form-item">
                    <label for="L_ClassName" class="layui-form-label">
                        <span class="x-red">*</span>课程名称
                    </label>
                    <div class="layui-input-inline">
                    <%if(session.getAttribute("ClassName")==null){ %>
                        <input type="text" id="L_ClassName" name="ClassName"
                        autocomplete="off" class="layui-input">
                        <%}else{ %>
                        <input type="text" id="L_ClassName" name="ClassName"
                        autocomplete="off" class="layui-input" value=<%=session.getAttribute("ClassName") %>>
                        <%} %>
                    </div>
                    <label for="L_BeginWeek" class="layui-form-label">
                        <span class="x-red">*</span>起始周数
                    </label>
                    <div class="layui-input-inline" style="width:190px;">
            			<select name="BeginWeek" id="L_BeginWeek" >
            			<option value="" disabled selected hidden>请选择</option>
            			<%
            				for(int i=1;i<=20;i++){
            					if(session.getAttribute("BeginWeek")!=null&&Integer.parseInt((String)session.getAttribute("BeginWeek"))==i){
            			%>
            			<option value=<%=i%> selected><%=i%></option>
            			<%}else{ %>
            			<option value=<%=i%>><%=i%></option>
            			<%
            			}
            				}
            			%>
            			</select>
            		</div>
                    <label for="L_EndWeek" class="layui-form-label">
                        <span class="x-red">*</span>终止周数
                    </label>
                    <div class="layui-input-inline" style="width:190px;">
            			<select name="EndWeek" id="L_EndWeek">
            			<option value="" disabled selected hidden>请选择</option>
            			<%
            				for(int i=1;i<=20;i++){
            					if(session.getAttribute("EndWeek")!=null&&Integer.parseInt((String)session.getAttribute("EndWeek"))==i){
            			%>
            			<option value=<%=i%> selected><%=i%></option>
            			<%}else{ %>
            			<option value=<%=i%>><%=i%></option>
            			<%
            			}
            				}
            			%>
            			</select>
            		</div>
                </div>
				<div class="layui-form-item">
                    <label for="L_Depart" class="layui-form-label">
                        <span class="x-red">*</span>所属部门
                    </label>
                    <div class="layui-input-inline" style="width:190px;">
            			<select name="Depart" id="L_Depart" >
            			<option value="" disabled selected hidden>请选择</option>
            			<%
            			ArrayList<DepInfo> s=(ArrayList<DepInfo>)request.getAttribute("depinfo");
            				for(DepInfo tt:s){
            					if(session.getAttribute("Term")!=null&&Integer.parseInt((String)session.getAttribute("Depart"))==tt.getDID()){
            			%>
            			<option value=<%=tt.getDID()%> selected><%="("+tt.getDID()+")"+tt.getName()%></option>
            			<%}else{ %>
            			<option value=<%=tt.getDID()%>><%="("+tt.getDID()+")"+tt.getName()%></option>
            			<%
            				}
            				}
            			%>
            		</select>
            		</div>
                    <label for="L_Fee" class="layui-form-label">
                        <span class="x-red">*</span>学习费用
                    </label>
                    <div class="layui-input-inline">
                         <%if(session.getAttribute("Fee")==null){ %>
                        <input type="text" id="L_Fee" name="Fee" 
                        autocomplete="off" class="layui-input">
                        <%}else{ %>
                        <input type="text" id="L_Fee" name="Fee" 
                        autocomplete="off" class="layui-input" value=<%=session.getAttribute("Fee") %>>
                        <%} %>
                    </div>
                    <label for="L_Term" class="layui-form-label">
                        <span class="x-red">*</span>上课学期
                    </label>
                     <div class="layui-input-inline" style="width:190px;">
            			<select name="Term" id="L_Term">
            			<option value="" disabled selected hidden>请选择</option>
            			<%
            				for(int i=1;i<=8;i++){
            				if(session.getAttribute("Term")!=null&&Integer.parseInt((String)session.getAttribute("Term"))==i){
            			%>
            			<option value=<%=i%> selected><%="第"+i+"学期"%></option>
            			<%}else{ %>
            			<option value=<%=i%>><%="第"+i+"学期"%></option>
            			<%
            				}
            				}
            			%>
            			</select>
            		</div>
            		</div>
				<div class="layui-form-item">
       				<script language="JavaScript"  defer=true>
       				function AdjustTime9(){
       					if(!window.confirm("重置后，您所有未保存的信息将全部丢失，请认真确认您的重置请求！")){
               				window.event.returnValue = false;
               			}
       				}
       				</script>
            		 <button class="btn btn-warning pull-right"  type="submit" onclick="AdjustTime8()"
                     name="op" value=2>
                                                           保存当前信息
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-warning pull-right" type="submit" onclick="AdjustTime5()"
                    name="op" value=1>
                                                      清空当前信息
                    </button>
                </div>
            </form>
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a>填写上课信息</a></legend>
            </fieldset>
            <form class="layui-form" action="AdminTempServlet" method="post">
                <input type="hidden" name="oopp" value=2>
            	<div class="layui-form-item">
                    <label for="L_Week" class="layui-form-label">
                        <span class="x-red">*</span>星期数
                    </label>
                    <div class="layui-input-inline" style="width:190px;">
            			<select name="Week" id="L_Week">
            			<option value="" disabled selected hidden>请选择</option>
            			<%
            				for(int i=1;i<=7;i++){
            			%>
            			<option value=<%=i%>><%=i%></option>
            			<%
            				}
            			%>
            			</select>
            		</div>
                    <label for="L_BeginC" class="layui-form-label">
                        <span class="x-red">*</span>起始节数
                    </label>
                    <div class="layui-input-inline" style="width:190px;">
            			<select name="BeginC" id="L_BeginC">
            			<option value="" disabled selected hidden>请选择</option>
            			<%
            				for(int i=1;i<=11;i++){
            			%>
            			<option value=<%=i%>><%="第"+i+"节"%></option>
            			<%
            				}
            			%>
            			</select>
            		</div>
                     <label for="L_EndC" class="layui-form-label">
                        <span class="x-red">*</span>终止节数
                    </label>
                    <div class="layui-input-inline" style="width:190px;">
            			<select name="EndC" id="L_EndC">
            			<option value="" disabled selected hidden>请选择</option>
            			<%
            				for(int i=1;i<=11;i++){
            			%>
            			<option value=<%=i%>><%="第"+i+"节"%></option>
            			<%
            				}
            			%>
            			</select>
            		</div>
                </div>
				<div class="layui-form-item">
                     <label for="L_Place" class="layui-form-label">
                        <span class="x-red">*</span>上课地点
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_Place" name="Place" 
                        autocomplete="off" class="layui-input">
                    </div>
                    <script language="JavaScript"  defer=true>
       				function AdjustTime5(){
       					if(!window.confirm("清空后，您所有的上课时间记录将全部丢失，请认真确认您的请求！")){
               				window.event.returnValue = false;
               				return false;
               			}
       					document.getElementById("form1").submit();
       				}
       				</script>
                    <div style="float:right">
                    <button class="btn btn-warning pull-right" type="submit" onclick="AdjustTime5()"
                    name="op" value=1>
                                                      清空所有上课时间记录
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <script language="JavaScript"  defer=true>
            		function AdjustTime1(){
            			var s1=document.getElementById("L_Week");
               			var index=s1.selectedIndex; 
               			if(index==0){
               				window.confirm("请补全星期信息！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			var i1=document.getElementById("L_BeginC");
               			var index=i1.selectedIndex; 
               			if(index==0){
               				window.confirm("请补全起始节数信息！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			var ii1=i1.options[index].value;
               			var i2=document.getElementById("L_EndC");
               			var index=i2.selectedIndex; 
               			if(index==0){
               				window.confirm("请补全终止节数信息！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			var name = document.getElementById('L_Place');
            			if(name.value==""){
            				window.confirm("请补全上课地点信息！");
                        	window.event.returnValue = false;
                        	return false;
            			}
               			var ii2=i2.options[index].value;
               			if(parseInt(ii2)<parseInt(ii1)){
               				window.confirm("您的这条课程记录非法，因为您的开始节数大于了终止节数！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			if(!window.confirm("请确认您的提交请求！")){
               				window.event.returnValue = false;
               			}
               			document.getElementById("form1").submit();
               		}
       				</script>
                    <button class="btn btn-warning pull-right"  type="submit" onclick="AdjustTime1()"
                     name="op" value=2>
                                                       形成一条上课时间记录
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                              星期数
                        </th>
                        <th>
                               起始节数
                        </th>
                        <th>
                           终止节数
                        </th>
                         <th>
                       上课地点
                        </th>
                        <th>
                       操作
                        </th>
                    </tr>
                </thead>
                <script language="JavaScript"  defer=true>
       				function AdjustTime4(){
       					if(!window.confirm("您确认删除该条记录？")){
               				window.event.returnValue = false;
               			}
       				}
       			</script>
                <% if(session.getAttribute("TempList")!=null){ 
                	ArrayList<CouTime> ct=(ArrayList<CouTime>)session.getAttribute("TempList");
                %>
                <tbody>
                <% int tempint=-1; 
                for(CouTime i:ct){
                	tempint++;%>
                <form action="AdminTemDelServlet" method="post">
                <tr>
						<th>
                            <%=i.getWeekDay()%>
                        </th>
                        <th>
                            <%=i.getBeginC()%>
                        </th>
                        <th>
                            <%=i.getEndC()%>
                        </th>
                         <th>
                            <%=i.getAddress()%>
                        </th>
                         <th>
                         <input type="hidden" name="op" value=1>
                         <input type="hidden" name="index" value=<%=tempint%>>
                    <button class="btn btn-warning pull-right" type="submit" onclick="AdjustTime4()">
                                                      删除本条记录
                    </button>
                        </th>
                </tr>
                </form>
                <%} %>
                </tbody>
                <%}
                %>
            </table>
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a >提交课程信息</a></legend>
            </fieldset>
            <div >
            	<script language="JavaScript"  defer=true>
            		function AdjustTime2(){
            			var name = document.getElementById('L_ClassName');
            			if(name.value==""){
            				window.confirm("请补全课程名称信息！");
                        	window.event.returnValue = false;
                        	return false;
            			}
            		    var fee = document.getElementById('L_Fee');
            		    if(fee.value==""){
            		    	window.confirm("请补全费用信息！");
                        	window.event.returnValue = false;
                        	return false;
            		    }
            			var s1=document.getElementById("L_Depart");
               			var index=s1.selectedIndex; 
               			if(index==0){
               				window.confirm("请补全部门信息！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			var s1=document.getElementById("L_Term");
               			var index=s1.selectedIndex; 
               			if(index==0){
               				window.confirm("请补全学期信息！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			var i1=document.getElementById("L_BeginWeek");
               			var index=i1.selectedIndex; 
               			if(index==0){
               				window.confirm("请补全起始周数信息！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			var ii1=i1.options[index].value;
               			var i2=document.getElementById("L_EndWeek");
               			var index=i2.selectedIndex; 
               			if(index==0){
               				window.confirm("请补全终止周数信息！");
                        	window.event.returnValue = false;
                        	return false;
               			}
               			var ii2=i2.options[index].value;
               			if(parseInt(ii2)<parseInt(ii1)){
               				window.confirm("您的这条课程信息非法，因为您的开始周数大于了终止周数！");
                        	window.event.returnValue = false;
               			}
               			if(!window.confirm("请确认您的提交请求！在提交前，请确认您已经保存了课程信息和时间信息！")){
               				window.event.returnValue = false;
               			}
               		}
       				</script>
       				<script language="JavaScript"  defer=true>
       				function AdjustTime3(){
       					if(!window.confirm("重置后，您所有未保存的信息将全部丢失，请认真确认您的重置请求！")){
               				window.event.returnValue = false;
               			}
       				}
       				</script>
       				<form action="AdminInseeClass" method="post">
       				<input type="hidden" name="oopp" value=2>
                    <button class="btn btn-warning pull-right" type="submit" onclick="AdjustTime2()"
                    name="oop" value=1>
                                                      更新课程信息
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-warning pull-right" type="submit" onclick="AdjustTime3()"
                    name="oop" value=2>
                                                          重置课程信息
                    </button>
                    </form>
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