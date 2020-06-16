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
		try {
		String op=request.getParameter("op");
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
				cs.setCID(CID);
				long idd=csop.InsertCouStu(cs);
				if(idd==60001) {
					request.setAttribute("RootInfo", 1);
					request.getRequestDispatcher("/RootInfo2.jsp").forward(request,response);
					return;
				}
				if(idd==60016) {
					request.setAttribute("RootInfo", 4);
					request.getRequestDispatcher("/RootInfo2.jsp").forward(request,response);
					return;
				}
				cfop.UpdateFee(sf);
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
			cs.setCID(CID);
			csop.DeleteCouStu(cs);
			cfop.UpdateFee(sf);
		}
		if(request.getParameter("CID")!=null&&request.getParameter("CID")!="") {
			int CID=Integer.parseInt(request.getParameter("CID"));
			CouStu cs=new CouStu();
			cs.setCID(CID);
			Course jdu=new Course();
			CourseOP jduop=new CourseOP();
			jdu.setCID(CID);
			jdu=jduop.FindCourse(jdu);
			if(jdu.getCID()<100000) {
				request.setAttribute("RootInfo", 7);
				request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
				return;
			}
			CouStuOP csop=new CouStuOP();
			ArrayList<CouStu> c=csop.FindStu(cs);
			if(c.size()==0) {
				request.setAttribute("RootInfo", 8);
				request.getRequestDispatcher("/RootInfo3.jsp").forward(request,response);
				return;
			}
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
		
		request.getRequestDispatcher("/RootManage.jsp").forward(request,response);
		return;
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
