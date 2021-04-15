package component.graph;

import java.awt.Color;
import java.awt.Graphics;

public class PortGraph extends Graph {
	private int x;
	private int y;
	private int width;
	private int height;

	public PortGraph(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void paintGraph(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
}
