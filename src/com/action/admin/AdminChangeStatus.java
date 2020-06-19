package com.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.DB.ConStuTempOP;
import com.control.DB.CouStuOP;
import com.control.DB.CouTimeOP;
import com.control.DB.CourseOP;
import com.control.DB.DepartOP;
import com.control.DB.StuFeeOP;
import com.control.DB.StudentOP;
import com.model.javabean.CouStu;
import com.model.javabean.CouStuTemp;
import com.model.javabean.CouTime;
import com.model.javabean.Course;
import com.model.javabean.DepInfo;
import com.model.javabean.Student;
import com.model.javabean.Stufee;

/**
 * Servlet implementation class AdminChangeStatus
 */
@WebServlet("/AdminChangeStatus")
public class AdminChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangeStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int DID = Integer.valueOf(request.getParameter("DID"));
		String Name = request.getParameter("Name");
		String flag = request.getParameter("flag");
		try {
		DepartOP dop=new DepartOP();
		DepInfo df=new DepInfo();
		ConStuTempOP cstop=new ConStuTempOP();
		CouStuOP csop=new CouStuOP();
		CouTimeOP ctop=new CouTimeOP();
		CourseOP cop=new CourseOP();
		StudentOP sop=new StudentOP();
		StuFeeOP sfop=new StuFeeOP();
		
		Course c=new Course();
		c.setDID(DID);
		
		df.setDID(DID);
		df.setName(Name);
		System.out.println(flag);
		switch(flag)
		{
		case "开启教师选课状态":df.setStatus(1);System.out.println("start teacher");
			break;
		case "开启学生选课状态":df.setStatus(2);System.out.println("start student");break;
		case "关闭教师选课状态":df.setStatus(0);System.out.println("close teacher");
			ArrayList<Course> noteacher=cop.FindTeaNullDepCou(c);
			for(int i=0;i<noteacher.size();i++)
			{
				cop.DeleteCourse(noteacher.get(i));//删除本学院无教师授课课程
				CouTime ct=new CouTime();
				ct.setCID(noteacher.get(i).getCID());//删除CouTea对应上课时间
				ctop.DeleteCou(ct);
			}
			break;
		case "关闭学生选课状态":df.setStatus(0);System.out.println("close student");
		Student stu=new Student();
		stu.setDID(DID);
		System.out.println("DID="+stu.getDID());
		ArrayList<Student> dstu=sop.FindDepStu(stu);
		boolean flagord0=false;
		for(int sn=0;sn<dstu.size();sn++)
		{
			System.out.println("正在检查学生"+dstu.get(sn).getSID());
			if(cstop.HasOrder0Stu(dstu.get(sn).getSID()))
			{
				System.out.println("学生"+dstu.get(sn).getSID()+"存在ord=0，注册关闭失败！");
				flagord0=true;
				break;
			}
		}
		if(flagord0)
		{
			
			df.setStatus(2);
			request.setAttribute("RootInfo", 1);
			request.getRequestDispatcher("./RootInfo4.jsp").forward(request,response);
			return;
		}
		else if(!cstop.FindFee())
		{
			boolean feeCanUse=false;
			long start=System.currentTimeMillis();
			long now=System.currentTimeMillis();
			while((now-start)/1000.0<10)
			{
				System.out.println("学费系统已检查"+(now-start)/1000+"s");
				if(cstop.FindFee())
					{feeCanUse=true;break;}
				now=System.currentTimeMillis();
			}
			if(!feeCanUse) {
				System.out.println("学费系统"+(now-start)/1000+"内关闭，注册关闭失败！");
			df.setStatus(2);
			request.setAttribute("RootInfo", 2);
			request.getRequestDispatcher("./RootInfo4.jsp").forward(request,response);
			return;}
		}
		
		/******************************************************************
		 *                             删除不足3人的课程	
		 ********************************************************************/
		ArrayList<Course> lessthan3=cop.FindPerless3DepCou(c);
			for(int i=0;i<lessthan3.size();i++)
			{
				CouStuTemp cst=new CouStuTemp();
				cst.setCID(lessthan3.get(i).getCID());
				cstop.DeleteConTemp(cst);          //删除ConStuTemp表里信息
				CouStu cs=new CouStu();
				cs.setCID(lessthan3.get(i).getCID());
				csop.DeleteCou(cs);                //删除CouStu表里的信息
				CouTime ct=new CouTime();
				ct.setCID(lessthan3.get(i).getCID());
				ctop.DeleteCou(ct);
				long flagg=cop.DeleteCourse(lessthan3.get(i));
				if(flagg==70008)
				{
					System.out.println("课程"+lessthan3.get(i).getCID()+"已删除");
					                               //删除Course里该学院人数<3的全部课程
				}
				else
					System.out.println("课程"+lessthan3.get(i).getCID()+"没能删除"+flagg);
			}
			
		    /******************************************************************
			 * 									课程补充
			 ***********************************************************************/
			
			for(int i=0;i<dstu.size();i++)
			{
				stu=dstu.get(i);
				System.out.println(stu.getSID());
				CouStu cs=new CouStu();
				cs.setSID(stu.getSID());
				//ArrayList<CouStu> stucous=csop.FindCou(cs);
				
				int gyear=Integer.parseInt(stu.getGradDate().substring(0,4));
				Calendar date = Calendar.getInstance();
				int nyear = date.get(Calendar.YEAR);
				int month = date.get(Calendar.MONTH);
				int term=(4-gyear+nyear)*2;
				if(month>8)term++;

				ArrayList<CouStu> stucous=csop.FindCouTerm(cs, term);
				System.out.println("自己在第"+term+"学期选择的课程个数："+stucous.size());
				
				CouStuTemp cst=new CouStuTemp();
				cst.setSID(stu.getSID());
				ArrayList<CouStuTemp> coustemp=cstop.FindOrder12CouTemp(cst);
				int kk=coustemp.size()-1;
				for(int k=kk;k>=0;k--)
				{
					Course cc=new Course();
					cc.setCID(coustemp.get(k).getCID());
					cc=cop.FindCourse(cc);
					if(cc.getPerson()>=10)
					{
						coustemp.remove(k);
					}
				}
				
				CouStu cs2=new CouStu();
				switch(stucous.size())
				{
				case 0:System.out.println("case 0");
				case 1:System.out.println("case 1");
				case 2:System.out.println("case 2");
					if(coustemp.size()>0)
					{
						cs2.setSID(coustemp.get(0).getSID());
						cs2.setCID(coustemp.get(0).getCID());
						csop.InsertCouStu(cs2);
						Course cc=new Course();
						cc.setCID(coustemp.get(0).getCID());
						cc=cop.FindCourse(cc);
						cc.setPerson(1+cc.getPerson());
						System.out.println(cc.getCID()+"人数变为"+cc.getPerson());
						cop.UpdateCourse(cc);
						cstop.DeleteConStuTemp(coustemp.get(0));
						System.out.println("候选区有>0个order记录");
					}
					if(coustemp.size()>1) {
					
						cs2.setSID(coustemp.get(1).getSID());
						cs2.setSID(coustemp.get(1).getSID());
						csop.InsertCouStu(cs2);
						Course cc=new Course();
						cc.setCID(coustemp.get(1).getCID());
						cc=cop.FindCourse(cc);
						cc.setPerson(1+cc.getPerson());
						System.out.println(cc.getCID()+"人数变为"+cc.getPerson());
						cop.UpdateCourse(cc);
						cstop.DeleteConStuTemp(coustemp.get(1));
						System.out.println("候选区有>1个order记录");
					}
					System.out.println("提交后的课程个数："+(stucous.size()+2));
					break;
				case 3:
					if(coustemp.size()>0) {
						cs2.setSID(coustemp.get(0).getSID());
						cs2.setCID(coustemp.get(0).getCID());
						csop.InsertCouStu(cs2);
						Course cc=new Course();
						cc.setCID(coustemp.get(0).getCID());
						cc=cop.FindCourse(cc);
						cc.setPerson(1+cc.getPerson());
						System.out.println(cc.getCID()+"人数变为"+cc.getPerson());
						cop.UpdateCourse(cc);
						cstop.DeleteConStuTemp(coustemp.get(0));
						System.out.println("进入课程表的是第"+coustemp.get(0).getOrder()+"个候选课");
						}
						
					System.out.println("提交后的课程个数："+(stucous.size()+1));
					break;
				default:break;
				}
				
				/******************************************************************
				 * 								学费生成
				 ********************************************************************/
				System.out.println("学费生成");
				
				ArrayList<CouStu> stucouU=csop.FindCouTerm(cs, term);
				int allfee=0;
				for(int j=0;j<stucouU.size();j++)
				{
					Course course=new Course();
					course.setCID(stucouU.get(j).getCID());
					if(cop.FindCourse(course).getCID()!=70009)
					{
						allfee+=cop.FindCourse(course).getFee();
					}
				}
				Stufee sf=new Stufee();
				sf.setFee(allfee);
				sf.setSID(stu.getSID());
				sf.setStatus("未缴清");
				sf.setTerm(term);
					
				long flagg=sfop.InsertStuFee(sf);
				if(flagg!=30003)
				{
					System.out.println("插入学费信息出错");
				}
				
				/******************************************************************
				 * 							清空候选记录
				 ********************************************************************/
				cstop.DeleteStuTemp(cst);
				
			}
			
			
			
			break;
			 
		default:break;
		}
		dop.UpdateDepartStatus(df);
		request.getRequestDispatcher("./GetDepServlet").forward(request,response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
