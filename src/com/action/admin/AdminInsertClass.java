package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.DepartOP;
import com.model.javabean.DepInfo;


@WebServlet("/AdminInsertClass")
public class AdminInsertClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminInsertClass() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		ArrayList<DepInfo> depinfo=new ArrayList<DepInfo>();
		DepartOP dop=new DepartOP();
		depinfo=dop.FindAllDep();
		request.setAttribute("depinfo",depinfo);
		if(request.getParameter("oopp")==null) {
			request.getRequestDispatcher("/RootInsertClass.jsp").forward(request,response);
			}else {
				request.getRequestDispatcher("RootModifyClass.jsp").forward(request,response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
