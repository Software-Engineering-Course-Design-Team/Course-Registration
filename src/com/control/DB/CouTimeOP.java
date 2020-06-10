
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.JDBC.JDBC;
import com.model.javabean.CouTime;

public class CouTimeOP {
	JDBC dbcon=new JDBC();
	public long InsertCouTime(CouTime coutime){
		if(coutime.getAddress()==null||coutime.getBeginC()==0||coutime.getCID()==0
	          ||coutime.getEndC()==0||coutime.getWeekDay()==0)return 50001;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res1=stmt.executeQuery("select * from courseinfo where"
					+" CID="+coutime.getCID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 50002;
			}
			ResultSet res=stmt.executeQuery("select * from coutimeinfo where"
					+" WeekDay="+coutime.getWeekDay()
					+" and CID="+coutime.getCID()
					+" and BeginC="+coutime.getBeginC()
					+" and EndC="+coutime.getEndC()+";");
			if(res.next()){
				dbcon.CancleConnection();
				return 50003;
			}
			ResultSet res2=stmt.executeQuery("select * from coutimeinfo where"
					+" WeekDay="+coutime.getWeekDay()
					+" and CID="+coutime.getCID()
					+" and ((BeginC>="+coutime.getBeginC()
					+" and BeginC<="+coutime.getEndC()+")"
					+" or (EndC>="+coutime.getBeginC()
					+" and EndC<="+coutime.getEndC()+"));");
			if(res2.next()){
				dbcon.CancleConnection();
				return 50004;
			}
			ResultSet res3=stmt.executeQuery("select * from coutimeinfo where"
					+" WeekDay="+coutime.getWeekDay()
					+" and Address='"+coutime.getCID()
					+"' and ((BeginC>="+coutime.getBeginC()
					+" and BeginC<="+coutime.getEndC()+")"
					+" or (EndC>="+coutime.getBeginC()
					+" and EndC<="+coutime.getEndC()+"));");
			if(res3.next()){
				dbcon.CancleConnection();
				return 50012;
			}
			stmt.execute("Insert into coutimeinfo values ("+coutime.getWeekDay()+","+
			coutime.getCID()+","+coutime.getBeginC()+","+coutime.getEndC()+",'"+coutime.getAddress()+"');");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 50005;
	}//�����
	public long DeleteCouTime(CouTime coutime){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from coutimeinfo where"
					+" WeekDay="+coutime.getWeekDay()
					+" and CID="+coutime.getCID()
					+" and BeginC="+coutime.getBeginC()
					+" and EndC="+coutime.getEndC()
					+" and Address='"+coutime.getAddress()+"';");
			if(!res.next()){
				dbcon.CancleConnection();
				return 50006;
			}
			stmt.execute("delete from coutimeinfo where WeekDay="+coutime.getWeekDay()
					+" and CID="+coutime.getCID()
					+" and BeginC="+coutime.getBeginC()
					+" and EndC="+coutime.getEndC()
					+" and Address='"+coutime.getAddress()+"';");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 50007;
	}//ɾ����Ӧʱ��Ŀ�
	public long DeleteAddress(CouTime coutime){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where Address='"+coutime.getAddress()+"';");
			if(!res.next()){
				dbcon.CancleConnection();
				return 50008;
			}
			stmt.execute("delete from coutimeinfo where  Address='"+coutime.getAddress()+"';");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 50009;
	}//ɾ����
	public long DeleteCou(CouTime coutime){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where CID="+coutime.getCID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 50010;
			}
			stmt.execute("delete from coutimeinfo where  CID="+coutime.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 50011;
	}//ɾ����
	public ArrayList<CouTime> FindCouTime(CouTime coutime){
		ArrayList<CouTime> result=new ArrayList<CouTime>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from coutimeinfo where CID="+coutime.getCID()+";");
			while(res.next()){
				CouTime temp=new CouTime();
				temp.setWeekDay(res.getInt(1));
				temp.setCID(res.getLong(2));
				temp.setBeginC(res.getInt(3));
				temp.setEndC(res.getInt(4));
				temp.setAddress(res.getString(5));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return result;
	}//���ݿβ�ʱ��
	public ArrayList<CouTime> FindAddress(CouTime coutime){
		ArrayList<CouTime> result=new ArrayList<CouTime>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from coutimeinfo where Address='"+coutime.getAddress()+"';");
			while(res.next()){
				CouTime temp=new CouTime();
				temp.setWeekDay(res.getInt(1));
				temp.setCID(res.getLong(2));
				temp.setBeginC(res.getInt(3));
				temp.setEndC(res.getInt(4));
				temp.setAddress(res.getString(5));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return result;
	}//���ݿβ�ʱ��
}
