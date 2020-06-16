
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.JDBC.JDBC;
import com.model.javabean.Count;

public class CountOP {
	JDBC dbcon=new JDBC();
	//changePassword
	public long ChangePassword(Count cou){
		try {
			dbcon.getConnection();
			/*if(dbcon.conn==null)
			{
				System.out.println("in Count conn==null");
			}*/
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from countinfo where ID="+cou.getID()+
					";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 80001;
			}
			stmt.execute("Update countinfo set Password='"+cou.getPassword()+"' where ID="
			+cou.getID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			if(dbcon.conn==null)
			{
				System.out.println("in Count conn==null");
			}
			e.printStackTrace();
		}  
		return 80006;
	}
	//delete count
	public long DeleteCount(Count cou){
		try {
			if(cou.getIdentity()!=0)return 80010;
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from countinfo where ID="+cou.getID()+
					" and Password="+cou.getPassword()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 80002;
			}
			stmt.execute("Delete from countinfo where ID="+cou.getID()+
					" and password='"+cou.getPassword()+"';");
			dbcon.CancleConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}  
		return 80007;
	}

	public long InsertCount(Count cou){
		try {
			if(cou.getID()==0||cou.getPassword()==null)return 80004;
			if(cou.getIdentity()!=0)return 80011;
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from countinfo where ID="+cou.getID()+
					";");
			if(res.next()){
				dbcon.CancleConnection();
				return 80005;
			}
			stmt.execute("Insert into countinfo values ("+cou.getIdentity()+",'"+
			cou.getPassword()+"',"+cou.getID()+");");
			dbcon.CancleConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}  
		return 80008;
	}
	//find count
	public Count FindCount(Count cou){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from countinfo where ID="+cou.getID()+";");
			if(res.next()){
				cou.setIdentity(res.getInt(1));
				cou.setPassword(res.getString(2));
				cou.setID(res.getLong(3));
			}else{
				cou.setID(80003);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}  
		return cou;
	}
}
