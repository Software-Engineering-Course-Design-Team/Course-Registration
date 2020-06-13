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
 * Servlet implementation class FindorDeleteDepServlet
 */
@WebServlet("/FindorDeleteDepServlet")
public class FindorDeleteDepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	 	String method=request.getParameter("action");
        if(method.equals("search"))
        {
            search(request, response);
        }
        else if(method.equals("delete")){
            delete(request, response);
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
			request.setAttribute("depInfo",result);
			request.getRequestDispatcher("/FindorDeleteDepServlet.jsp").forward(request,response);
		}
    }
    
    private  void delete(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
    	request.setCharacterEncoding("UTF-8");
		long dID = Integer.parseInt(request.getParameter("DID"));
		DepInfo dep = new DepInfo();
		dep.setDID(dID);
		DepartOP depOp=new DepartOP();
		long DID = depOp.DeleteDepart(dep);
		String message = null;
		if(DID==10003) {
			  message = "删除学院信息失败！原因:学院信息不存在！;";
		}else if(DID==10008) {
			message = "删除学院信息失败！原因:学院有课程信息！;";
		}else if(DID==10009) {
			message = "删除学院信息失败！原因:学院有学生未删除！;";
		}else if(DID==10010) {
			message = "删除学院信息失败！原因:学院有教师未删除！;";
		}else if(DID==10004){
			message = "删除学院信息成功！";
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+message+"');window.location.href='RootMenu.html'</script>");
        
    }
}
