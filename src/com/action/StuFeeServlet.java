package com.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.StuFeeOP;
import com.model.javabean.Stufee;


@WebServlet("/StuFeeServlet")
public class StuFeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StuFeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fromURL = request.getHeader("Referer");     
		System.out.println(fromURL);
		if(fromURL==null) {
			System.out.println(fromURL);
			request.getRequestDispatcher("/StuError.html").forward(request,response);
			return;
		}
		if(fromURL.equals("http://localhost:8080/SoftEngProj/CheckServlet")||
				fromURL.equals("http://localhost:8080/SoftEngProj/StudFeeServlet?username="+request.getParameter("username"))) {
			long CID=Integer.parseInt(request.getParameter("username"));
			StuFeeOP sfop=new StuFeeOP();
			Stufee temp=new Stufee();
			temp.setSID(CID);
			ArrayList<Stufee> s=sfop.FindStuAllFee(temp);
			request.setAttribute("stufeeall", s);
			request.getRequestDispatcher("/StuFeeServlet.jsp").forward(request,response);
		}else {
			System.out.println(fromURL);
			request.getRequestDispatcher("/StuError.html").forward(request,response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
