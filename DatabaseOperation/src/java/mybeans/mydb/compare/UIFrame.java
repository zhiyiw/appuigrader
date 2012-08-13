/*
 * Copyright (c) 2012, Xiao yuan, Zhiyi wu. All rights reserved.
 *
 */

package mybeans.mydb.compare;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import java.lang.System;

@SessionScoped
// @SuppressWarnings("serial")
public class UIFrame {
	static private final String newline = "\n";

	public TreeBean getTree1() {
		return tree1;
	}

	public void setTree1(TreeBean tree1) {
		this.tree1 = tree1;
	}

	public TreeBean getTree2() {
		return tree2;
	}

	public void setTree2(TreeBean tree2) {
		this.tree2 = tree2;
	}

	TreeBean tree1;
	TreeBean tree2;
	
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

	public String compareFiles(String oriFile, String tarFile) throws IOException {
		compResult = "";

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
		
		 ZipFile zf = null;
		
		 try {
		 zf = new ZipFile(oriFile);
		 } catch (IOException e1) {
		 // TODO Auto-generated catch block
		
		 e1.printStackTrace();
		 }
		
		 dataBuild db = new dataBuild(zipFilestream(zf));
		 
		 tree1 = new TreeBean("Original File", db.compArr.get( db.compArr.size() - 1));
		
		 // ////////////////////////////////
		
		 try {
		 zf = new ZipFile(tarFile);
		 } catch (IOException e1) {
		 // TODO Auto-generated catch block
		 e1.printStackTrace();
		 }
		
		 dataBuild db2 = new dataBuild(zipFilestream(zf));
		
		 tree2 = new TreeBean("Target File", db2.compArr.get(db2.compArr.size() - 1));
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