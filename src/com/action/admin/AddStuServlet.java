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
		try {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		if (name.equals("")) name = null;
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
		
		Student stu = new Student(name, sex, gradDate, birthday, idNumber, 0, department, status);
		StudentOP stuOp=new StudentOP();
		long SID = stuOp.InsertStudent(stu);
		String message = null;
		if(SID==40001) {
			  message = "添加学生失败！原因:信息不完整;";
		}else if(SID==40002) {
			message = "添加学生失败！原因:数据库插入操作失败;";
		}else if(SID==40003) {
			message = "添加学生失败！原因:学院不存在;";
		}else {
			message = "添加学生成功！学生Id:"+SID+";";
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
