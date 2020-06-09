
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.JDBC.JDBC;
import com.model.javabean.Stufee;

public class StuFeeOP {
	JDBC dbcon=new JDBC();
	public long InsertStuFee(Stufee stufee){
		if(stufee.getFee()==0||stufee.getTerm()==0||stufee.getSID()==0||
				stufee.getStatus()!="未缴清")return 30002;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stuinfo where SID="+stufee.getSID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 30001;
			}
			stmt.execute("Insert into stufeeinfo values('未缴清',"
					+stufee.getFee()+","+stufee.getTerm()+","+stufee.getSID()+");");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 30003;
	}
	public long DeleteStuFee(Stufee stufee){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stuinfo where SID="+stufee.getSID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 30004;
			}
			ResultSet res1=stmt.executeQuery("select * from stufeeinfo where SID="+stufee.getSID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 30005;
			}
			stmt.execute("Delete from stufeeinfo where SID="+stufee.getSID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 30006;
	}
	public Stufee FindStuFee(Stufee stufee){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stufeeinfo where SID="+stufee.getSID()+""
					+ " and Term="+stufee.getTerm()+";");
			if(res.next()){
				stufee.setStatus(res.getString(1));
				stufee.setFee(res.getInt(2));
				stufee.setTerm(res.getInt(3));
				stufee.setSID(res.getLong(4));
			}else{
				stufee.setSID(30007);//错误，没有获取到数据
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return stufee;
	}
	public ArrayList<Stufee> FindStuAllFee(Stufee stufee){
		ArrayList<Stufee> result=new ArrayList<Stufee>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stufeeinfo where SID="+stufee.getSID()+""
					+" order by Term;");
			while(res.next()){
				Stufee temp=new Stufee();
				temp.setStatus(res.getString(1));
				temp.setFee(res.getInt(2));
				temp.setTerm(res.getInt(3));
				temp.setSID(res.getLong(4));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return result;
	}
	public ArrayList<Stufee> FindStuNoFee(){
		ArrayList<Stufee> result=new ArrayList<Stufee>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stufeeinfo where status='未缴清';");
			while(res.next()){
				Stufee temp=new Stufee();
				temp.setStatus(res.getString(1));
				temp.setFee(res.getInt(2));
				temp.setTerm(res.getInt(3));
				temp.setSID(res.getLong(4));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return result;
	}
	public long UpdateStuFee(Stufee stufee){
		if(stufee.getFee()==0||stufee.getTerm()==0||stufee.getSID()==0)return 30008;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stufeeinfo where SID="+stufee.getSID()+""
					+ " and Term="+stufee.getTerm()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 30009;
			}
			stmt.execute("Update stufeeinfo set status='"+stufee.getStatus()
			+"',Fee="+stufee.getFee()
			+",Term="+stufee.getTerm()
			+" where SID="+stufee.getSID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 30010;
	}
	public int[] returnAllfeeinfo(){
		int[] result=new int[3];
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select sum(Fee) from stufeeinfo ;");
			if(res.next())result[0]=res.getInt(1);
			ResultSet res1=stmt.executeQuery("select sum(Fee) from stufeeinfo where status='未缴清';");
			if(res1.next())result[1]=res1.getInt(1);
			ResultSet res2=stmt.executeQuery("select sum(Fee) from stufeeinfo where status='已缴清';");
			if(res2.next())result[2]=res2.getInt(1);
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	public int[] returnStufeeinfo(Stufee stufee){
		int[] result=new int[3];
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select sum(Fee) from stufeeinfo where SID="+stufee.getSID()+";");
			if(res.next())result[0]=res.getInt(1);
			ResultSet res1=stmt.executeQuery("select sum(Fee) from stufeeinfo where status='未缴清' and SID="+stufee.getSID()+";");
			if(res1.next())result[1]=res1.getInt(1);
			ResultSet res2=stmt.executeQuery("select sum(Fee) from stufeeinfo where status='已缴清' and SID="+stufee.getSID()+";");
			if(res2.next())result[2]=res2.getInt(1);
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
}

