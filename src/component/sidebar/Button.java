package component.sidebar;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

import action.sidebarAction.ButtonAction;
import component.graph.Graph;

public class Button extends JComponent {
	private Graph graph;
	private boolean selected = false;


	Button(Graph graph) {
		super();
		this.graph = graph;
	}

	public void setSelected() {
		selected = !selected;
		repaint();
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
