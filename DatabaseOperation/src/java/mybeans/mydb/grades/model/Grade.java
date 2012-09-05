package mybeans.mydb.grades.model;

public class Grade {
	public int gradeID;
	public String studentName;
	public int assignmentID;
	public String gradeDirectory;
	public String latestUploadDate;
	public int tryCount;
	public int currentStatus;
	
	public int getGradeID() {
		return gradeID;
	}
	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getGradeDirectory() {
		return gradeDirectory;
	}
	public void setGradeDirectory(String gradeDirectory) {
		this.gradeDirectory = gradeDirectory;
	}
	public int getTryCount() {
		return tryCount;
	}
	public void setTryCount(int tryCount) {
		this.tryCount = tryCount;
	}
	public int getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	public int getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}
	
	public String getLatestUploadDate() {
		return latestUploadDate;
	}
	public void setLatestUploadDate(String latestUploadDate) {
		this.latestUploadDate = latestUploadDate;
	}
	
}
