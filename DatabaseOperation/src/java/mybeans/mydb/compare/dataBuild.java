package mybeans.mydb.compare;

import java.util.*;

public class dataBuild {

	ArrayList<Component> compArr;

	int button;
	int label;
	int vertArrange;
	int horiArrange;

	public dataBuild(String s) {

		button = 0;
		label = 0;
		vertArrange = 0;
		horiArrange = 0;

		int pos = 1;
		int k_head = 0;
		int k_tail = 0;
		int v_head = 0;
		int v_tail = 0;
		int depth = 0;
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

				c = new Component(depth); // assign the depth to the Component

				depth++;

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

				// count total number of each type of component
				if (c.map.get("$Type") != null) {
					this.countTypeNum(c.map.get("$Type"));
				}
				
				c.changeName(c.map.get("$Name"));

				if (c.map.get("$Type") == null) {
					c.changeType("empty");
				} else {
					c.changeType(c.map.get("$Type"));
				}

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

	public void countTypeNum(String comp) {

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

	}

}
