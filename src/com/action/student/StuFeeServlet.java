package com.action.student;

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
		try {
		request.setCharacterEncoding("utf-8");
		String fromURL = request.getHeader("Referer");     
		System.out.println(fromURL);
		if(fromURL==null) {
			System.out.println(fromURL);
			request.getRequestDispatcher("/StuError.html").forward(request,response);
			return;
		}
		long CID=Integer.parseInt(request.getParameter("username"));
		StuFeeOP sfop=new StuFeeOP();
		Stufee temp=new Stufee();
		temp.setSID(CID);
		ArrayList<Stufee> s=sfop.FindStuAllFee(temp);
		request.setAttribute("stufeeall", s);
		request.getRequestDispatcher("/StuFeeServlet.jsp").forward(request,response);
		}catch(Exception e)
		{
			String path=request.getHeader("Referer");
			String last=path.substring(path.length()-1);
			String s[]=path.split("/");
			String lastURL;
			for(int i=0;i<s.length;i++)
			{
				System.out.println(s[i]);
			}
			if(last.equals("/")||s[s.length-1].equals(request.getHeader("Referer")))
			{
				lastURL="";
			}
			else
			{
				 lastURL=s[s.length-1];
			}
			request.setAttribute("lastURL",lastURL );
			request.getRequestDispatcher("/SqlConnError.jsp").forward(request,response);
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
