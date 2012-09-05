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
	
	public List<Assignment> getAssignmentList()throws SQLException{
		 
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select * from assignments order by a_id ASC"); 
 
		//get student data from database
		ResultSet result =  ps.executeQuery();
		
		list = new ArrayList<Assignment>();
		int count=1;
		while(result.next()){
			Assignment assign = new Assignment();
			assign.setDescription(result.getString("description"));
			assign.setAssignmentID(result.getInt("a_id"));
			assign.setAssignmentDirectory(result.getString("document_dict"));			
		    //get screenshot directory
			String tempSSD=result.getString("screenshot_dict");
			if(tempSSD==null)
				assign.setScreenDirectory("noScreenshot");
			else if("".equals(tempSSD))
				assign.setScreenDirectory("noScreenshot");
			else
				tempSSD="/"+tempSSD;
			assign.setScreenDirectory(tempSSD);
		
			assign.setPoint(result.getInt("point"));
			assign.setRating(result.getInt("rating"));
			//get assignment name
			String name;	
			if(count<10)
				name = "Assignment0"+count;
			else
				name = "Assignment"+count;
			assign.setAssignmentName(name);
			count++;
			//store all data into a List
			list.add(assign);
		}
		con.close();
		selectedAssignment = list.get(0);
		return list;
	}
	
	public Assignment getAssignmentByID(int a_id) throws SQLException{
		Assignment assign = new Assignment();
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
		
		PreparedStatement ps 
		= con.prepareStatement(
		   "select * from assignments where a_id=?;");
		
		ps.setInt(1, a_id);
		ResultSet result =  ps.executeQuery();
		
		result.next();
		assign.setAssignmentDirectory(result.getString("document_dict"));
		assign.setDescription(result.getString("description"));
		assign.setScreenDirectory(result.getString("screenshot_dict"));
		assign.setPoint(result.getInt("point"));
		assign.setRating(result.getInt("rating"));
		
		con.close();
		return assign;
	}
	
	public void addAssignment(String docu_dict, String description, String ss_dict, int point, int rating) throws SQLException{
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
		
		PreparedStatement ps 
		= con.prepareStatement(
		   "select max(a_id) from assignments;");
		
		ResultSet result =  ps.executeQuery();
		
		result.next();
		int current = result.getInt(1)+1;
		

		ps = con.prepareStatement("insert into assignments values (?,?,SYSDATE(),?,?,?,?)");
		
		ps.setInt(1, current);
		ps.setString(2, docu_dict);
		ps.setString(3,description);
		ps.setString(4, ss_dict);
		ps.setInt(5, point);
		ps.setInt(6,rating);
		
		int updated = ps.executeUpdate();
		
		if(updated==0)
			throw new SQLException("Insert Error!");
		
		con.close();
		
	}

	public void updateAssignment(String docu_dict,
			int a_id, String description, String ss_dict, int point, int rating) throws SQLException {
		// TODO Auto-generated method stub
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		
		PreparedStatement ps = con.prepareStatement("update assignments set document_dict=?, uploaded_date=SYSDATE(), description=?, screenshot_dict=?, point=?, rating=? where a_id=?");
		
		ps.setString(1, docu_dict);
		ps.setString(2, description);
		ps.setString(3, ss_dict);
		ps.setInt(4,point);
		ps.setInt(5, rating);
		ps.setInt(6, a_id);
		
		
		int updated = ps.executeUpdate();
		
		if(updated==0)
			throw new SQLException("Update Error!");
		
		con.close();
		
	}
	
	public void deleteAssignment(int a_id) throws SQLException{
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		
		PreparedStatement ps = con.prepareStatement("delete from assignments where a_id=?");
		ps.setInt(1, a_id);	
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