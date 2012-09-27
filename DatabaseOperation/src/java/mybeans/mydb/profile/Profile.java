package mybeans.mydb.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mybeans.mydb.profile.model.ProfileModel;

public class Profile {
	private DataSource ds;
	
	private Connection con;
	
	
	public Profile(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ProfileModel getProfile(String username) throws SQLException{
		ProfileModel model = new ProfileModel();
		
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		model.setUserID(username);
		
		PreparedStatement ps 
			= con.prepareStatement(
			   "select sum(try_count) from assignments left join (select * from grades where username='123') r using(a_id) order by a_id ASC");
		
		ResultSet result =  ps.executeQuery();
		result.next();
		model.setTotalAttempts(result.getInt(1));
		
		ps = con.prepareStatement("select count(a_id) from assignments left join (select * from grades where username='123') r using(a_id) where g_id>0");
		result = ps.executeQuery();
		result.next();
		int tempCurrent = result.getInt(1);
		ps = con.prepareStatement("select count(a_id) from assignments left join (select * from grades where username='123') r using(a_id)");
		result = ps.executeQuery();
		result.next();
		int tempTotal = result.getInt(1);
		model.setCompletenessPercent(100*(tempCurrent/tempTotal)/100.0);
		
		ps = con.prepareStatement("select max(rating) from assignments left join (select * from grades where username='123') r using(a_id) where g_id>0");
		result = ps.executeQuery();
		result.next();
		model.setHighestRating(result.getInt(1));
		
		ps = con.prepareStatement("select count(g_id) from assignments left join (select * from grades where username='123') r using(a_id) where current_status=1");
		result = ps.executeQuery();
		result.next();
		model.setSuccessRate(100*(result.getInt(1)/model.getTotalAttempts())/100.0);
		
		ps = con.prepareStatement("select sum(point) from assignments left join (select * from grades where username='123') r using(a_id) where current_status=1");
		result = ps.executeQuery();
		result.next();
		model.setTotalScore(result.getInt(1));
		
		con.close();
		
		return model;
	}
}
