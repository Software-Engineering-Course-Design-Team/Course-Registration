
package com.control.DB;

import com.model.javabean.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.JDBC.*;

public class TeacherOP {
	JDBC dbcon=new JDBC();
	//insert
	public long InsertTeacher(Teacher tea){
		long id=20001;
		try {
			if(tea.getBirthday()==null || tea.getStatus()==null || tea.getSex()==null
					|| tea.getIdcard() == null || tea.getName()==null || tea.getDID()==0) return 20000;
				dbcon.getConnection();
				Statement stmt1=dbcon.conn.createStatement();
				ResultSet res=stmt1.executeQuery("select * from departmentInfo where DID="+tea.getDID()+";");
				if(!res.next()){
					dbcon.CancleConnection();
					return 20007;
				}
				String sql="Insert into profinfo values ('"+tea.getBirthday()
					+"','"+tea.getStatus()+"','"+tea.getSex()+"','"+tea.getIdcard()+"','"
					+tea.getName()+"',NULL,"+tea.getDID()+");";
				java.sql.PreparedStatement ps=dbcon.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.executeUpdate();
				ResultSet rs=ps.getGeneratedKeys();
				if(rs.next()){
					id=rs.getLong(1);
				}else{
					dbcon.CancleConnection();
					return 20001;
				}
				stmt1.execute("Insert into countinfo values(1,'000000',"+id+");");
				dbcon.CancleConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return id;
	}
	//delete
	public long DeleteTeacher(Teacher tea){
		try {
			dbcon.getConnection();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res=stmt1.executeQuery("select * from profinfo where PID="+tea.getPID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 20002;
			}
			Statement stmt2=dbcon.conn.createStatement();
			ResultSet res2=stmt2.executeQuery("select * from courseinfo where PID="+tea.getPID()+";");
			if(res2.next()){
				dbcon.CancleConnection();
				return 20009;
			}
			ResultSet res3=stmt2.executeQuery("select * from couteatable where PID="+tea.getPID()+";");
			if(res3.next()){
				dbcon.CancleConnection();
				return 20010;
			}
			Statement stmt=dbcon.conn.createStatement();
			stmt.execute("Delete from profinfo where PID="+tea.getPID()+";");
			stmt.execute("Delete from countinfo where ID="+tea.getPID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return 20003;
	}
	//find
	public Teacher FindTeacher(Teacher tea){
		dbcon.getConnection();
		try {
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from profinfo where PID="+tea.getPID()+";");
			if(res.next()){
				tea.setBirthday(res.getString(1));
				tea.setStatus(res.getString(2));
				tea.setSex(res.getString(3));
				tea.setIdcard(res.getString(4));
				tea.setName(res.getString(5));
				tea.setPID(res.getLong(6));
				tea.setDID(res.getLong(7));
			}else{
				tea.setPID(20002);
				//TODO:xr
				//confirm 原先为20004
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbcon.CancleConnection();
		return tea;
	}
	//update
	public long UpdateTeacher(Teacher tea){
		try {
			if(tea.getBirthday()!=null&&tea.getStatus()!=null&&tea.getSex()!=null
					&&tea.getIdcard()!=null&&tea.getName()!=null&&tea.getDID()!=0){
			dbcon.getConnection();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res=stmt1.executeQuery("select * from profinfo where PID="+tea.getPID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 20005;
			}
			ResultSet res2=stmt1.executeQuery("select * from departmentInfo where DID="+tea.getDID()+";");
			if(!res2.next()){
				dbcon.CancleConnection();
				return 20011;
			}
			Statement stmt=dbcon.conn.createStatement();
			stmt.execute("Update profinfo set Birthday='"+tea.getBirthday()
			+"',Status='"+tea.getStatus()+"',Sex='"+tea.getSex()
			+"',Idcard='"+tea.getIdcard()+"',Name='"+tea.getName()
			+"',DID="+tea.getDID()+" "
			+"where PID="+tea.getPID()+";");
			dbcon.CancleConnection();
			}else {
				dbcon.CancleConnection();
				return 20008;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}  
		return 20006;
	}
	//find2
	public ArrayList<Teacher> FindDepTea(Teacher tea){
		dbcon.getConnection();
		ArrayList<Teacher> result=new ArrayList<Teacher>();
		try {
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from profinfo where DID="+tea.getDID()+";");
			while(res.next()){
				Teacher temp=new Teacher();
				temp.setBirthday(res.getString(1));
				temp.setStatus(res.getString(2));
				temp.setSex(res.getString(3));
				temp.setIdcard(res.getString(4));
				temp.setName(res.getString(5));
				temp.setPID(res.getLong(6));
				temp.setDID(res.getLong(7));
				result.add(temp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		dbcon.CancleConnection();
		return result;
	}
}

