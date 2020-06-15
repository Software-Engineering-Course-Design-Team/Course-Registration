package com.action.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.StuFeeOP;
import com.model.javabean.Stufee;

/**
 * Servlet implementation class AdminUpDelFee
 */
@WebServlet("/AdminUpDelFee")
public class AdminUpDelFee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpDelFee() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String op=request.getParameter("op");
		StuFeeOP sfop=new StuFeeOP();
		long id=Integer.parseInt(request.getParameter("userid"));
		int term=Integer.parseInt(request.getParameter("term"));
		Stufee sf=new Stufee();
		if(op.equals("删除")) {
			sf.setSID(id);
			sf.setTerm(term);
			sfop.DeleteStuFee(sf);
			request.getRequestDispatcher("AdminStudFee").forward(request,response);
		}else {
			sf.setSID(id);
			sf.setTerm(term);
			sfop.UpdateStuFee(sf);
			request.getRequestDispatcher("AdminStudFee").forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
