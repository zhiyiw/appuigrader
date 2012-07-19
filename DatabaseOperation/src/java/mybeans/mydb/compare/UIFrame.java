/*
 * Copyright (c) 2012, Xiao yuan, Zhiyi wu. All rights reserved.
 *
 */

package mybeans.mydb.compare;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.awt.*;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mybeans.mydb.assignments.model.Assignment;

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

@ManagedBean(name = "compare")
@SessionScoped
// @SuppressWarnings("serial")
public class UIFrame {
	static private final String newline = "\n";

	private File oriFile;
	private File tarFile;

	private String compResult;

	public String getCompResult() {
		return compResult;
	}

	public void setCompResult(String compResult) {
		this.compResult = compResult;
	}

	public File getOriFile() {
		return oriFile;
	}

	public void setOriFile(File oriFile) {
		this.oriFile = oriFile;
	}

	public File getTarFile() {
		return tarFile;
	}

	public void setTarFile(File tarFile) {
		this.tarFile = tarFile;
	}

	boolean isValidFile = false;

	public String zipFilestream(ZipFile zf) {
		ZipEntry entry;
		Enumeration<? extends ZipEntry> enu = zf.entries();
		InputStream is = null;
		String str = null;
		while (enu.hasMoreElements()) {
			entry = (ZipEntry) enu.nextElement();

			if (entry.isDirectory())
				continue;

			System.out.println(entry.getName());
			String filename = entry.getName();
			String last3char = filename.substring(filename.length() - 12,
					filename.length());
			System.out.println(last3char);

			if (last3char.equals("/Screen1.scm")) {
				// isValidFile = true;
				try {
					is = zf.getInputStream(entry);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			int len = 0;
			byte[] b = new byte[20480];
			try {
				while ((len = is.read(b)) != -1) {
					str = new String(b, 0, len, "gb2312");
				}

			} catch (Exception e) {
				System.out.println("byte read Error");
			}

		}
		return str;

	}

	public String compareFiles() throws IOException {
		compResult = "";

		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		String filePath = ctx.getRealPath("/Student/");

		//File fileTest = new File(filePath + "test.txt");

//		FileInputStream fstream = new FileInputStream(filePath + "//" +"test.txt");
//		DataInputStream in = new DataInputStream(fstream);
//		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//		String str;
//		
//		while((str = br.readLine()) != null){
//			compResult = compResult + str;
//		}
//		
//		in.close();

		 oriFile = new File(filePath + "//" +"123.zip");
		 tarFile = new File(filePath + "//" + "button.zip");
		
		 ZipFile zf = null;
		
		 try {
		 zf = new ZipFile(oriFile);
		 } catch (IOException e1) {
		 // TODO Auto-generated catch block
		
		 e1.printStackTrace();
		 }
		
		 dataBuild db = new dataBuild(zipFilestream(zf));
		
		 // ////////////////////////////////
		
		 try {
		 zf = new ZipFile(tarFile);
		 } catch (IOException e1) {
		 // TODO Auto-generated catch block
		 e1.printStackTrace();
		 }
		
		 dataBuild db2 = new dataBuild(zipFilestream(zf));
		
		 // if (db == null && db2 == null) {
		 // compResult = "Pleases add Files !!!\n";
		 // System.out.println(compResult);
		 // return compResult;
		 // }
		 //
		 // if (db == null && db2 != null) {
		 // compResult = "Pleases choose teacher's file !!!\n";
		 // return compResult;
		 // }
		 //
		 // if (db != null && db2 == null) {
		 // compResult = "Pleases add your OWN file !!!\n";
		 // return compResult;
		 // }
		
		 if (db != null && db2 != null) {
		 compareUI testUI = new compareUI();
		 if (testUI.compare(db.compArr, db2.compArr).equals(
		 "GREAT!! Structure matched!!")) {
		
		 compResult = compResult + testUI.compare(db.compArr, db2.compArr) +
		 "\n";
		
		 if (!testUI.compareProperty(db.compArr, db2.compArr).equals(
		 "Great!! Properties matched!!"))
		
		 compResult = compResult + testUI.compareProperty(db.compArr,
		 db2.compArr);
		
		 } else {
		 //compResult="NOFILLLLLLLLLLLE";
		  compResult = testUI.compare(db.compArr, db2.compArr);
		  compResult = compResult + "Evaluation Details -->"
		  + testUI.compareTypeNum(db, db2);
		 }
		
		 // if (testUI.totalArrangeEqual(db, db2)) {
		 // compResult = testUI
		 // .compareArrangement(db.compArr, db2.compArr);
		 // }
		
		 }

		// compResult = "NNNNNNNNNNN";
		return compResult;
	}

}