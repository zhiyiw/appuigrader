package mybeans.mydb.students;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
import mybeans.mydb.students.model.Student;

@ManagedBean(name="s12345")
@SessionScoped

public class StudentBean implements Serializable{
	//resource injection
	//@Resource(name="jdbc/projectdb")
	
	public Student selectStudent;
	private DataSource ds;
	
	List<Student> list;
 
	//if resource injection is not support, you still can get it manually.
	public StudentBean(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}
 
	//connect to DB and get customer list
	public void getStudentList() throws SQLException{
 
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		Connection con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select username from users"); 
 
		//get student data from database
		ResultSet result =  ps.executeQuery();
 
		list = new ArrayList<Student>();
 
		while(result.next()){
			Student stud = new Student();
 
			stud.setLname(result.getString("username"));
 
			//store all data into a List
			list.add(stud);
		}
	}

	public Student getSelectStudent() {
		return selectStudent;
	}

	public void setSelectStudent(Student selectStudent) {
		this.selectStudent = selectStudent;
	}

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}
	
}