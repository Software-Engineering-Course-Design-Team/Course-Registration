package com.model.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public Connection conn=null;
	public void getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");  //�������ݿ�����
			System.out.println("���ݿ��������سɹ�");
			String url="jdbc:mysql://121.199.79.214:3306/softengpro?"
					+ "useUnicode=true&characterEncoding=UTF8";
			//�������useSSL=false�ͻ��о��棬����jdbc��mysql�汾��ͬ����һ�����Ӱ�ȫ����
			
			String user="root";
			String passWord="13786086097Xr@";

			conn=(Connection)DriverManager.getConnection(url,user,passWord); //��������
			System.out.println("�ѳɹ��������ݿ�MySQL�������ӣ���");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void CancleConnection(){
		try {
			conn.close();
			System.out.println("�ѳɹ��������ݿ�MySQL�Ͽ����ӣ���");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		conn=null;
	}
}

