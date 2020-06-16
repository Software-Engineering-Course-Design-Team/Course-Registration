package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.model.javabean.CouTime;
import com.model.javabean.Course;

/**
 * Servlet implementation class AdminInseClass
 */
@WebServlet("/AdminInseClass")
public class AdminInseClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInseClass() {
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
				HttpSession session = request.getSession();
				if(Integer.parseInt((String)request.getParameter("oop"))==1){
				if(session.getAttribute("TempList")==null) {
					request.setAttribute("RootInfo", 4);
					request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
					return;
				}
				ArrayList<CouTime> ct=(ArrayList<CouTime>)session.getAttribute("TempList");
				if(ct.size()==0) {
					request.setAttribute("RootInfo", 4);
					request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
					return;
				}
				Course cou=new Course();
				CourseOP couop=new CourseOP(); 
				CouTimeOP ctop=new CouTimeOP();
				if(session.getAttribute("BeginWeek")==null||session.getAttribute("EndWeek")==null
						||session.getAttribute("Fee")==null||session.getAttribute("ClassName")==null
						||session.getAttribute("Term")==null||session.getAttribute("Depart")==null) {
					request.setAttribute("RootInfo", 5);
					request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
					return;
				}
				cou.setBeginweek(Integer.parseInt((String)session.getAttribute("BeginWeek")));
				cou.setDID(Integer.parseInt((String)session.getAttribute("Depart")));
				cou.setEndWeek(Integer.parseInt((String)session.getAttribute("EndWeek")));
				cou.setFee(Integer.parseInt((String)session.getAttribute("Fee")));
				cou.setName((String)session.getAttribute("ClassName"));
				cou.setTerm(Integer.parseInt((String)session.getAttribute("Term")));
				if(request.getParameter("oopp")==null) {
					long CID=couop.InsertCourse(cou);
					for(CouTime ttemp:ct) {
						ttemp.setCID(CID);
						ctop.InsertCouTime(ttemp);
					}
					request.setAttribute("RootInfo", 3);
				}else {
					cou.setCID(Integer.parseInt((String)session.getAttribute("CID")));
					couop.UpdateCourse2(cou);
					CouTime ttcc=new CouTime();
					ttcc.setCID(cou.getCID());
					ctop.DeleteCou(ttcc);
					for(CouTime ttemp:ct) {
						ttemp.setCID(cou.getCID());
						ctop.InsertCouTime(ttemp);
					}
					request.setAttribute("RootInfo", 7);
				}
				//info:successful
				Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
				while (em.hasMoreElements()) {
		             request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
				}
				request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
				return;
				}else {
					Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
					while (em.hasMoreElements()) {
			             request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
					}
					request.getRequestDispatcher("/AdminInsClass").forward(request,response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
