package component.canvas;

import java.util.ArrayList;

import component.graph.PortGraph;

public class PortObject extends CanvasObject {
	private ArrayList<ConnectionObject> connect = new ArrayList<ConnectionObject>();
	private int portLength = 5;

	PortObject() {
		graph = new PortGraph(0, 0, portLength, portLength);
	}

	protected int getPortLength() {
		return portLength;
	}

	protected void addConnect(ConnectionObject obj) {
		connect.add(obj);
	}

	protected void moveConnection() {
		for (int i = 0; i < connect.size(); i++) {
			connect.get(i).moveObject();
		}
	}
}
