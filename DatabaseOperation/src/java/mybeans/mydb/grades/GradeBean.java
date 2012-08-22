package mybeans.mydb.grades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import mybeans.mydb.assignments.model.Assignment;
import mybeans.mydb.grades.model.Grade;

@ManagedBean(name="gradeBean")
@SessionScoped
public class GradeBean {
	
	//resource injection
	//@Resource(name="jdbc/projectdb")
	private DataSource ds;
	
	private Connection con;
	
	
	public GradeBean(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}
	
	public List<Grade> getAssignmentGradeList(int a_id) throws SQLException{
	
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select log_id, s_id, log_directory, log_upload_date from grades where a_id=?");
		
		ps.setInt(1, a_id);
		
		ResultSet result =  ps.executeQuery();
		
		List<Grade> list = new ArrayList<Grade>();
		
		while(result.next()){
			Grade grade = new Grade();
			grade.setLogID(result.getInt("log_id"));
			grade.setStudentID(result.getString("s_id"));
			grade.setLogDirectory(result.getString("log_directory"));
			grade.setLatestUploadDate(result.getString("log_upload_date"));
			
			list.add(grade);
		}
		
		con.close();
		return list;
		
	}
	
	
	public List<Grade> getStudentGradeList(String s_id) throws SQLException{
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select log_id, a_id, log_directory, log_upload_date from grades where s_id=?");
		
		ps.setString(1, s_id);
		
		ResultSet result =  ps.executeQuery();
		
		List<Grade> list = new ArrayList<Grade>();
		
		while(result.next()){
			Grade grade = new Grade();
			grade.setLogID(result.getInt("log_id"));
			grade.setAssignmentID(result.getInt("a_id"));
			grade.setLogDirectory(result.getString("log_directory"));
			grade.setLatestUploadDate(result.getString("log_upload_date"));
			
			list.add(grade);
		}
		
		con.close();
		return list;
		
	}
	
	public Grade getStudentAssignment(int a_id, String s_id) throws SQLException{
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps 
			= con.prepareStatement(
			   "select log_id, log_directory, log_upload_date from grades where s_id=? and a_id=?");
		
		ps.setString(1, s_id);
		ps.setInt(2, a_id);
		
		ResultSet result =  ps.executeQuery();
		
		List<Grade> list = new ArrayList<Grade>();
		
		while(result.next()){
			Grade grade = new Grade();
			grade.setLogID(result.getInt("log_id"));
			grade.setLogDirectory(result.getString("log_directory"));
			grade.setLatestUploadDate(result.getString("log_upload_date"));
			
			list.add(grade);
		}
		
		con.close();
		
		if(list.isEmpty())
			return null;
		else return list.get(0);
		
	}
	
	public void createNewGrade(int a_id, String s_id, String content, String currentTime, String matchResult) throws SQLException, IOException{
		Grade g = getStudentAssignment(a_id, s_id);
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
		
		
		PreparedStatement ps 
		= con.prepareStatement(
		   "select count(log_id) from grades");
		
		ResultSet result =  ps.executeQuery();
		
		result.next();
		
		int current = result.getInt(1)+1;
		
		
        File file = null;
        OutputStream output = null;
		
		if(g==null)
		{

			//write into file
			
        	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        	String deploymentDirectoryPath = ctx.getRealPath("/")+"Records";
        	File target = new File(deploymentDirectoryPath);
        	
        	if(!target.exists())
        		target.mkdir();
        	//if(directory.exists())
        	file = File.createTempFile("s_id_"+s_id+ "_a_id_"+a_id, "." + "gr",new File(deploymentDirectoryPath));
        	output = new FileOutputStream(file);
        	
    		byte[] contentInBytes = content.getBytes();
    		 
			output.write(contentInBytes);
			output.flush();
			output.close();
			
			String directory="/Records/"+file.getName();
			
			ps = con.prepareStatement(
					   "insert into grades values (?,?,?,?,?,1,?)");
			
			ps.setInt(1, current);
			ps.setInt(2, a_id);
			ps.setString(3, s_id);
			ps.setString(4,directory);
			ps.setString(5, currentTime);
			ps.setString(6, matchResult);
			
			int updated = ps.executeUpdate();
			
			if(updated==0)
				throw new SQLException("Insert Error!");
			
			con.close();
			
		}
		else
		{
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String deploymentDirectoryPath = ctx.getRealPath(g.logDirectory);
			output = new FileOutputStream(deploymentDirectoryPath,true);
			
    		byte[] contentInBytes = content.getBytes();
   		 
			output.write(contentInBytes);
			output.flush();
			output.close();
			
			ps=con.prepareStatement(
					"update grades set try_count=try_count+1, log_upload_date=?, current_status=? where log_id=?");
			
			ps.setString(1,currentTime);
			ps.setString(2, matchResult);
			ps.setInt(3, g.logID);
			
			int updated = ps.executeUpdate();
			
			if(updated==0)
				throw new SQLException("Update Error!");
			
			con.close();
		}
	}
}
