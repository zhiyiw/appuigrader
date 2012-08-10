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

@ManagedBean(name = "register")
@SessionScoped
public class Register {

	private DataSource ds;
	private Connection con;
	
	public String username;
	public String password;
	
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


	public Register() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/projectdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public boolean addUser(int year, String term) throws SQLException {
		if (ds == null)
			throw new SQLException("Can't get data source");

		con = ds.getConnection();

		if (con == null)
			throw new SQLException("Can't get database connection");

		PreparedStatement ps = con
				.prepareStatement("select * from users where username = ?");

		ps.setString(1, username);

		ResultSet result = ps.executeQuery();

		int count = 0;

		while (result.next()) {
			count++;
		}

		if (count == 0) {

			ps = con.prepareStatement("insert into users values (?, ?, ?, ?)");

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, year);
			ps.setString(4, term);

			int updated = ps.executeUpdate();
		
			con.close();
			
			return true;
			
//			if (updated == 0)
//				throw new SQLException("Insert Error!");

		} else
			throw new SQLException("User Already Exists!!");
		


	}

		
}
