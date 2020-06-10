package com.action.admin;

import java.io.IOException;
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
		//TODO(sjz):�������ڸ�ʽ����
		//java.sql.Date birthday=java.sql.Date.valueOf(request.getParameter("birthday"));
		//java.sql.Date gradDate=java.sql.Date.valueOf(request.getParameter("gradDate"));
		String birthday = request.getParameter("birthday");
		String gradDate = request.getParameter("gradDate");
		String sex = request.getParameter("sex");
		
		Student stu = new Student(name, sex, gradDate, birthday, idNumber, 0, department, status);
		StudentOP stuOp=new StudentOP();
		long SID = stuOp.InsertStudent(stu);
		if(SID==40001) {
			  request.setAttribute("default", "���ѧ��ʧ�ܣ�ԭ��:��Ϣ������;");
		}else if(SID==40002) {
			request.setAttribute("default", "���ѧ��ʧ�ܣ�ԭ��:���ݿ�������ʧ��;");
		}else if(SID==4003) {
			request.setAttribute("default", "���ѧ��ʧ�ܣ�ԭ��:ѧԺ������;");
		}else {
			request.setAttribute("default", "���ѧ���ɹ���ѧ��Id:"+SID+";");
		}
		request.getRequestDispatcher("/RootMenu.html").forward(request,response);
	}
}
