package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.model.JDBC.JDBC;
import com.model.javabean.CouStuTemp;

public class ConStuTempOP {
	JDBC dbcon=new JDBC();
	public long InsertConStuTemp(CouStuTemp constutemp){
		if(constutemp.getCID()==0||constutemp.getSID()==0)return 90002;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutemp where SID="+constutemp.getSID()+
					" and CID="+constutemp.getCID()+";");
			if(res.next()){
				dbcon.CancleConnection();
				return 90001;
			}
			ResultSet res1=stmt.executeQuery("select * from courseinfo where CID="+constutemp.getCID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 90003;
			}
			ResultSet res10=stmt.executeQuery("select * from stuinfo where SID="+constutemp.getSID()+";");
			if(!res10.next()){
				dbcon.CancleConnection();
				return 90018;
			}
			if(constutemp.getOrder()==0){
				ResultSet res2=stmt.executeQuery("select count(*) from constutemp where CID="+constutemp.getCID()+
						" and ord=0;");
				res2.next();
				if(res2.getInt(1)>=10){
					dbcon.CancleConnection();
					return 90004;
				}
				ResultSet res3=stmt.executeQuery("select count(*) from constutemp where ord=0 and SID="+constutemp.getSID()+";");
				res3.next();
				if(res3.getInt(1)>=4){
					dbcon.CancleConnection();
					return 90006;
				}
			}else{
				ResultSet res3=stmt.executeQuery("select count(*) from constutemp where ord<>0 and SID="+constutemp.getSID()+";");
				res3.next();
				int tt=res3.getInt(1);
				if(tt==2){
					dbcon.CancleConnection();
					return 90007;
				}
				else if(tt==1&&constutemp.getOrder()==1){
					dbcon.CancleConnection();
					return 90017;
				}
			}
			ResultSet res4=stmt.executeQuery("select DID from stuinfo where SID="+constutemp.getSID()+";");
			res4.next();
			long t1=res4.getLong(1);
			ResultSet res5=stmt.executeQuery("select DID from courseinfo where CID="+constutemp.getCID()+";");
			res5.next();
			long t2=res5.getLong(1);
			if(t1!=t2){
				dbcon.CancleConnection();
				return 90016;
			}
			stmt.execute("Insert into constutemp values("+constutemp.getOrder()+","+constutemp.getSID()+","
					+constutemp.getCID()+");");
			stmt.execute("Update courseinfo set person=person+1 where CID="+constutemp.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		return 90005;
	}//插入新的选课记录
	public ArrayList<CouStuTemp> FindStuTemp(CouStuTemp constutemp){
		ArrayList<CouStuTemp> result = new ArrayList<CouStuTemp>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutemp where CID="+
					constutemp.getCID()+";");
			while(res.next()){
				CouStuTemp temp=new CouStuTemp();
				temp.setOrder(res.getInt(1));
				temp.setSID(res.getLong(2));
				temp.setCID(res.getLong(3));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return result;
	}//发现选课记录（根据课程ID找学生）
	public ArrayList<CouStuTemp> FindCouTemp(CouStuTemp constutemp){
		ArrayList<CouStuTemp> result = new ArrayList<CouStuTemp>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutemp where SID="+constutemp.getSID()+";");
			while(res.next()){
				CouStuTemp temp=new CouStuTemp();
				temp.setOrder(res.getInt(1));
				temp.setSID(res.getLong(2));
				temp.setCID(res.getLong(3));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return result;
	}//发现选课记录（根据课程学生找课）
	public long DeleteConTemp(CouStuTemp constutemp){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res1=stmt.executeQuery("select * from courseinfo where CID="+constutemp.getCID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 90008;
			}
			stmt.execute("Delete from constutemp where CID="
					+constutemp.getCID()+";");
			stmt.execute("Update courseinfo set person=0 where CID="+constutemp.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		return 90009;
	}//删课所有学生
	public long DeleteStuTemp(CouStuTemp constutemp){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stuinfo where SID"
					+ "="+constutemp.getSID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 90010;
			}
			ResultSet res1=stmt.executeQuery("select CID from constutable where SID"
					+ "="+constutemp.getSID()+";");
			ArrayList<Integer> list = new ArrayList<Integer>();
			while(res1.next()){
				list.add(res1.getInt(1));
			}
			Iterator<Integer> it=list.iterator();
			while(it.hasNext()){
				stmt.execute("Update courseinfo set person=person-1 where CID="+it.next()+";");
			}
			stmt.execute("Delete from constutable where SID="+constutemp.getSID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		return 90011;
	}//删学生所有课
	public CouStuTemp FindCouStuTemp(CouStuTemp constu){
		CouStuTemp temp=new CouStuTemp();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutemp where SID="+
					constu.getSID()+" and CID="+constu.getCID()+";");
			if(res.next()){
				temp.setSID(res.getLong(2));
				temp.setCID(res.getLong(3));
			}else temp.setSID(90014);
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return temp;
	}
	public long DeleteConStuTemp(CouStuTemp constutemp){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from constutemp where SID="+constutemp.getSID()+
					" and CID="+constutemp.getCID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 90012;
			}
			stmt.execute("Delete from constutemp where CID="+constutemp.getCID()
			+" and SID="+constutemp.getSID()+";");
			stmt.execute("Update courseinfo set person=person-1 where CID="+constutemp.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		return 90013;
	}//删某学生某课(某条选课记录)
}

