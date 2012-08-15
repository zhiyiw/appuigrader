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
    private String fileName;
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

        // Just to demonstrate what information you can get from the uploaded file.
        System.out.println("File type: " + uploadedFile.getContentType());
        System.out.println("File name: " + uploadedFile.getFileName());
        System.out.println("File size: " + uploadedFile.getSize() + " bytes");

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
				assbean.addAssignment(directory, year, term,des, deadlineTime);
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
        
    }
    
    public boolean updateExist(int year, String term, int fileID, Date deadline) {

        // Just to demonstrate what information you can get from the uploaded file.
        System.out.println("File type: " + uploadedFile.getContentType());
        System.out.println("File name: " + uploadedFile.getFileName());
        System.out.println("File size: " + uploadedFile.getSize() + " bytes");

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
				assbean.updateAssignment(directory,fileID, des, deadlineTime);
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

}