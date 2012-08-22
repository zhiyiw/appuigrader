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
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean(name="upload")
@SessionScoped
public class Upload {

	private UploadedFile uploadedFile;
    private String fileName;
    // Actions ------------------------------------------------------------------------------------
    // Getters ------------------------------------------------------------------------------------
    public Upload(){
    	fileName="Choose New File...";
    }
    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getFileName() {
        return fileName;
    }

    // Setters ------------------------------------------------------------------------------------

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void handleFileUpload(FileUploadEvent event) { 
    	FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded."); 
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    	fileName=event.getFile().getFileName();
    	
    }

	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 

}