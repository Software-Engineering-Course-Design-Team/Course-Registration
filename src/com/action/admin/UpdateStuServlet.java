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
 * Servlet implementation class UpdateStuServlet
 */
@WebServlet("/UpdateStuServlet")
public class UpdateStuServlet extends HttpServlet {
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
			request.setAttribute("result",result);
			request.getRequestDispatcher("/UpdateStuServlet.jsp").forward(request,response);
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
		long sID = Integer.parseInt(request.getParameter("SID"));
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
		String gradDate = request.getParameter("gradDate");
		if (gradDate.equals("")) name = null;
		String sex = request.getParameter("sex");
		if (sex.equals("")) name = null;
		try {
		Student stu = new Student(name, sex, gradDate, birthday, idNumber, sID, department, status);
		StudentOP stuOp=new StudentOP();
		long SID = stuOp.UpdateStudent(stu);
		String message = null;
		if(SID==40011) {
			  message = "更新学生信息失败！原因:学生信息不存在！;";
		}else if(SID==40012) {
			message = "更新学生信息失败！原因:学生信息不全！;";
		}else if(SID==40013) {
			message = "更新学生信息失败！原因:学院不存在！;";
		}else if(SID==40014){
			message = "更新学生信息成功！";
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
