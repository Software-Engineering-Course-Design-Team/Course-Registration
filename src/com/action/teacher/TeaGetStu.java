package com.action.teacher;

import java.io.IOException;
import java.util.ArrayList;

import com.control.DB.CouStuOP;
import com.control.DB.StudentOP;
import com.model.javabean.CouStu;
import com.model.javabean.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeaGetStu
 */
@WebServlet("/TeaGetStu")
public class TeaGetStu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaGetStu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CouStuOP csop=new CouStuOP();
		int cID=Integer.valueOf(request.getParameter("CID"));
		int option=Integer.valueOf(request.getParameter("option"));
		//String grade = request.getParameter("grade");
		CouStu cs=new CouStu();
		StudentOP sop=new StudentOP();
		cs.setCID(cID);
		ArrayList<CouStu> stus=csop.FindStu(cs);
		ArrayList<Student> thiss=new ArrayList<Student>();
		for(int i=0;i<stus.size();i++)
		{
			Student s=new Student();
			s.setSID(stus.get(i).getSID());
			thiss.add(sop.FindStudent(s));
		}
		thiss.sort(Student.SIDComparator);
		request.setAttribute("cID",cID);
		request.setAttribute("option",option);
		request.setAttribute("students",thiss);
		//request.setAttribute("grade", grade);
		request.getRequestDispatcher("/TeaGetStuList.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
