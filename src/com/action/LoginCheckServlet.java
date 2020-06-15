package com.action;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.JDBC.JDBC;
import com.model.javabean.Count;
import com.model.javabean.Student;
import com.control.DB.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Enumeration;

public class LoginCheckServlet extends HttpServlet {
	//public static 
	Count logincount;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
		while (em.hasMoreElements()) {
             request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
		}
		String userName = request.getParameter("username");
		String userPwd = request.getParameter("password");
		String info = "";
		
		CountOP cop=new CountOP();
		Count curr_c=cop.FindCount(new Count(0,"",Integer.parseInt(userName)));
		if(curr_c.getID()==80003)
		{
			logincount=null;
			request.getRequestDispatcher("/relogin.html").forward(request,response);
			return;
		}
		else
		{
			if(curr_c.getPassword().equals(userPwd))
			{
				logincount=curr_c;
				if(curr_c.getIdentity()==0)
				{
					request.getRequestDispatcher("/RootMenu.html").forward(request,response);
				}
				else if(curr_c.getIdentity()==1)
				{
					request.setAttribute(userName, "username");
					
					request.getRequestDispatcher("/TeaMenu.jsp?username="+userName).forward(request,response);
				}
				else
				{
					request.getRequestDispatcher("/StudReturnServlet").forward(request,response);
				}
			}
			else
			{
				logincount=null;
				request.getRequestDispatcher("/relogin.html").forward(request,response);
			}
		}
		
	
	}
	
}
