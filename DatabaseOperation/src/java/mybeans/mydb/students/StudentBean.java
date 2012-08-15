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

@ManagedBean(name="student")
@SessionScoped

public class StudentBean implements Serializable{
	//resource injection
	//@Resource(name="jdbc/projectdb")
	private DataSource ds;
 
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
	public List<Student> getStudentList() throws SQLException{
 
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		Connection con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select s_id, s_lname from students"); 
 
		//get student data from database
		ResultSet result =  ps.executeQuery();
 
		List<Student> list = new ArrayList<Student>();
 
		while(result.next()){
			Student stud = new Student();
 
			stud.setStudentID(result.getInt("s_id"));
			stud.setLname(result.getString("s_lname"));
 
			//store all data into a List
			list.add(stud);
		}
 
		return list;
	}
	
}