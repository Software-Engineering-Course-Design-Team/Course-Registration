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
 * Servlet implementation class UpdateTeaServlet
 */
@WebServlet("/UpdateTeaServlet")
public class UpdateTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	 	String method=request.getParameter("action");
        if(method.equals("search"))
        {
            search(request, response);
        }
        else if(method.equals("update")){
            update(request, response);
        }	
	}
	private void search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
		try {
		TeacherOP teaOp=new TeacherOP();
		long pID = Integer.parseInt(request.getParameter("PID"));
		Teacher tea = new Teacher();
		tea.setPID(pID);
		Teacher result = teaOp.FindTeacher(tea);
		if(result.getPID()==20005) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('找不到该教师！');window.location.href='RootMenu.html'</script>");
		}else {
			request.setAttribute("result",result);
			request.getRequestDispatcher("/UpdateTeaServlet.jsp").forward(request,response);
		}
		}catch(Exception e)
		{
			String path=request.getHeader("Referer");
			String last=path.substring(path.length()-1);
			String s[]=path.split("/");
			String lastURL;
			for(int i=0;i<s.length;i++)
			{
				System.out.println(s[i]);
			}
			if(last.equals("/")||s[s.length-1].equals(request.getHeader("Referer")))
			{
				lastURL="";
			}
			else
			{
				lastURL=s[s.length-1];
			}
			request.setAttribute("lastURL",lastURL );
			request.getRequestDispatcher("/SqlConnError.jsp").forward(request,response);
			e.printStackTrace();
		}
    }
    
    private  void update(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
    	request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		if (name.equals("")) name = null;
		long pID = Integer.parseInt(request.getParameter("PID"));
		String idNumber = request.getParameter("idNumber");
		if (idNumber.equals("")) name = null;
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
		try {
		Teacher tea = new Teacher(name, sex, birthday, idNumber, pID, department, status);
		TeacherOP teaOp=new TeacherOP();
		long PID = teaOp.UpdateTeacher(tea);
		String message = null;
		if(PID==20005) {
			  message = "更新教师信息失败！原因:教师信息不存在！;";
		}else if(PID==20008) {
			message = "更新教师信息失败！原因:教师信息不全！;";
		}else if(PID==20011) {
			message = "更新教师信息失败！原因:学院不存在！;";
		}else if(PID==20006){
			message = "更新教师信息成功！";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");
		}catch(Exception e)
		{
			String path=request.getHeader("Referer");
			String last=path.substring(path.length()-1);
			String s[]=path.split("/");
			String lastURL;
			for(int i=0;i<s.length;i++)
			{
				System.out.println(s[i]);
			}
			if(last.equals("/")||s[s.length-1].equals(request.getHeader("Referer")))
			{
				lastURL="";
			}
			else
			{
				lastURL=s[s.length-1];
			}
			request.setAttribute("lastURL",lastURL );
			request.getRequestDispatcher("/SqlConnError.jsp").forward(request,response);
			e.printStackTrace();
		}
    }
}
