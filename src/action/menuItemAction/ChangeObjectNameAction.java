package action.menuItemAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import component.canvas.BasicObject;
import component.canvas.Canvas;

public class ChangeObjectNameAction extends MouseAdapter {
	private Canvas canvas;

	public ChangeObjectNameAction(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (canvas.getSelected() != null) {
			if (canvas.getSelected().getClass().equals(BasicObject.class)) {
				String name = JOptionPane.showInputDialog("Please enter a new name:");
				((BasicObject)canvas.getSelected()).setGraphName(name);
			}
			else {
				//group object or else
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please select a basic object first.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
