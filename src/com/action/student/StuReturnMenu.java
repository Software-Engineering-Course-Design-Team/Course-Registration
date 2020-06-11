package com.action.student;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.StudentOP;
import com.model.javabean.Student;

@WebServlet("/StuReturnMenu")
public class StuReturnMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StuReturnMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		Student temp=new Student();
		StudentOP stop=new StudentOP();
		temp.setSID(Integer.parseInt(userName));
		temp=stop.FindStudent(temp);
		request.setAttribute("StuName",temp.getName());
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int day=date.get(Calendar.DATE);
		int n1=Integer.parseInt(temp.getBirthday().substring(0,4));
		int n2=Integer.parseInt(temp.getBirthday().substring(5,7));
		int n3=Integer.parseInt(temp.getBirthday().substring(8,10));
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(month);
		System.out.println(day);
		if(month+1==n2&&day==n3) {
			request.setAttribute("BIR","YES");
			request.setAttribute("BIRY",year-n1);
		}else{
			request.setAttribute("BIR","NO");
		}
		request.getRequestDispatcher("/StuMenu.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
