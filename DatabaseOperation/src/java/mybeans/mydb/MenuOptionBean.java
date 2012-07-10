package mybeans.mydb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="menuoption")
@SessionScoped

public class MenuOptionBean {
	
	public int selectYear;
	public String selectTerm;
	
	
	public int getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(int selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectTerm() {
		return selectTerm;
	}

	public void setSelectTerm(String selectTerm) {
		this.selectTerm = selectTerm;
	}

	public List getYearList(){
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List list = new ArrayList();
		for(int i=0;i<3;i++)
			list.add(year-i);
		return list;
	}
	
	public List<String> getTermList(){
		List<String> list = new ArrayList<String>();
		list.add("Spring");
		list.add("Summer");
		list.add("Fall");
		list.add("Winter");
		return list;
	}
}
