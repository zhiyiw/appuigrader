package mybeans.mydb.compare;

import java.util.*;

public class blockBuild {

	ArrayList<String> compArr;
    boolean hasRIG;

	public blockBuild(String s) {
		int i; 
		int flag = 0;
		int start=0;
		int end=0;
		String tmp;
		hasRIG = false;
		compArr = new ArrayList<String>();
		
		for(i=0;i<s.length();i++) {
			
			//System.out.println(i + " " + s.charAt(i));
			
			
			
			if(flag == 1) {
				if(s.charAt(i) == '"') {
					end = i;
					tmp = s.substring(start, end);
					if(tmp.equals("Random-Integer-Generator")) hasRIG = true;

					compArr.add(tmp);
					
					flag = 0;
				}
				continue;
			}
			
			if(s.charAt(i) == 'g') {
				if(s.substring(i, i+10).equals("genus-name")) {
					i = i+12;
					start = i;
					flag = 1;
				}
				else {
					continue;
				}
								
			}
			
		}

		Iterator<String> iter = compArr.iterator();
		
		while(iter.hasNext()) {
			
			System.out.println(iter.next());
		}
	}
	
}
