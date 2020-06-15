package com.action.admin;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminTempInfo
 */
@WebServlet("/AdminTempInfo")
public class AdminTempInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminTempInfo() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if(Integer.parseInt((String)request.getParameter("op"))==2) {
		if(request.getParameter("ClassName")!=null)
			session.setAttribute("ClassName", request.getParameter("ClassName"));
		if(request.getParameter("BeginWeek")!=null)
			session.setAttribute("BeginWeek", request.getParameter("BeginWeek"));
		if(request.getParameter("EndWeek")!=null)
			session.setAttribute("EndWeek", request.getParameter("EndWeek"));
		if(request.getParameter("Depart")!=null)
			session.setAttribute("Depart", request.getParameter("Depart"));
		if(request.getParameter("Fee")!=null)
			session.setAttribute("Fee", request.getParameter("Fee"));
		if(request.getParameter("Term")!=null)
			session.setAttribute("Term", request.getParameter("Term"));
		}else {
			Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
			while (em.hasMoreElements()) {
                 request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
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
