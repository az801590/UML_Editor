package mode;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import component.canvas.BasicObject;
import component.canvas.Canvas;
import component.canvas.Group;

public class BasicObjMode extends Mode {
	private int index;
	private BasicObjectAction basicObjAct = new BasicObjectAction();
	private Point mouseFrom = new Point();
	
	public BasicObjMode(int index) {
		this.index = index;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		BasicObject obj = new BasicObject(e.getPoint());
		obj.setGraph(index);
		obj.addMouseListener(basicObjAct);
		obj.addMouseMotionListener(basicObjAct);
		Canvas.getInstance().add(obj);
		Canvas.getInstance().addBasicObjSet(obj);
		Canvas.getInstance().moveToFront(obj);
	}
	
	class BasicObjectAction extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(Canvas.getInstance().getMode().isSelectMode()) {
				BasicObject tmp = (BasicObject) e.getComponent();
				if(Canvas.getInstance().getSelected() != tmp.getGroupRoot()) {
					if(Canvas.getInstance().getSelected() != null) {
						Canvas.getInstance().getSelected().showPorts(false);
						Canvas.getInstance().setSelected(null);
					}
				}
				else {
					//move
					mouseFrom.setLocation(e.getPoint());
				}
			}
			else if(Canvas.getInstance().getMode().isLineMode()) {
				Canvas.getInstance().getMode().mousePressed(e);
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(Canvas.getInstance().getMode().isSelectMode()) {
				BasicObject selected = (BasicObject) e.getComponent();
				Group tmp = selected.getGroupRoot();
				
				if(Canvas.getInstance().getSelected() == null) {
					tmp.showPorts(true);
					Canvas.getInstance().setSelected(tmp);
				}
				else {
					//move without motion
				}
			}
			else if(Canvas.getInstance().getMode().isLineMode()) {
				Canvas.getInstance().getMode().mouseReleased(e);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if(Canvas.getInstance().getMode().isLineMode()) {
				Canvas.getInstance().getMode().mouseEntered(e);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(Canvas.getInstance().getMode().isLineMode()) {
				Canvas.getInstance().getMode().mouseExited(e);
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(Canvas.getInstance().getMode().isSelectMode()) {
				if(Canvas.getInstance().getSelected() != null) {
					Canvas.getInstance().getSelected().moveObject(new int[] { e.getX() - mouseFrom.x, e.getY() - mouseFrom.y });
				}
			}
		}
	}
}
