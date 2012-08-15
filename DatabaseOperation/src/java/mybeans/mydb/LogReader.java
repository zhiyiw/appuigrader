package mybeans.mydb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


@ManagedBean(name="logReader")
@RequestScoped
public class LogReader {
	public String log;

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	public void getLogFrom(String directory) throws IOException{
    	ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	String deploymentDirectoryPath = ctx.getRealPath(directory);
    	FileReader fr = new FileReader(deploymentDirectoryPath); 
    	BufferedReader br = new BufferedReader(fr); 
    	StringBuffer sb = new StringBuffer();
    	String line;
    	while((line = br.readLine()) != null) { 
    		sb.append(line);
    	} 
    		fr.close(); 
    		setLog(sb.toString());
	}
	
}
