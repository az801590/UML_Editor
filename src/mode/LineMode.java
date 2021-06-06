package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import component.canvas.BasicObject;
import component.canvas.Canvas;
import component.canvas.ConnectionObject;

public class LineMode extends Mode {
	private int index;
	private BasicObject from, to;
	private Point mouseFrom = new Point();
	
	public LineMode(int index) {
		this.index = index;
	}
	
	public void mousePressed(MouseEvent e){
		from = (BasicObject)e.getComponent();
		mouseFrom.setLocation(e.getPoint());
		from.showPorts(true);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(from != null) {
			if(to != null) {
				if(from != to) {
					ConnectionObject connect = new ConnectionObject(from, to);
					connect.init(mouseFrom, e.getPoint(), index);
					Canvas.getInstance().add(connect);
					Canvas.getInstance().moveToFront(connect);
						
					from.showPorts(false);
					to.showPorts(false);
					from = to = null;
				}
				else {
					System.out.println("error: from == to");
				}
			}
			else {
				from.showPorts(false);
				from = null;
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		if(from != null) {
			to = (BasicObject) e.getComponent();
			to.showPorts(true);
		}
	}
	
	public void mouseExited(MouseEvent e) {
		if(from != null && to != null) {
			to.showPorts(false);
			to = null;
		}
	}
	
	@Override
	public boolean isLineMode() {
		return true;
	}
	
}
