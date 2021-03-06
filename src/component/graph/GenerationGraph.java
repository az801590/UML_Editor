package component.graph;

import java.awt.Graphics;

public class GenerationGraph extends Graph {
	private int start[];
	private int end[];
	private int lineStop[] = {-1, -1};
	private double lineLength = 0;
	private int triLen = 10;
	
	public GenerationGraph(int start[], int end[]){
		this.start = start;
		this.end = end;
		
		setLineStopPoint();
	}
	
	private void getLineLength() {
		lineLength = Math.sqrt(Math.pow((double) (start[0] - end[0]), 2) + Math.pow(start[1] - end[1], 2));
	}
	
	private void setLineStopPoint() {
		getLineLength();
		
		lineStop[0] = (int) (end[0] + (triLen / 2) * (start[0] - end[0]) / lineLength * Math.sqrt(3));
		lineStop[1] = (int) (end[1] + (triLen / 2) * (start[1] - end[1]) / lineLength * Math.sqrt(3));
	}
	
	@Override
	public void paintGraph(Graphics g) {
		paintLine(g);
		paintTriangle(g);
	}
	
	private void paintLine(Graphics g) {
		g.drawLine(start[0], start[1], lineStop[0], lineStop[1]);
	}
	
	private void paintTriangle(Graphics g) {
		Triangle tri = new Triangle(end, lineStop);
		int tri0[] = tri.getPoint1();
		int tri1[] = tri.getPoint2();
		g.drawPolygon(new int[] { end[0], tri0[0], tri1[0] }, new int[] { end[1], tri0[1], tri1[1] }, 3);
	}

}
