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

import com.control.DB.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginCheckServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		String userPwd = request.getParameter("password");
		String info = "";

		CountOP cop=new CountOP();
		Count curr_c=cop.FindCount(new Count(0,"",Integer.parseInt(userName)));
		if(curr_c.getID()==80003)
		{
			request.getRequestDispatcher("/relogin.html").forward(request,response);
			return;
		}
		else
		{
			if(curr_c.getPassword().equals(userPwd))
			{
				if(curr_c.getIdentity()==0)
				{
					request.getRequestDispatcher("/RootMenu.html").forward(request,response);
				}
				else if(curr_c.getIdentity()==1)
				{
					request.getRequestDispatcher("/TeaMenu.html").forward(request,response);
				}
				else
				{
					getServletContext().setAttribute(userName,"No");
					request.getRequestDispatcher("/StuMenu.jsp").forward(request,response);
				}
			}
			else
			{
				request.getRequestDispatcher("/relogin.html").forward(request,response);
			}
		}
		
	
	}
	
}
