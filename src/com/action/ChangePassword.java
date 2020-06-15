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
		if(request.getParameter("username")==null||request.getParameter("username").equals("null"))
		{
			System.out.println("no curr_count");
			request.getRequestDispatcher("/login.html").forward(request, response);
			
		}
		
		else
		{
			int id=Integer.valueOf(request.getParameter("username"));
			Count c=new Count();
			c.setID(id);
			c=cop.FindCount(c);
			
			if(c.getIdentity()!=0)
			{
				c=c;
			}
			else
			{
				int ID=Integer.valueOf(request.getParameter("id"));
				c=new Count();
				c.setID(ID);
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
				switch(c.getIdentity())
				{
				case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
				case 1:request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
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
						switch(c.getIdentity())
						{
						case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
						case 1:request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
								break;
							
						case 2:request.getRequestDispatcher("/StuPwdError.jsp").forward(request,response);break;
						default:break;
						}
						return;
					}
					else if(newpass.length()>16)
					{
						request.setAttribute("info", "密码长度不可大于16位！");
						switch(c.getIdentity())
						{
						case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
						case 1:request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
								break;
							
						case 2:request.getRequestDispatcher("/StuPwdError.jsp").forward(request,response);break;
						default:break;
						}
						return;
					}
					else {
						c.setPassword(newpass);
						cop.ChangePassword(c);
						request.setAttribute("info", "密码更改成功");
						request.setAttribute("option", "changepass");
						switch(c.getIdentity())
						{
						case 0:request.getRequestDispatcher("/AdminInfo.jsp").forward(request,response);break;
						case 1:request.getRequestDispatcher("/TeaInfo.jsp?username="+id).forward(request,response);
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
					switch(c.getIdentity())
					{
					case 0:request.getRequestDispatcher("/AdminPwdError.jsp").forward(request,response);break;
					case 1:request.getRequestDispatcher("/TeaPwdError.jsp?username="+id).forward(request,response);
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
