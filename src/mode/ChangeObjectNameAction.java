package mode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import component.canvas.BasicObject;
import component.canvas.Canvas;

public class ChangeObjectNameAction extends MouseAdapter {

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Canvas.getInstance().getSelected() != null) {
			if (!Canvas.getInstance().getSelected().isGroupObject()) {
				String name = JOptionPane.showInputDialog("Please enter a new name:");
				((BasicObject) Canvas.getInstance().getSelected()).setGraphName(name);
			} 
			else {
				// group object or else
			}
		} 
		else {
			JOptionPane.showMessageDialog(null, "Please select a basic object first.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
