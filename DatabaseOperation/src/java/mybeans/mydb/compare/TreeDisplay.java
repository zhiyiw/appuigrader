package mybeans.mydb.compare;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeDisplay {

	int depth;
	String tree;

	String space = "";
	String gap = "     ";

	public TreeDisplay() {

	}

	public String TreeDisp(Component treeData) {
		int i;
		int j;

		String temp;

		if (treeData.list.size() > 0) {

			for (i = 0; i < treeData.list.size(); i++) {

				for (j = 0; j < treeData.list.get(i).depth; j++) {
					space = space + gap;
				}

				temp = "\n" + space + "¡ª¡ª NAME: "
						+ treeData.list.get(i).getName() + ", TYPE: "
						+ treeData.list.get(i).getType();
				space = "";
				tree = tree + temp;
				TreeDisp(treeData.list.get(i));
			}

		}

		return tree;
	}

	public DefaultMutableTreeNode treeNodes(DefaultMutableTreeNode top,
			Component treeData) {
		ArrayList<DefaultMutableTreeNode> node = new ArrayList<DefaultMutableTreeNode>();

		int i;

		if (treeData.list.size() > 0) {

			for (i = 0; i < treeData.list.size(); i++) {
				DefaultMutableTreeNode temp = new DefaultMutableTreeNode(
						treeData.list.get(i).getType());
				top.add(temp);
				node.add(temp);

				treeNodes(node.get(i), treeData.list.get(i));
			}

		}

		return top;
	}

	public String getTree() {
		return tree;
	}

}
