package com.action.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CouTeaOP;
import com.model.javabean.CouTea;

/**
 * Servlet implementation class DeleteQuali
 */
@WebServlet("/DeleteQuali")
public class DeleteQuali extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuali() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Name=request.getParameter("Name");
		int PID=Integer.valueOf(request.getParameter("PID"));
		CouTeaOP ctop=new CouTeaOP();
		CouTea c=new CouTea();
		c.setName(Name);
		c.setPID(PID);
		ctop.DeleteCouTea(c);
		request.setAttribute("option", "delquail");
		request.setAttribute("info", "该教师资格信息已删除！");
		request.getRequestDispatcher("/AdminInfo.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
