package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.control.DB.DepartOP;
import com.control.DB.TeacherOP;
import com.model.javabean.CouStu;
import com.model.javabean.CouTime;
import com.model.javabean.Course;
import com.model.javabean.DepInfo;
import com.model.javabean.Teacher;

/**
 * Servlet implementation class AdminFindServlet
 */
@WebServlet("/AdminFindServlet")
public class AdminFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		ArrayList<Course> s1=new ArrayList<Course>();
		ArrayList<ArrayList<CouTime>> s2=new ArrayList<ArrayList<CouTime>>();
		ArrayList<String> s3=new ArrayList<String>();
		CourseOP cop=new CourseOP();
		CouTimeOP ctop=new CouTimeOP();
		TeacherOP teaop=new TeacherOP();
        if(request.getParameter("ClassNum")!=null&&!request.getParameter("ClassNum").equals("")) {
			Course cc=new Course();
			cc.setCID(Integer.parseInt(request.getParameter("ClassNum")));
			cc=cop.FindCourse(cc);
			s1.add(cc);
			if(cc.getCID()<100000) {
				request.setAttribute("RootInfo", 1);
				request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
				return;
			}
			CouTime ct=new CouTime();
			ct.setCID(cc.getCID());
			s2.add(ctop.FindCouTime(ct));
			Teacher tea=new Teacher();
			if(cc.getPID()!=0) {
				tea.setPID(cc.getPID());
				tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
			}else {
				s3.add("");
			}
		}else if(request.getParameter("ClassName")!=null&&!request.getParameter("ClassName").equals("")) {
			Course cc=new Course();
			cc.setName(request.getParameter("ClassName"));
			s1=cop.FindNameCou(cc);
			if(s1.size()==0) {
				request.setAttribute("RootInfo", 1);
				request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
				return;
			}
			for(Course ccc:s1) {
				CouTime ct=new CouTime();
				ct.setCID(ccc.getCID());
				s2.add(ctop.FindCouTime(ct));
				Teacher tea=new Teacher();
				if(ccc.getPID()!=0) {
					tea.setPID(ccc.getPID());
					tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
				}else {
					s3.add("");
				}
			}
		}
		else if(request.getParameter("ClassDep")!=null&&!request.getParameter("ClassDep").equals("")) {
			
			if(request.getParameter("ClassTerm")!=null&&!request.getParameter("ClassTerm").equals("")) {
				Course cc=new Course();
				cc.setTerm(Integer.parseInt(request.getParameter("ClassTerm")));
				cc.setDID(Integer.parseInt(request.getParameter("ClassDep")));
				s1=cop.FindTermDepCou(cc);
				if(s1.size()==0) {
					request.setAttribute("RootInfo", 4);
					request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
					return;
				}
				for(Course ccc:s1) {
					CouTime ct=new CouTime();
					ct.setCID(ccc.getCID());
					s2.add(ctop.FindCouTime(ct));
					Teacher tea=new Teacher();
					if(ccc.getPID()!=0) {
						tea.setPID(ccc.getPID());
						tea=teaop.FindTeacher(tea);
					s3.add(tea.getName());
					}else {
						s3.add("");
					}
				}
			}else {
				Course cc=new Course();
				cc.setDID(Integer.parseInt(request.getParameter("ClassDep")));
				s1=cop.FindDepCou(cc);
				if(s1.size()==0) {
					request.setAttribute("RootInfo", 2);
					request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
					return;
				}
				for(Course ccc:s1) {
					CouTime ct=new CouTime();
					ct.setCID(ccc.getCID());
					s2.add(ctop.FindCouTime(ct));
					Teacher tea=new Teacher();
					if(ccc.getPID()!=0) {
						tea.setPID(ccc.getPID());
						tea=teaop.FindTeacher(tea);
					s3.add(tea.getName());
					}else {
						s3.add("");
					}
				}
			}
		}else if(request.getParameter("ClassTerm")!=null&&!request.getParameter("ClassTerm").equals("")) {
			Course cc=new Course();
			cc.setTerm(Integer.parseInt(request.getParameter("ClassTerm")));
			s1=cop.FindTermCou(cc);
			if(s1.size()==0) {
				request.setAttribute("RootInfo", 3);
				request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
				return;
			}
			for(Course ccc:s1) {
				CouTime ct=new CouTime();
				ct.setCID(ccc.getCID());
				s2.add(ctop.FindCouTime(ct));
				Teacher tea=new Teacher();
				if(ccc.getPID()!=0) {
					tea.setPID(ccc.getPID());
					tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
				}else {
					s3.add("");
				}
			}
		}else if(request.getParameter("ClassPlace")!=null&&!request.getParameter("ClassPlace").equals("")) {
			CouTime cttemp=new CouTime();
			CouTimeOP timeop=new CouTimeOP();
			cttemp.setAddress(request.getParameter("ClassPlace"));
			ArrayList<CouTime> ctttttt=timeop.FindAddress(cttemp);
			if(ctttttt.size()==0) {
				request.setAttribute("RootInfo", 5);
				request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
				return;
			}
			for(CouTime c:ctttttt) {
				Course cc=new Course();
				cc.setCID(c.getCID());
				cc=cop.FindCourse(cc);
				s1.add(cc);
			}
			for(Course ccc:s1) {
				CouTime ct=new CouTime();
				ct.setCID(ccc.getCID());
				ct.setAddress(request.getParameter("ClassPlace"));
				s2.add(ctop.FindCouAddTime(ct));
				Teacher tea=new Teacher();
				if(ccc.getPID()!=0) {
					tea.setPID(ccc.getPID());
					tea=teaop.FindTeacher(tea);
				s3.add(tea.getName());
				}else {
					s3.add("");
				}
			}
		}
		
		request.setAttribute("Courseinfo", s1);
		request.setAttribute("CouTimeinfo",s2);
		request.setAttribute("TeaNameinfo",s3);
		ArrayList<DepInfo> depinfo=new ArrayList<DepInfo>();
		DepartOP dop=new DepartOP();
		depinfo=dop.FindAllDep();
		request.setAttribute("depinfo",depinfo);
		request.getRequestDispatcher("/RootFindClass.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
