package component.graph;

import java.awt.Graphics;

public class CompositionGraph extends Graph {
	private int start[];
	private int end[];
	private int lineStop[] = { -1, -1 };
	private double lineLength = 0;
	private int rectLen = 8;

	public CompositionGraph(int start[], int end[]) {
		this.start = start;
		this.end = end;

		setLineStopPoint();
	}

	private void setLineLength() {
		lineLength = Math.sqrt(Math.pow((double) (start[0] - end[0]), 2) + Math.pow(start[1] - end[1], 2));
	}

	private void setLineStopPoint() {
		setLineLength();
		lineStop[0] = (int) (end[0] + rectLen * (start[0] - end[0]) / lineLength * Math.sqrt(2));
		lineStop[1] = (int) (end[1] + rectLen * (start[1] - end[1]) / lineLength * Math.sqrt(2));
	}

	@Override
	public void paintGraph(Graphics g) {
		paintLine(g);
		paintRectangle(g);
	}

	private void paintLine(Graphics g) {
		g.drawLine(start[0], start[1], lineStop[0], lineStop[1]);
	}

	private void paintRectangle(Graphics g) {
		int rect0[] = new int[2];
		int rect1[] = new int[2];

		rect0[0] = (int) ((end[0] + lineStop[0]) / 2 + (end[1] - start[1]) / lineLength * rectLen / Math.sqrt(2));
		rect0[1] = (int) ((end[1] + lineStop[1]) / 2 + (start[0] - end[0]) / lineLength * rectLen / Math.sqrt(2));

		rect1[0] = (int) ((end[0] + lineStop[0]) / 2 - (end[1] - start[1]) / lineLength * rectLen / Math.sqrt(2));
		rect1[1] = (int) ((end[1] + lineStop[1]) / 2 - (start[0] - end[0]) / lineLength * rectLen / Math.sqrt(2));

		g.drawPolygon(new int[] { end[0], rect0[0], lineStop[0], rect1[0] },
				new int[] { end[1], rect0[1], lineStop[1], rect1[1] }, 4);

	}
}
