package action.menuItemAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import component.canvas.BasicObject;
import component.canvas.Canvas;
import component.canvas.Group;
import component.canvas.GroupObject;

public class UngroupAction extends MouseAdapter {
	private Canvas canvas;

	public UngroupAction(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Group selected = canvas.getSelected();
		if (selected != null) {
			if (selected.getClass().equals(BasicObject.class)) {

			} else if (selected.getClass().equals(GroupObject.class)) {
				((GroupObject) selected).unsetChildParentLink();
			}
		}
	}
}
