package com.action.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.ConStuTempOP;
import com.model.javabean.CouStuTemp;

/**
 * Servlet implementation class StuInsertServlet
 */
@WebServlet("/StuInsertServlet")
public class StuInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StuInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CouStuTemp temp=new CouStuTemp();
		ConStuTempOP cstop=new ConStuTempOP();
		temp.setSID(Integer.parseInt(request.getParameter("username")));
		temp.setCID(Integer.parseInt(request.getParameter("CID")));
		if(request.getParameter("flag").equals("选课")) {
			temp.setOrder(0);
			cstop.InsertConStuTemp(temp);
		}else {
			temp.setOrder(1);
			cstop.InsertConStuTemp(temp);
		}
		request.getRequestDispatcher("/StudCouServlet").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
