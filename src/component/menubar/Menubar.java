package component.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import action.menuItemAction.*;

import component.canvas.Canvas;
import component.sidebar.Sidebar;

public class Menubar extends JMenuBar {
	private JMenu edit = new JMenu("edit");
	private JMenuItem changeObjectName = new JMenuItem("Change Object Name");
	private JMenuItem group = new JMenuItem("Group");
	private JMenuItem ungroup = new JMenuItem("Ungroup");
	
	
	//Canvas canvas;

	public Menubar(Canvas canvas, Sidebar sidebar){
		//this.canvas = canvas;
		
		edit.add(changeObjectName);
		edit.add(group);
		edit.add(ungroup);
		
		add(edit);
	}
	
	public void init(Canvas canvas) {
		changeObjectName.addMouseListener(new ChangeObjectNameAction(canvas));
		group.addMouseListener(new GroupAction(canvas));
		ungroup.addMouseListener(new UngroupAction(canvas));
	}
}
