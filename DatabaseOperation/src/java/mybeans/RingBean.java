package mybeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="ringBean")
@ApplicationScoped

public class RingBean {
	private List<String> coders;
	public RingBean(){
		coders = new ArrayList<String>();
		coders.add("<script type=\"IN/MemberProfile\" data-id=\"http://www.linkedin.com/pub/yang-shu/40/727/177\" data-format=\"inline\" data-related=\"false\"></script>");
		coders.add("<script type=\"IN/MemberProfile\" data-id=\"http://www.linkedin.com/pub/xiao-yuan/2b/a24/977\" data-format=\"inline\" data-related=\"false\"></script>");
		coders.add("<script type=\"IN/MemberProfile\" data-id=\"http://www.linkedin.com/in/zhiyiwu\" data-format=\"inline\" data-related=\"false\"></script>");
	}
	public List<String> getCoders() {
		return coders;
	}
	public void setCoders(List<String> coders) {
		this.coders = coders;
	}
}
