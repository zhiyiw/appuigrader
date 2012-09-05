package mybeans.mydb.assignments.model;

public class Assignment {
	
	public int assignmentID;
	public String assignmentName; //add on after query
	public String assignmentDirectory;
	public String uploadedDate;
	public String description;
	public String screenDirectory;//screenshot directory
	public int point;
	public int rating;
	public int getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public String getAssignmentDirectory() {
		return assignmentDirectory;
	}
	public void setAssignmentDirectory(String assignmentDirectory) {
		this.assignmentDirectory = assignmentDirectory;
	}
	public String getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getScreenDirectory() {
		return screenDirectory;
	}
	public void setScreenDirectory(String screenDirectory) {
		this.screenDirectory = screenDirectory;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
