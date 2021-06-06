package mode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import component.canvas.Canvas;
import component.canvas.Group;
import component.canvas.GroupObject;

public class GroupAction extends MouseAdapter {

	@Override
	public void mouseReleased(MouseEvent e) {
		Group selected = Canvas.getInstance().getSelected();
		if (selected != null) {
			if (selected.isGroupObject()) {
				((GroupObject)selected).setChildParentLink();
			}
			else{
				// Only select a basic component to form a group
				
			}
		}
		else {
			//select at least two basic component first
		}
	}
}
