package com.action.teacher;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.LoginCheckServlet;
import com.control.DB.CouTimeOP;
import com.control.DB.CountOP;
import com.control.DB.CourseOP;
import com.model.javabean.Count;
import com.model.javabean.Course;

/**
 * Servlet implementation class TeaChooseCou
 */
@WebServlet("/TeaChooseCou")
public class TeaChooseCou extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaChooseCou() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int CID=Integer.valueOf(request.getParameter("CID"));
		int option=Integer.valueOf(request.getParameter("option"));
		String Cname=request.getParameter("CName");
		int id=Integer.parseInt(request.getParameter("username"));
		request.setAttribute("username", id);
		System.out.println("username="+id);
		CountOP ccop=new CountOP();
		Count logincount=new Count();
		logincount.setID(id);
		logincount=ccop.FindCount(logincount);
		if(logincount!=null)
		{
			long PID=logincount.getID();
			CourseOP cop=new CourseOP();
			CouTimeOP ctop=new CouTimeOP();
			Course c=new Course();
			c.setPID(PID);
			c.setCID(CID);
			c.setName(Cname);
			ArrayList<Course> teaCous=cop.FindTeaCou(c);
			
			if(option==1)//选课
			{
				boolean flag=false;
				for(int i=0;i<teaCous.size();i++)
				{
					if(!ctop.FindConflict(CID, (int)teaCous.get(i).getCID()))
					{
						flag=true;
						break;
					}
				}
				if(!flag)
				{
					if(cop.InsertTeacher(c)==70018)
					{
						request.setAttribute("option", "choosecourse");
						request.setAttribute("info", "选课成功！");
						request.setAttribute("username", id);
						request.getRequestDispatcher("/TeaInfo.jsp").forward(request,response);
						return;
					}
					else {
						System.out.println("查找出错");
					}
					
				}
				else
				{
					request.setAttribute("option", "choosecourse");
					request.setAttribute("info", "该课程与已选课程间有冲突");
					request.setAttribute("username", id);
					request.getRequestDispatcher("/TeaInfo.jsp").forward(request,response);
					return;
				}
			}
			else if(option==2)//退课
			{
				if(cop.DeleteTeacher(c)==70019) {
					request.setAttribute("option", "choosecourse");
				request.setAttribute("info", "已退课！");
				request.setAttribute("username", id);
				request.getRequestDispatcher("/TeaInfo.jsp").forward(request,response);
				}
				else {
					System.out.println("查找出错");
				}
				
				
			}
			else
			{
				System.out.println("option 传递失败");
			}
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
