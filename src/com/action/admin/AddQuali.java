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
 * Servlet implementation class AddQuali
 */
@WebServlet("/AddQuali")
public class AddQuali extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuali() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int PID = Integer.valueOf(request.getParameter("PID"));
		String qName = request.getParameter("qName");
		try {
		CouTeaOP ctop=new CouTeaOP();
		CouTea ct=new CouTea();
		ct.setName(qName);
		ct.setPID(PID);
		
		long info=ctop.InsertCouTea(ct);
		request.setAttribute("option", "addquail");
		if(info==55007)
		{
			request.setAttribute("info", "添加失败！教师资格相关信息不全面");
		}
		else if(info==55008)
		{
			request.setAttribute("info", "添加失败！教师PID不存在！");
			
		}
		else if(info==55009)
		{
			request.setAttribute("info", "该教师资格信息已存在！");
		}
		else if(info==55010)
		{
			request.setAttribute("info", "添加成功！");
		}
		request.getRequestDispatcher("/AdminInfo.jsp").forward(request,response);
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
