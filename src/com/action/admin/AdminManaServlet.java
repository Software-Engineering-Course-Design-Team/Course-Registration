package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CouStuOP;
import com.control.DB.CourseOP;
import com.control.DB.StuFeeOP;
import com.control.DB.StudentOP;
import com.model.javabean.CouStu;
import com.model.javabean.Course;
import com.model.javabean.Student;
import com.model.javabean.Stufee;

/**
 * Servlet implementation class AdminManaServlet
 */
@WebServlet("/AdminManaServlet")
public class AdminManaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminManaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String op=request.getParameter("op");
		if(request.getParameter("CID")!=null&&request.getParameter("CID")!="") {
			int CID=Integer.parseInt(request.getParameter("CID"));
			CouStu cs=new CouStu();
			cs.setCID(CID);
			CouStuOP csop=new CouStuOP();
			ArrayList<CouStu> c=csop.FindStu(cs);
			ArrayList<Student> stu=new ArrayList<Student>();
			StudentOP sop=new StudentOP();
			for(CouStu a:c) {
				Student sta=new Student();
				sta.setSID(a.getSID());
				sta=sop.FindStudent(sta);
				stu.add(sta);
			}
			Course cou=new Course();
			CourseOP cop=new CourseOP();
			cou.setCID(CID);
			cou=cop.FindCourse(cou);
			if(cou.getPerson()>=10) request.setAttribute("flag", "yes");
			else request.setAttribute("flag", "no");//10人标记
			request.setAttribute("stuinfo", stu);
		}
		if(op!=null&&op.equals("Verify")) {
			int SID=Integer.parseInt(request.getParameter("SID"));
			CouStu cs=new CouStu();
			cs.setSID(SID);
			CouStuOP csop=new CouStuOP();
			Student sta=new Student();
			sta.setSID(SID);
			StudentOP sop=new StudentOP();
			sta=sop.FindStudent(sta);
			if(sta.getSID()<100000) {
				request.setAttribute("RootInfo", 3);
				request.getRequestDispatcher("/RootInfo2.jsp").forward(request,response);
				return;
			}
			ArrayList<CouStu> ccc=csop.FindCou(cs);
			if(ccc.size()>=4) {
				request.setAttribute("RootInfo", 2);
				request.getRequestDispatcher("/RootInfo2.jsp").forward(request,response);
				return;
			}else {
				Course cou=new Course();
				CourseOP cop=new CourseOP();
				int CID=Integer.parseInt(request.getParameter("CID"));
				cou.setCID(CID);
				cou=cop.FindCourse(cou);
				int gyear=Integer.parseInt(sta.getGradDate().substring(0,4));
				Calendar date = Calendar.getInstance();
				int nyear = date.get(Calendar.YEAR);
				int month = date.get(Calendar.MONTH);
				int term=(4-gyear+nyear)*2;
				if(month+1>8)term++;
				Stufee sf=new Stufee();
				StuFeeOP cfop=new StuFeeOP();
				sf.setSID(SID);
				sf.setTerm(term);
				sf=cfop.FindStuFee(sf);
				sf.setFee(sf.getFee()+cou.getFee());
				cs.setSID(SID);
				csop.InsertCouStu(cs);
				cfop.UpdateStuFee(sf);
			}
		}else if(op!=null&&op.equals("Delete")) {
			int SID=Integer.parseInt(request.getParameter("SID"));
			CouStu cs=new CouStu();
			cs.setSID(SID);
			CouStuOP csop=new CouStuOP();
			Student sta=new Student();
			sta.setSID(SID);
			StudentOP sop=new StudentOP();
			sta=sop.FindStudent(sta);
			if(sta.getSID()<100000) {
				request.setAttribute("RootInfo", 3);
				request.getRequestDispatcher("/RootInfo2.jsp").forward(request,response);
				return;
			}
			Course cou=new Course();
			CourseOP cop=new CourseOP();
			int CID=Integer.parseInt(request.getParameter("CID"));
			cou.setCID(CID);
			cou=cop.FindCourse(cou);
			int gyear=Integer.parseInt(sta.getGradDate().substring(0,4));
			Calendar date = Calendar.getInstance();
			int nyear = date.get(Calendar.YEAR);
			int month = date.get(Calendar.MONTH);
			int term=(4-gyear+nyear)*2;
			if(month+1>8)term++;
			Stufee sf=new Stufee();
			StuFeeOP cfop=new StuFeeOP();
			sf.setSID(SID);
			sf.setTerm(term);
			sf=cfop.FindStuFee(sf);
			sf.setFee(sf.getFee()-cou.getFee());
			cs.setSID(SID);
			csop.DeleteCou(cs);
			cfop.UpdateStuFee(sf);
		}
		request.getRequestDispatcher("/RootManage.jsp").forward(request,response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
