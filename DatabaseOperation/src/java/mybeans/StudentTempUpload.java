package mybeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


import mybeans.mydb.compare.UIFrame;
import mybeans.mydb.grades.GradeBean;

@ManagedBean(name="compare")
@SessionScoped
public class StudentTempUpload {

    // Init ---------------------------------------------------------------------------------------

    private UploadedFile uploadedFile;
    private String directory;
    private UIFrame frame;
    private ArrayList<String> compResult;
    public String selectedFilename = "Choose a file...";
    public String getSelectedFilename() {
		return selectedFilename;
	}

	public void setSelectedFilename(String selectedFilename) {
		this.selectedFilename = selectedFilename;
	}

	public ArrayList<String> getBothHaveList() {
		return bothHaveList;
	}

	public void setBothHaveList(ArrayList<String> bothHaveList) {
		this.bothHaveList = bothHaveList;
	}

	private ArrayList<String> origOnlyList;
	public ArrayList<String> getOrigOnlyList() {
		return origOnlyList;
	}

	public void setOrigOnlyList(ArrayList<String> origOnlyList) {
		this.origOnlyList = origOnlyList;
	}

	public ArrayList<String> getTargOnlyList() {
		return targOnlyList;
	}

	public void setTargOnlyList(ArrayList<String> targOnlyList) {
		this.targOnlyList = targOnlyList;
	}

	private ArrayList<String> targOnlyList;
	private ArrayList<String> bothHaveList;
	
	private boolean everMatched;
	
	public boolean isEverMatched() {
		return everMatched;
	}

	public void setEverMatched(boolean everMatched) {
		this.everMatched = everMatched;
	}

	public ArrayList<Entry<String, Integer>> getOrigMapList() {
		return origMapList;
	}

	public void setOrigMapList(ArrayList<Entry<String, Integer>> origMapList) {
		this.origMapList = origMapList;
	}

	private ArrayList<Entry<String, Integer>> origMapList;
	
	public ArrayList<Entry<String, Integer>> getTargMapList() {
		return targMapList;
	}

	public void setTargMapList(ArrayList<Entry<String, Integer>> targMapList) {
		this.targMapList = targMapList;
	}

	private ArrayList<Entry<String, Integer>> targMapList;
	
	public ArrayList<Entry<String, Integer[]>> getDifferList() {
		return differList;
	}

	public void setDifferList(ArrayList<Entry<String, Integer[]>> differList) {
		this.differList = differList;
	}

	private ArrayList<Entry<String, Integer[]>> differList;
	public ArrayList<String> getDifferStringList() {
		return differStringList;
	}

	public void setDifferStringList(ArrayList<String> differStringList) {
		this.differStringList = differStringList;
	}

	public ArrayList<Integer> getDifferIntOrigList() {
		return differIntOrigList;
	}

	public void setDifferIntOrigList(ArrayList<Integer> differIntOrigList) {
		this.differIntOrigList = differIntOrigList;
	}

	public ArrayList<Integer> getDifferIntTargList() {
		return differIntTargList;
	}

	public void setDifferIntTargList(ArrayList<Integer> differIntTargList) {
		this.differIntTargList = differIntTargList;
	}

	private ArrayList<String> differStringList;
	private ArrayList<Integer> differIntOrigList;
	private ArrayList<Integer> differIntTargList;
    
    
    // Actions ------------------------------------------------------------------------------------

	public ArrayList<String> getCompResult() {
		return compResult;
	}

	public void setCompResult(ArrayList<String> compResult) {
		this.compResult = compResult;
	}

	public UIFrame getFrame() {
		return frame;
	}

	public void setFrame(UIFrame frame) {
		this.frame = frame;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public boolean tempUpload(String studentID, String rubricPath, int assignmentID) throws SQLException {

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
            compResult = new ArrayList<String>();
            compResult = frame.compareFiles(ctx.getRealPath(rubricPath),ctx.getRealPath(directory)); 
            
            bothHaveList = frame.getBothHaveList();
            origMapList = frame.getOrigMapList();
            targMapList = frame.getTargMapList();
            origOnlyList = frame.getOrigOnlyList();
            targOnlyList = frame.getTargOnlyList();
            differList = frame.getDifferList();

	        differStringList = frame.getDifferStringList();
        	differIntOrigList = frame.getDifferIntOrigList();
	        differIntTargList = frame.getDifferIntTargList();
	        
	        everMatched = frame.isEverCorrect();
	        
	        int match;
	        if(everMatched){
	        	match = 2;
	        }
	        else{
	        	match = 1;
	        }

            
            StringBuffer sb = new StringBuffer();
            
            for(String s:compResult)
            	sb.append(s);
                        
            String split = "\n\n\n***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****\n\n";
            Date date = new Date();
            
            String header = split + date.toString() + "\n\n" + "RESULT RECORD: \n\n";
            
            sb.insert(0, header);
            
            String sss = sb.toString();
            GradeBean grade = new GradeBean();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            
            String currentTime = sdf.format(date);
            if(!"Guest".equals(studentID))
            	grade.createNewGrade(assignmentID, studentID, sss, currentTime, match);
        } catch (IOException e) {
            // Cleanup.
            if (file != null) file.delete();

            // Show error message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "File upload failed with I/O error.", null));

            // Always log stacktraces (with a real logger).
            e.printStackTrace();
        } finally {

        	file.delete();
            IOUtils.closeQuietly(output);
            uploadedFile=null;
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
    
    public void handleZipUpload(FileUploadEvent event) {
    	uploadedFile=event.getFile();
    	selectedFilename=event.getFile().getFileName();
    }
    
    public void reset(){
    	uploadedFile=null;
    	selectedFilename="Choose a file...";
    }
}
