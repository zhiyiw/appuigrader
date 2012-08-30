package mybeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import java.lang.System;
import java.util.Date;
import java.sql.SQLException;

import mybeans.mydb.assignments.AssignmentBean;

@ManagedBean(name="controller")
@SessionScoped
public class FileUploadController {

    // Init ---------------------------------------------------------------------------------------

    private UploadedFile uploadedFile;
    private UploadedFile screenshot;
    private String fileName;
    public String selectedZipFilename = "Choose a file...";
    public String selectedImageFilename = "Choose a file...";
    
	public UploadedFile getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(UploadedFile screenshot) {
		this.screenshot = screenshot;
	}

	private String directory;
    public String des;
    // Actions ------------------------------------------------------------------------------------



	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	public void clearDes() {
		this.des=null;
	}

	public boolean addNew(int year, String term, Date deadline) {
		if(uploadedFile!=null){
        // Prepare filename prefix and suffix for an unique filename in upload folder.
        String prefix = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String suffix = FilenameUtils.getExtension(uploadedFile.getFileName());
        
        // Prepare file and outputstream.
        File file = null;
        OutputStream output = null;
        
        try {
            // Create file with unique name in upload folder and write to it.
        	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        	String deploymentDirectoryPath = ctx.getRealPath("/")+"Rubrics";
        	File target = new File(deploymentDirectoryPath);
        	if(!target.exists())
        		target.mkdir();
        	//if(directory.exists())
        	file = File.createTempFile(prefix + "_", "." + suffix,new File(deploymentDirectoryPath));
            output = new FileOutputStream(file);
            IOUtils.copy(uploadedFile.getInputstream(), output);
            fileName = file.getName();
            directory = "Rubrics/"+fileName;

            // Show succes message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                FacesMessage.SEVERITY_INFO, deploymentDirectoryPath, null));
            
            AssignmentBean assbean = new AssignmentBean();
            
            
            java.text.SimpleDateFormat sdf = 
            	     new java.text.SimpleDateFormat("yyyy-MM-dd");

            String deadlineTime = sdf.format(deadline);
            
            try {
            	if(screenshot!=null){
            		deploymentDirectoryPath=ctx.getRealPath("/")+"Screenshot";
            		target = new File(deploymentDirectoryPath);
                	if(!target.exists())
                		target.mkdir();
                	
                    prefix = FilenameUtils.getBaseName(screenshot.getFileName());
                    suffix = FilenameUtils.getExtension(screenshot.getFileName());
                	file = File.createTempFile(prefix + "_", "." + suffix,new File(deploymentDirectoryPath));
                    output = new FileOutputStream(file);
                    IOUtils.copy(screenshot.getInputstream(), output);
                    fileName = file.getName();
                    String ssDirectory = "Screenshot/"+fileName;
            		assbean.addAssignment(directory, year, term,des, deadlineTime, ssDirectory);
            	}
            	else
            		assbean.addAssignment(directory, year, term,des, deadlineTime, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            // Cleanup.
            if (file != null) file.delete();

            // Show error message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "File upload failed with I/O error.", null));

            // Always log stacktraces (with a real logger).
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(output);
        }
        
        this.uploadedFile=null;
        this.des=null;
        this.fileName=null;
        this.directory=null;
        return true;
		}else return false;
        
    }
    
    public boolean updateExist(int year, String term, int fileID, Date deadline) {
    	
    	if(uploadedFile!=null){
        // Prepare filename prefix and suffix for an unique filename in upload folder.
        String prefix = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String suffix = FilenameUtils.getExtension(uploadedFile.getFileName());
        
        // Prepare file and outputstream.
        File file = null;
        OutputStream output = null;
        
        try {
            // Create file with unique name in upload folder and write to it.
        	

        	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        	String deploymentDirectoryPath = ctx.getRealPath("/")+"Rubrics";
        	File target = new File(deploymentDirectoryPath);
        	if(!target.exists())
        		target.mkdir();
        	//if(directory.exists())
        	file = File.createTempFile(prefix + "_", "." + suffix,new File(deploymentDirectoryPath));
            output = new FileOutputStream(file);
            IOUtils.copy(uploadedFile.getInputstream(), output);
            fileName = file.getName();
            directory = "Rubrics/"+fileName;
            
            AssignmentBean assbean = new AssignmentBean();
            
            java.text.SimpleDateFormat sdf = 
           	     new java.text.SimpleDateFormat("yyyy-MM-dd");

           String deadlineTime = sdf.format(deadline);
            try {
               	if(screenshot!=null){
            		deploymentDirectoryPath=ctx.getRealPath("/")+"Screenshot";
            		target = new File(deploymentDirectoryPath);
                	if(!target.exists())
                		target.mkdir();
                	
                    prefix = FilenameUtils.getBaseName(screenshot.getFileName());
                    suffix = FilenameUtils.getExtension(screenshot.getFileName());
                	file = File.createTempFile(prefix + "_", "." + suffix,new File(deploymentDirectoryPath));
                    output = new FileOutputStream(file);
                    IOUtils.copy(screenshot.getInputstream(), output);
                    fileName = file.getName();
                    String ssDirectory = "Screenshot/"+fileName;
                    assbean.updateAssignment(directory,fileID, des, deadlineTime, ssDirectory);
               	}
               	else
               		assbean.updateAssignment(directory,fileID, des, deadlineTime, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            // Cleanup.
            if (file != null) file.delete();

            // Show error message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "File upload failed with I/O error.", null));

            // Always log stacktraces (with a real logger).
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(output);
            
            this.uploadedFile=null;
            this.des=null;
            this.fileName=null;
            this.directory=null;
            
            this.screenshot=null;
            this.selectedImageFilename="Choose a file...";
            this.selectedZipFilename="Choose a file...";
        }

        return true;
    	}else return false;
    }

    // Getters ------------------------------------------------------------------------------------

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getFileName() {
        return fileName;
    }
    
    public String getDirectory(){
    	return directory;
    }

    // Setters ------------------------------------------------------------------------------------

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void handleZipUpload(FileUploadEvent event) {  
    	uploadedFile=event.getFile();
    	selectedZipFilename=event.getFile().getFileName();
    }

    public void handleImageUpload(FileUploadEvent event) {  
    	screenshot=event.getFile();
    	selectedImageFilename=event.getFile().getFileName();
    }
    
    public String getSelectedZipFilename() {
		return selectedZipFilename;
	}

	public void setSelectedZipFilename(String selectedZipFilename) {
		this.selectedZipFilename = selectedZipFilename;
	}

	public String getSelectedImageFilename() {
		return selectedImageFilename;
	}

	public void setSelectedImageFilename(String selectedImageFilename) {
		this.selectedImageFilename = selectedImageFilename;
	}

	public void reset(){
    	uploadedFile=null;
    	screenshot=null;
    	selectedZipFilename="Choose a file...";
    	selectedImageFilename="Choose a file...";
    }

}