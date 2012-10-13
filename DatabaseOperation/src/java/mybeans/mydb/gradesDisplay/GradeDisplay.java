package mybeans.mydb.gradesDisplay;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletContext;
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
		
		List<StudentAssignmentModel> l=new ArrayList<StudentAssignmentModel>();
		
		if("Guest".equals(username)){
			PreparedStatement ps 
			= con.prepareStatement(
			   "select * from assignments order by a_id ASC");
			
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				StudentAssignmentModel sam = new StudentAssignmentModel();
				sam.setAssignmentID(result.getInt("a_id"));
				sam.setAssignmentDes(result.getString("description"));
				String temp = sam.getAssignmentDes();
				
				if(temp.length()>30){
					sam.setAssignmentShortDes(temp.substring(0, 29)+" ...");
				}else
					sam.setAssignmentShortDes(temp);
				
				
				sam.setAssignmentTries(0);
				sam.setAssignmentDirectory(result.getString("document_dict"));
				sam.setAssignmentHistory("No compare history!");
				sam.setAssignmentRating(result.getInt("rating"));
				sam.setAssignmentStatus("Not Yet Compared");
				sam.setAssignmentPoint(result.getInt("point"));
				sam.setAssignmentAuthor(result.getString("author"));
				sam.setDownloadable(result.getInt("downloadable"));
				
				String tempScreenshot=result.getString("screenshot_dict");
				if(tempScreenshot==null)
					sam.setScreenshotDirectory("noScreenshot");
				else if("".equals(tempScreenshot))
					sam.setScreenshotDirectory("noScreenshot");
				else
			    	sam.setScreenshotDirectory("/"+tempScreenshot);
				
				sam.setAssignmentName(result.getString("name"));
				l.add(sam);
			}
			
		}else{
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select * from assignments left join (select * from grades where username=?) r using(a_id) order by a_id ASC");
		
		ps.setString(1, username);

		
		ResultSet result =  ps.executeQuery();
		
		
		while(result.next()){
			StudentAssignmentModel sam = new StudentAssignmentModel();
			sam.setAssignmentID(result.getInt("a_id"));
			sam.setAssignmentDes(result.getString("description"));
			String temp = sam.getAssignmentDes();
			
			if(temp.length()>30){
				sam.setAssignmentShortDes(temp.substring(0, 29)+" ...");
			}else
				sam.setAssignmentShortDes(temp);
			
			sam.setAssignmentTries(result.getInt("try_count"));
			sam.setAssignmentDirectory(result.getString("document_dict"));
			sam.setAssignmentPoint(result.getInt("point"));
			sam.setAssignmentAuthor(result.getString("author"));
			sam.setDownloadable(result.getInt("downloadable"));
			int canDownload = result.getInt("downloadable");
			
			String filePath = result.getString("grade_dict");
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			
			if(filePath!=null && filePath!=""){
				String realPath = ctx.getRealPath(filePath);
				FileInputStream fstream = new FileInputStream(realPath);
				  // Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String str;
				StringBuffer sb = new StringBuffer();
				//Read File Line By Line
				while ((str = br.readLine()) != null)   {
				// Print the content on the console
					sb.append(str);
					sb.append("\n");
				}
				  //Close the input stream
				  in.close();
				
				sam.setAssignmentHistory(sb.toString());
			}else{
				sam.setAssignmentHistory("No compare history yet!");
			}
        	
			sam.setAssignmentRating(result.getInt("rating"));
			
			int tempStatus = result.getInt("current_status");
			
			
			if(tempStatus==0)
				sam.setAssignmentStatus("Not Yet Compared");	
			else if(tempStatus==1)
				sam.setAssignmentStatus("Not Yet Matched");
			else if(canDownload==1){
				//sam.setAssignmentStatus("<p:commandButton value=\"Download\" icon=\"ui-icon-arrowthichk-s\"><p:fileDownload value=\"#{fileDownloadController.getFile(selectedAssignment.assignmentDirectory)}\"/></p:commandButton>");
				String zipFilePath = ctx.getRealPath(sam.assignmentDirectory);
				sam.setAssignmentStatus("<a href=\""+sam.assignmentDirectory+"\" target=\"_blank\">Download</a>");
			}
			else
				sam.setAssignmentStatus("Macthed");
			
			String tempScreenshot=result.getString("screenshot_dict");
			if(tempScreenshot==null)
				sam.setScreenshotDirectory("noScreenshot");
			else if("".equals(tempScreenshot))
				sam.setScreenshotDirectory("noScreenshot");
			else
		    	sam.setScreenshotDirectory("/"+tempScreenshot);
			

			sam.setAssignmentName(result.getString("name"));
			
			l.add(sam);
		}
		}
		
		setAssignmentList(l);
		if(!l.isEmpty())
			selectedAssignment = l.get(0);
		con.close();
	}
	

	public List<StudentAssignmentModel> getAssignmentList() {
		return assignmentList;
	}

	public void setAssignmentList(List<StudentAssignmentModel> assignmentList) {
		this.assignmentList = assignmentList;
	}
}
