package com.action.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.LoginCheckServlet;
import com.control.DB.CouStuOP;
import com.control.DB.CountOP;
import com.control.DB.CourseOP;
import com.control.DB.StudentOP;
import com.model.javabean.CouStu;
import com.model.javabean.Count;
import com.model.javabean.Course;
import com.model.javabean.Student;

/**
 * Servlet implementation class TeaGetCrsList
 */
@WebServlet("/TeaGetCrsList")
public class TeaGetCrsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaGetCrsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String referer=request.getHeader("referer");
		String nativeServerName=request.getServerName();
		try {
		
		int option=Integer.parseInt(request.getParameter("option"));
		System.out.println("option="+option);
		/*System.out.println(nativeServerName);
		if(!nativeServerName.equals("localhost:8080"))
		{*/
		int id=Integer.parseInt(request.getParameter("username"));
		
		System.out.println("username="+id);
		CountOP cop=new CountOP();
		Count logincount=new Count();
		logincount.setID(id);
		logincount=cop.FindCount(logincount);
		if(logincount!=null)
		{
			CourseOP crsop=new CourseOP();
			Course cs=new Course();
			cs.setPID(logincount.getID());
			ArrayList<Course> crs=crsop.FindTeaCou(cs);
			for(int i=0;i<crs.size();i++)
			{
				System.out.println(crs.get(i).getCID());
				if(crs.get(i).getPerson()==0)//人数为0，表示是当前学期的课程
				{
					System.out.println("Person=0");
					continue;
				}
				else
				{
					CouStuOP csop=new CouStuOP();
					CouStu coustu=new CouStu();
					coustu.setCID(crs.get(i).getCID());
					ArrayList<CouStu> thiscrsstu=csop.FindStu(coustu);
					Student s=new Student();
					s.setSID(thiscrsstu.get(0).getSID());
					StudentOP stuop=new StudentOP();
					s=stuop.FindStudent(s);
					System.out.println(s.getSID());
					int gyear=Integer.parseInt(s.getGradDate().substring(0,4));
					Calendar date = Calendar.getInstance();
					int nyear = date.get(Calendar.YEAR);
					int month = date.get(Calendar.MONTH);
					int stuterm=(4-gyear+nyear)*2;
					if(month>8)stuterm++;
					System.out.println("学生是第"+stuterm+"学期");
					System.out.println("课程开放在第"+crs.get(i).getTerm()+"学期");
					if(stuterm!=crs.get(i).getTerm())
					{
						crs.remove(i);
						i--;
					}
				}
			}
			Calendar date = Calendar.getInstance();
			int month = date.get(Calendar.MONTH);
			int xueqi=1;
			if(month>6)
				xueqi=0;
			request.setAttribute("option", option);
			request.setAttribute("teacrslist", crs);
			request.setAttribute("username", id);
			request.getRequestDispatcher("/TeaCrsList.jsp?username="+id).forward(request,response);
			
		}
		else
		{
			System.out.println("no curr_count");
			request.getRequestDispatcher("/login.html").forward(request,response);
		}
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
		//}
		//else
		//{
			//request.getRequestDispatcher("/StuError.html").forward(request,response);
			//System.out.println("盗链");
		//}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		
		
	}

}
