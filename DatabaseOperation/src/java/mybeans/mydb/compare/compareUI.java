package mybeans.mydb.compare;

import java.util.*;
import java.util.Map.Entry;

public class compareUI {

	private static ArrayList<String> Key = new ArrayList<String>();
	private static ArrayList<String> Value = new ArrayList<String>();

	private static ArrayList<HashMap> Maps = new ArrayList<HashMap>();

	// constructor
	public compareUI() {

	}

	public String compareTypeNum(dataBuild origin, dataBuild target) {
		int oB = origin.totalButton();
		int tB = target.totalButton();
		int oL = origin.totalLabel();
		int tL = target.totalLabel();
		int oV = origin.totalVertical();
		int tV = target.totalVertical();
		int oH = origin.totalHorizontal();
		int tH = target.totalHorizontal();

		String buttonComp = "";
		String labelComp = "";
		String vertComp = "";
		String horiComp = "";

		if (oB != tB) {
			buttonComp = "\nThe total number of BUTTON is NOT correct!!!"
					+ "(original: " + oB + ", target: " + tB + ")\n";
		}

		if (oL != tL) {
			labelComp = "\nThe total number of LABEL is NOT correct!!!"
					+ "(original: " + oL + ", target: " + tL + ")\n";
		}

		if (oV != tV) {
			vertComp = "\nThe total number of VERTICAL Arrangement is NOT correct!!!"
					+ "(original: " + oV + ", target: " + tV + ")\n";
		}

		if (oH != tH) {
			horiComp = "\nThe total number of HORIZONTAL Arrangement is NOT correct!!!"
					+ "(original: " + oH + ", target: " + tH + ")\n";
		}

		return buttonComp + labelComp + vertComp + horiComp;

	}

	// check if the total number of arrangements is equal
	public boolean totalArrangeEqual(dataBuild origin, dataBuild target) {
		int oV = origin.totalVertical();
		int tV = target.totalVertical();
		int oH = origin.totalHorizontal();
		int tH = target.totalHorizontal();

		if (oV == tV && oH == tH) {
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
					|| origin.get(i).getType().equals("HorizontalArrangement")) {
				m++;

				aaa = origin.get(i).getType();
				originArrange.put(m, aaa);

				System.out.println("origin = " + aaa);
			}
		}

		// put target arrangement into hashmap
		for (i = 0; i < target.size(); i++) {

			if (target.get(i).getType().equals("VerticalArrangement")
					|| target.get(i).getType().equals("HorizontalArrangement")) {
				n++;

				bbb = target.get(i).getType();
				targetArrange.put(n, bbb);

				System.out.println("target = " + bbb);

			}
		}

		for (i = 1; i < originArrange.size() + 1; i++) {
			if (!originArrange.get(i).equals(targetArrange.get(i))) {
				arrangeErr.append("\nNo. " + i
						+ " arrangement is different: Original is '"
						+ originArrange.get(i) + "', Target is '"
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
	public ArrayList<ArrayList<String>> getNeed(ArrayList<HashMap> origMap) {
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

		for (j = 0; j < origMap.size(); j++) {
			System.out.println("get: " + origMap.get(j).get("$Name"));
			if (!origMap.get(j).containsKey("$Name")) {
				needAll.add(noName);
				continue;
			}

			temp = (String) origMap.get(j).get("$Name");
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

			if (orig.containsKey(needCompare.get(j))) {
				if (!orig.get(needCompare.get(j)).equals(
						targ.get(needCompare.get(j)))) {
					result = result + "Original Type: " + orig.get("$Type") + "'s "
							+ needCompare.get(j) + " is "
							+ orig.get(needCompare.get(j)) + "      Yours: "
							+ targ.get("$Name") + "'s " + needCompare.get(j)
							+ " is " + targ.get(needCompare.get(j)) + "\n";
				}

				System.out.println("EACH-result: " + result);
			}

		}

		return result;
	}

	// compare properties of both files
	public String compareProperty(ArrayList<Component> origin,
			ArrayList<Component> target) {
		int size = origin.size();
		int i, j, n;
		String proErr = "";
		boolean same = true;

		
		for (i = 0; i < size; i++) {
			if (!origin.get(i).map.keySet().equals(target.get(i).map.keySet())) {
				same = false;
			}

		}

		if (!same) {

			decompos(origin.get(origin.size() - 1));
			ArrayList<HashMap> oriMap = new ArrayList<HashMap>();
			for (HashMap cpy : Maps) {
				oriMap.add((HashMap) cpy.clone());
			}
			Maps.clear();

			// ArrayList<String> oriKey = Key;
			// ArrayList<String> oriValue = Value;
			// Key.clear();
			// Value.clear();

			decompos(target.get(target.size() - 1));
			ArrayList<HashMap> tarMap = new ArrayList<HashMap>();
			for (HashMap cpy : Maps) {
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
			return "HOWEVER....\n\n" + proErr;
		}

		return "Great!! Properties matched!!";
	}

	// compare files and return the answer
	public String compare(ArrayList<Component> origin,
			ArrayList<Component> target) {
		int i;

		// compare number of components
		if (origin.size() != target.size()) {

			System.out.println("Total number of components NOT match");

			return "Total number of components does NOT match !!!\n";
			// return false;
		}

		// compare if each component contains same number of sub-components
		for (i = 0; i < origin.size(); i++) {
			if (origin.get(i).list.size() != target.get(i).list.size()) {
				System.out
						.println("Each component's next-level number NOT match");
				return "Component's next-level number NOT match\n";
				// return false;
			}
		}

		// compare if each component is with same type
		for (i = 0; i < origin.size(); i++) {
			System.out.println("Origin type " + i + "-> "
					+ origin.get(i).getType() + "\nTarget type " + i + "-> "
					+ target.get(i).getType());
			if (!origin.get(i).getType().equals(target.get(i).getType())) {

				System.out.println("Type NOT match");
				return "Type NOT match\n";
				// return false;
			}
		}
		return "GREAT!! Structure matched!!";

	}

}
