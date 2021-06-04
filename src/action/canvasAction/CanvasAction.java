package action.canvasAction;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import action.basicObjectAction.BasicObjectAction;
import component.canvas.BasicObject;
import component.canvas.Canvas;
import component.canvas.GroupObject;
import component.sidebar.Sidebar;

public class CanvasAction extends MouseAdapter {
	private Canvas canvas;
	private Sidebar sidebar;

	private BasicObjectAction basicObjAction;
	private Point selectFromPoint;

	public void init(Canvas canvas, Sidebar sidebar) {
		this.canvas = canvas;
		this.sidebar = sidebar;

		basicObjAction = new BasicObjectAction(canvas, sidebar);
	}

	private void clearCanvasSelect() {
		if (canvas.getSelected() != null) {
			canvas.getSelected().showPorts(false);
		}
		canvas.setSelected(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int index = sidebar.getSelectIndex();

		if (index == 0) {
			clearCanvasSelect();
			selectFromPoint = e.getPoint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int index = sidebar.getSelectIndex();
		if (index == 0) {
			// positionRange[0] for x, ascending order
			// positionRange[1] for y, ascending order
			int positionRange[][] = new int[2][2];

			positionRange[0] = (selectFromPoint.x > e.getX() ? new int[] { e.getX(), selectFromPoint.x }
					: new int[] { selectFromPoint.x, e.getX() });
			positionRange[1] = (selectFromPoint.y > e.getY() ? new int[] { e.getY(), selectFromPoint.y }
					: new int[] { selectFromPoint.y, e.getY() });

			Component c[] = canvas.getComponents();
			GroupObject tmpGroup = new GroupObject();
			int num = 0;
			for (int i = 0; i < c.length; i++) {
				if (c[i].getClass().equals(BasicObject.class)) {
					if (c[i].getX() >= positionRange[0][0] && c[i].getX() <= positionRange[0][1]
							&& c[i].getY() >= positionRange[1][0] && c[i].getY() <= positionRange[1][1]) {
						tmpGroup.addGroupList(((BasicObject) c[i]).getGroupRoot());
						num++;
					}
				}
			}
			if (num > 0) {
				tmpGroup.showPorts(true);
				canvas.setSelected(tmpGroup);
			}
		} else if (index == 4 || index == 5) {
			BasicObject obj = new BasicObject(e.getPoint());
			obj.setGraph(index);
			obj.addMouseListener(basicObjAction);
			obj.addMouseMotionListener(basicObjAction);
			canvas.add(obj);
			canvas.moveToFront(obj);
		}
	}
}
