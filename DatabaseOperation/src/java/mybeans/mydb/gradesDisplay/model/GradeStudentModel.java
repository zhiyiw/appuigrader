package mybeans.mydb.gradesDisplay.model;

public class GradeStudentModel {
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
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
	public String getLog_directory() {
		return log_directory;
	}
	public void setLog_directory(String log_directory) {
		this.log_directory = log_directory;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String studentName;
	public String studentID;
	public int assignmentID;
	public String assignmentName;
	public String log_directory;
	public String submitDate;
}
