package component.graph;

import java.awt.Color;
import java.awt.Graphics;

public class ClassGraph extends Graph {
	private int x;
	private int y;
	private int width;
	private int height;
	private String name;

	public ClassGraph(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void paintGraph(Graphics g) {
		paintRectangle(g);
		paintLine(g);

		if (name != null) {
			paintName(g);
		}
	}

	private void paintRectangle(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

	private void paintLine(Graphics g) {
		g.drawLine(x, y + height / 3, x + width, y + height / 3);
		g.drawLine(x, y + height / 3 * 2, x + width, y + height / 3 * 2);
	}

	private void paintName(Graphics g) {
		g.drawString(name, x + width / 4, y + height / 5);
	}
}
