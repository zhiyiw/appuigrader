package mybeans.mydb.compare;

import java.util.*;
import java.util.Map.Entry;

import mybeans.mydb.compare.Component;

public class compareUI {

	private static ArrayList<String> Key = new ArrayList<String>();
	private static ArrayList<String> Value = new ArrayList<String>();

	private static ArrayList<HashMap> Maps = new ArrayList<HashMap>();

	ArrayList<Component> list1;
	ArrayList<Component> list2;

	// constructor
	public compareUI() {
		list1 = new ArrayList<Component>();
		list2 = new ArrayList<Component>();
	}

	public String compareTypeNum(dataBuild origin, dataBuild target) {
		int oF = origin.totalForm();
		int tF = target.totalForm();
		int oE = origin.totalEmpty();
		int tE = target.totalEmpty();
		
		int oB = origin.totalButton();
		int tB = target.totalButton();
		int oL = origin.totalLabel();
		int tL = target.totalLabel();
		int oV = origin.totalVertical();
		int tV = target.totalVertical();
		int oH = origin.totalHorizontal();
		int tH = target.totalHorizontal();

		int oT = origin.totalTableArr();
		int tT = target.totalTableArr();
		int oCB = origin.totalcheckbox();
		int tCB = target.totalcheckbox();
		int oTB = origin.totaltextbox();
		int tTB = target.totaltextbox();
		int oPTT = origin.totalpasstextbox();
		int tPTT = target.totalpasstextbox();
		int oLP = origin.totallistpicker();
		int tLP = target.totallistpicker();
		int oI = origin.totalimage();
		int tI = target.totalimage();
		int oIP = origin.totalimagepicker();
		int tIP = target.totalimagepicker();
		int oC = origin.totalcanvas();
		int tC = target.totalcanvas();
		int oEP = origin.totalemailpicker();
		int tEP = target.totalemailpicker();
		int oPNP = origin.totalphonenumberpicker();
		int tPNP = target.totalphonenumberpicker();
		int oCP = origin.totalcontactpicker();
		int tCP = target.totalcontactpicker();
		int oVP = origin.totalvideoplayer();
		int tVP = target.totalvideoplayer();
		int oWV = origin.totalwebviewer();
		int tWV = target.totalwebviewer();

		String buttonComp = "";
		String labelComp = "";
		String vertComp = "";
		String horiComp = "";

		String tabComp = "";
		String checkboxComp = "";
		String textboxComp = "";
		String passtextboxComp = "";
		String listpickerComp = "";
		String imageComp = "";
		String imagepickerComp = "";
		String canvasComp = "";
		String emailpickerComp = "";
		String phonenumberpickerComp = "";
		String contactpickerComp = "";
		String videoplayerComp = "";
		String webviewerComp = "";
		
		if (oF != tF) {
			buttonComp = "\nTotal number of FORM is mismatched!!!"
					+ "(Target: " + oF + ", Yours: " + tF + ")\n";
		}
		
		if (oE != tE) {
			buttonComp = "\nTotal number of EMPTY is mismatched!!!"
					+ "(Target: " + oE + ", Yours: " + tE + ")\n";
		}

		if (oB != tB) {
			buttonComp = "\nTotal number of BUTTON is mismatched!!!"
					+ "(Target: " + oB + ", Yours: " + tB + ")\n";
		}

		if (oL != tL) {
			labelComp = "\nTotal number of LABEL is mismatched!!!"
					+ "(Target: " + oL + ", Yours: " + tL + ")\n";
		}

		if (oV != tV) {
			vertComp = "\nTotal number of VERTICAL Arrangement is mismatched!!!"
					+ "(Target: " + oV + ", Yours: " + tV + ")\n";
		}

		if (oH != tH) {
			horiComp = "\nTotal number of HORIZONTAL Arrangement is mismatched!!!"
					+ "(Target: " + oH + ", Yours: " + tH + ")\n";
		}

		if (oT != tT) {
			tabComp = "\nTotal number of TABLE Arrangement is mismatched!!!"
					+ "(Target: " + oT + ", Yours: " + tT + ")\n";
		}

		if (oCB != tCB) {
			checkboxComp = "\nTotal number of CheckBox is mismatched!!!"
					+ "(Target: " + oCB + ", Yours: " + tCB + ")\n";
		}

		if (oTB != tTB) {
			textboxComp = "\nTotal number of TextBox is mismatched!!!"
					+ "(Target: " + oTB + ", Yours: " + tTB + ")\n";
		}

		if (oPTT != tPTT) {
			passtextboxComp = "\nTotal number of PasswordTextBox is mismatched!!!"
					+ "(Target: " + oPTT + ", Yours: " + tPTT + ")\n";
		}

		if (oLP != tLP) {
			listpickerComp = "\nTotal number of ListPicker is mismatched!!!"
					+ "(Target: " + oLP + ", Yours: " + tLP + ")\n";
		}

		if (oI != tI) {
			imageComp = "\nTotal number of Image is mismatched!!!"
					+ "(Target: " + oI + ", Yours: " + tI + ")\n";
		}

		if (oIP != tIP) {
			imagepickerComp = "\nTotal number of ImagePicker is mismatched!!!"
					+ "(Target: " + oIP + ", Yours: " + tIP + ")\n";
		}

		if (oC != tC) {
			canvasComp = "\nTotal number of Canvas is mismatched!!!"
					+ "(Target: " + oC + ", Yours: " + tC + ")\n";
		}

		if (oEP != tEP) {
			emailpickerComp = "\nTotal number of EmailPicker is mismatched!!!"
					+ "(Target: " + oEP + ", Yours: " + tEP + ")\n";
		}

		if (oPNP != tPNP) {
			phonenumberpickerComp = "\nTotal number of PhoneNumberPicker is mismatched!!!"
					+ "(Target: " + oPNP + ", Yours: " + tPNP + ")\n";
		}

		if (oCP != tCP) {
			contactpickerComp = "\nTotal number of ContactPicker is mismatched!!!"
					+ "(Target: " + oCP + ", Yours: " + tCP + ")\n";
		}

		if (oVP != tVP) {
			videoplayerComp = "\nTotal number of VideoPlayer is mismatched!!!"
					+ "(Target: " + oVP + ", Yours: " + tVP + ")\n";
		}

		if (oWV != tWV) {
			webviewerComp = "\nTotal number of WebViewer is mismatched!!!"
					+ "(Target: " + oWV + ", Yours: " + tWV + ")\n";
		}

		return buttonComp + labelComp + vertComp + horiComp + tabComp
				+ checkboxComp + textboxComp + passtextboxComp + listpickerComp
				+ imageComp + imagepickerComp + canvasComp + emailpickerComp
				+ phonenumberpickerComp + contactpickerComp + videoplayerComp
				+ webviewerComp;

	}

	// check if the total number of arrangements is equal
	public boolean totalArrangeEqual(dataBuild origin, dataBuild target) {
		int oV = origin.totalVertical();
		int tV = target.totalVertical();
		int oH = origin.totalHorizontal();
		int tH = target.totalHorizontal();
		int oT = origin.totalTableArr();
		int tT = target.totalTableArr();

		if (oV == tV && oH == tH && oT == tT) {
			return true;
		}
		return false;
	}

	// compare arrangement layout
	public String compareArrangement(ArrayList<Component> origin,
			ArrayList<Component> target) {
		int i;
		int m = 0;
		int n = 0;
		StringBuffer arrangeErr = new StringBuffer();
		String aaa;
		String bbb;

		HashMap<Integer, String> originArrange = new HashMap<Integer, String>();
		HashMap<Integer, String> targetArrange = new HashMap<Integer, String>();

		// put origin arrangements into hashmap
		for (i = 0; i < origin.size(); i++) {
			if (origin.get(i).getType().equals("VerticalArrangement")
					|| origin.get(i).getType().equals("HorizontalArrangement")
					|| origin.get(i).getType().equals("TableArrangment")) {
				m++;

				aaa = origin.get(i).getType();
				originArrange.put(m, aaa);

				System.out.println("origin = " + aaa);
			}
		}

		// put target arrangement into hashmap
		for (i = 0; i < target.size(); i++) {

			if (target.get(i).getType().equals("VerticalArrangement")
					|| target.get(i).getType().equals("HorizontalArrangement")
					|| target.get(i).getType().equals("TableArrangment")) {
				n++;

				bbb = target.get(i).getType();
				targetArrange.put(n, bbb);

				System.out.println("Yours = " + bbb);

			}
		}

		for (i = 1; i < originArrange.size() + 1; i++) {
			if (!originArrange.get(i).equals(targetArrange.get(i))) {
				arrangeErr.append("\nNo. " + i
						+ " arrangement is different: Target is '"
						+ originArrange.get(i) + "', Yours is '"
						+ targetArrange.get(i) + "'\n");
			}
		}
		String arrErr = arrangeErr.toString();
		return arrErr;
	}

	// decompose component and insert to ArrayList
	public void decompos(Component c) {
		int k;

		Maps.add(c.map);

		if (c.list != null) {
			for (k = 0; k < c.list.size(); k++) {
				decompos(c.list.get(k));
			}
		}

	}

	// get properties that need to compare
	public ArrayList<ArrayList<String>> getNeed(ArrayList<HashMap<String, String>> oriMap) {
		// char[] nameVal;
		char[] nameValue;
		String temp;
		int k, s, j;
		int st = 0;
		int ed = 0;
		int pos = 0;
		boolean isAssign = false;
		ArrayList<String> noName = new ArrayList<String>();
		noName.add("no-need");

		ArrayList<String> needOne = new ArrayList<String>();
		ArrayList<ArrayList<String>> needAll = new ArrayList<ArrayList<String>>();

		for (j = 0; j < oriMap.size(); j++) {
			temp = (String) oriMap.get(j).get("$Name");
			
			System.out.println("get: " + temp);
			if (!oriMap.get(j).containsKey("$Name")) {
				needAll.add(noName);
				continue;
			}
			
//			if(temp.equals("Screen1")) {
//				
//				System.out.println("SCREEEEEEEEEEEEEEEEN:  "+temp.equals("Screen1"));
//				
//				
//				System.out.println("SCROLLLLLLLLLLLLL:  "+origMap.get(j).containsKey("Scrollable"));
//
//				if(origMap.get(j).containsKey("$Scrollable")) {
//					needOne.add((String) origMap.get(j).get("$Scrollable"));
//					
//					ArrayList<String> scroll = new ArrayList<String>();
//					for (String ss : needOne) {
//						scroll.add(ss);
//					}
//					
//					needAll.add(scroll);
//					needOne.clear();
//					System.out.println("needALLLLLLLLLLLLL ===== " + needAll.get(0).get(0));
//					continue;
//				}
//			}
			
			
			
//			if(temp.equals("Screen1")) {
//				origMap.get(j).put("$Name", "Screen1_Scrollable");
//				temp = "Screen1_Scrollable";
//			}
			
			
			
			for (k = 0; k < temp.length(); k++) {
				if (temp.charAt(k) == '_') {
					isAssign = true;
					break;
				}
			}

			System.out.println("temp: " + temp);
			System.out.println(isAssign);

			// pos = 0;
			if (isAssign) {
				// nameVal = temp.toCharArray();
				nameValue = new char[temp.length() + 1];
				for (s = 0; s < temp.length(); s++) {
					nameValue[s] = temp.charAt(s);
				}
				nameValue[nameValue.length - 1] = '_';
				String tmp = new String(nameValue);
				// System.out.print(" nameV: " + nameValue);
				System.out.print("mody: " + tmp);
				System.out.println(" leng: " + tmp.length());
				for (k = 0; k < tmp.length(); k++) {
					System.out.print(tmp.charAt(k));
					System.out.println(" pos: " + pos);
					if (tmp.charAt(k) == '_') {

						if (pos == 0) {
							st = k + 1;
							pos = 1;
						} else if (pos == 1) {
							ed = k;
							needOne.add(tmp.substring(st, ed));
							// System.out.println("Instant: " + needOne.get(0));
							System.out.println("sub: " + tmp.substring(st, ed));
							// pos = 0;
							st = k + 1;
						}

					}

					if (k == tmp.length() - 1) {
						pos = 0;
					}
				}

				isAssign = false;

			} else {
				needOne.add("no-need");
			}

			// deep copy needOne to backup ArrayList
			ArrayList<String> backup = new ArrayList<String>();
			for (String ss : needOne) {
				backup.add(ss);
			}

			System.out.println("Need: " + needOne.get(0));
			if (needOne.size() > 1) {
				System.out.println("Need: " + needOne.get(1));
			}
			needAll.add(backup);
			// System.out.println("AXXX: " + needAll.get(0).get(0));
			needOne.clear();
			// System.out.println("ALLL: " + needAll.get(0).get(0));
		}
		return needAll;
	}

	// compare and get result of comparison of one component
	public String propertyResult(HashMap orig, HashMap targ,
			ArrayList<String> needCompare) {
		String result = "";
		int j;

		for (j = 0; j < needCompare.size(); j++) {
			// if (needCompare.get(0) == "no-need") continue;

			System.out.println("Key: " + needCompare.get(j));
			System.out.println("IsContain: "
					+ orig.containsKey(needCompare.get(j)));

			System.out.println("MapInfo: " + orig.get("$Name"));
			
			//if(orig.containsKey(""))

			if (orig.containsKey(needCompare.get(j))) {
				System.out.println("*****" + orig.get(needCompare.get(j)) + "******" + targ.get(needCompare.get(j)));
				
				if (!orig.get(needCompare.get(j)).equals(
						targ.get(needCompare.get(j)))) {
					result = result + orig.get("$Type")
							+ "'s " + needCompare.get(j) + ": Target File is "
							+ orig.get(needCompare.get(j)) + ", " + "      Yours is "
							+ targ.get(needCompare.get(j)) + "\n";
				}

				System.out.println("EACH-result: " + result);
			}

		}

		return result;
	}

	//Check if Screen is scrollable
	public String compareScrollable(ArrayList<Component> origin,
			ArrayList<Component> target){
		
		StringBuffer result = new StringBuffer();
		StringBuffer sb1 = new StringBuffer("Target File: ");
		StringBuffer sb2 = new StringBuffer("Your File: ");
		
		decompos(origin.get(origin.size() - 1));
		//ArrayList<HashMap<String, String>> oriMap = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> cpy : Maps) {
			if(cpy.containsKey("$Name")) {
				if(cpy.get("$Name").equals("Screen1")) {
					if(cpy.containsKey("Scrollable")){
						sb1.append("Not scrollable\n");
					}
					else {
						sb1.append("Scrollable\n");
					}
					break;
				}
			}
			//oriMap.add((HashMap) cpy.clone());
		}
		Maps.clear();
		
		
		decompos(target.get(target.size() - 1));
		//ArrayList<HashMap<String, String>> tarMap = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> cpy : Maps) {
			if(cpy.containsKey("$Name")) {
				if(cpy.get("$Name").equals("Screen1")) {
					if(cpy.containsKey("Scrollable")){
						sb2.append("Not scrollable\n" + "----------------------\n");
					}
					else {
						sb2.append("Scrollable\n" + "----------------------\n");
					}
					break;
				}
			}
			//tarMap.add((HashMap) cpy.clone());
		}
		Maps.clear();
		
		result.append(sb1);
		result.append(sb2);
		
		return result.toString();
	}
	
	// compare properties of both files
	public String compareProperty(ArrayList<Component> origin,
			ArrayList<Component> target) {
		int size = origin.size();
		int i, j, n;
		String proErr = "";
		boolean same = true;

//		for (i = 0; i < size; i++) {
//			if (!origin.get(i).map.keySet().equals(target.get(i).map.keySet())) {
//				same = false;
//			}
//
//		}
//
//		if (!same) {

			decompos(origin.get(origin.size() - 1));
			ArrayList<HashMap<String, String>> oriMap = new ArrayList<HashMap<String, String>>();
			for (HashMap<String, String> cpy : Maps) {
//				if(cpy.containsKey("$Name")) {
//					if(cpy.get("$Name").equals("Screen1")) {
//						cpy.put("$Name", "Screen1_Scrollable");
//					}
//				}
				oriMap.add((HashMap) cpy.clone());
			}
			Maps.clear();

			// ArrayList<String> oriKey = Key;
			// ArrayList<String> oriValue = Value;
			// Key.clear();
			// Value.clear();

			decompos(target.get(target.size() - 1));
			ArrayList<HashMap<String, String>> tarMap = new ArrayList<HashMap<String, String>>();
			for (HashMap<String, String> cpy : Maps) {
				tarMap.add((HashMap) cpy.clone());
			}
			Maps.clear();

			for (i = 0; i < oriMap.size(); i++) {
				System.out.println("test: " + oriMap.get(i).get("$Name"));
			}

			System.out.println("Property running!!");

			String tmp = "";

			ArrayList<ArrayList<String>> needCompare = new ArrayList<ArrayList<String>>();
			needCompare = getNeed(oriMap);

			for (j = 0; j < needCompare.size(); j++) {
				System.out.println("content: " + needCompare.get(j).get(0));
				if (needCompare.get(j).size() > 1) {
					System.out.println("content: " + needCompare.get(j).get(1));
				}
			}

			for (n = 0; n < needCompare.size(); n++) {
				// System.out.println(oriMap.get(n).get("$Name") + "  "
				// + tarMap.get(n).get("$Name") + "   "
				// + needCompare.get(n).get(0));
				tmp = propertyResult(oriMap.get(n), tarMap.get(n),
						needCompare.get(n));
				proErr = proErr + tmp;
			}

			System.out.println("RESULT: " + proErr);
			
			if(proErr.isEmpty()) 
				return "\n Great!! Properties are matched!!";
			else
				return "\n HOWEVER....\n\n" + proErr;
//		}
//
//		return "Great!! Properties matched!!";
	}

	public ArrayList<Component> addComp(Component comp,
			ArrayList<Component> list) {
		int i;

		if (comp.list.size() > 0) {

			for (i = 0; i < comp.list.size(); i++) {
				list.add(comp.list.get(i));
				addComp(comp.list.get(i), list);
			}

		}
		return list;
	}
	
	public void replaceComp(Component orig, Component comp){
		int i;
		
		for(i=0; i < orig.list.size(); i++) {
			if(orig.getPosition() == comp.getPosition()) {
				orig.setPosition(comp.getPosition());
				break;
			}
			
			replaceComp(orig.list.get(i), comp);
		}
	}

	public String comparePosition(ArrayList<Component> origin,
			ArrayList<Component> target) {
		
		String result = "";
		StringBuffer temp = new StringBuffer();
		ArrayList<Component> l1 = new ArrayList<Component>();
		ArrayList<Component> l2 = new ArrayList<Component>();
		int i;
		int size = origin.size();

//		System.out.println(size);
//		for (i = 0; i < size; i++) {
//			System.out.println(origin.get(i).getType());
//			System.out.println(origin.get(i).list.size());
//
//		}

		Component c1 = new Component();
		Component c2 = new Component();

		l1 = addComp(origin.get(size - 1), list1);
		l2 = addComp(target.get(size - 1), list2);
		
		
		StringBuffer buff;

		for (i = 0; i < size - 1; i++) {

			c1 = l1.get(i);
			c2 = l2.get(i);
//			System.out.println("AAAA: " + c1.getType() + " " + c1.getPosition()
//					+ " BBB: " + c2.getType() + " " + c2.getPosition());
			if (!c1.getType().equals(c2.getType())) {
				temp.append("\nNo." + c1.getPosition()
						+ " component is mismatched! \n" + "Target is: "
						+ c1.getType() + ", Yours is: " + c2.getType() + "\n");
				
				//add "!" to components that mismatched
				buff = new StringBuffer(c1.getType());
				c1.setType(buff.append("!!").toString());
				
				buff = new StringBuffer(c2.getType());
				c2.setType(buff.append("!!").toString());
				
				replaceComp(origin.get(size - 1), c1);
				replaceComp(origin.get(size - 1), c2);
			
			} else if (c1.getDepth() != c2.getDepth()) {
				temp.append("\nNo." + c1.getPosition()
						+ " component is mismatched! \n" + "Your "
						+ c2.getType() + "'s level is DIFFEREN from Target's");
				
				//add "!" to components that mismatched
				buff = new StringBuffer(c1.getType());
				c1.setType(buff.append("!!").toString());
				
				buff = new StringBuffer(c2.getType());
				c2.setType(buff.append("!!").toString());
				
				replaceComp(origin.get(size - 1), c1);
				replaceComp(origin.get(size - 1), c2);
			}
		}
		
		System.out.println("LENGTH" + temp.length());
		if (temp.length() != 0) {
			result = temp.toString();
			System.out.println(result);

			return result;
		}

		return "Good! All components are placed in the right position!";
	}

	// compare files and return the answer
	public String compare(ArrayList<Component> origin,
			ArrayList<Component> target) {
		int i;

		// compare number of components
		if (origin.size() != target.size()) {

			System.out.println("Total number of components mismatched");

			return "Total number of components does mismatched !!!\n";
			// return false;
		}

		// compare if each component contains same number of sub-components
//		for (i = 0; i < origin.size(); i++) {
//			if (origin.get(i).list.size() != target.get(i).list.size()) {
//				System.out
//						.println("Each component's next-level number mismatched");
//				return "Component's next-level number mismatched\n";
//				// return false;
//			}
//		}

		// compare if each component is with same type
//		for (i = 0; i < origin.size(); i++) {
//			System.out.println("Target type " + i + "-> "
//					+ origin.get(i).getType() + "\nTarget type " + i + "-> "
//					+ target.get(i).getType());
//			if (!origin.get(i).getType().equals(target.get(i).getType())) {
//
//				System.out.println("Type NOT match");
//				return "Type NOT match\n";
//				// return false;
//			}
//		}
		return "GREAT!! The number of components are matched!!";

	}
	
	public String compareAssets(ArrayList<String> list1, ArrayList<String> list2){
		String results1 = "";
		String results2 = "";
		String result = "";
		
		StringBuffer buffer1 = new StringBuffer("");
		StringBuffer buffer2 = new StringBuffer("");
		StringBuffer buffer = new StringBuffer("");
		
		
		for (String str : list1) {
			buffer1.append(str + "\n");
		}
		results1 = buffer1.toString();
		
		
		if(list2.isEmpty()) {
			results2 = "No Media Files Added!\n";
		}
		else {
			for(String str : list2){
				buffer2.append(str + "\n");
			}
			results2 = buffer2.toString();
		}

		buffer.append("Target: " + results1 + "\n" + "Yours: " + results2 + "\n");
		
		result = buffer.toString();
		return result;
	}

}
