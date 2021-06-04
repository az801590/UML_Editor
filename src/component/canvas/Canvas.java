package component.canvas;

import java.awt.Color;

import javax.swing.JLayeredPane;

import action.canvasAction.CanvasAction;
import component.sidebar.Sidebar;

public class Canvas extends JLayeredPane {
	private static final long serialVersionUID = 1L;
	
	private Group selected;

	public Canvas() {
		setOpaque(true);
		setBackground(Color.WHITE);
		setLayout(null);
	}

	public void init(Sidebar sidebar) {
		CanvasAction action = new CanvasAction();
		action.init(this, sidebar);
		addMouseListener(action);
	}
	
	public Group getSelected() {
		return selected;
	}
	
	public void setSelected (Group obj) {
		selected = obj;
	}
}
