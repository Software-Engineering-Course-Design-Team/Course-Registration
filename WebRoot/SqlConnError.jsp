<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String value=(String)request.getAttribute("lastURL");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据库连接出错！</title>
<link rel="stylesheet" href="css/denglu.css">
</head>
<body>
	<div class="box">
		<form action="" method="post">
			<input type="hidden" name="lastURL" value=<%="/"+value %> required="required">
			<p style="font-size:15px;color:red">数据库连接出错！请返回重试！</p>
		
		</form>
		
	</div>

</body>
</html>