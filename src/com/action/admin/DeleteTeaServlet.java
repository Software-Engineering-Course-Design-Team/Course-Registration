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
 * Servlet implementation class DeleteTeaServlet
 */
@WebServlet("/DeleteTeaServlet")
public class DeleteTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	 	String method=request.getParameter("action");
        if(method.equals("search"))
        {
            search(request, response);
        }
        else if(method.equals("delete")){
            delete(request, response);
        }	
	}
	private void search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
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
    
    private  void delete(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
    	request.setCharacterEncoding("UTF-8");
		long pID = Integer.parseInt(request.getParameter("PID"));
		Teacher tea = new Teacher();
		tea.setPID(pID);
		TeacherOP teaOp=new TeacherOP();
		long PID = teaOp.DeleteTeacher(tea);
		String message = null;
		if(PID==20002) {
			  message = "删除教师信息失败！原因:教师信息不存在！;";
		}else if(PID==20009) {
			message = "删除教师信息失败！原因:教师有授课信息！;";
		}else if(PID==20010) {
			message = "删除教师信息失败！原因:教师有授课资格！;";
		}else if(PID==20003){
			message = "删除教师信息成功！";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");
        
    }

}
