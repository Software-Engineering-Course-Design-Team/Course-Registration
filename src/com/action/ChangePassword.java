package com.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.CountOP;
import com.model.javabean.Count;

public class ChangePassword extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("count");
		String oldpass=request.getParameter("oldpass");
		String newpass=request.getParameter("newpass");
		String repass=request.getParameter("repass");
		CountOP cop=new CountOP();
		if(LoginCheckServlet.logincount==null)
		{
			System.out.println("no curr_count");
			request.setAttribute("info", "当前未登录");
			switch(LoginCheckServlet.logincount.getIdentity())
			{
			case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
			case 1:request.getRequestDispatcher("/TeaPwdError.jsp").forward(request,response);
					break;
				
			case 2:request.getRequestDispatcher("/StuPwdError.jsp").forward(request,response);break;
			default:break;
				
			}
			return;
			
		}
		else
		{
			//Count curr_c=LoginCheckServlet.logincount;
			
			if(!LoginCheckServlet.logincount.getPassword().equals(oldpass))
			{
				request.setAttribute("info", "输入的原密码错误！");
				switch(LoginCheckServlet.logincount.getIdentity())
				{
				case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
				case 1:request.getRequestDispatcher("/TeaPwdError.jsp").forward(request,response);
						break;
					
				case 2:request.getRequestDispatcher("/StuPwdError.jsp").forward(request,response);break;
				default:break;
				}
				return;
				
			}
			else 
			{
				if(newpass.equals(repass))
				{
					if(newpass.length()<6)
					{
						request.setAttribute("info", "密码长度不可小于6位！");
						switch(LoginCheckServlet.logincount.getIdentity())
						{
						case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
						case 1:request.getRequestDispatcher("/TeaPwdError.jsp").forward(request,response);
								break;
							
						case 2:request.getRequestDispatcher("/StuPwdError.jsp").forward(request,response);break;
						default:break;
						}
						return;
					}
					else if(newpass.length()>16)
					{
						request.setAttribute("info", "密码长度不可大于16位！");
						switch(LoginCheckServlet.logincount.getIdentity())
						{
						case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
						case 1:request.getRequestDispatcher("/TeaPwdError.jsp").forward(request,response);
								break;
							
						case 2:request.getRequestDispatcher("/StuPwdError.jsp").forward(request,response);break;
						default:break;
						}
						return;
					}
					else {
						LoginCheckServlet.logincount.setPassword(newpass);
						cop.ChangePassword(LoginCheckServlet.logincount);
						request.setAttribute("info", "密码更改成功");
						switch(LoginCheckServlet.logincount.getIdentity())
						{
						case 0:request.getRequestDispatcher("/AdminInfo.jsp").forward(request,response);break;
						case 1:request.getRequestDispatcher("/TeaInfo.jsp").forward(request,response);
								break;
							
						case 2:request.getRequestDispatcher("/StuInfo.jsp").forward(request,response);break;
						default:break;
						}
						return;
					}
					
				}
				else
				{
					request.setAttribute("info", "两次密码不一致！");
					switch(LoginCheckServlet.logincount.getIdentity())
					{
					case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
					case 1:request.getRequestDispatcher("/TeaPwdError.jsp").forward(request,response);
							break;
						
					case 2:request.getRequestDispatcher("/StuPwdError.jsp").forward(request,response);break;
					default:break;
					}
					return;
					
				}
				
			}
		}
		
	}
}
