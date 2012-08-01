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
import org.apache.myfaces.custom.fileupload.UploadedFile;

import java.lang.System;
import java.sql.SQLException;

import mybeans.mydb.assignments.AssignmentBean;

@ManagedBean(name="navi")
@SessionScoped


public class naviTest{
	public String navig;
	
	
	public String getNavig() {
		return navig;
	}


	public void setNavig(String navig) {
		this.navig = navig;
	}


	public String respons(){
		setNavig("nav");
		
		return navig;
	}
	
	
}