
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
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res1=stmt.executeQuery("select * from courseinfo where"
					+" CID="+coutime.getCID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 50001;
			}
			ResultSet res=stmt.executeQuery("select * from coutimeinfo where"
					+" WeekDay="+coutime.getWeekDay()
					+" and CID="+coutime.getCID()
					+" and BeginC="+coutime.getBeginC()
					+" and EndC="+coutime.getEndC()+";");
			if(res.next()){
				dbcon.CancleConnection();
				return 50002;
			}
			stmt.execute("Insert into coutimeinfo values ("+coutime.getWeekDay()+","+
			coutime.getCID()+","+coutime.getBeginC()+","+coutime.getEndC()+");");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 50003;
	}//插入课
	public long DeleteCouTime(CouTime coutime){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from coutimeinfo where"
					+" WeekDay="+coutime.getWeekDay()
					+" and CID="+coutime.getCID()
					+" and BeginC="+coutime.getBeginC()
					+" and EndC="+coutime.getEndC()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 50004;
			}
			stmt.execute("delete from coutimeinfo where WeekDay="+coutime.getWeekDay()
					+" and CID="+coutime.getCID()
					+" and BeginC="+coutime.getBeginC()
					+" and EndC="+coutime.getEndC()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 50005;
	}//删除对应时间的课
	public long DeleteCou(CouTime coutime){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from courseinfo where CID="+coutime.getCID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 50006;
			}
			stmt.execute("delete from coutimeinfo where  CID="+coutime.getCID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 50007;
	}//删除课
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
				temp.setBeginC(res.getInt(4));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}//根据课查时间
}
