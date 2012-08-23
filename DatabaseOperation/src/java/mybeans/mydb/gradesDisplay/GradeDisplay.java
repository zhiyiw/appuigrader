package mybeans.mydb.gradesDisplay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.primefaces.event.ToggleEvent;

import mybeans.mydb.grades.GradeBean;
import mybeans.mydb.grades.model.Grade;
import mybeans.mydb.gradesDisplay.model.GradeStudentModel;
import mybeans.mydb.gradesDisplay.model.StudentAssignmentModel;

@ManagedBean(name="gradestudentBean")
@SessionScoped
public class GradeDisplay {
	
	public GradeStudentModel log;
	
	public GradeStudentModel getLog() {
		return log;
	}

	public void setLog(GradeStudentModel log) {
		this.log = log;
	}

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
	
	public List<GradeStudentModel> list;
	public List<StudentAssignmentModel> assignmentList;
	
	public StudentAssignmentModel selectedAssignment;
	public StudentAssignmentModel getSelectedAssignment() {
		return selectedAssignment;
	}

	public void setSelectedAssignment(StudentAssignmentModel selectedAssignment) {
		this.selectedAssignment = selectedAssignment;
	}

	public GradeStudentModel currentSelect;
	
	public List<GradeStudentModel> getList() {
		return list;
	}

	public void setList(List<GradeStudentModel> list) {
		this.list = list;
	}

	public void getGradeStudentList(int a_id) throws SQLException, IOException{
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select u.username, r.log_directory, r.log_upload_date from (select * from grades left join assignments using(a_id) where a_id=?) r, users u where u.username=r.s_id");
		
		ps.setInt(1, a_id);
		
		ResultSet result =  ps.executeQuery();
		
		List<GradeStudentModel> l=new ArrayList<GradeStudentModel>();
		
		while(result.next()){
			GradeStudentModel gsm = new GradeStudentModel();
			gsm.setStudentID(result.getString("username"));
			
			
	    	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    	String deploymentDirectoryPath = ctx.getRealPath(result.getString("log_directory"));
	    	FileReader fr = new FileReader(deploymentDirectoryPath); 
	    	BufferedReader br = new BufferedReader(fr); 
	    	StringBuffer sb = new StringBuffer();
	    	String line;
	    	while((line = br.readLine()) != null) { 
	    		sb.append(line+"\n");
	    	}
	    	
	    	fr.close(); 
			
			gsm.setLog_directory(sb.toString());
			gsm.setSubmitDate(result.getString("log_upload_date"));
			l.add(gsm);
		}
		
		setList(l);	
		con.close();
	}
	
	public void getGradeStudentSingleEntry(int a_id, String username) throws SQLException, IOException{
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select u.username, r.log_directory from (select * from grades g left join assignments a using(a_id) where a_id=? and g.s_id=?) r, users u where u.username=r.s_id");
		
		ps.setInt(1, a_id);
		ps.setString(2, username);
		ResultSet result =  ps.executeQuery();
		
		List<GradeStudentModel> l=new ArrayList<GradeStudentModel>();
		
		while(result.next()){
			GradeStudentModel gsm = new GradeStudentModel();
			gsm.setStudentID(result.getString("username"));
			
			
	    	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    	String deploymentDirectoryPath = ctx.getRealPath(result.getString("log_directory"));
	    	FileReader fr = new FileReader(deploymentDirectoryPath); 
	    	BufferedReader br = new BufferedReader(fr); 
	    	StringBuffer sb = new StringBuffer();
	    	String line;
	    	while((line = br.readLine()) != null) { 
	    		sb.append(line+"\n");
	    	}
	    	
	    	fr.close(); 
			
			gsm.setLog_directory(sb.toString());
			l.add(gsm);
		}
		
		if(l.isEmpty())
			setCurrentSelect(null);
		else
			setCurrentSelect(l.get(0));	
		con.close();
	}
	
	
	public void getStudentAssignmentList(String username, int year, String term) throws SQLException, IOException{
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select r.a_id, r.a_name, r.description, r.a_directory, g.try_count, g.current_status from (select * from  assignments a cross join users u) r left join grades g using (a_id) where r.username=? and r.a_year=? and r.a_term=?");
		
		ps.setString(1, username);
		ps.setInt(2, year);
		ps.setString(3, term);
		
		ResultSet result =  ps.executeQuery();
		
		List<StudentAssignmentModel> l=new ArrayList<StudentAssignmentModel>();
		
		while(result.next()){
			StudentAssignmentModel sam = new StudentAssignmentModel();
			sam.setAssignmentID(result.getInt("a_id"));
			sam.setAssignmentName(result.getString("a_name"));
			sam.setAssignmentDes(result.getString("description"));
			sam.setAssignmentTries(result.getInt("try_count"));
			sam.setAssignmentDirectory(result.getString("a_directory"));
			String tempStatus = result.getString("current_status");
			if(tempStatus.equals(""))
				sam.setAssignmentStatus("Not Yet Compared");
			else
				sam.setAssignmentStatus(tempStatus);
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

	public GradeStudentModel getCurrentSelect() {
		return currentSelect;
	}

	public void setCurrentSelect(GradeStudentModel currentSelect) {
		this.currentSelect = currentSelect;
	}
}
