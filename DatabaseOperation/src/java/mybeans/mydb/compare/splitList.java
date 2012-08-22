package mybeans.mydb.compare;

import java.util.*;
import java.util.Map.Entry;


public class splitList {
	
	public ArrayList<String> getOrigOnly() {
		return origOnly;
	}

	public void setOrigOnly(ArrayList<String> origOnly) {
		this.origOnly = origOnly;
	}

	public ArrayList<String> getTargOnly() {
		return targOnly;
	}

	public void setTargOnly(ArrayList<String> targOnly) {
		this.targOnly = targOnly;
	}

	public ArrayList<String> getBothHave() {
		return bothHave;
	}

	public void setBothHave(ArrayList<String> bothHave) {
		this.bothHave = bothHave;
	}

	public HashMap<String, Integer> getOrig() {
		return orig;
	}

	public void setOrig(HashMap<String, Integer> orig) {
		this.orig = orig;
	}

	public HashMap<String, Integer> getTarg() {
		return targ;
	}

	public void setTarg(HashMap<String, Integer> targ) {
		this.targ = targ;
	}

	public HashMap<String, Integer[]> getDiffer() {
		return differ;
	}

	public void setDiffer(HashMap<String, Integer[]> differ) {
		this.differ = differ;
	}

	ArrayList<String> origOnly;
	ArrayList<String> targOnly;
	ArrayList<String> bothHave;
	
	

	public ArrayList<Entry<String, Integer>> getOrigMap() {
		return origMap;
	}

	public void setOrigMap(ArrayList<Entry<String, Integer>> origMap) {
		this.origMap = origMap;
	}

	ArrayList<Entry<String, Integer>> origMap;
	ArrayList<Entry<String, Integer>> targMap;
	
	ArrayList<Entry<String, Integer[]>> differList;
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

	ArrayList<String> differStringList;
	ArrayList<Integer> differIntOrigList;
	ArrayList<Integer> differIntTargList;
	
	public ArrayList<Entry<String, Integer[]>> getDifferList() {
		return differList;
	}

	public void setDifferList(ArrayList<Entry<String, Integer[]>> differList) {
		this.differList = differList;
	}

	public ArrayList<Entry<String, Integer>> getTargMap() {
		return targMap;
	}

	public void setTargMap(ArrayList<Entry<String, Integer>> targMap) {
		this.targMap = targMap;
	}

	HashMap<String, Integer> orig;
	HashMap<String, Integer> targ;

	HashMap<String, Integer[]> differ;
	
	public splitList(){
		
		origOnly = new ArrayList<String>();
		targOnly = new ArrayList<String>();
		bothHave = new ArrayList<String>();	
		
		//both = new HashMap<String, Integer>();
		orig = new HashMap<String, Integer>();
		targ = new HashMap<String, Integer>();
		
		differ = new HashMap<String, Integer[]>();
		
		origMap = new ArrayList<Entry<String, Integer>>();
		targMap = new ArrayList<Entry<String, Integer>>();
		differList = new ArrayList<Entry<String, Integer[]>>();
		
		differStringList = new ArrayList<String>();
		differIntOrigList = new ArrayList<Integer>();
		differIntTargList = new ArrayList<Integer>();
	}
	
	public void split(ArrayList<String> origin, ArrayList<String> target){
		String temp = "";
		int i;
		
		
		//build map for original file
		for(i=0;i<origin.size();i++) {
			
			temp = origin.get(i);
			
			if(orig.containsKey(temp)) {
				
				orig.put(temp, orig.get(temp)+1);
			}
			else{
				
				orig.put(temp, 1);
			}
			
		}

		
		//build map for target file & build target only map
		for(i=0;i<target.size();i++){
			
			temp = target.get(i);
			
			if(targ.containsKey(temp)) {
				
				targ.put(temp, targ.get(temp)+1);
			}
			else{
				
				targ.put(temp, 1);
			}
			
		}
		
		for(Entry<String, Integer> entr : orig.entrySet()){
			if(targ.containsKey(entr.getKey())){
				bothHave.add(entr.getKey());
			}
			else{
				origOnly.add(entr.getKey());
			}
		}
//		
//		if(orig.containsKey(temp)){
//			bothHave.add(target.get(i));
//		}
//		else{
//			targOnly.add(temp);
//		}
		
		
		//add Map entries to ArrayList
		for(Entry<String, Integer> entr : orig.entrySet()){
			
			origMap.add(entr);
			
		}
		
		
		for(Entry<String, Integer> entr2 : targ.entrySet()){
			
			targMap.add(entr2);
			
		}

		
		//build original only map
		for(Entry<String, Integer> entr : targ.entrySet()){
			if(!orig.containsKey(entr.getKey())){
				targOnly.add(entr.getKey());
			}
		}

		
		//add all difference into ArrayList
		int origCount=0;
		int targCount=0;
		Integer[] diff = new Integer[2];
		
		for(String both : bothHave) {
			origCount = orig.get(both);
			targCount = targ.get(both);
			if(origCount != targCount){
				diff[0] = origCount;
				diff[1] = targCount;
				differ.put(both, diff);
				differStringList.add(both);
				differIntOrigList.add(differ.get(both)[0]);
				differIntTargList.add(differ.get(both)[1]);
			}
		}
		
		for(Entry<String, Integer[]> dif : differ.entrySet()){
			differList.add(dif);

		}
		
		
//		for(i=0;i<differList.size();i++){
//			differStringList.add(differList.get(i).getKey());
//			differIntOrigList.add(differList.get(i).getValue()[0]);
//			differIntTargList.add(differList.get(i).getValue()[1]);
//		}
		
	}	
	
}
	
	
	
	