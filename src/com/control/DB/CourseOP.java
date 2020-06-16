
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.JDBC.JDBC;
import com.model.javabean.Course;

public class CourseOP {
	JDBC dbcon=new JDBC();
	public long DeleteTeacher(Course course)
	{
		try {
			dbcon.getConnection();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res1=stmt1.executeQuery("select * from profinfo where PID="+course.getPID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 70009;
			}
			stmt1.execute("Update courseinfo set PID=null where CID="+course.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 70019;
	}
	public long InsertTeacher(Course course){
		try {
			dbcon.getConnection();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res1=stmt1.executeQuery("select * from profinfo where PID="+course.getPID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 70013;
			}
			ResultSet res2=stmt1.executeQuery("select * from couteatable where PID="+course.getPID()+
					" and Name='"+course.getName()+"';");
			if(!res2.next()){
				dbcon.CancleConnection();
				return 70014;
			}
			ResultSet res3=stmt1.executeQuery("select * from courseinfo where PID="+course.getPID()+
					" and Name='"+course.getName()+"';");
			if(res3.next()){
				dbcon.CancleConnection();
				return 70017;
			}
			stmt1.execute("Update courseinfo set PID="+course.getPID()+
					" where CID="+course.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 70018;
	}
	public long InsertCourse(Course course){
		if(course.getTerm()==0||course.getDID()==0||course.getFee()==0
				||course.getName()==null||course.getBeginweek()==0||course.getEndWeek()==0)
			return 70001;
		long id=70002;
		try {
			dbcon.getConnection();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res=stmt1.executeQuery("select * from departmentInfo where DID="+course.getDID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 70003;
			}
			String sql="Insert into courseinfo values ("+course.getCID()
				+","+course.getTerm()+","+course.getDID()+","+course.getFee()+",'"
				+course.getName()+"',null,"+course.getBeginweek()+
				","+course.getEndWeek()+",0);";
			java.sql.PreparedStatement ps=dbcon.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()){
				id=rs.getLong(1);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return id;
	}
	public long DeleteCourse(Course course){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			Statement stmt2=dbcon.conn.createStatement();
			ResultSet res2=stmt2.executeQuery("select * from courseinfo where CID="+course.getCID()+";");
			if(!res2.next()){
				dbcon.CancleConnection();
				return 70007;
			}
			ResultSet res4=stmt2.executeQuery("select * from constutable where CID="+course.getCID()+";");
			if(res4.next()){
				dbcon.CancleConnection();
				return 70005;
			}
			ResultSet res5=stmt2.executeQuery("select * from constutemp where CID="+course.getCID()+";");
			if(res5.next()){
				dbcon.CancleConnection();
				return 70004;
			}
			stmt.execute("Delete from courseinfo where CID="+course.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return 70008;
	}
	public Course FindCourse(Course course){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where CID="+course.getCID()+";");
			if(res.next()){
				course.setCID(res.getLong(1));
				course.setTerm(res.getInt(2));
				course.setDID(res.getInt(3));
				course.setFee(res.getInt(4));
				course.setName(res.getString(5));
				course.setPID(res.getLong(6));
				course.setBeginweek(res.getInt(7));
				course.setEndWeek(res.getInt(8));
				course.setPerson(res.getInt(9));
			}else{
				course.setCID(70009);//����û�л�ȡ������
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return course;
	}
	public ArrayList<Course> FindTermCou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where "
					+ "Term="+course.getTerm()+";");
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<Course> FindDepCou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where "
					+ "DID="+course.getDID()+";");
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<Course> FindTermDepCou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where "
					+ "DID="+course.getDID()+" and Term="+course.getTerm()+";");
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return result;
	}
	
	public ArrayList<Course> FindTeaNullDepCou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where "
					+ "DID="+course.getDID()+" and PID is Null;");
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<Course> FindPerless3DepCou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where "
					+ "DID="+course.getDID()+" and Person<3;");
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<Course> FindTeaCou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res;
			if(course.getPID()==0)
			{
				res=stmt.executeQuery("select * from courseinfo where "
						+ "PID is null;");
			}
			else	
			{
				res=stmt.executeQuery("select * from courseinfo where "
					+ "PID="+course.getPID()+";");
				}
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<Course> FindTeaPer0Cou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res;
			if(course.getPID()==0)
			{
				res=stmt.executeQuery("select * from courseinfo where "
						+ "PID is null and Person=0;");
			}
			else	
			{
				res=stmt.executeQuery("select * from courseinfo where "
					+ "PID="+course.getPID()+" and Person=0;");
				}
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<Course> FindNameCou(Course course){
		ArrayList<Course> result=new ArrayList<Course>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res;
			res=stmt.executeQuery("select * from courseinfo where "
					+ "Name='"+course.getName()+"';");
				
			while(res.next()){
				Course temp=new Course();
				temp.setCID(res.getLong(1));
				temp.setTerm(res.getInt(2));
				temp.setDID(res.getInt(3));
				temp.setFee(res.getInt(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setBeginweek(res.getInt(7));
				temp.setEndWeek(res.getInt(8));
				temp.setPerson(res.getInt(9));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return result;
	}
	public long UpdateCourse2(Course course){
		if(course.getTerm()==0||course.getDID()==0||course.getFee()==0
				||course.getName()==null||course.getBeginweek()==0||course.getEndWeek()==0)
			return 70010;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res2=stmt.executeQuery("select * from courseinfo where CID="+course.getCID()+";");
			if(!res2.next()){
				dbcon.CancleConnection();
				return 70011;
			}
			stmt.execute("Update courseinfo set Term="+course.getTerm()+
					",DID="+course.getDID()+
					",Fee="+course.getFee()+
					",Name='"+course.getName()+
					"',BeginWeek="+course.getBeginweek()+
					",EndWeek="+course.getEndWeek()+
					" where CID="+course.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return 70012;
	}
	public long UpdateCourse(Course course){
		if(course.getPID()==0||course.getTerm()==0||course.getDID()==0||course.getFee()==0
				||course.getName()==null||course.getBeginweek()==0||course.getEndWeek()==0)
			return 70010;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res1=stmt1.executeQuery("select * from profinfo where PID="+course.getPID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 70015;
			}
			ResultSet res3=stmt1.executeQuery("select * from couteatable where PID="+course.getPID()+
					" and Name='"+course.getName()+"';");
			if(!res3.next()){
				dbcon.CancleConnection();
				return 70016;
			}
			ResultSet res2=stmt.executeQuery("select * from courseinfo where CID="+course.getCID()+";");
			if(!res2.next()){
				dbcon.CancleConnection();
				return 70011;
			}
			ResultSet res4=stmt1.executeQuery("select * from courseinfo where PID="+course.getPID()+
					" and Name='"+course.getName()+"';");
			if(res4.next()){
				dbcon.CancleConnection();
				return 70018;
			}
			stmt.execute("Update courseinfo set Term="+course.getTerm()+
					",DID="+course.getDID()+
					",Fee="+course.getFee()+
					",Name='"+course.getName()+
					"',PID="+course.getPID()+
					",BeginWeek="+course.getBeginweek()+
					",EndWeek="+course.getEndWeek()+
					" where CID="+course.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return 70012;
	}
}
