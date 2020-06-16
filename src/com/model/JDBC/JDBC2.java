package com.model.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC2 {
	public Connection conn=null;
	public void getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			System.out.println("启动成功！");
			String url="jdbc:mysql://121.199.79.214:3306/softengpro1?"
					+ "useUnicode=true&characterEncoding=UTF8";

			
			String user="root";
			String passWord="13786086097Xr@";

			conn=(Connection)DriverManager.getConnection(url,user,passWord); 
			//System.out.println("连接成功！");
		}catch(Exception e){
			conn=null;
			System.out.println("in JDBC conn==null");
			e.printStackTrace();
		}
	}
	public void CancleConnection(){
		try {
			conn.close();
			System.out.println("SQL断开连接成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn=null;
	}
}

