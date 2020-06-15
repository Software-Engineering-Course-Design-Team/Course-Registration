package com.action.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.LoginCheckServlet;
import com.control.DB.CouStuOP;
import com.model.javabean.CouStu;

/**
 * Servlet implementation class ChangeGrade
 */
@WebServlet("/ChangeGrade")
public class ChangeGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String grade = request.getParameter("grade");
		int sID=Integer.valueOf(request.getParameter("SID"));
		int cID=Integer.valueOf(request.getParameter("CID"));
		request.setAttribute("option", "changegrade");
		if(!grade.equals("A")&&!grade.equals("B")&&!grade.equals("C")&&!grade.equals("D")&&!grade.equals("F")&&!grade.equals("I"))
			{
				request.setAttribute("info", "成绩范围应该是A、B、C、D、F、I的大写字母");
				request.setAttribute("CID", cID);	
				request.setAttribute("SID", sID);
				request.setAttribute("SName", request.getParameter("SName"));
				request.setAttribute("grade", grade);
				request.getRequestDispatcher("/TeaChangeGradeError.jsp?option=3").forward(request,response);
				return;
			}
		else
			{
				CouStu cs=new CouStu();
				cs.setCID(cID);
				cs.setSID(sID);
				cs.setGrade(grade);
				CouStuOP csop=new CouStuOP();
				System.out.println("SID="+sID);
				System.out.println("CID="+cID);
				if(csop.UpdataConStu(cs)!=60011)
				{
					request.setAttribute("info", "成绩修改失败!");
					/*request.setAttribute("CID", cID);	
					request.setAttribute("SID", sID);
					request.setAttribute("SName", request.getParameter("SName"));
					request.setAttribute("grade", grade);
					request.getRequestDispatcher("/TeaChangeGradeError.jsp").forward(request,response);*/
					request.setAttribute("option", "changegrade");
					request.setAttribute("CID", cID);
					request.getRequestDispatcher("/TeaInfo.jsp").forward(request,response);
					return;
				}
				request.setAttribute("info", "成绩已录入！");
				request.setAttribute("option", "changegrade");
				request.setAttribute("CID", cID);
				request.getRequestDispatcher("/TeaInfo.jsp").forward(request,response);
			
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
