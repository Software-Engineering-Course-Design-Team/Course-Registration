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


@WebServlet("/StuNoGradeServlet")
public class StuNoGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StuNoGradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		String fromURL = request.getHeader("Referer");     
		System.out.println(fromURL);
		if(fromURL==null) {
			System.out.println(fromURL);
			request.getRequestDispatcher("/StuError.html").forward(request,response);
			return;
		}
		long SID=Integer.parseInt(request.getParameter("username"));
		Student temp=new Student();
		StudentOP sop=new StudentOP();
		temp.setSID(SID);
		temp=sop.FindStudent(temp);
		int gyear=Integer.parseInt(temp.getGradDate().substring(0,4));
		Calendar date = Calendar.getInstance();
		int nyear = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int term=(4-gyear+nyear)*2;
		if(month+1>8)term++;
		request.setAttribute("ATerm",term);
		request.getRequestDispatcher("/StuGradeServlet2.jsp").forward(request,response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
