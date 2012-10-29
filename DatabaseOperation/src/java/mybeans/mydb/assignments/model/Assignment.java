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
	public String shortDescription;
	public String author;
	public int downloadable;
	public boolean value;
	public String pdfDirectory;
	public String pdfDownloadLink;
	
	public String getPdfDownloadLink() {
		return pdfDownloadLink;
	}

	public void setPdfDownloadLink(String pdfDownloadLink) {
		this.pdfDownloadLink = pdfDownloadLink;
	}

	public String getPdfDirectory() {
		return pdfDirectory;
	}

	public void setPdfDirectory(String pdfDirectory) {
		this.pdfDirectory = pdfDirectory;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	public int getDownloadable() {
		return downloadable;
	}
	public void setDownloadable(int downloadable) {
		this.downloadable = downloadable;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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
