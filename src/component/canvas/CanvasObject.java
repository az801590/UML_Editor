package component.canvas;

import java.awt.Graphics;

import javax.swing.JComponent;

import component.graph.Graph;

public class CanvasObject extends JComponent {
	protected Graph graph;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (graph != null) {
			graph.paintGraph(g);
		}
	}
}
