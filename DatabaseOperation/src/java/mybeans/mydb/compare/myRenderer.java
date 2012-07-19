package mybeans.mydb.compare;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

class myRenderer extends DefaultTreeCellRenderer {
	Icon icon;
	Icon buttonIcon = new ImageIcon("Button.png");
	Icon labelIcon = new ImageIcon("Label.png");
	Icon horiIcon = new ImageIcon("HorizontalArrangement.png");
	Icon VertIcon = new ImageIcon("VerticalArrangement.png");
	Icon TableIcon = new ImageIcon("TableArrangement.png");

	public myRenderer() {
		//this.icon = icon;
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);

		if (leaf && isButton(value)) {
			setIcon(buttonIcon);
		}

		if (leaf && isLabel(value)) {
			setIcon(labelIcon);
		}

		if (!leaf && isHoriz(value)) {
			setIcon(horiIcon);
		}

		if (!leaf && isVert(value)) {
			setIcon(VertIcon);
		}

		if (!leaf && isTabl(value)) {
			setIcon(TableIcon);
		}

		return this;
	}

	protected boolean isButton(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		String nodeType = node.getUserObject().toString();
		
		if (nodeType.indexOf("Button") >= 0) {
			return true;
		}
		return false;
	}

	protected boolean isLabel(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		String nodeType = node.getUserObject().toString();
		
		if (nodeType.indexOf("Label") >= 0) {
			return true;
		}
		return false;
	}

	protected boolean isHoriz(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		String nodeType = node.getUserObject().toString();
		
		if (nodeType.indexOf("Horizontal") >= 0) {
			return true;
		}
		return false;
	}

	protected boolean isVert(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		String nodeType = node.getUserObject().toString();
		
		if (nodeType.indexOf("Vertical") >= 0) {
			return true;
		}
		return false;
	}

	protected boolean isTabl(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		String nodeType = node.getUserObject().toString();
		
		if (nodeType.indexOf("Table") >= 0) {
			return true;
		}
		return false;
	}

}
