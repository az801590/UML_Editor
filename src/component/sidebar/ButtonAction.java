package component.sidebar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import component.canvas.Canvas;

public class ButtonAction extends MouseAdapter {
	Button current;
	
	@Override
	public void mousePressed(MouseEvent e) {
		Canvas.getInstance().clearSelected();
		
		if(current != null) {
			current.setSelected();
		}

		Button button = (Button) e.getComponent();
		button.setSelected();
		current = button;
		Canvas.getInstance().setMode(button.getMode());
	}
}
