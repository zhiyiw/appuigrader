package mybeans.mydb.grades.model;

public class Grade {
	public int logID;
	public int studentID;
	public int assignmentID;
	public String logDirectory;
	public String latestUploadDate;
	
	public int getLogID() {
		return logID;
	}
	public void setLogID(int logID) {
		this.logID = logID;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}
	public String getLogDirectory() {
		return logDirectory;
	}
	public void setLogDirectory(String logDirectory) {
		this.logDirectory = logDirectory;
	}
	public String getLatestUploadDate() {
		return latestUploadDate;
	}
	public void setLatestUploadDate(String latestUploadDate) {
		this.latestUploadDate = latestUploadDate;
	}
	
}
