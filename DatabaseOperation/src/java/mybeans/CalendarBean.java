package mybeans;

import java.util.Date;  

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="calendarBean")
@RequestScoped

public class CalendarBean {
	
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	} 

}
