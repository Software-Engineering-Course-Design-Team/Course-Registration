package com.teacher.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.LoginCheckServlet;
import com.control.DB.CourseOP;
import com.model.javabean.Course;

/**
 * Servlet implementation class TeaGetCrsList
 */
@WebServlet("/TeaGetCrsList")
public class TeaGetCrsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaGetCrsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		/*String referer=request.getHeader("referer");
		String nativeServerName=request.getServerName();
		System.out.println(nativeServerName);
		if(!nativeServerName.equals("localhost:8080"))
		{*/
		if(LoginCheckServlet.logincount!=null)
		{
			CourseOP crsop=new CourseOP();
			Course cs=new Course();
			cs.setPID(LoginCheckServlet.logincount.getID());
			ArrayList<Course> crs=crsop.FindTeaCou(cs);
			request.setAttribute("teacrslist", crs);
			request.getRequestDispatcher("/TeaCrsList.jsp").forward(request,response);
		}
		else
		{
			System.out.println("no curr_count");
			request.setAttribute("info", "当前未登录");
			request.getRequestDispatcher("/TeaError.jsp").forward(request,response);
		}
		//}
		//else
		//{
			//request.getRequestDispatcher("/StuError.html").forward(request,response);
			//System.out.println("盗链");
		//}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		
		
	}

}
