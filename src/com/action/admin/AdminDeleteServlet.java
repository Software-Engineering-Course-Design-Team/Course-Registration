package com.action.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CourseOP;
import com.model.javabean.Course;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet("/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Course dc=new Course();
		dc.setCID(Integer.parseInt(request.getParameter("CID")));
		CourseOP cop=new CourseOP();
		long temp=cop.DeleteCourse(dc);
		if(temp==70004||temp==70005) {
			request.setAttribute("RootInfo", 8);
			request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
			return;
		}
		request.setAttribute("RootInfo", 6);
		request.getRequestDispatcher("/RootInfo.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
