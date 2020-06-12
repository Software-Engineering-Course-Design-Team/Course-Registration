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
 * Servlet implementation class DeleteStuServlet
 */
@WebServlet("/DeleteStuServlet")
public class DeleteStuServlet extends HttpServlet {
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
			request.getRequestDispatcher("/DeleteStuServlet.jsp").forward(request,response);
		}
    }
    
    private  void delete(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
    	request.setCharacterEncoding("UTF-8");
		long sID = Integer.parseInt(request.getParameter("SID"));
		Student stu = new Student();
		stu.setSID(sID);
		StudentOP stuOp=new StudentOP();
		long SID = stuOp.DeleteStudent(stu);
		String message = null;
		if(SID==40004) {
			  message = "删除学生信息失败！原因:学生信息不存在！;";
		}else if(SID==40005) {
			message = "删除学生信息失败！原因:学生有未缴费！;";
		}else if(SID==40006) {
			message = "删除学生信息失败！原因:学生有选课信息！;";
		}else if(SID==40007) {
			message = "删除学生信息失败！原因:学生有课程信息！;";
		}else if(SID==40008){
			message = "删除学生信息成功！";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");
        
    }

}
