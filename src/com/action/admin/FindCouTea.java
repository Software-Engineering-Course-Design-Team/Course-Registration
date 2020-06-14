package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CouTeaOP;
import com.model.javabean.CouTea;

/**
 * Servlet implementation class FindCouTea
 */
@WebServlet("/FindCouTea")
public class FindCouTea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCouTea() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String search=request.getParameter("search");
		CouTeaOP ctop=new CouTeaOP();
		CouTea c=new CouTea();
		ArrayList<CouTea> cttable;
		try {
			int pid=Integer.valueOf(search);
			c.setPID(pid);
			cttable=ctop.FindCou(c);
			request.setAttribute("info", "共查找到"+cttable.size()+"条数据");
			
		}catch(Exception e)
		{
			c.setName(search);
			cttable=ctop.FindTea(c);
			request.setAttribute("info", "共查找到"+cttable.size()+"条数据");
		}
		request.setAttribute("cttable", cttable);
		request.getRequestDispatcher("/AdminDeleteQuali.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
