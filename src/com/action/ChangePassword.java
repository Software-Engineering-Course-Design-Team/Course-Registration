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
		try {
		CountOP cop=new CountOP();
		int id;
		System.out.println(request.getParameter("username"));
		System.out.println(request.getAttribute("username"));
		if(request.getParameter("username")==null||request.getParameter("username").equals("null"))
		{
			System.out.println("no curr_count");
			request.getRequestDispatcher("/login.html").forward(request, response);
			
		}
		
		else
		{
			Count c=new Count();
			
			if(!request.getParameter("username").equals("999999"))
			{
				id=Integer.valueOf(request.getParameter("username"));
				
				c.setID(id);
				c=cop.FindCount(c);
				c=c;
			}
			else
			{
				
				id=Integer.valueOf(request.getParameter("id"));
				c=new Count();
				c.setID(id);
				if(cop.FindCount(c).getID()==80003)
				{
					request.setAttribute("info", "不存在该ID的用户！");
					request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);
					return;
				}
				c=cop.FindCount(c);
				//c.setPassword(newpass);
				//cop.ChangePassword(c);
			}
			//Count curr_c=LoginCheckServlet.logincount;
			if(!c.getPassword().equals(oldpass))
			{
				request.setAttribute("info", "输入的原密码错误！");
				if(request.getParameter("username").equals("999999"))
				{
					request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);
				}
				else if(c.getIdentity()==1)
				{
					request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
				}
				else if(c.getIdentity()==2)
				{
					request.getRequestDispatcher("/StuPwdError.jsp?username="+id).forward(request,response);
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
						if(request.getParameter("username").equals("999999"))
						{
							request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);
						}
						else if(c.getIdentity()==1)
						{
							request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
						}
						else if(c.getIdentity()==2)
						{
							request.getRequestDispatcher("/StuPwdError.jsp?username="+id).forward(request,response);
						}
						
						return;
					}
					else if(newpass.length()>16)
					{
						request.setAttribute("info", "密码长度不可大于16位！");
						if(request.getParameter("username").equals("999999"))
						{
							request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);
						}
						else if(c.getIdentity()==1)
						{
							request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
						}
						else if(c.getIdentity()==2)
						{
							request.getRequestDispatcher("/StuPwdError.jsp?username="+id).forward(request,response);
						}
						
						return;
					}
					else {
						c.setPassword(newpass);
						cop.ChangePassword(c);
						request.setAttribute("info", "密码更改成功");
						request.setAttribute("option", "changepass");
						if(request.getParameter("username").equals("999999"))
						{
							request.getRequestDispatcher("/AdminInfo.jsp").forward(request,response);
						}
						else if(c.getIdentity()==1)
						{
							request.setAttribute("username", id);
							request.getRequestDispatcher("/TeaInfo.jsp?username="+id).forward(request,response);
						}
						else if(c.getIdentity()==2)
						{
							request.setAttribute("username", id);
							request.getRequestDispatcher("/StuInfo.jsp?username="+id).forward(request,response);
						}
						
						
						return;
					}
					
				}
				else
				{
					request.setAttribute("info", "两次密码不一致！");
					if(request.getParameter("username").equals("999999"))
					{
						request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);
					}
					else if(c.getIdentity()==1)
					{
						request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
					}
					else if(c.getIdentity()==2)
					{
						request.getRequestDispatcher("/StuPwdError.jsp?username="+id).forward(request,response);
					}
					return;
					
				}
				
			}
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
}
