package com.action.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.DepartOP;
import com.model.javabean.DepInfo;

/**
 * Servlet implementation class AddDepServlet
 */
@WebServlet("/AddDepServlet")
public class AddDepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		if (name.equals("")) name = null;

		try {
		DepInfo dep = new DepInfo();
		dep.setName(name);
		DepartOP depOp=new DepartOP();
		long DID = depOp.InsertDepart(dep);
		String message = null;
		if(DID==10005) {
			  message = "添加学院失败！原因:信息不完整;";
		}else if(DID==10006) {
			message = "添加学院失败！原因:数据库插入操作失败;";
		}else {
			message = "添加学院成功！学院Id:"+DID+";";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");
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
