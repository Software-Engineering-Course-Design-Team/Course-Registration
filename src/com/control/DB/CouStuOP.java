
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
	public void ConfirmConStu(){
		dbcon.getConnection();
		Statement stmt;
		try {
			stmt = dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select CID,SID from constutemp where ord=0;");
			CouStuOP op=new CouStuOP();
			while(res.next()){
				CouStu constu=new CouStu();
				constu.setCID(res.getLong(1));
				constu.setSID(res.getLong(2));
				op.InsertCouStu(constu);
			}
			ResultSet res1=stmt.executeQuery("select CID from courseinfo where person<3;");
			ArrayList<Integer> tem=new ArrayList<Integer>();
			while(res1.next()){
				tem.add(res1.getInt(1));
			}
			Iterator<Integer> it=tem.iterator();
			while(it.hasNext()){
				long temp=it.next();
				stmt.execute("delete from constutable where CID="+temp+";");
				stmt.execute("delete from constutemp where CID="+temp+";");
				stmt.execute("delete from coutimeinfo where CID="+temp+";");
				stmt.execute("delete from courseinfo where CID="+temp+";");
			}
			ResultSet res2=stmt.executeQuery("select SID,CID from constutemp where ord=1;");
			ArrayList<Integer> tem1=new ArrayList<Integer>();
			ArrayList<Integer> tem2=new ArrayList<Integer>();
			while(res2.next()){
				tem1.add(res2.getInt(1));
				tem2.add(res2.getInt(2));
			}
			Iterator<Integer> it1=tem1.iterator();
			Iterator<Integer> it2=tem2.iterator();
			while(it1.hasNext()){
				long temp=it1.next();
				ResultSet res3=stmt.executeQuery("select count(*) from constutable where SID="
						+temp+";");
				long ttt=it2.next();
				res3.next();
				if(res3.getInt(1)<4){
					CouStu constu=new CouStu();
					constu.setCID(ttt);
					constu.setSID(temp);
					op.InsertCouStu(constu);
				}
			}
			ResultSet res4=stmt.executeQuery("select SID,CID from constutemp where ord=2;");
			ArrayList<Integer> tem3=new ArrayList<Integer>();
			ArrayList<Integer> tem4=new ArrayList<Integer>();
			while(res4.next()){
				tem3.add(res4.getInt(1));
				tem4.add(res4.getInt(2));
			}
			Iterator<Integer> it3=tem3.iterator();
			Iterator<Integer> it4=tem4.iterator();
			while(it3.hasNext()){
				long temp=it3.next();
				long temp1=it4.next();
				ResultSet res5=stmt.executeQuery("select count(*) from constutable where SID="
						+temp+";");
				res5.next();
				if(res5.getInt(1)<4){
					CouStu constu=new CouStu();
					constu.setCID(temp1);
					constu.setSID(temp);
					op.InsertCouStu(constu);
				}
			}
			stmt.execute("delete from constutemp;");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
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
	public static void main(String[] args){
		CouStuOP op=new CouStuOP();
		op.ConfirmConStu();
	}
}
