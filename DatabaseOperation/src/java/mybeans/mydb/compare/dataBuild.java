package mybeans.mydb.compare;

import java.util.*;

public class dataBuild {

	ArrayList<Component> compArr;

	int form;
	int empt;
	
	int button;
	int label;
	int vertArrange;
	int horiArrange;
	
	int tabArrange;
	int checkbox;
	int textbox;
	int passtextbox;
	int listpicker;
	int image;
	int imagepicker;
	int canvas;
	int emailpicker;
	int phonenumberpicker;
	int contactpicker;
	int videoplayer;
	int webviewer;

	public dataBuild(String s) {

		form = 0;
		empt = 0;
		
		button = 0;
		label = 0;
		vertArrange = 0;
		horiArrange = 0;
		
		tabArrange = 0;
		checkbox = 0;
		textbox = 0;
		passtextbox = 0;
		listpicker = 0;
		image = 0;
		imagepicker = 0;
		canvas = 0;
		emailpicker = 0;
		phonenumberpicker = 0;
		contactpicker = 0;
		videoplayer = 0;
		webviewer = 0;
		

		int pos = 1;
		int k_head = 0;
		int k_tail = 0;
		int v_head = 0;
		int v_tail = 0;
		int depth = 0;
		int position = 0;
		int countComponent[] = new int[20];
		int countTypeForm = 0;

		String key = null;
		String value = null;
		Component c = null;

		compArr = new ArrayList<Component>();

		Stack<Component> stack;
		stack = new Stack<Component>();

		// parse for each char
		for (int i = 0; i < s.length(); i++) {

			// when the char is '{'
			if (s.charAt(i) == '{') {

				c = new Component(depth, position); // assign the depth to the Component

				depth++;
				position++;
					
				stack.push(c);
			}

			// when the char is '}'
			if (s.charAt(i) == '}') {

				c = stack.pop();

				depth--;

				// ignore components with special names
				if (c.map.get("$Name") != null
						&& c.map.get("$Name").equals("OPT") == true)
					continue;
				

				if (c.map.get("$Type") == null) {
					c.changeType("empty");
				} else {
					c.changeType(c.map.get("$Type"));
				}
				

				// count total number of each type of component
				if (c.map.get("$Type") != null) {
					this.countTypeNum(c.map.get("$Type"));
				}
				
				c.changeName(c.map.get("$Name"));

				System.out.println("The name of this component is: "
						+ c.getName());
				System.out.println("The depth of this component is: "
						+ c.getDepth());
				System.out.println("The type of this component is: "
						+ c.getType() + "\n");
				
				
				if (stack.empty() == false) {
					try {
						stack.peek().list.add(c);
					} catch (NullPointerException e) {
						System.out.println("Null Pointer!!!");
					}
					;
				}

				compArr.add(c);

			}

			// when the char is '['
			if (s.charAt(i) == '[') {

				countComponent[depth]++;

				if (stack.empty() == false)
					try {
						stack.peek().list = new ArrayList<Component>();
					} catch (EmptyStackException e) {
						System.out.println("Stack is empty!!!");
					}
			}

			// when the char is ']'
			if (s.charAt(i) == ']') {
				// depth--;
				c.component = c.list.size();
				continue;
			}

			// when the char is something else
			else {

				if (s.charAt(i) == '"') {

					if (pos == 4) {
						v_tail = i;

						pos = 1;

						value = s.substring(v_head + 1, v_tail);

						if (stack.empty() == false && key != null
								&& value != null) {

							if (value == "Form") {
								countTypeForm++;

							}

							try {
								stack.peek().map.put(key, value);
							} catch (NullPointerException e) {
								System.out
										.println("\n NULL pointer error!!! \n");
							}

						}
						continue;
					}

					if (pos == 3) {
						pos = 4;
						v_head = i;

					}

					if (pos == 2) {

						k_tail = i;

						if (s.charAt(k_tail + 2) == '{'
								|| s.charAt(k_tail + 2) == '[') {

							pos = 1;
							key = s.substring(k_head + 1, k_tail);
							value = "";

							try {
								stack.peek().map.put(key, value);
							} catch (NullPointerException e) {
								System.out
										.println("\n NULL pointer error!!! \n");
							}

							continue;
						}

						pos = 3;

						key = s.substring(k_head + 1, k_tail);

					}

					if (pos == 1) {

						pos = 2;
						k_head = i;

					}

				}

			}
		}

	}
	
	public int totalForm(){
		return form;
	}
	
	public int totalEmpty(){
		return empt;
	}

	public int totalButton() {
		return button;
	}

	public int totalLabel() {
		return label;
	}

	public int totalVertical() {
		return vertArrange;
	}

	public int totalHorizontal() {
		return horiArrange;
	}
	
	public int totalTableArr() {
		return tabArrange;
	}
	
	public int totalcheckbox() {
		return checkbox;
	}
	
	public int totaltextbox() {
		return textbox;
	}
	
	public int totalpasstextbox() {
		return passtextbox;
	}
	
	public int totallistpicker() {
		return listpicker;
	}
	
	public int totalimage() {
		return image;
	}
	
	public int totalimagepicker() {
		return imagepicker;
	}
	
	public int totalcanvas() {
		return canvas;
	}

	public int totalemailpicker() {
		return emailpicker;
	}
	
	public int totalphonenumberpicker() {
		return phonenumberpicker;
	}
	
	public int totalcontactpicker() {
		return contactpicker;
	}
	
	public int totalvideoplayer() {
		return videoplayer;
	}
	
	public int totalwebviewer() {
		return webviewer;
	}
	
	public void countTypeNum(String comp) {

		if (comp.equals("Form")) {
			form++;
			System.out.println("Total Form = " + form);
		}
		
		if (comp.equals("empty")) {
			empt++;
			System.out.println("Total Empty = " + empt);
		}
		
		if (comp.equals("Button")) {
			button++;
			System.out.println("Total Button = " + button);
		}

		if (comp.equals("Label")) {
			label++;
			System.out.println("Total label = " + label);
		}

		if (comp.equals("VerticalArrangement")) {
			vertArrange++;
			System.out.println("Total VerticalArrangement = " + vertArrange);
		}

		if (comp.equals("HorizontalArrangement")) {
			horiArrange++;
			System.out.println("Total HorizontalArrangement = " + horiArrange);
		}
		
		if (comp.equals("TableArrangement")) {
			tabArrange++;
		}

		if (comp.equals("CheckBox")) {
			checkbox++;
		}
		
		if (comp.equals("TextBox")) {
			textbox++;
		}
		
		if (comp.equals("PasswordTextBox")) {
			passtextbox++;
		}
		
		if (comp.equals("ListPicker")) {
			listpicker++;
		}
		
		if (comp.equals("Image")) {
			image++;
		}
		
		if (comp.equals("ImagePicker")) {
			imagepicker++;
		}
		
		if (comp.equals("Canvas")) {
			canvas++;
		}
		
		if (comp.equals("EmailPicker")) {
			emailpicker++;
		}
		
		if (comp.equals("PhoneNumberPicker")) {
			phonenumberpicker++;
		}
		
		if (comp.equals("ContactPicker")) {
			contactpicker++;
		}
		
		if (comp.equals("VideoPlayer")) {
			videoplayer++;
		}
		
		if (comp.equals("WebViewer")) {
			webviewer++;
		}
		
	}

}
