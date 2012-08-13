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
import org.primefaces.model.UploadedFile;


import mybeans.mydb.compare.UIFrame;

@ManagedBean(name="compare")
@SessionScoped
public class StudentTempUpload {

    // Init ---------------------------------------------------------------------------------------

    private UploadedFile uploadedFile;
    private String directory;
    private UIFrame frame;
    
    
    // Actions ------------------------------------------------------------------------------------

	public UIFrame getFrame() {
		return frame;
	}

	public void setFrame(UIFrame frame) {
		this.frame = frame;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public boolean tempUpload(String studentID, String rubricPath) {

        // Prepare filename prefix and suffix for an unique filename in upload folder.
        String prefix = "temp0"+studentID+FilenameUtils.getBaseName(uploadedFile.getFileName());
        String suffix = FilenameUtils.getExtension(uploadedFile.getFileName());
        
        // Prepare file and outputstream.
        File file = null;
        OutputStream output = null;
 
        try {
            // Create file with unique name in upload folder and write to it.
        	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        	String deploymentDirectoryPath = ctx.getRealPath("/")+"Temp";
        	File target = new File(deploymentDirectoryPath);
        	if(!target.exists())
        		target.mkdir();
        	//if(directory.exists())
        	file = File.createTempFile(prefix + "_", "." + suffix,new File(deploymentDirectoryPath));
            output = new FileOutputStream(file);
            IOUtils.copy(uploadedFile.getInputstream(), output);
            directory = "/Temp/"+file.getName();

            // Show succes message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                FacesMessage.SEVERITY_INFO, directory, null));
            
            frame = new UIFrame();

            frame.compareFiles(ctx.getRealPath(rubricPath),ctx.getRealPath(directory));            
            
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
        
        return true;
        
    }

    // Getters ------------------------------------------------------------------------------------

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getDirectory(){
    	return directory;
    }

    // Setters ------------------------------------------------------------------------------------

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}