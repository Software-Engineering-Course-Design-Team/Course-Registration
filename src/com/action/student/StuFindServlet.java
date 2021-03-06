package com.action.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CouStuOP;
import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.control.DB.DepartOP;
import com.control.DB.StudentOP;
import com.control.DB.TeacherOP;
import com.control.DB.ConStuTempOP;
import com.model.javabean.CouStu;
import com.model.javabean.CouStuTemp;
import com.model.javabean.CouTime;
import com.model.javabean.Course;
import com.model.javabean.DepInfo;
import com.model.javabean.Student;
import com.model.javabean.Teacher;



@WebServlet("/StuFindServlet")
public class StuFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StuFindServlet() {
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
		long DID=temp.getDID();
		DepInfo temp1=new DepInfo();
		DepartOP dop=new DepartOP();
		temp1.setDID(DID);
		temp1=dop.FindDepartDID(temp1);
		int flag=temp1.getStatus();
		if(flag!=2) {
			CouStuOP csop=new CouStuOP();
			CourseOP cop=new CourseOP();
			CouStu temp2=new CouStu();
			temp2.setSID(SID);
			CouTimeOP ctop=new CouTimeOP();
			ArrayList<CouStu> s=csop.FindCouTerm(temp2,term);
			ArrayList<Course> s1=new ArrayList<Course>();
			ArrayList<ArrayList<CouTime>> s2=new ArrayList<ArrayList<CouTime>>();
			ArrayList<String> s3=new ArrayList<String>();
			TeacherOP teaop=new TeacherOP();
			for(CouStu i:s) {
				Course temp_c=new Course();
				temp_c.setCID(i.getCID());
				s1.add(cop.FindCourse(temp_c));
				long pid=temp_c.getPID();
				Teacher tea=new Teacher();
				tea.setPID(pid);
				tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
				CouTime ct=new CouTime();
				ct.setCID(i.getCID());
				s2.add(ctop.FindCouTime(ct));
			}
			request.setAttribute("Courseinfo", s1);
			request.setAttribute("CouTimeinfo",s2);
			request.setAttribute("TeaNameinfo",s3);
			request.getRequestDispatcher("/StuFindServlet.jsp").forward(request,response);
		}else {
			ConStuTempOP csop=new ConStuTempOP();
			CourseOP cop=new CourseOP();
			CouStuTemp temp2=new CouStuTemp();
			temp2.setSID(SID);
			CouTimeOP ctop=new CouTimeOP();
			ArrayList<CouStuTemp> s=csop.FindCouTemp(temp2);
			ArrayList<Course> s1=new ArrayList<Course>();
			ArrayList<ArrayList<CouTime>> s2=new ArrayList<ArrayList<CouTime>>();
			ArrayList<String> s3=new ArrayList<String>();
			ArrayList<String> s4=new ArrayList<String>();
			TeacherOP teaop=new TeacherOP();
			for(CouStuTemp i:s) {
				Course temp_c=new Course();
				temp_c.setCID(i.getCID());
				s1.add(cop.FindCourse(temp_c));
				long pid=temp_c.getPID();
				Teacher tea=new Teacher();
				tea.setPID(pid);
				tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
				CouTime ct=new CouTime();
				ct.setCID(i.getCID());
				s2.add(ctop.FindCouTime(ct));
				if(i.getOrder()==0)s4.add("已选择必选课");
				else if(i.getOrder()==1)s4.add("第一候选课");
				else s4.add("第二候选课");
			}
			
			CouStuOP scop=new CouStuOP();
			CouStu cs=new CouStu();
			cs.setSID(SID);
			ArrayList<CouStu> tempp=scop.FindCouTerm(cs, term);
			for(CouStu i:tempp) {
				Course temp_c=new Course();
				temp_c.setCID(i.getCID());
				s1.add(cop.FindCourse(temp_c));
				long pid=temp_c.getPID();
				Teacher tea=new Teacher();
				tea.setPID(pid);
				tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
				CouTime ct=new CouTime();
				ct.setCID(i.getCID());
				s2.add(ctop.FindCouTime(ct));
				s4.add("已注册必选课");
			}
			
			request.setAttribute("Courseinfo", s1);
			request.setAttribute("CouTimeinfo",s2);
			request.setAttribute("TeaNameinfo",s3);
			request.setAttribute("Selectinfo",s4);
			request.getRequestDispatcher("/StuFindServlet2.jsp").forward(request,response);
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
