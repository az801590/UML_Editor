package component.canvas;

import component.graph.AssociationGraph;
import component.graph.CompositionGraph;
import component.graph.GenerationGraph;

public class ConnectionObject extends CanvasObject {
	private BasicObject from;
	private BasicObject to;

	private PortObject fromPort;
	private PortObject toPort;
	
	private int graphIndex = -1;

	public ConnectionObject(BasicObject from, BasicObject to) {
		this.from = from;
		this.to = to;
	}

	public void init(int startPosition[], int endPosition[], int index) {
		setObjBounds();

		endPosition = setAxis2To(endPosition);
		fromPort = from.getNearestPort(startPosition);
		toPort = to.getNearestPort(endPosition);

		fromPort.addConnect(this);
		toPort.addConnect(this);

		setGraph(index);
	}

	private void setObjBounds() {
		int x = from.getX() > to.getX() ? to.getX() : from.getX();
		int y = from.getY() > to.getY() ? to.getY() : from.getY();
		int width = Math.abs(from.getX() - to.getX())
				+ ((from.getX() + from.getWidth()) > (to.getX() + to.getWidth()) ? (from.getWidth()) : (to.getWidth()));
		int height = Math.abs(from.getY() - to.getY())
				+ ((from.getY() + from.getHeight() > to.getY() + to.getHeight()) ? (from.getHeight())
						: (to.getHeight()));
		setBounds(x, y, width, height);
	}

	private int[] setAxis2To(int endPosition[]) {
		int x = Math.abs(Math.abs(endPosition[0]) - Math.abs(to.getX() - from.getX()));
		int y = Math.abs(Math.abs(endPosition[1]) - Math.abs(to.getY() - from.getY()));

		return (new int[] { x, y });
	}

	private void setGraph(int index) {
		this.graphIndex = index;
		int lineStart[] = new int[2];
		int lineEnd[] = new int[2];

		lineStart[0] = fromPort.getX() + from.getX() - this.getX() + fromPort.getPortLength() / 2 + 1;
		lineStart[1] = fromPort.getY() + from.getY() - this.getY() + fromPort.getPortLength() / 2 + 1;

		lineEnd[0] = toPort.getX() + to.getX() - this.getX() + toPort.getPortLength() / 2 + 1;
		lineEnd[1] = toPort.getY() + to.getY() - this.getY() + toPort.getPortLength() / 2 + 1;

		if (index == 1) {
			graph = new AssociationGraph(lineStart, lineEnd);
		} else if (index == 2) {
			graph = new GenerationGraph(lineStart, lineEnd);
		} else if (index == 3) {
			graph = new CompositionGraph(lineStart, lineEnd);
		}
	}
	
	protected void moveObject() {
		setObjBounds();
		setGraph(this.graphIndex);
	}
}
