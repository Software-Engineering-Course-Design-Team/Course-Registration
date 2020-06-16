package com.action.student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.ConStuTempOP;
import com.control.DB.CouStuOP;
import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.model.javabean.CouStu;
import com.model.javabean.CouStuTemp;
import com.model.javabean.Course;


@WebServlet("/StuCommitServlet")
public class StuCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StuCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		CouStuTemp cst=new CouStuTemp();
		ConStuTempOP cstOP=new ConStuTempOP();
		long SID=Integer.parseInt(request.getParameter("username"));
		cst.setSID(SID);
		ArrayList<CouStuTemp> ctemp=cstOP.FindCouTempOrder(cst);
		CouStu cs=new CouStu();
		CouStuOP csOP=new CouStuOP();
		ArrayList<CouStu> cctemp=csOP.FindCou(cs);
		ArrayList<Integer> tttemp=new ArrayList<Integer>();
		CouTimeOP ssop=new CouTimeOP();
		CourseOP cop=new CourseOP();
		for(CouStuTemp c:ctemp) {
			Course tempcc=new Course();
			tempcc.setCID(c.getCID());
			tempcc=cop.FindCourse(tempcc);
			if(tempcc.getPerson()>=10) {
				request.setAttribute("Flaginfo", "2");
				request.setAttribute("courseid", tempcc.getCID());
				request.setAttribute("coursename", tempcc.getName());
				request.getRequestDispatcher("/StuMenuInfo.jsp").forward(request,response);
				return;
			}
			int i=(int)c.getCID();
			tttemp.add(i);
		}
		for(CouStu cc:cctemp) {
			int i=(int)cc.getCID();
			tttemp.add(i);
		}
		boolean flag=true;
		int cou1=0,cou2=0;
		for(Integer a:tttemp) {
			for(Integer b:tttemp) {
				if(a!=b) {
					if(!ssop.FindConflict(a, b)) {
						cou1=a;
						cou2=b;
						flag=false;
						break;
					}
				}
			}
			if(!flag)break;
		}
		if(!flag) {
			Course c1=new Course();
			c1.setCID(cou1);
			c1=cop.FindCourse(c1);
			Course c2=new Course();
			c2.setCID(cou2);
			c2=cop.FindCourse(c2);
			request.setAttribute("class1", c1.getName());
			request.setAttribute("class1id", c1.getCID());
			request.setAttribute("class2", c2.getName());
			request.setAttribute("class2id", c2.getCID());
			request.getRequestDispatcher("/StuMenuInfo.jsp").forward(request,response);
			return;
		}else {
			for(CouStuTemp c:ctemp) {
				int i=(int)c.getCID();
				CouStu ntemp=new CouStu();
				ntemp.setCID(i);
				ntemp.setSID(SID);
				csOP.InsertCouStu(ntemp);
				cstOP.DeleteConStuTemp(c);
			}
			request.getRequestDispatcher("/StudFindServlet").forward(request,response);
			return;
		}}catch(Exception e)
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
