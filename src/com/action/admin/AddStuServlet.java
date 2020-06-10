package com.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.StudentOP;
import com.model.javabean.Student;

@WebServlet("/AddStuServlet")
public class AddStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		long idNumber = Integer.parseInt(request.getParameter("idNumber"));
		long department = Integer.parseInt(request.getParameter("department"));
		String status = request.getParameter("status");
		//TODO(sjz):处理日期格式问题
		//java.sql.Date birthday=java.sql.Date.valueOf(request.getParameter("birthday"));
		//java.sql.Date gradDate=java.sql.Date.valueOf(request.getParameter("gradDate"));
		String birthday = request.getParameter("birthday");
		String gradDate = request.getParameter("gradDate");
		String sex = request.getParameter("sex");
		
		Student stu = new Student(name, sex, gradDate, birthday, idNumber, 0, department, status);
		StudentOP stuOp=new StudentOP();
		long SID = stuOp.InsertStudent(stu);
		String message = null;
		if(SID==40001) {
			  message = "添加学生失败！原因:信息不完整;";
		}else if(SID==40002) {
			message = "添加学生失败！原因:数据库插入操作失败;";
		}else if(SID==4003) {
			message = "添加学生失败！原因:学院不存在;";
		}else {
			message = "添加学生成功！学生Id:"+SID+";";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");

	}
}
