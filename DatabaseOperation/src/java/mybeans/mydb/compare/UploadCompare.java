package mybeans.mydb.compare;

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


public class UploadCompare {
	
	//resource injection
		//@Resource(name="jdbc/projectdb")
		private DataSource ds;
		
		private Connection con;

		//if resource injection is not support, you still can get it manually.
		public UploadCompare(){
			try {
				Context ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");
			} catch (NamingException e) {
				e.printStackTrace();
			}
	 
		}
	
	
		public void uploadCompare(String directory, String name) throws SQLException{
			
			
			if(ds==null)
				throw new SQLException("Can't get data source");
	 
			//get database connection
			con = ds.getConnection();
	 
			if(con==null)
				throw new SQLException("Can't get database connection");
			
			PreparedStatement ps 
			= con.prepareStatement(
			   "select * from compareFile");
			
			ResultSet result =  ps.executeQuery();
			
			result.next();
			int current = result.getInt(1)+1;
			
			ps = con.prepareStatement("insert into compareFile values (?,?,SYSDATE())");
			

			ps.setString(1, name);
			ps.setString(2, directory);
			
			int updated = ps.executeUpdate();
			
			if(updated==0)
				throw new SQLException("Insert Error!");
			
			con.close();
			
		}
	
	
}