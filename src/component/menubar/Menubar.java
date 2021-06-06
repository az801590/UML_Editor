package component.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mode.ChangeObjectNameAction;
import mode.GroupAction;
import mode.UngroupAction;

public class Menubar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private JMenu edit = new JMenu("edit");
	private JMenuItem changeObjectName = new JMenuItem("Change Object Name");
	private JMenuItem group = new JMenuItem("Group");
	private JMenuItem ungroup = new JMenuItem("Ungroup");
	
	
	//Canvas canvas;

	public Menubar(){
		changeObjectName.addMouseListener(new ChangeObjectNameAction());
		group.addMouseListener(new GroupAction());
		ungroup.addMouseListener(new UngroupAction());
		
		edit.add(changeObjectName);
		edit.add(group);
		edit.add(ungroup);
		
		add(edit);
	}
}
