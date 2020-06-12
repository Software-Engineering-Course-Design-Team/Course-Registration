package com.action.student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CouStuOP;
import com.control.DB.CourseOP;
import com.model.javabean.CouStu;
import com.model.javabean.Course;


@WebServlet("/StuGradeServlet")
public class StuGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public StuGradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String fromURL = request.getHeader("Referer");     
		System.out.println(fromURL);
		if(fromURL==null) {
			System.out.println(fromURL);
			request.getRequestDispatcher("/StuError.html").forward(request,response);
			return;
		}
		long CID=Integer.parseInt(request.getParameter("username"));
		int term=Integer.parseInt(request.getParameter("Term"));
		CouStuOP csop=new CouStuOP();
		CourseOP cop=new CourseOP();
		CouStu temp=new CouStu();
		temp.setSID(CID);
		ArrayList<CouStu> s=new ArrayList<CouStu>();
		if(term==0)s=csop.FindCou(temp);
		else {
			s=csop.FindCouTerm(temp, term);
		}
		ArrayList<Course> s1=new ArrayList<Course>();
		for(CouStu i:s) {
			if(!(i.getGrade().contentEquals("A")||i.getGrade().contentEquals("B")
					||i.getGrade().contentEquals("C")||i.getGrade().contentEquals("D")
					||i.getGrade().contentEquals("I")||i.getGrade().contentEquals("F")))
				i.setGrade("未公布成绩");
			Course temp_c=new Course();
			temp_c.setCID(i.getCID());
			s1.add(cop.FindCourse(temp_c));
		}
		request.setAttribute("Gradeinfo", s);
		request.setAttribute("Courseinfo",s1);
		request.getRequestDispatcher("/StuGradeServlet.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
