package mybeans.mydb.compare;

import java.util.*;

class Component {

	public HashMap<String, String> map;
	public ArrayList<Component> list;

	int depth;
	int position;
	private String name;
	private String type;
	
	private String version;
	private String height;
	private String weight;
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	int component;
	public Component(){
		
	}

	public Component(int depthcount, int position) {

		map = new HashMap<String, String>();
		list = new ArrayList<Component>();
		depth = depthcount;
		component = 0;
		this.position = position;

	}

	public void countComponent(Component c) {

	}

	public int getDepth() {
		return depth;
	}

	public int addDepth() {
		return depth++;
	}

	public String getName() {
		return name;
	}

	public void changeName(String n) {
		name = n;
	}

	public void changeType(String t) {
		type = t;
	}

	public void changeComponent(int n) {
		component = n;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getComponent() {
		return component;
	}

}
