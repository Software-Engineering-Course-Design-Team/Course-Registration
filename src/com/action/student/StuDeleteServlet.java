package com.action.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.ConStuTempOP;
import com.control.DB.CouStuOP;
import com.model.javabean.CouStu;
import com.model.javabean.CouStuTemp;

@WebServlet("/StuDeleteServlet")
public class StuDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		long username=Integer.parseInt(request.getParameter("username"));
		long CID=Integer.parseInt(request.getParameter("CID"));
		CouStuTemp cst=new CouStuTemp();
		ConStuTempOP cstop=new ConStuTempOP();
		if(CID!=19999) {
			cst.setCID(CID);
			cst.setSID(username);
			if(cstop.DeleteConStuTemp(cst)==90012) {
				CouStu sc=new CouStu();
				CouStuOP csop=new CouStuOP();
				sc.setSID(username);
				sc.setCID(CID);
				csop.DeleteCouStu(sc);
			};
		}else {
			CouStu sc=new CouStu();
			CouStuOP csop=new CouStuOP();
			cst.setSID(username);
			sc.setSID(username);
			cstop.DeleteStuTemp(cst);
			csop.DeleteStu(sc);
		}
		if(request.getParameter("optype")!=null&&request.getParameter("optype").equals("2")) {
			request.getRequestDispatcher("/StudCouServlet").forward(request,response);
		}else
		request.getRequestDispatcher("/StudFindServlet").forward(request,response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
