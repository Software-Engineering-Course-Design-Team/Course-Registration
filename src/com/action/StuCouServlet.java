package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.ConStuTempOP;
import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.control.DB.DepartOP;
import com.control.DB.StudentOP;
import com.control.DB.TeacherOP;
import com.model.javabean.DepInfo;
import com.model.javabean.Student;
import com.model.javabean.Teacher;
import com.model.javabean.CouStuTemp;
import com.model.javabean.CouTime;
import com.model.javabean.Course;

@WebServlet("/StuCouServlet")
public class StuCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StuCouServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		long DID=temp.getDID();
		DepInfo temp1=new DepInfo();
		DepartOP dop=new DepartOP();
		temp1.setDID(DID);
		temp1=dop.FindDepartDID(temp1);
		int flag=temp1.getStatus();
		CourseOP cop=new CourseOP();
		if(flag==2) {
			int gyear=Integer.parseInt(temp.getGradDate().substring(0,4));
			Calendar date = Calendar.getInstance();
			int nyear = date.get(Calendar.YEAR);
			int month = date.get(Calendar.MONTH);
			int term=(4-gyear+nyear)*2;
			if(month>8)term++;
			Course tempc=new Course();
			tempc.setDID(DID);
			tempc.setTerm(term);
			ArrayList<Course> s=cop.FindTermDepCou(tempc);
			ArrayList<ArrayList<CouTime>> s2=new ArrayList<ArrayList<CouTime>>();
			ArrayList<String> s3=new ArrayList<String>();
			ArrayList<String> s4=new ArrayList<String>();
			TeacherOP teaop=new TeacherOP();
			CouTimeOP ctop=new CouTimeOP();
			for(Course i:s) {
				long pid=i.getPID();
				Teacher tea=new Teacher();
				tea.setPID(pid);
				tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
				CouTime ct=new CouTime();
				ct.setCID(i.getCID());
				s2.add(ctop.FindCouTime(ct));
				ConStuTempOP h=new ConStuTempOP();
				CouStuTemp tt=new CouStuTemp();
				tt.setCID(i.getCID());
				tt.setSID(SID);
				tt=h.FindCouStuTemp(tt);
				String tempstr="";
				if(tt.getSID()==90014) {
					tempstr="no";
					s4.add(tempstr);
				}else {
					tempstr="yes";
					s4.add(tempstr);
				}
			}
			request.setAttribute("Courseinfo", s);
			request.setAttribute("CouTimeinfo",s2);
			request.setAttribute("TeaNameinfo",s3);
			request.setAttribute("Extrainfo",s4);
			request.getRequestDispatcher("/StuCouServlet.jsp").forward(request,response);
		}else {
			request.setAttribute("Flaginfo","1");
			request.getRequestDispatcher("/StuMenuInfo.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
