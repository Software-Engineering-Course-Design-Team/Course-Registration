package com.action.teacher;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CouTeaOP;
import com.control.DB.CourseOP;
import com.control.DB.DepartOP;
import com.action.LoginCheckServlet;
import com.model.javabean.CouTea;
import com.model.javabean.Course;
import com.model.javabean.DepInfo;

/**
 * Servlet implementation class TeaGetQualiList
 */
@WebServlet("/TeaGetQualiList")
public class TeaGetQualiList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaGetQualiList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseOP cop=new CourseOP();
		CouTeaOP ctop=new CouTeaOP();
		DepartOP dop=new DepartOP();
		int option=Integer.parseInt(request.getParameter("option"));
		if(LoginCheckServlet.logincount!=null)
		{
			CouTea ct=new CouTea();
			ct.setPID(LoginCheckServlet.logincount.getID());
			ArrayList<CouTea> findtea=ctop.FindCou(ct);//获取全部用有资格的课程名
			ArrayList<Course> AbleCou=new ArrayList<Course>();
			ArrayList<Course> UnableCou=new ArrayList<Course>();
			for(int i=0;i<findtea.size();i++)
			{
				Course c=new Course();
				c.setName(findtea.get(i).getName());
				ArrayList<Course> sear=cop.FindNameCou(c);//获取有资格教授的全部课程信息
				if(sear.size()==0)
				{
					UnableCou.add(c);
					
				}
				else {
					for(int j=0;j<sear.size();j++)
					{
						Course cc=sear.get(j);
						if(option==1)
						{
							DepInfo df=new DepInfo();
							df.setDID(cc.getDID());
							if(dop.FindDepartDID(df).getDID()==10007)
							{
								System.out.println("未找到该学院信息，数据设置有错误");
							}
							else
							{
								df=dop.FindDepartDID(df);
								if(df.getStatus()==1)
								{
									AbleCou.add(cc);
								}
							}
						}
						else
						{
							AbleCou.add(cc);
						}
						
					}
				}
			}
			
			AbleCou.sort(Course.CIDComparator);
			UnableCou.sort(Course.CIDComparator);
			request.setAttribute("option", option);
			System.out.println("option="+option);
			request.setAttribute("ablecou", AbleCou);
			request.setAttribute("unablecou",UnableCou);
			request.getRequestDispatcher("/TeaQuali.jsp").forward(request,response);
		}
		else
		{
			System.out.println("no curr_count");
			request.setAttribute("info", "当前未登录");
			request.getRequestDispatcher("/TeaError.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
