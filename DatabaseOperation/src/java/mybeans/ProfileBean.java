package mybeans;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mybeans.mydb.profile.Profile;
import mybeans.mydb.profile.model.ProfileModel;

@ManagedBean(name="profileBean")
@SessionScoped
public class ProfileBean {
	public ProfileModel pm;
	
	public ProfileModel getPm() {
		return pm;
	}
	public void setPm(ProfileModel pm) {
		this.pm = pm;
	}
	public void readProfile(String username) throws SQLException{
		Profile profile = new Profile();
		setPm(profile.getProfile(username));
	}
}
