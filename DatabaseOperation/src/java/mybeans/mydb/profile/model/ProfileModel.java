package mybeans.mydb.profile.model;

public class ProfileModel {
	String userID;
	int totalAttempts;//attempt across all assignments
	//int highestAttempt;
	double completenessPercent;
	int highestRating;//the hardest difficulty he ever encountered
	double successRate;
	int totalScore;
	

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getTotalAttempts() {
		return totalAttempts;
	}
	public void setTotalAttempts(int totalAttempts) {
		this.totalAttempts = totalAttempts;
	}
	public double getCompletenessPercent() {
		return completenessPercent;
	}
	public void setCompletenessPercent(double completenessPercent) {
		this.completenessPercent = completenessPercent;
	}
	
	public int getHighestRating() {
		return highestRating;
	}
	public void setHighestRating(int highestRating) {
		this.highestRating = highestRating;
	}
	public double getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
}
