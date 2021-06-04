package action.sidebarAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import component.canvas.Canvas;
import component.sidebar.Button;
import component.sidebar.Sidebar;

public class ButtonAction extends MouseAdapter {
	private Sidebar sidebar;
	private Canvas canvas;

	public ButtonAction(Sidebar sidebar, Canvas canvas) {
		this.sidebar = sidebar;
		this.canvas = canvas;
	}

	private void clearCanvasSelect() {
		if (canvas.getSelected() != null) {
			canvas.getSelected().showPorts(false);
		}
		canvas.setSelected(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clearCanvasSelect();

		Button button = (Button) e.getComponent();
		button.setSelected();

		Button old = sidebar.getSelectButton();
		if (old != button) {
			if (old != null) {
				old.setSelected();
			}
			sidebar.setSelectButton(button);
		} else {
			sidebar.setSelectButton(null);
		}
	}
}