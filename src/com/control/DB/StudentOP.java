
package com.control.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.JDBC.JDBC;
import com.model.javabean.Student;

public class StudentOP {
	JDBC dbcon=new JDBC();
	public long InsertStudent(Student stu){
		long id=40002;
		try {
			if(stu.getGradDate()==null||stu.getBirthday()==null||stu.getStatus()==null
					||stu.getSex()==null||stu.getIdcard()==0||stu.getName()==null
					||stu.getDID()==0)return 40001;
			dbcon.getConnection();
			Statement stmt1=dbcon.conn.createStatement();
			ResultSet res=stmt1.executeQuery("select * from departmentInfo where DID="+stu.getDID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 40003;
			}
			String sql="Insert into stuinfo values('"+stu.getGradDate()+"','"
					+stu.getBirthday()+"','"+stu.getStatus()+"','"+stu.getSex()
					+"',"+stu.getIdcard()+",'"+stu.getName()+"',"+"NULL"
					+","+stu.getDID()+");";
			java.sql.PreparedStatement ps=dbcon.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()){
				id=rs.getLong(1);
			}
			stmt1.execute("Insert into countinfo values(2,'000000',"+id+");");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return id;
	}
	public long UpdateStudent(Student stu){
		if(stu.getGradDate()==null||stu.getBirthday()==null||stu.getStatus()==null
				||stu.getSex()==null||stu.getIdcard()==0||stu.getName()==null
				||stu.getDID()==0)return 40012;
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stuinfo where SID"
					+ "="+stu.getSID()+";");
			if(!res.next()){
				dbcon.CancleConnection();
				return 40011;
			}
			ResultSet res1=stmt.executeQuery("select * from departmentInfo where DID="+stu.getDID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 40013;
			}
			stmt.execute("Update stuinfo set GradDate='"+stu.getGradDate()
				+"',Birthday='"+stu.getBirthday()
				+"',Status='"+stu.getStatus()
				+"',Sex='"+stu.getSex()
				+"',Idcard="+stu.getIdcard()
				+",Name='"+stu.getName()
				+"',DID="+stu.getDID()
				+" where SID="+stu.getSID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 40014;
	}
	public ArrayList<Student> FindDepTermStu(Student stu){
		ArrayList<Student> result=new ArrayList<Student>();
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stuinfo where DID"
					+ "="+stu.getDID()+" and GradDate='"+stu.getGradDate()+"';");
			while(res.next()){
				Student temp=new Student();
				temp.setGradDate(res.getString(1));
				temp.setBirthday(res.getString(2));
				temp.setStatus(res.getString(3));
				temp.setSex(res.getString(4));
				temp.setIdcard(res.getLong(5));
				temp.setName(res.getString(6));
				temp.setSID(res.getLong(7));
				temp.setDID(res.getLong(8));
				result.add(temp);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	public Student FindStudent(Student stu){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from stuinfo where SID"
					+ "="+stu.getSID()+";");
			if(res.next()){
				stu.setGradDate(res.getString(1));
				stu.setBirthday(res.getString(2));
				stu.setStatus(res.getString(3));
				stu.setSex(res.getString(4));
				stu.setIdcard(res.getLong(5));
				stu.setName(res.getString(6));
				stu.setSID(res.getLong(7));
				stu.setDID(res.getLong(8));
			}else{
				stu.setSID(40009);
			}
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return stu;
	}
	public long DeleteStudent(Student stu){
		try {
			dbcon.getConnection();
			Statement stmt=dbcon.conn.createStatement();
			ResultSet res1=stmt.executeQuery("select * from stuinfo where SID"
					+ "="+stu.getSID()+";");
			if(!res1.next()){
				dbcon.CancleConnection();
				return 40004;
			}
			ResultSet res2=stmt.executeQuery("select * from stufeeinfo where SID"
					+ "="+stu.getSID()+" and status='未缴清';");
			if(res2.next()){
				dbcon.CancleConnection();
				return 40005;
			}
			ResultSet res3=stmt.executeQuery("select * from constutable where SID"
					+ "="+stu.getSID()+";");
			if(res3.next()){
				dbcon.CancleConnection();
				return 40006;
			}
			ResultSet res4=stmt.executeQuery("select * from constutemp where SID"
					+ "="+stu.getSID()+";");
			if(res4.next()){
				dbcon.CancleConnection();
				return 40007;
			}
			stmt.execute("Delete from stuinfo where SID="+stu.getSID()+";");
			stmt.execute("Delete from countinfo where ID="+stu.getSID()+";");
			dbcon.CancleConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 40008;
	}
}
