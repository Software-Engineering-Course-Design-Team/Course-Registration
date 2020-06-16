package com.action.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.ConStuTempOP;
import com.control.DB.CouStuOP;
import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.control.DB.DepartOP;
import com.control.DB.StudentOP;
import com.control.DB.TeacherOP;
import com.model.javabean.DepInfo;
import com.model.javabean.Student;
import com.model.javabean.Teacher;
import com.model.javabean.CouStu;
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
			if(month+1>8)term++;
			Course tempc=new Course();
			tempc.setDID(DID);
			tempc.setTerm(term);
			
			CouStuTemp tttt=new CouStuTemp();
			ConStuTempOP h=new ConStuTempOP();
			int coursenum1=0;
			int coursenum2=0;
			tttt.setSID(SID);
			ArrayList<CouStuTemp> cst=h.FindCouTempTerm(tttt,term);
			coursenum1+=cst.size();
			coursenum2+=h.FindCouTempTermCan(tttt,term).size();
			
			CouStu tttemp=new CouStu();
			CouStuOP ccss=new CouStuOP();
			tttemp.setSID(SID);
			ArrayList<CouStu> ccsst=ccss.FindCouTerm(tttemp,term);
			coursenum1+=ccsst.size();
			
			request.setAttribute("coursenum1", coursenum1);
			System.out.println(coursenum1);
			request.setAttribute("coursenum2",coursenum2);
			
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
				CouStuTemp tt=new CouStuTemp();
				tt.setCID(i.getCID());
				tt.setSID(SID);
				tt=h.FindCouStuTemp(tt);
				String tempstr="";
				if(tt.getSID()!=90014) {
					if(tt.getOrder()==0)tempstr="yes";
					else if(tt.getOrder()==1)tempstr="yes2";
					else tempstr="yes3";
					s4.add(tempstr);
				}else {
					CouStu ttt=new CouStu();
					ttt.setCID(i.getCID());
					ttt.setSID(SID);
					CouStuOP csop=new CouStuOP();
					ttt=csop.FindCouStu(ttt);
					if(ttt.getSID()!=60014) {
						tempstr="yes1";
						s4.add(tempstr);
					}else {
						tempstr="no";
						s4.add(tempstr);
					}
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
