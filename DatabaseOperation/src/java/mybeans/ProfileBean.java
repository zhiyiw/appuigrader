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
		if("Guest".equals(username)){
			pm = new ProfileModel();
			pm.setUserID("Guest");
			pm.setCompletenessPercent(0.00);
			pm.setHighestRating(0);
			pm.setSuccessRate(0.00);
			pm.setTotalAttempts(0);
			pm.setTotalScore(0);
		}else{
			Profile profile = new Profile();
			setPm(profile.getProfile(username));
		}
	}
}
