package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.DepartOP;
import com.control.DB.StuFeeOP;
import com.control.DB.StudentOP;
import com.model.javabean.DepInfo;
import com.model.javabean.Student;
import com.model.javabean.Stufee;

@WebServlet("/AdminStuFee")
public class AdminStuFee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminStuFee() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
		if(request.getParameter("info").equals("查看未缴费名单")) {
			StuFeeOP sfop=new StuFeeOP();
			ArrayList<Stufee> info=sfop.FindStuNoFee();
			StudentOP sdop=new StudentOP();
			DepartOP dop=new DepartOP();
			ArrayList<Student> stuinfo=new ArrayList<Student>();
			ArrayList<String> depinfo=new ArrayList<String>();
			for(Stufee tt:info) {
				Student temp=new Student();
				temp.setSID(tt.getSID());
				temp=sdop.FindStudent(temp);
				stuinfo.add(temp);
				DepInfo temp1=new DepInfo();
				temp1.setDID(temp.getDID());
				temp1=dop.FindDepartDID(temp1);
				depinfo.add(temp1.getName());
			}
			request.setAttribute("NoFeeInfo", info);
			request.setAttribute("student", stuinfo);
			request.setAttribute("depinfo", depinfo);
			request.getRequestDispatcher("/RootStuFee2.jsp").forward(request,response);
		}else {
			StuFeeOP sfop=new StuFeeOP();
			if(request.getParameter("stuid")==null||request.getParameter("stuid").equals("")) {
				request.getRequestDispatcher("/RootStuFee.html").forward(request,response);
				return;
			}
			long num=Integer.parseInt(request.getParameter("stuid"));
			Stufee sftemp=new Stufee();
			sftemp.setSID(num);
			ArrayList<Stufee> info=sfop.FindStuAllFee(sftemp);
			StudentOP sdop=new StudentOP();
			DepartOP dop=new DepartOP();
			ArrayList<Student> stuinfo=new ArrayList<Student>();
			ArrayList<String> depinfo=new ArrayList<String>();
			for(Stufee tt:info) {
				Student temp=new Student();
				temp.setSID(tt.getSID());
				temp=sdop.FindStudent(temp);
				stuinfo.add(temp);
				DepInfo temp1=new DepInfo();
				temp1.setDID(temp.getDID());
				temp1=dop.FindDepartDID(temp1);
				depinfo.add(temp1.getName());
			}
			request.setAttribute("NoFeeInfo", info);
			request.setAttribute("student", stuinfo);
			request.setAttribute("depinfo", depinfo);
			request.getRequestDispatcher("/RootStuFee2.jsp").forward(request,response);
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
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
