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
 * Servlet implementation class FindTeaServlet
 */
@WebServlet("/FindTeaServlet")
public class FindTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TeacherOP teaOp=new TeacherOP();
		long pID = Integer.parseInt(request.getParameter("PID"));
		Teacher tea = new Teacher();
		tea.setPID(pID);
		Teacher result = teaOp.FindTeacher(tea);
		if(result.getPID()==20002) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('找不到该教师！');window.location.href='RootMenu.html'</script>");
		}else {
			request.setAttribute("teaInfo",result);
			request.getRequestDispatcher("/DeleteTeaServlet.jsp").forward(request,response);
		}
    }
}
