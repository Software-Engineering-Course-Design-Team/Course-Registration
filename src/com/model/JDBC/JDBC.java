package com.model.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public Connection conn=null;
	public void getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");  //加载数据库驱动
			System.out.println("数据库驱动加载成功");
			String url="jdbc:mysql://121.199.79.214:3306/softengpro?"
					+ "useUnicode=true&characterEncoding=UTF8";
			//如果不加useSSL=false就会有警告，由于jdbc和mysql版本不同，有一个连接安全问题
			
			String user="root";
			String passWord="13786086097Xr@";

			conn=(Connection)DriverManager.getConnection(url,user,passWord); //创建连接
			System.out.println("已成功的与数据库MySQL建立连接！！");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void CancleConnection(){
		try {
			conn.close();
			System.out.println("已成功的与数据库MySQL断开连接！！");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		conn=null;
	}
}

