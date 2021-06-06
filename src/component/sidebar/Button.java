package component.sidebar;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import component.graph.Graph;
import mode.Mode;

public class Button extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private Graph graph;
	private boolean selected = false;
	private Mode mode;


	Button(Graph graph, Mode mode) {
		super();
		this.graph = graph;
		this.mode = mode;
	}

	public void setSelected() {
		selected = !selected;
		repaint();
	}
	
	public Mode getMode() {
		return mode;
	}

	public boolean getStatus() {
		return selected;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (selected) {
			g.setColor(Color.BLACK);
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.BLACK);
		}

		graph.paintGraph(g);
	}

	@Override
	public void paintBorder(Graphics g) {
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
	}

//	@Override
//	public void getPreferredSize() {
//
//	}
//
//	public void getMaximumSize() {
//
//	}
//
//	@Override
//	public void getMinimumSize() {
//
//	}
}
