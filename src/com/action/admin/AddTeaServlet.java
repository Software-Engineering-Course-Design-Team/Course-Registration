package com.action.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.TeacherOP;
import com.model.javabean.Teacher;

/**
 * Servlet implementation class AddTeaServlet
 */
@WebServlet("/AddTeaServlet")
public class AddTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		if (name.equals("")) name = null;
		long idNumber = Integer.parseInt(request.getParameter("idNumber"));
		long department = Integer.parseInt(request.getParameter("department"));
		String status = request.getParameter("status");
		if (status.equals("")) name = null;
		//TODO(sjz):处理日期格式问题
		//java.sql.Date birthday=java.sql.Date.valueOf(request.getParameter("birthday"));
		//java.sql.Date gradDate=java.sql.Date.valueOf(request.getParameter("gradDate"));
		String birthday = request.getParameter("birthday");
		if (birthday.equals("")) name = null;
		String sex = request.getParameter("sex");
		if (sex.equals("")) name = null;
		
		Teacher tea = new Teacher(name, sex, birthday, idNumber, 0, department, status);
		TeacherOP teaOp=new TeacherOP();
		long PID = teaOp.InsertTeacher(tea);
		String message = null;
		if(PID==20000) {
			  message = "添加教师失败！原因:信息不完整;";
		}else if(PID==20001) {
			message = "添加教师失败！原因:数据库插入操作失败;";
		}else if(PID==20007) {
			message = "添加教师失败！原因:学院不存在;";
		}else {
			message = "添加教师成功！教师Id:"+PID+";";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");
	}
}
