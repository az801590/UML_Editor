package component.canvas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import javax.swing.JLayeredPane;

import mode.Mode;

public class Canvas extends JLayeredPane {
	private static final long serialVersionUID = 1L;
	
	private Group selected;
	private Mode mode = new Mode();
	private HashSet<BasicObject> basicObjSet = new HashSet<>();

	private Canvas() {
		setOpaque(true);
		setBackground(Color.WHITE);
		setLayout(null);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!mode.isLineMode()) {
					mode.mousePressed(e);
				}	
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!mode.isLineMode()) {
					mode.mouseReleased(e);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!mode.isLineMode()) {
					mode.mouseEntered(e);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(!mode.isLineMode()) {
					mode.mouseExited(e);
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!mode.isLineMode()) {
					mode.mouseDragged(e);
				}
			}
		});
	}
	
	private static class Singleton {
		private static final Canvas INSTANCE = new Canvas();
	}
	
	public static Canvas getInstance() {
		return Singleton.INSTANCE;
	}


	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public Mode getMode() {
		return mode;
	}
	
	public HashSet<BasicObject> getBasicObjSet() {
		return basicObjSet;
	}
	
	public void addBasicObjSet(BasicObject obj) {
		basicObjSet.add(obj);
	}
	
	public void clearSelected() {
		if(selected != null) {
			selected.showPorts(false);
		}
		selected = null;
	}
	
	public Group getSelected() {
		return selected;
	}
	
	public void setSelected (Group obj) {
		selected = obj;
	}
}
