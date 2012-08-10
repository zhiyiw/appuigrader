package mybeans.mydb.administration;

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

import mybeans.mydb.assignments.model.Assignment;

@ManagedBean(name="student")
@SessionScoped

public class StudentInfo {
	//resource injection
	//@Resource(name="jdbc/projectdb")
	private DataSource ds;
	
	private Connection con;
	
	//if resource injection is not support, you still can get it manually.
	public StudentInfo(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}
	
	public List<Students> getAllStudentList(String term, int year) throws SQLException{
		 
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select username, term, year from users where year = ? and term = ?"); 
		
		ps.setInt(1, year);
		ps.setString(2, term);
 
		ResultSet result =  ps.executeQuery();
		
		List<Students> list = new ArrayList<Students>();
		
		while(result.next()){
			Students stu = new Students();
			
			stu.setUsername(result.getString("username"));
			stu.setTerm(result.getString("term"));
			stu.setYear(result.getInt("year"));

 
			//store all data into a List
			list.add(stu);
		}
 
		return list;
	}
	
	
}
