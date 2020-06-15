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
 * Servlet implementation class UpdateDepServlet
 */
@WebServlet("/UpdateDepServlet")
public class UpdateDepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	 	String method=request.getParameter("action");
        if(method.equals("search"))
        {
            search(request, response);
        }
        else if(method.equals("update")){
            update(request, response);
        }	
	}
	private void search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
		DepartOP depOp=new DepartOP();
		long DID = Integer.parseInt(request.getParameter("DID"));
		DepInfo dep = new DepInfo();
		dep.setDID(DID);
		DepInfo result = depOp.FindDepartDID(dep);
		if(result.getDID()==10007) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('找不到该学院！');window.location.href='RootMenu.html'</script>");
		}else {
			request.setAttribute("result",result);
			request.getRequestDispatcher("/UpdateDepServlet.jsp").forward(request,response);
		}
    }
    
    private  void update(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
    	request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		if (name.equals("")) name = null;
		long dID = Integer.parseInt(request.getParameter("DID"));
		
		DepartOP depOp=new DepartOP();
		DepInfo tempDep = new DepInfo();
		tempDep.setDID(dID);
		DepInfo oldDep = depOp.FindDepartDID(tempDep);
		oldDep.setName(name);
		
		long DID = depOp.UpdateDepartInfo(oldDep);
		String message = null;
		if(DID==10002) {
			  message = "更新学院信息失败！原因:学院信息不存在！;";
		}else if(DID==10001) {
			message = "更新学院信息失败！原因:学院信息不全！;";
		}else if(DID==10000){
			message = "更新学院信息成功！";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");
        
    }

}
