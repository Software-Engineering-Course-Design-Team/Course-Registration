
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.JDBC.JDBC;
import com.model.javabean.CouTea;

public class CouTeaOP {
	JDBC dbcon=new JDBC();
	public long InsertCouTea(CouTea coutea){
		if(coutea.getPID()==0||coutea.getName()==null)return 55007;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from profinfo where PID="+coutea.getPID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 55008;
			}
			ResultSet res2=stmt.executeQuery("select * from couteatable where PID="+coutea.getPID()+
					" and Name='"+coutea.getName()+"';");
			if(res2.next()){
				dbcon.CancleConnection();
				return 55009;
			}
			stmt.execute("Insert into couteatable values ('"+
			coutea.getName()+"',"+coutea.getPID()+");");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 55010;
	}
	public long DeleteCou(CouTea coutea){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from couteatable where Name='"+coutea.getName()+"';");
			if(!res.next()){
				dbcon.CancleConnection();
				return 55005;
			}
			stmt.execute("Delete from couteatable where Name='"+coutea.getName()+"';");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 55006;
	}
	public long DeleteTea(CouTea coutea){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from profinfo where PID="+coutea.getPID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 55003;
			}
			stmt.execute("Delete from couteatable where PID="+coutea.getPID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 55004;
	}
	public long DeleteCouTea(CouTea coutea){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from couteatable where PID="+coutea.getPID()+
					" and  Name='"+coutea.getName()+"';");
			if(!res.next()){
				dbcon.CancleConnection();
				return 55001;
			}
			stmt.execute("Delete from couteatable where PID="+coutea.getPID()+
					" and  Name='"+coutea.getName()+"';");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 55002;
	}
	public ArrayList<CouTea> FindTea(CouTea coutea){
		ArrayList<CouTea> result = new ArrayList<CouTea>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select PID,Name from couteatable where Name='"+coutea.getName()+"';");
			while(res.next()){
				CouTea temp=new CouTea();
				temp.setPID(res.getLong(1));
				temp.setName(res.getString(2));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<CouTea> FindCou(CouTea coutea){
		ArrayList<CouTea> result = new ArrayList<CouTea>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select PID,Name from couteatable where PID="+
					coutea.getPID()+";");
			while(res.next()){
				CouTea temp=new CouTea();
				temp.setPID(res.getLong(1));
				temp.setName(res.getString(2));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
}
