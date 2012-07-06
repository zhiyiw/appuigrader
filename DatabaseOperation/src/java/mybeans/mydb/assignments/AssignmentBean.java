package mybeans.mydb.assignments;

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

@ManagedBean(name="assignment")
@SessionScoped

public class AssignmentBean {
	//resource injection
	//@Resource(name="jdbc/projectdb")
	private DataSource ds;
	
	private Connection con;
	
	//if resource injection is not support, you still can get it manually.
	public AssignmentBean(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}
	
	public List<Assignment> getAssignmentList(int year, String term) throws SQLException{
		 
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select a_name, a_directory, a_uploaded_date from assignments where year=? and term=?"); 
 
		//get student data from database
		ps.setInt(1, year);
		ps.setString(2, term);
		
		ResultSet result =  ps.executeQuery();
		
		List<Assignment> list = new ArrayList<Assignment>();
		
		while(result.next()){
			Assignment assign = new Assignment();
			
			assign.setAssignmentName(result.getString("a_name"));
			assign.setAssignmentDirectory(result.getString("a_directory"));
			assign.setUploadedDate(result.getString("a_uploaded_date"));
 
			//store all data into a List
			list.add(assign);
		}
 
		return list;
	}
	
	public void addAssignment(String directory, int year ,String term) throws SQLException{
		List<Assignment> list = getAssignmentList(year,term);
		
		int temp = list.size();
		String name;
		
		if(temp<10)
			name = "Assignment0"+temp;
		else
			name = "Assignment"+temp;
		
		PreparedStatement ps 
		= con.prepareStatement(
		   "select count(a_id) from assignments");
		
		ResultSet result =  ps.executeQuery();
		
		result.next();
		int current = result.getInt(1)+1;
		
		ps = con.prepareStatement("insert into assignments values (?,?,?,?,?,SYSDATE())");
		
		ps.setInt(1, current);
		ps.setInt(2, year);
		ps.setString(3, term);
		ps.setString(4,name);
		ps.setString(5, directory);
		
		int updated = ps.executeUpdate();
		
		if(updated==0)
			throw new SQLException("Insert Error!");
		
	}

}
