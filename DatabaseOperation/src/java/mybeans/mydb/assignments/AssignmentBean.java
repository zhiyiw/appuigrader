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
	
	private Assignment selectedAssignment;
	
	public List<Assignment> list;
	
	public Assignment getSelectedAssignment() {
		return selectedAssignment;
	}

	public void setSelectedAssignment(Assignment selectedAssignment) {
		this.selectedAssignment = selectedAssignment;
	}

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
			   "select a_id, a_name, a_directory, a_uploaded_date, description, deadline, screenshot_dict from assignments where a_year=? and a_term=? order by a_id ASC"); 
 
		//get student data from database
		ps.setInt(1, year);
		ps.setString(2, term);
		
		ResultSet result =  ps.executeQuery();
		
		list = new ArrayList<Assignment>();
		
		while(result.next()){
			Assignment assign = new Assignment();
			assign.setDescription(result.getString("description"));
			assign.setAssignmentID(result.getInt("a_id"));
			assign.setAssignmentName(result.getString("a_name"));
			assign.setAssignmentDirectory(result.getString("a_directory"));
			assign.setUploadedDate(result.getString("a_uploaded_date"));
			assign.setDeadline(result.getString("deadline"));
			
			String tempSSD=result.getString("screenshot_dict");
			if(tempSSD==null)
				assign.setSsd("noScreenshot");
			else if("".equals(tempSSD))
				assign.setSsd("noScreenshot");
			else
				tempSSD="/"+tempSSD;
			assign.setSsd(tempSSD);
			//store all data into a List
			list.add(assign);
		}
		con.close();
		return list;
	}
	
	public void addAssignment(String directory, int year ,String term, String des, String deadline, String ssDirectory) throws SQLException{
		List<Assignment> listTemp = getAssignmentList(year,term);
		
		int temp = listTemp.size()+1;
		String name;
		
		if(temp<10)
			name = "Assignment0"+temp;
		else
			name = "Assignment"+temp;
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
		
		PreparedStatement ps 
		= con.prepareStatement(
		   "select count(a_id) from assignments");
		
		ResultSet result =  ps.executeQuery();
		
		result.next();
		int current = result.getInt(1)+1;
		

		ps = con.prepareStatement("insert into assignments values (?,?,?,?,?,SYSDATE(),?,?,?)");
		
		ps.setInt(1, current);
		ps.setInt(2, year);
		ps.setString(3, term);
		ps.setString(4,name);
		ps.setString(5, directory);
		ps.setString(6, des);
		ps.setString(7, deadline);
		ps.setString(8, ssDirectory);
		
		int updated = ps.executeUpdate();
		
		if(updated==0)
			throw new SQLException("Insert Error!");
		
		con.close();
		
	}

	public void updateAssignment(String directory,
			int id, String des, String deadline, String ssDirectory) throws SQLException {
		// TODO Auto-generated method stub
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		
		PreparedStatement ps = con.prepareStatement("update assignments set a_directory=?, a_uploaded_date=SYSDATE(), description=?, deadline=?, screenshot_dict=? where a_id=?");
		
		ps.setString(1, directory);
		ps.setString(2, des);
		ps.setString(3, deadline);
		ps.setString(4, ssDirectory);
		ps.setInt(5, id);
		
		
		int updated = ps.executeUpdate();
		
		if(updated==0)
			throw new SQLException("Update Error!");
		
		con.close();
		
	}

	public List<Assignment> getList() {
		return list;
	}

	public void setList(List<Assignment> list) {
		this.list = list;
	}

}
