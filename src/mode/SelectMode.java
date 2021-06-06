package mode;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import component.canvas.BasicObject;
import component.canvas.Canvas;
import component.canvas.GroupObject;

public class SelectMode extends Mode {
	private Point selectFromPoint;

	@Override
	public void mousePressed(MouseEvent e) {
		Canvas.getInstance().clearSelected();
		selectFromPoint = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int positionRange[][] = new int[2][2];

		positionRange[0] = (selectFromPoint.x > e.getX() ? new int[] { e.getX(), selectFromPoint.x }: new int[] { selectFromPoint.x, e.getX() });
		positionRange[1] = (selectFromPoint.y > e.getY() ? new int[] { e.getY(), selectFromPoint.y }: new int[] { selectFromPoint.y, e.getY() });
	
		GroupObject tmpGroup = new GroupObject();
		int num = 0;
		for(BasicObject obj: Canvas.getInstance().getBasicObjSet()) {
			if(obj.getX() >= positionRange[0][0] && obj.getX() <= positionRange[0][1] && obj.getY() >= positionRange[1][0] && obj.getY() <= positionRange[1][1]) {
				tmpGroup.addGroupList((obj).getGroupRoot());
				num++;
			}
		}
		if(num > 0) {
			tmpGroup.showPorts(true);
			Canvas.getInstance().setSelected(tmpGroup);
		}
	}
	
	@Override
	public boolean isSelectMode() {
		return true;
	}
}
