package mode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import component.canvas.Canvas;
import component.canvas.Group;
import component.canvas.GroupObject;

public class UngroupAction extends MouseAdapter {

	@Override
	public void mouseReleased(MouseEvent e) {
		Group selected = Canvas.getInstance().getSelected();
		if (selected != null) {
			if (selected.isGroupObject()) {
				((GroupObject) selected).unsetChildParentLink();
			}
			else {
				
			}
		}
	}
}
