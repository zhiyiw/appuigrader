package mybeans.mydb.authentication;

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

@ManagedBean(name="login")
@SessionScoped

public class Login {
	
	private DataSource ds;
	private Connection con;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String username;
	public String password;
	
	
	public Login() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/projectdb");			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public int loginCheck() throws SQLException {
		int isMatched = 0;
		
		
		if(ds == null) throw new SQLException("Can't get data source");
		
		con = ds.getConnection();
		
		if(con == null) throw new SQLException("Can't get database connection");
		
		PreparedStatement ps = con.prepareStatement("select * from users where username = ?");
		
		ps.setString(1, username);
		//ps.setString(2, password);
		
		ResultSet result = ps.executeQuery();
		
		int count=0;
		
		while(result.next()) {
			count++;
		}
		
		if(count == 1) {
			ps = con.prepareStatement("select * from users where username = ? and password = AES_ENCRYPT(?, 'AndroidAppUIGrader')");
			ps.setString(1, username);
			ps.setString(2, password);

			
			result = ps.executeQuery();
			
			count=0;
			
			while(result.next()) {
				count++;
			}
			
			if(count==1)
				isMatched = 1;
			else
				isMatched = 0;
		}
		else {
			isMatched = 2;
		}
		
		con.close();
		
		return isMatched;
	}
	

}





