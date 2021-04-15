package action.basicObjectAction;

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
	int mouseFromPosition[] = new int[2];

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
				mouseFromPosition[0] = e.getX();
				mouseFromPosition[1] = e.getY();
			}
		} else if (index == 1 || index == 2 || index == 3) {
			from = (BasicObject) e.getComponent();
			mouseFromPosition[0] = e.getX();
			mouseFromPosition[1] = e.getY();
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
				// move
				tmp.moveObject(new int[] { e.getX() - mouseFromPosition[0], e.getY() - mouseFromPosition[1] });
			}
		} else if (index == 1 || index == 2 || index == 3) {
			if (from != null) {
				if (to != null) {
					if (from != to) {
						ConnectionObject connect = new ConnectionObject(from, to);
						connect.init(mouseFromPosition, new int[] { e.getX(), e.getY() }, index);
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

		if ((index == 1 || index == 2 || index == 3) && from != null) {
			to = (BasicObject) e.getComponent();
			to.showPorts(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		int index = sidebar.getSelectIndex();

		if ((index == 1 || index == 2 || index == 3) && from != null && to != null) {
			to.showPorts(false);
			to = null;
		}
	}
}
