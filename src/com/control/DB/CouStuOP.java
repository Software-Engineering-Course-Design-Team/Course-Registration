
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.model.JDBC.JDBC;
import com.model.javabean.CouStu;

public class CouStuOP {
	JDBC dbcon=new JDBC();
	public long InsertCouStu(CouStu constu){
		if(constu.getSID()==0||constu.getCID()==0)return 60001;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res10=stmt.executeQuery("select * from constutable"
					+ " where CID="+constu.getCID()+" and SID="+constu.getSID()+";");
			if(res10.next()){
				dbcon.CancleConnection();
				return 60001;
			}
			ResultSet res=stmt.executeQuery("select * from stuinfo where SID"
					+ "="+constu.getSID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 60002;
			}
			ResultSet res1=stmt.executeQuery("select * from courseinfo where CID="+constu.getCID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 60003;
			}
			ResultSet res2=stmt.executeQuery("select person from courseinfo where CID="+constu.getCID()+";");
			res2.next();
			if(res2.getInt(1)>=10){
				dbcon.CancleConnection();
				return 60014;
			}
			ResultSet res20=stmt.executeQuery("select count(*) from constutable where SID="+constu.getSID()+";");
			res20.next();
			if(res20.getInt(1)>=4){
				dbcon.CancleConnection();
				return 60015;
			}
			ResultSet res4=stmt.executeQuery("select DID from stuinfo where SID="+constu.getSID()+";");
			res4.next();
			long t1=res4.getLong(1);
			ResultSet res5=stmt.executeQuery("select DID from courseinfo where CID="+constu.getCID()+";");
			res5.next();
			long t2=res5.getLong(1);
			if(t1!=t2){
				dbcon.CancleConnection();
				return 60016;
			}
			stmt.execute("Insert into constutable values('',"+constu.getSID()+","
					+constu.getCID()+");");
			stmt.execute("Update courseinfo set person=person+1 where CID="+constu.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 60004;
	}
	public ArrayList<CouStu> FindStu(CouStu constu){
		ArrayList<CouStu> result = new ArrayList<CouStu>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutable where CID="+
					constu.getCID()+";");
			while(res.next()){
				CouStu temp=new CouStu();
				temp.setGrade(res.getString(1));
				temp.setSID(res.getLong(2));
				temp.setCID(res.getLong(3));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<CouStu> FindCou(CouStu constu){
		ArrayList<CouStu> result = new ArrayList<CouStu>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutable where SID="+
					constu.getSID()+";");
			while(res.next()){
				CouStu temp=new CouStu();
				temp.setGrade(res.getString(1));
				temp.setSID(res.getLong(2));
				temp.setCID(res.getLong(3));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<CouStu> FindCouTerm(CouStu constu,int term){
		ArrayList<CouStu> result1 = new ArrayList<CouStu>();
		ArrayList<CouStu> result = new ArrayList<CouStu>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutable where SID="+
					constu.getSID()+";");
			while(res.next()){
				CouStu temp=new CouStu();
				temp.setGrade(res.getString(1));
				temp.setSID(res.getLong(2));
				temp.setCID(res.getLong(3));
				result1.add(temp);
			}
			for(CouStu i:result1) {
				ResultSet res2=stmt.executeQuery("select * from courseinfo where CID="+
						i.getCID()+" and Term="+term+";");
				if(res2.next())result.add(i);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return result;
	}
	public CouStu FindCouStu(CouStu constu){
		CouStu temp=new CouStu();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutable where SID="+
					constu.getSID()+" and CID="+constu.getCID()+";");
			if(res.next()){
				temp.setGrade(res.getString(1));
				temp.setSID(res.getLong(2));
				temp.setCID(res.getLong(3));
			}else temp.setSID(60014);
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return temp;
	}
	public long UpdataConStu(CouStu constu){
		if(constu.getSID()==0||constu.getCID()==0||constu.getGrade()==null)return 60009;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutable where SID="+constu.getSID()+
					" and CID="+constu.getCID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 60010;
			}
			stmt.execute("Update constutable set Grade='"+constu.getGrade()
			+"' where CID="+constu.getCID()+" and SID="+constu.getSID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 60011;
	}
	public long DeleteCou(CouStu constu){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res1=stmt.executeQuery("select * from courseinfo where CID="+constu.getCID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 60005;
			}
			stmt.execute("Update courseinfo set person=0 where CID="+constu.getCID()+";");
			stmt.execute("Delete from constutable where CID="+constu.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 60006;
	}
	public long DeleteStu(CouStu constu){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stuinfo where SID"
					+ "="+constu.getSID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 60007;
			}
			ResultSet res1=stmt.executeQuery("select CID from constutable where SID"
					+ "="+constu.getSID()+";");
			ArrayList<Integer> list = new ArrayList<Integer>();
			while(res1.next()){
				list.add(res1.getInt(1));
			}
			Iterator<Integer> it=list.iterator();
			while(it.hasNext()){
				stmt.execute("Update courseinfo set person=person-1 where CID="+it.next()+";");
			}
			stmt.execute("Delete from constutable where SID="+constu.getSID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 60008;
	}
	public long DeleteCouStu(CouStu constu){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutable where SID="+constu.getSID()+
					" and CID="+constu.getCID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 60012;
			}
			stmt.execute("Delete from constutable where SID="+constu.getSID()+
					" and CID="+constu.getCID()+";");
			stmt.execute("Update courseinfo set person=person-1 where CID="+constu.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 60013;
	}
}
