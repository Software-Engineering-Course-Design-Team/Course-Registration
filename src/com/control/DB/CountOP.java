
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.JDBC.JDBC;
import com.model.javabean.Count;

public class CountOP {
	JDBC dbcon=new JDBC();
	//changePassword 账户修改密码
	public long ChangePassword(Count cou){
		try {
			dbcon.getConnection();
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return 80006;
	}
	//delete count 账户删除
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return 80007;
	}
	//insert count 新建账户
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return 80008;
	}
	//find count 查找账户
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
				cou.setID(80003);//错误，没有获取到数据
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return cou;
	}
}
