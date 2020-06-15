package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.control.DB.DepartOP;
import com.model.javabean.CouTime;
import com.model.javabean.Course;
import com.model.javabean.DepInfo;

/**
 * Servlet implementation class AdminUpdateServlet
 */
@WebServlet("/AdminUpdateServlet")
public class AdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String opp=request.getParameter("op");
		if(opp.equals("修改")) {
		Course op=new Course();
		CourseOP cop=new CourseOP();
		op.setCID(Integer.parseInt((String)request.getParameter("CID")));
		System.out.println(op.getCID());
		op=cop.FindCourse(op);
		HttpSession session = request.getSession();
		session.setAttribute("ClassName",""+op.getName());
		session.setAttribute("BeginWeek", ""+op.getBeginweek());
		session.setAttribute("EndWeek", ""+op.getEndWeek());
		session.setAttribute("Depart", ""+op.getDID());
		session.setAttribute("Fee", ""+op.getFee());
		session.setAttribute("Term", ""+op.getTerm());
		session.setAttribute("CID", ""+op.getCID());
		ArrayList<DepInfo> depinfo=new ArrayList<DepInfo>();
		CouTime a=new CouTime();
		a.setCID(op.getCID());
		CouTimeOP ctop=new CouTimeOP();
		ArrayList<CouTime> array1=ctop.FindCouTime(a);
		session.setAttribute("TempList", array1);
		DepartOP dop=new DepartOP();
		depinfo=dop.FindAllDep();
		request.setAttribute("depinfo",depinfo);
		request.getRequestDispatcher("/RootModifyClass.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/AdminDelServlet").forward(request,response);
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
