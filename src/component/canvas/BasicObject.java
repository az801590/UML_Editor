package component.canvas;

import component.graph.ClassGraph;
import component.graph.UseGraph;

public class BasicObject extends CanvasObject implements Group {
	private PortObject ports[] = new PortObject[4];
	private Group group = null;

	public void setGraph(int index) {
		setPorts(index);

		if (index == 4) {
			graph = new ClassGraph(ports[0].getPortLength(), ports[1].getPortLength(),
					getWidth() - ports[0].getPortLength() - ports[2].getPortLength(),
					getHeight() - ports[1].getPortLength() - ports[3].getPortLength());
		} else if (index == 5) {
			graph = new UseGraph(ports[0].getPortLength(), ports[1].getPortLength(),
					getWidth() - ports[0].getPortLength() - ports[2].getPortLength(),
					getHeight() * 2 / 3 - ports[1].getPortLength() - ports[3].getPortLength());
		}
	}

	private void setPorts(int index) {
		for (int i = 0; i < ports.length; i++) {
			ports[i] = new PortObject();
			ports[i].setVisible(false);
			add(ports[i]);
		}

		// top
		ports[1].setBounds((getWidth() - ports[1].getPortLength()) / 2, 0, ports[1].getPortLength(),
				ports[1].getPortLength());
		if (index == 4) {
			// left
			ports[0].setBounds(0, (getHeight() - ports[0].getPortLength()) / 2, ports[0].getPortLength(),
					ports[0].getPortLength());
			// right
			ports[2].setBounds(getWidth() - ports[2].getPortLength(), (getHeight() - ports[2].getPortLength()) / 2,
					ports[2].getPortLength(), ports[2].getPortLength());
			// down
			ports[3].setBounds((getWidth() - ports[3].getPortLength()) / 2, getHeight() - ports[3].getPortLength(),
					ports[3].getPortLength(), ports[3].getPortLength());

		} else if (index == 5) {
			// left
			ports[0].setBounds(0, getHeight() / 3 - ports[1].getPortLength() / 2, ports[0].getPortLength(),
					ports[0].getPortLength());
			// right
			ports[2].setBounds(getWidth() - ports[2].getPortLength(), getHeight() / 3 - ports[1].getPortLength() / 2,
					ports[2].getPortLength(), ports[2].getPortLength());
			// down
			ports[3].setBounds((getWidth() - ports[1].getPortLength()) / 2,
					getHeight() * 2 / 3 - ports[3].getPortLength(), ports[3].getPortLength(), ports[3].getPortLength());
		}
	}
	
	public void setGraphName(String name) {
		graph.setName(name);
		this.repaint();
	}

	protected PortObject getNearestPort(int position[]) {
		int minDistance = Integer.MAX_VALUE, portIndex = 0;

		for (int i = 0; i < ports.length; i++) {
			int distance = (int) (Math.pow(position[0] - ports[i].getX(), 2)
					+ Math.pow(position[1] - ports[i].getY(), 2));
			if (distance < minDistance) {
				portIndex = i;
				minDistance = distance;
			}
		}
		return ports[portIndex];
	}

	@Override
	public Group getGroupRoot() {
		if (group != null) {
			return group.getGroupRoot();
		} else {
			return this;
		}
	}

	@Override
	public void showPorts(boolean stat) {
		for (int i = 0; i < ports.length; i++) {
			ports[i].setVisible(stat);
		}
	}

	@Override
	public void moveObject(int position[]) {
		setLocation(this.getX() + position[0], this.getY() + position[1]);

		// move ports
		for (int i = 0; i < ports.length; i++) {
			ports[i].moveConnection();
		}
	}

	@Override
	public void setGroupParent(GroupObject g) {
		this.group = g;
	}
}
