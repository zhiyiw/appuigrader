package mybeans.mydb.assignments.model;

public class Assignment {
	
	public int assignmentID;
	public String assignmentName;
	public String assignmentDirectory;
	public String uploadedDate;
	public String term;
	public int year;
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
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
	
}
