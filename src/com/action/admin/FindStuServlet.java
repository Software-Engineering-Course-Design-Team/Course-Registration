package com.action.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.StudentOP;
import com.model.javabean.Student;

/**
 * Servlet implementation class FindStuServlet
 */
@WebServlet("/FindStuServlet")
public class FindStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		StudentOP stuOp=new StudentOP();
		long sID = Integer.parseInt(request.getParameter("SID"));
		Student stu = new Student();
		stu.setSID(sID);
		Student result = stuOp.FindStudent(stu);
		if(result.getSID()==40009) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('找不到该学生！');window.location.href='RootMenu.html'</script>");
		}else {
			request.setAttribute("stuInfo",result);
			request.getRequestDispatcher("/FindStuServlet.jsp").forward(request,response);
		}
    }
}
