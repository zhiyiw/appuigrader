package mybeans.mydb.gradesDisplay;


import java.io.IOException;
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


import mybeans.mydb.gradesDisplay.model.StudentAssignmentModel;

@ManagedBean(name="gradestudentBean")
@SessionScoped
public class GradeDisplay {

	private DataSource ds;
	
	private Connection con;
	
	
	public GradeDisplay(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}
	
	public List<StudentAssignmentModel> assignmentList;
	
	public StudentAssignmentModel selectedAssignment;
	
	public StudentAssignmentModel getSelectedAssignment() {
		return selectedAssignment;
	}

	public void setSelectedAssignment(StudentAssignmentModel selectedAssignment) {
		this.selectedAssignment = selectedAssignment;
	}

	

	public void getStudentAssignmentList(String username) throws SQLException, IOException{
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select * from assignments left join (select * from grades where username=?) r using(a_id) order by r.a_id ASC");
		
		ps.setString(1, username);

		
		ResultSet result =  ps.executeQuery();
		
		List<StudentAssignmentModel> l=new ArrayList<StudentAssignmentModel>();
		
		int count=1;
		while(result.next()){
			StudentAssignmentModel sam = new StudentAssignmentModel();
			sam.setAssignmentID(result.getInt("a_id"));
			sam.setAssignmentDes(result.getString("description"));
			sam.setAssignmentTries(result.getInt("try_count"));
			sam.setAssignmentDirectory(result.getString("document_dict"));
			sam.setAssignmentRating(result.getInt("rating"));
			
			int tempStatus = result.getInt("current_status");
			
			if(tempStatus==0)
				sam.setAssignmentStatus("Not Yet Compared");	
			else if(tempStatus==1)
				sam.setAssignmentStatus("Not Yet Matched");
			else
				sam.setAssignmentStatus("Macthed");
			
			String tempScreenshot=result.getString("screenshot_dict");
			if(tempScreenshot==null)
				sam.setScreenshotDirectory("noScreenshot");
			else if("".equals(tempScreenshot))
				sam.setScreenshotDirectory("noScreenshot");
			else
		    	sam.setScreenshotDirectory("/"+tempScreenshot);
			
			String name;	
			if(count<10)
				name = "Assignment0"+count;
			else
				name = "Assignment"+count;
			
			count++;
			sam.setAssignmentName(name);
			
			l.add(sam);
		}
		
		setAssignmentList(l);	
		con.close();
	}

	public List<StudentAssignmentModel> getAssignmentList() {
		return assignmentList;
	}

	public void setAssignmentList(List<StudentAssignmentModel> assignmentList) {
		this.assignmentList = assignmentList;
	}
}
