package mybeans.mydb.compare;

import java.awt.*;

import javax.swing.JComponent;

public class ImagePanel extends JComponent {
	private Image image;

	public ImagePanel(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}