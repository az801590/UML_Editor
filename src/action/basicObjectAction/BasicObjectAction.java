package action.basicObjectAction;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import component.canvas.BasicObject;
import component.canvas.Canvas;
import component.canvas.ConnectionObject;
import component.canvas.Group;
import component.sidebar.Sidebar;

public class BasicObjectAction extends MouseAdapter {
	private Sidebar sidebar;
	private Canvas canvas;

	private BasicObject from, to;
	private Point mouseFrom = new Point();

	public BasicObjectAction(Canvas canvas, Sidebar sidebar) {
		this.canvas = canvas;
		this.sidebar = sidebar;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int index = sidebar.getSelectIndex();

		if (index == 0) {
			BasicObject tmp = (BasicObject) e.getComponent();
			if (canvas.getSelected() != tmp.getGroupRoot()) {
				if (canvas.getSelected() != null) {
					canvas.getSelected().showPorts(false);
					canvas.setSelected(null);
				}
			} else {
				// move
				mouseFrom.setLocation(e.getPoint());
			}
		} else if (index == 1 || index == 2 || index == 3) {
			from = (BasicObject) e.getComponent();
			mouseFrom.setLocation(e.getPoint());
			from.showPorts(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int index = sidebar.getSelectIndex();
		if (index == 0) {
			BasicObject selected = (BasicObject) e.getComponent();
			Group tmp = selected.getGroupRoot();

			if (canvas.getSelected() == null) {
				tmp.showPorts(true);
				canvas.setSelected(tmp);
			} else {
				// move without motion
//				tmp.moveObject(new int[] { e.getX() - mouseFromPoint.x, e.getY() - mouseFromPoint.y });
			}
		} else if (index == 1 || index == 2 || index == 3) {
			if (from != null) {
				if (to != null) {
					if (from != to) {
						ConnectionObject connect = new ConnectionObject(from, to);
						connect.init(mouseFrom, e.getPoint(), index);
						canvas.add(connect);
						canvas.moveToFront(connect);

						from.showPorts(false);
						to.showPorts(false);
						from = to = null;
					} else {
						System.out.println("error: from == to");
					}
				} else {
					from.showPorts(false);
					from = null;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int index = sidebar.getSelectIndex();
		if (index == 0) {

		} else if ((index == 1 || index == 2 || index == 3) && from != null) {
			to = (BasicObject) e.getComponent();
			to.showPorts(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		int index = sidebar.getSelectIndex();
		if (index == 0) {

		} else if ((index == 1 || index == 2 || index == 3) && from != null && to != null) {
			to.showPorts(false);
			to = null;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (sidebar.getSelectIndex() == 0) {
			if (canvas.getSelected() != null) {
				canvas.getSelected().moveObject(new int[] { e.getX() - mouseFrom.x, e.getY() - mouseFrom.y });
			}
		}
	}
}
