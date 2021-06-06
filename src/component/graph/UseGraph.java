package component.graph;

import java.awt.Color;
import java.awt.Graphics;

public class UseGraph extends Graph {
	private int x;
	private int y;
	private int width;
	private int height;
	private String name;

	public UseGraph(int x, int y, int w, int h) {
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
		paintOval(g);

		if (name != null) {
			paintName(g);
		}
	}

	private void paintOval(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);

		g.setColor(Color.BLACK);
		g.drawOval(x, y, width, height);
	}

	private void paintName(Graphics g) {
		g.drawString(name, x + width / 8, y + height / 2 + 5);
	}

}
