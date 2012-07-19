package mybeans.mydb.compare;

public class appuigrader {

	public static void main(String[] args) throws Exception {

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIFrame UIelement = new UIFrame();
				// comment
				UIelement.createAndShowGUI();

			}
		});

	}//test on mbp.
//test ryan
}
