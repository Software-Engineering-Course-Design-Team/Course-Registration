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
import com.model.javabean.CouTime;
import com.model.javabean.Course;

/**
 * Servlet implementation class AdminTempServlet
 */
@WebServlet("/AdminTempServlet")
public class AdminTempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTempServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		System.out.println(request.getParameter("Depart"));
		if(Integer.parseInt(request.getParameter("op"))==2) {
			if(session.getAttribute("TempList")==null){
				ArrayList<CouTime> ct=new ArrayList<CouTime>();
				CouTime temp=new CouTime();
				temp.setWeekDay(Integer.parseInt(request.getParameter("Week")));
				temp.setAddress(request.getParameter("Place"));
				temp.setBeginC(Integer.parseInt(request.getParameter("BeginC")));
				temp.setEndC(Integer.parseInt(request.getParameter("EndC")));
				ct.add(temp);
				session.setAttribute("TempList",ct);
			}else {
				ArrayList<CouTime> ct=(ArrayList<CouTime>)session.getAttribute("TempList");
				CouTime temp=new CouTime();
				CouTimeOP top=new CouTimeOP();
				temp.setWeekDay(Integer.parseInt(request.getParameter("Week")));
				temp.setAddress(request.getParameter("Place"));
				temp.setBeginC(Integer.parseInt(request.getParameter("BeginC")));
				temp.setEndC(Integer.parseInt(request.getParameter("EndC")));
				for(CouTime i:ct) {
					if(i.getWeekDay()==temp.getWeekDay()&&((i.getBeginC()>=temp.getBeginC()&&
							i.getBeginC()<=temp.getEndC())||(i.getEndC()>=temp.getBeginC()
							&&i.getEndC()<=temp.getEndC()))) {
						request.setAttribute("RootInfo", 1);
						request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
						return;
					}
					if(request.getParameter("oopp")==null) {
					if(top.FindPlaceConflict(temp)!=null) {
						request.setAttribute("RootInfo", 2);
						Course ttc=new Course();
						ttc.setCID(top.FindPlaceConflict(temp).getCID());
						CourseOP tcop=new CourseOP();
						ttc=tcop.FindCourse(ttc);
						request.setAttribute("cname", ttc.getName());
						request.setAttribute("ctinfo", top.FindPlaceConflict(temp));
						request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
						return;
					}
					}else {
						if(top.FindPlaceNSConflict(temp)!=null) {
							request.setAttribute("RootInfo", 2);
							Course ttc=new Course();
							ttc.setCID(top.FindPlaceNSConflict(temp).getCID());
							CourseOP tcop=new CourseOP();
							ttc=tcop.FindCourse(ttc);
							request.setAttribute("cname", ttc.getName());
							request.setAttribute("ctinfo", top.FindPlaceNSConflict(temp));
							request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
							return;
						}
					}
				}
				ct.add(temp);
				session.setAttribute("TempList",ct);
			}
		}else {
			if(session.getAttribute("TempList")!=null) {
				ArrayList<CouTime> ct=(ArrayList<CouTime>)session.getAttribute("TempList");
				ct.clear();
			}
		}
		request.getRequestDispatcher("/AdminInsClass").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
