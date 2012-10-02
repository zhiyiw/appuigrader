package mybeans.mydb.gradesDisplay.model;

public class StudentAssignmentModel {
	public String getAssignmentDirectory() {
		return assignmentDirectory;
	}
	public void setAssignmentDirectory(String assignmentDirectory) {
		this.assignmentDirectory = assignmentDirectory;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public int getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}
	public String getAssignmentDes() {
		return assignmentDes;
	}
	public void setAssignmentDes(String assignmentDes) {
		this.assignmentDes = assignmentDes;
	}
	public String getAssignmentStatus() {
		return assignmentStatus;
	}
	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}
	public int getAssignmentTries() {
		return assignmentTries;
	}
	public void setAssignmentTries(int assignmentTries) {
		this.assignmentTries = assignmentTries;
	}
	public String assignmentName;
	public int assignmentID;
	public String assignmentDes;
	public String assignmentStatus;
	public int assignmentTries;
	public String assignmentDirectory;
	public String screenshotDirectory;
	public int assignmentRating;
	public String assignmentHistory;
	public int assignmentPoint;
	
	public int getAssignmentPoint() {
		return assignmentPoint;
	}
	public void setAssignmentPoint(int assignmentPoint) {
		this.assignmentPoint = assignmentPoint;
	}
	public String getAssignmentHistory() {
		return assignmentHistory;
	}
	public void setAssignmentHistory(String assignmentHistory) {
		this.assignmentHistory = assignmentHistory;
	}
	public int getAssignmentRating() {
		return assignmentRating;
	}
	public void setAssignmentRating(int assignmentRating) {
		this.assignmentRating = assignmentRating;
	}
	public String getScreenshotDirectory() {
		return screenshotDirectory;
	}
	public void setScreenshotDirectory(String screenshotDirectory) {
		this.screenshotDirectory = screenshotDirectory;
	}
}
