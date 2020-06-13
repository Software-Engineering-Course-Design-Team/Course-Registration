package com.action.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeaChagGrade
 */
@WebServlet("/TeaChagGrade")
public class TeaChagGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaChagGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String grade = request.getParameter("grade");
		int sID=Integer.valueOf(request.getParameter("SID"));
		int cID=Integer.valueOf(request.getParameter("CID"));
		int option=Integer.valueOf(request.getParameter("option"));
		String sName=request.getParameter("SName");
		
		request.setAttribute("CID", cID);	
		request.setAttribute("SID", sID);
		request.setAttribute("SName", sName);
		request.setAttribute("grade", grade);
		request.getRequestDispatcher("/TeaChangeGrade.jsp?option="+option).forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
