import javax.swing.JFrame;

import component.UMLpanel;

public class UML_Editor {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 871, 634);
		frame.setContentPane(new UMLpanel());
		frame.setVisible(true);
	}
}