/*
 * Copyright (c) 2012, Xiao yuan, Zhiyi wu. All rights reserved.
 *
 */

package mybeans.mydb.compare;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import java.lang.System;

@ManagedBean(name = "UI")
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

	TreeBean origMap;
	TreeBean targMap;
	TreeBean diffMap;
	TreeBean bothHaveTree;
	TreeBean origOnlyTree;
	TreeBean targOnlyTree;

	private File oriFile;
	private File tarFile;

	public String getCompResult() {
		return compResult;
	}

	public void setCompResult(String compResult) {
		this.compResult = compResult;
	}

	private String compResult;

	public boolean isEverCorrect() {
		return isEverCorrect;
	}

	public void setEverCorrect(boolean isEverCorrect) {
		this.isEverCorrect = isEverCorrect;
	}

	private boolean isEverCorrect = false;

	public ArrayList<Entry<String, Integer>> getOrigMapList() {
		return origMapList;
	}

	public void setOrigMapList(ArrayList<Entry<String, Integer>> origMapList) {
		this.origMapList = origMapList;
	}

	private ArrayList<Entry<String, Integer>> origMapList;
	private ArrayList<Entry<String, Integer>> targMapList;

	private ArrayList<Entry<String, Integer[]>> differList;
	private ArrayList<String> differStringList;

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

	private ArrayList<Integer> differIntOrigList;
	private ArrayList<Integer> differIntTargList;

	public ArrayList<Entry<String, Integer[]>> getDifferList() {
		return differList;
	}

	public void setDifferList(ArrayList<Entry<String, Integer[]>> differList) {
		this.differList = differList;
	}

	public ArrayList<Entry<String, Integer>> getTargMapList() {
		return targMapList;
	}

	public void setTargMapList(ArrayList<Entry<String, Integer>> targMapList) {
		this.targMapList = targMapList;
	}

	public ArrayList<String> getBothHaveList() {
		return bothHaveList;
	}

	public void setBothHaveList(ArrayList<String> bothHaveList) {
		this.bothHaveList = bothHaveList;
	}

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

	private ArrayList<String> origOnlyList;
	private ArrayList<String> targOnlyList;
	private ArrayList<String> bothHaveList;

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

	public String blockFilestream(ZipFile zf) {
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

			if (last3char.equals("/Screen1.blk")) {
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
	
	public ArrayList<String> showMediaFile(ZipFile zf){
		ArrayList<String> mediaFiles = new ArrayList<String>();
		
		ZipEntry entry;
		Enumeration<? extends ZipEntry> enu = zf.entries();
		InputStream is = null;
		String str = null;
		while(enu.hasMoreElements()){
			entry = enu.nextElement();
			str = entry.getName();
			if(str.startsWith("assets")){
				mediaFiles.add(str);
			}
		}
		
		return mediaFiles;
	}

	public String compareFiles(String oriFile, String tarFile)
			throws IOException {

		splitList sl = new splitList();

		ZipFile zf = null;

		try {
			zf = new ZipFile(oriFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();
		}

		dataBuild db = new dataBuild(zipFilestream(zf));
		blockBuild bb = new blockBuild(blockFilestream(zf));
		
		ArrayList<String> mediaFiles1;
		mediaFiles1 = showMediaFile(zf);
		
		for(String str : mediaFiles1){
			System.out.println("$$$$$$$$$$$$$   Media Files 1: "+str);
		}

		// ////////////////////////////////

		try {
			zf = new ZipFile(tarFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dataBuild db2 = new dataBuild(zipFilestream(zf));
		blockBuild bb2 = new blockBuild(blockFilestream(zf));

		ArrayList<String> mediaFiles2;
		mediaFiles2 = showMediaFile(zf);
		
		for(String str : mediaFiles2){
			System.out.println("$$$$$$$$$$$$$   Media Files 2: "+str);
		}

		sl.split(bb.compArr, bb2.compArr);

		bothHaveList = sl.bothHave;
		origMapList = sl.origMap;
		targMapList = sl.targMap;
		origOnlyList = sl.origOnly;
		targOnlyList = sl.targOnly;
		differList = sl.differList;

		differStringList = sl.differStringList;
		differIntOrigList = sl.differIntOrigList;
		differIntTargList = sl.differIntTargList;


		StringBuffer sb = new StringBuffer();

		if (db != null && db2 != null) {
			compareUI testUI = new compareUI();
			compResult = "";

			if(!mediaFiles1.isEmpty()){
				String mediaFiles = testUI.compareAssets(mediaFiles1, mediaFiles2);
				sb.append("Media Files: \n" + mediaFiles);
			}
			
			String structResult = testUI.compare(db.compArr, db2.compArr);

			if (structResult
					.equals("GREAT!! The number of components are matched!!")) {

				sb.append(structResult + "\n");
				
				String typeResult = testUI.compareTypeNum(db, db2);
				
				sb.append(typeResult + "\n");

				String position = testUI.comparePosition(db.compArr,
						db2.compArr);

				if (position
						.equals("Good! All components are placed in the right position!")) {

					sb.append(position
							+ "\n");

					String propertyResult = testUI.compareProperty(db.compArr,
							db2.compArr);
					
					System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCC             +++++++    " + propertyResult);

					if(propertyResult.equals("Great!! Properties matched!!")) {
						setEverCorrect(true);
					}
					
					sb.append(propertyResult);
				}
				else sb.append(position
						+ "\n");

			} else {
				sb.append(structResult);
				sb.append("Evaluation Details:\n");
				String typeResult = testUI.compareTypeNum(db, db2);
				sb.append(typeResult);
			}

			if (testUI.totalArrangeEqual(db, db2)) {
				String arrResult = testUI.compareArrangement(db.compArr,
						db2.compArr);
				sb.append(arrResult);
			}

		}


		FileWriter writer = new FileWriter("output.txt");
		writer.write(compResult);
		writer.close();


		if (!origOnlyList.isEmpty()) {
			sb.append(sl.Invisible);
		}

		compResult = sb.toString();
		
		tree1 = new TreeBean("Target File",
				db.compArr.get(db.compArr.size() - 1));
		
		tree2 = new TreeBean("Your File",
				db2.compArr.get(db2.compArr.size() - 1));

		return compResult;
	}

}