package action.canvasAction;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import action.basicObjectAction.BasicObjectAction;
import component.canvas.BasicObject;
import component.canvas.Canvas;
import component.canvas.Group;
import component.canvas.GroupObject;
import component.sidebar.Sidebar;

public class CanvasAction extends MouseAdapter {
	private Canvas canvas;
	private Sidebar sidebar;

	private BasicObjectAction basicObjAction;
	private int basicObjLength = 60;
	private int selectFromPosition[] = new int[2];

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
			selectFromPosition[0] = e.getX();
			selectFromPosition[1] = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int index = sidebar.getSelectIndex();
		if (index == 0) {
			// positionRange[0] for x, ascending order
			// positionRange[1] for y, ascending order
			int positionRange[][] = new int[2][2];

			positionRange[0] = (selectFromPosition[0] > e.getX() ? new int[] { e.getX(), selectFromPosition[0] }
					: new int[] { selectFromPosition[0], e.getX() });
			positionRange[1] = (selectFromPosition[1] > e.getY() ? new int[] { e.getY(), selectFromPosition[1] }
					: new int[] { selectFromPosition[1], e.getY() });

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
			BasicObject obj = new BasicObject();
			obj.setBounds(e.getX(), e.getY(), basicObjLength, basicObjLength);
			obj.setGraph(index);
			obj.addMouseListener(basicObjAction);
			canvas.add(obj);
			canvas.moveToFront(obj);
		}
	}
}
