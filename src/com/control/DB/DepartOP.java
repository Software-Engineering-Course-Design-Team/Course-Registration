
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.JDBC.JDBC;
import com.model.javabean.DepInfo;
//author:xr
//test time:6-5 16:48
public class DepartOP {
	JDBC dbcon=new JDBC();
	public long InsertDepart(DepInfo dep){
		long id=10005;
		try {
			if(dep.getName()!=null){
				dbcon.getConnection();
				String sql="Insert into departmentInfo values('"+dep.getName()
					+"',NULL);";
				java.sql.PreparedStatement ps=dbcon.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.executeUpdate();
				ResultSet rs=ps.getGeneratedKeys();
				if(rs.next()){
					id=rs.getLong(1);
				}else {
					dbcon.CancleConnection();
					return 10006;
				}
				dbcon.CancleConnection();
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return id;
	}
	public long DeleteDepart(DepInfo dep){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res=stmt1.executeQuery("select * from departmentInfo where DID="+dep.getDID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 10003;
			}
			ResultSet res2=stmt1.executeQuery("select * from courseinfo where DID="+dep.getDID()+";");
			if(!res2.next()){
				dbcon.CancleConnection();
				return 10008;
			}
			ResultSet res3=stmt1.executeQuery("select * from stuinfo where DID="+dep.getDID()+";");
			if(!res3.next()){
				dbcon.CancleConnection();
				return 10009;
			}
			ResultSet res4=stmt1.executeQuery("select * from profinfo where DID="+dep.getDID()+";");
			if(!res4.next()){
				dbcon.CancleConnection();
				return 10010;
			}
			stmt.execute("Delete from departmentInfo where DID="+dep.getDID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 10004;
	}
	public long UpdateDepart(DepInfo dep){
		try {
			if(dep.getName()!=null&&dep.getDID()!=0){
				dbcon.getConnection();
				Statement stmt1=dbcon.conn.createStatement();
				ResultSet res=stmt1.executeQuery("select * from departmentInfo where DID="+
						dep.getDID()+" and Name='"+dep.getName()+"';");
				if(!res.next()){
					dbcon.CancleConnection();
					return 10002;
				}
				Statement stmt=dbcon.conn.createStatement();
				stmt.execute("Update departmentInfo set Name='"+dep.getName()
				+"' where DID="+dep.getDID()+";");
				dbcon.CancleConnection();
				return 10000;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 10001;
	}
	public DepInfo FindDepart(DepInfo dep){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from departmentInfo where DID="+dep.getDID()+";");
			if(res.next()){
				dep.setName(res.getString(1));
				dep.setDID(res.getLong(2));
			}else{
				dep.setDID(10007);//错误，没有获取到数据
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return dep;
	}
	public ArrayList<DepInfo> FindAllDep(){
		ArrayList<DepInfo> result=new ArrayList<DepInfo>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from departmentInfo;");
			while(res.next()){
				DepInfo temp=new DepInfo();
				temp.setName(res.getString(1));
				temp.setDID(res.getLong(2));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		return result;
	}
}

